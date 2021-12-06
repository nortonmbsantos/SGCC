package br.edu.utfpr.sgcc.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.hamcrest.Matchers;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = br.edu.utfpr.sgcc.Application.class)
@AutoConfigureMockMvc
public class BookingControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@WithUserDetails(value = "norton", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void userBookingListByCommomAreaLoads() throws Exception {
		mockMvc.perform(get("/user/condominium/commomarea/bookings").param("id_commom_area", "1"))
		.andExpect(status().isOk())
		.andExpect(view().name("user/condominium/commomarea/bookings"));
	}

	@Test
	@WithUserDetails(value = "norton", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void userBookignAccpetFormPost() throws Exception {
		mockMvc.perform(post("/user/condominium/commomarea/booking/accept").param("id", "27"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrlPattern("/user/condominium/commomarea/bookings?id_commom_area={[0-9]*}"));
	}

	@Test
	@WithUserDetails(value = "norton", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void userBookignRefuseFormPost() throws Exception {
		mockMvc.perform(post("/user/condominium/commomarea/booking/refuse").param("id", "26"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrlPattern("/user/condominium/commomarea/bookings?id_commom_area={[0-9]*}"));
	}

	@Test
	@WithUserDetails(value = "norton@teste.com", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void residentBookingListLoads() throws Exception {
		mockMvc.perform(get("/resident/condominium/booking/list").param("idCondominium", "1"))
		.andExpect(status().isOk())
		.andExpect(view().name("resident/bookings"));
	}

	@Test
	@WithUserDetails(value = "norton@teste.com", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void residentBookingFormLoads() throws Exception {
		mockMvc.perform(get("/resident/condominium/booking/new").param("idCondominium", "1"))
		.andExpect(status().isOk())
		.andExpect(view().name("resident/newbooking"));
	}

	@Test
	@WithUserDetails(value = "norton@teste.com", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void residentBookignFormWitouhGuestsPost() throws Exception {
		mockMvc.perform(post("/resident/condominium/booking/add").param("bookingDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date())).param("idCommomArea", "1"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrlPattern("/resident/condominium/booking/list?idCondominium={[0-9]*}"));
	}

	@Test
	@WithUserDetails(value = "norton@teste.com", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void residentBookignFormWithGuestsPost() throws Exception {
		mockMvc.perform(post("/resident/condominium/booking/add").param("bookingDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date())).param("idCommomArea", "1")
				.param("guests[0].id", "15").param("guests[0].name", "Norton").param("guests[0].cpf", "09853127695").param("guests[0].phone", "41991213013")
				.param("guests[1].id", "23").param("guests[1].name", "Anderson").param("guests[1].cpf", "09853127696").param("guests[1].phone", "41991213013")
				)
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrlPattern("/resident/condominium/booking/list?idCondominium={[0-9]*}"));
	}



}
