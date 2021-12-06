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
public class WarningControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@WithUserDetails(value = "norton", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void userCondominiumWarningFormLoads() throws Exception {
		mockMvc.perform(get("/user/condominium/warning/new").param("id_condominium", "1").param("id_resident", "10"))
		.andExpect(status().isOk())
		.andExpect(view().name("user/condominium/warning/form"));
	}
	
	@Test
	@WithUserDetails(value = "norton", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void userCondominiumWarningFormShouldNotLoads() throws Exception {
		mockMvc.perform(get("/user/condominium/warning/new").param("id_condominium", "1").param("id_resident", "12"))
		.andExpect(status().isOk())
		.andExpect(view().name("errors/accessdenied"));
	}
	
	@Test
	@WithUserDetails(value = "norton", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void userCondominiumCommomAreaListLoads() throws Exception {
		mockMvc.perform(get("/user/condominium/resident/warnings").param("id_condominium", "1").param("id_resident", "10"))
		.andExpect(status().isOk())
		.andExpect(view().name("user/condominium/warning/warnings"));
	}
	
	
	@Test
	@WithUserDetails(value = "norton", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void userCondominiumCommomAreaListShouldNotLoad() throws Exception {
		mockMvc.perform(get("/user/condominium/resident/warnings").param("id_condominium", "1").param("id_resident", "12"))
		.andExpect(status().isOk())
		.andExpect(view().name("errors/accessdenied"));
	}
	
	
	@Test
	@WithUserDetails(value = "norton", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void userWarningFormPost() throws Exception {
		mockMvc.perform(post("/user/condominium/warning/new").param("idResident", "10").param("idCondominiumFee", "10").param("warningDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date())).param("description", "Novo teste multa").param("value", "0.0"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrlPattern("/user/condominium/resident/warnings?id_condominium={[0-9]*}&id_resident={[0-9]*}"));		
	}

	@Test
	@WithUserDetails(value = "norton", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void userCondominiumWarningUpdateFormLoads() throws Exception {
		mockMvc.perform(get("/user/condominium/warning/update").param("id", "7"))
		.andExpect(status().isOk())
		.andExpect(view().name("user/condominium/warning/form"));
	}

	@Test
	@WithUserDetails(value = "norton", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void userWarningFormUpdate() throws Exception {
		mockMvc.perform(post("/user/condominium/warning/update").param("id", "7").param("idResident", "10").param("idCondominiumFee", "10").param("warningDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date())).param("description", "Alterado teste multa").param("value", "0.0"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrlPattern("/user/condominium/resident/warnings?id_condominium={[0-9]*}&id_resident={[0-9]*}"));		
	}
//	
//	@Test
//	@WithUserDetails(value = "norton", setupBefore = TestExecutionEvent.TEST_EXECUTION)
//	public void userCommomAreaFormUpdatePost() throws Exception {
//		mockMvc.perform(post("/user/condominium/commomarea/update").param("id", "12").param("name", "Área comum de testes").param("idCondominium", "1").param("bookingFee", "100.0"))
//		.andExpect(status().is3xxRedirection())
//		.andExpect(redirectedUrlPattern("/user/condominium/commomareas?id_condominium={[0-9]*}"));		
//	}

	
	
	
}
