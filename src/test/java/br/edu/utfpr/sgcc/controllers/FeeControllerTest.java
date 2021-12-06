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
public class FeeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@WithUserDetails(value = "norton", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void userCondominiumFeeListLoads() throws Exception {
		mockMvc.perform(get("/user/condominium/condominiumfees").param("id_condominium", "1"))
		.andExpect(status().isOk())
		.andExpect(view().name("user/condominium/condominiumfee/condominiumfees"));
	}

	@Test
	@WithUserDetails(value = "norton@teste.com", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void residentCondominiumFeeListLoads() throws Exception {
		mockMvc.perform(get("/resident/condominium/condominiumfees").param("id_condominium", "1"))
		.andExpect(status().isOk())
		.andExpect(view().name("resident/condominiumfees"));
	}

	@Test
	@WithUserDetails(value = "norton", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void userCondominiumFeeFormLoads() throws Exception {
		mockMvc.perform(get("/user/condominium/condominiumfee/form").param("id_condominium", "1").param("id", "0"))
		.andExpect(status().isOk())
		.andExpect(view().name("user/condominium/condominiumfee/form"));
	}

	@Test
	@WithUserDetails(value = "norton", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void userFeeFormInstalmentLoads() throws Exception {
		mockMvc.perform(get("/user/condominium/fee/forminstalment").param("id_condominium_fee", "1").param("id_fee", "0"))
		.andExpect(status().isOk())
		.andExpect(view().name("user/condominium/fee/forminstalment"));
	}

	@Test
	@WithUserDetails(value = "norton", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void userFeeFormInstalmentPost() throws Exception {
		mockMvc.perform(post("/user/condominium/fee/forminstalment").param("idCondominiumFee", "1").param("description", "Nova Parcela").param("id", "0").param("idFeeType", "1").param("dueDate", "2021-03-01").param("father", "10"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrlPattern("/user/condominium/condominiumfee?idCondominiumFee={[0-9]*}"));		
	}

	@Test
	@WithUserDetails(value = "norton", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void userFeeFormPost() throws Exception {
		mockMvc.perform(post("/user/condominium/fee/form").param("idCondominiumFee", "1").param("description", "Teste de taxa").param("id", "0").param("idFeeType", "3").param("dueDate", "2021-03-01").param("father", "0").param("value", "200.00").param("installments", "1"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrlPattern("/user/condominium/condominiumfee?idCondominiumFee={[0-9]*}"));		
	}

	@Test
	@WithUserDetails(value = "norton", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void userCondominiumFeeFormPost() throws Exception {
		mockMvc.perform(post("/user/condominium/condominiumfee/form").param("idCondominium", "1").param("id", "0").param("closingDate", "2023-01-01"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrlPattern("/user/condominium/condominiumfees?id_condominium={[0-9]*}"));
	}

	@Test
	@WithUserDetails(value = "norton", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void userCondominiumFeeFormClosingLoads() throws Exception {
		mockMvc.perform(get("/user/condominium/condominiumfee/closing").param("id_condominium_fee", "1"))
		.andExpect(status().isOk())
		.andExpect(view().name("user/condominium/condominiumfee/confirmfinishing"));
	}

	@Test
	@WithUserDetails(value = "norton", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void userFeeListLoads() throws Exception {
		mockMvc.perform(get("/user/condominium/fees").param("id_condominium_fee", "1").param("id_condominium", "1"))
		.andExpect(status().isOk())
		.andExpect(view().name("user/condominium/fee/fees"));
	}

	@Test
	@WithUserDetails(value = "norton", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void userFeeViewLoads() throws Exception {
		mockMvc.perform(get("/user/condominium/fee").param("id_fee", "1"))
		.andExpect(status().isOk())
		.andExpect(view().name("user/condominium/fee/fee"));
	}

	@Test
	@WithUserDetails(value = "norton", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void userCondominiumFeeViewLoads() throws Exception {
		mockMvc.perform(get("/user/condominium/condominiumfee").param("idCondominiumFee", "1"))
		.andExpect(status().isOk())
		.andExpect(view().name("user/condominium/condominiumfee/condominiumfee"));
	}
	
	@Test
	@WithUserDetails(value = "norton@teste.com", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void residentCondominiumFeeViewLoads() throws Exception {
		mockMvc.perform(get("/resident/condominium/condominiumfee").param("idCondominiumFee", "1"))
		.andExpect(status().isOk())
		.andExpect(view().name("resident/viewCondominiumFee"));
	}

	@Test
	@WithUserDetails(value = "norton@teste.com", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void residentFeeViewLoads() throws Exception {
		mockMvc.perform(get("/resident/viewCondominiumFees").param("id_condominium_fee", "1"))
		.andExpect(status().isOk())
		.andExpect(view().name("resident/viewCondominiumFee"));
	}



}
