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

import org.hamcrest.Matchers;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = br.edu.utfpr.sgcc.Application.class)
@AutoConfigureMockMvc
public class CommomAreaControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@WithUserDetails(value = "norton", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void userCondominiumCommomAreaListLoads() throws Exception {
		mockMvc.perform(get("/user/condominium/commomareas").param("id_condominium", "1"))
		.andExpect(status().isOk())
		.andExpect(view().name("user/condominium/commomarea/commomareas"));
	}

	@Test
	@WithUserDetails(value = "norton", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void userCondominiumCommomAreaViewLoads() throws Exception {
		mockMvc.perform(get("/user/condominium/commomarea").param("id", "1"))
		.andExpect(status().isOk())
		.andExpect(view().name("user/condominium/commomarea/commomarea"));
	}

	@Test
	@WithUserDetails(value = "norton", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void userCondominiumCommomAreaFormLoads() throws Exception {
		mockMvc.perform(get("/user/condominium/commomarea/new").param("id_condominium", "1"))
		.andExpect(status().isOk())
		.andExpect(view().name("user/condominium/commomarea/form"));
	}

	@Test
	@WithUserDetails(value = "norton", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void userCondominiumCommomAreaUpdateLoads() throws Exception {
		mockMvc.perform(get("/user/condominium/commomarea/update").param("id", "1"))
		.andExpect(status().isOk())
		.andExpect(view().name("user/condominium/commomarea/form"));
	}

	@Test
	@WithUserDetails(value = "norton", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void userCommomAreaFormPost() throws Exception {
		mockMvc.perform(post("/user/condominium/commomarea/add").param("id", "0").param("name", "Área comum de testes").param("idCondominium", "1").param("bookingFee", "0.0"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrlPattern("/user/condominium/commomareas?id_condominium={[0-9]*}"));		
	}
	
	@Test
	@WithUserDetails(value = "norton", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void userCommomAreaFormUpdatePost() throws Exception {
		mockMvc.perform(post("/user/condominium/commomarea/update").param("id", "12").param("name", "Área comum de testes").param("idCondominium", "1").param("bookingFee", "100.0"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrlPattern("/user/condominium/commomareas?id_condominium={[0-9]*}"));		
	}

	
	
	
}
