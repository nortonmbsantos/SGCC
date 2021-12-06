package br.edu.utfpr.sgcc.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import br.edu.utfpr.sgcc.models.Condominium;
import br.edu.utfpr.sgcc.models.Result;
import br.edu.utfpr.sgcc.models.User;
import br.edu.utfpr.sgcc.service.CondominiumService;
import br.edu.utfpr.sgcc.service.UserService;

import static org.assertj.core.api.Assertions.useRepresentation;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.hamcrest.Matchers;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = br.edu.utfpr.sgcc.Application.class)
@AutoConfigureMockMvc
public class CondominiumControllerTest {

	private UserService userServiceTest = new UserService();
	private CondominiumService condominiumServiceTest = new CondominiumService();

	@Autowired
	private MockMvc mockMvc;

	@Before
	public void setup() {
//		User userInsert = new User();
//		userInsert.setActive(true);
//		userInsert.setFirstName("Norton");
//		userInsert.setEmail("norton@teste.com");
//		userInsert.setPassword("norton123");
//		userInsert.setRoles("ROLE_USER");
//		userInsert.setUserName("norton");
//		userServiceTest.insert(userInsert);

//		Condominium condominium = new Condominium();
//		condominium.setCep("81110070");
//		condominium.setDescription("Raphael Ville");
//		condominium.setName("Raphael Ville");
//		condominium.setIdUser(1);
//		condominium.setResidential(true);
//		condominiumServiceTest.insert(condominium);

	}

	@Test
	@WithUserDetails(value = "norton", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void condominiumCreateFormShouldLoad() throws Exception {
		mockMvc.perform(get("/user/condominium/new")).andExpect(status().isOk())
				.andExpect(view().name("user/condominium/form"));

	}

	@Test
	@WithUserDetails(value = "norton", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void condominiumUpdateFormShouldLoad() throws Exception {
		mockMvc.perform(get("/user/condominium/update").param("id", "1")).andExpect(status().isOk())
				.andExpect(view().name("user/condominium/form"));

	}

	@Test
	@WithUserDetails(value = "norton", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void condominiumUpdateFormShouldSave() throws Exception {
		Condominium condominium = condominiumServiceTest.returnById(1);
		String descrition = "Alterado " + new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(new Date());
		condominium.setDescription(descrition);
		mockMvc.perform(post("/user/condominium/form")
				.param("id", String.valueOf(condominium.getId()))
				.param("name", condominium.getName())
				.param("description", condominium.getDescription())
				.param("cep", condominium.getCep())
				.param("street", condominium.getStreet())
				.param("streetNumber", condominium.getStreetNumber())
				.param("numberComplement", condominium.getNumberComplement())
				.param("neighborhood", condominium.getNeighborhood())
				.param("city", condominium.getCity())
				.param("state", condominium.getState())
				)
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/user/condominium?id=" + condominium.getId()))
//		.andExpect(flash().attribute("result", new Result("Login efetuado com sucesso", "success")))
//		.andExpect(view().name("user/condominium/condominium"));
;
	}
	
	@Test
	@WithUserDetails(value = "norton", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void condominiumAddFormShouldSave() throws Exception {
		Condominium condominium = condominiumServiceTest.returnById(1);
		condominium.setId(0);
		condominium.setName("Novo condominio teste");
		String descrition = "Criado " + new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(new Date());
		condominium.setDescription(descrition);
		mockMvc.perform(post("/user/condominium/form")
				.param("id", String.valueOf(condominium.getId()))
				.param("name", condominium.getName())
				.param("description", condominium.getDescription())
				.param("cep", condominium.getCep())
				.param("street", condominium.getStreet())
				.param("streetNumber", condominium.getStreetNumber())
				.param("numberComplement", condominium.getNumberComplement())
				.param("neighborhood", condominium.getNeighborhood())
				.param("city", condominium.getCity())
				.param("state", condominium.getState())
				)
		.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrlPattern("/user/condominium?id={[0-9]*}"))
//			.andExpect(flash().attribute("result", new Result("Login efetuado com sucesso", "success")))
//			.andExpect(view().name("user/condominium/condominium"))
		;
//
//		condominium = condominiumServiceTest.returnById(1);
//		assertEquals(condominium.getDescription(), descrition);
	}

	@Test
	@WithUserDetails(value = "user", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void condominiumUpdateFormShouldNotLoad_IfUserIsNotTheOwner() throws Exception {
		mockMvc.perform(get("/user/condominium/update").param("id", "1")).andExpect(status().isOk())
				.andExpect(view().name("errors/accessdenied"));

	}

	@Test
	@WithUserDetails(value = "norton", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void condominiumViewShouldLoad() throws Exception {
		mockMvc.perform(get("/user/condominium").param("id", "1")).andExpect(status().isOk())
				.andExpect(view().name("user/condominium/condominium"));
	}

	@Test
	@WithUserDetails(value = "user", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void condominiumViewShouldNotLoad_IfUserIsNotTheOwner() throws Exception {
		mockMvc.perform(get("/user/condominium").param("id", "1")).andExpect(status().isOk())
				.andExpect(view().name("errors/accessdenied"));
	}

	@Test
	@WithUserDetails(value = "norton", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void userListOfCondominiunsViewShouldLoad() throws Exception {
		mockMvc.perform(get("/user/condominiuns").param("id", "1")).andExpect(status().isOk())
				.andExpect(view().name("user/condominium/condominiuns"));
	}

	@Test
	@WithUserDetails(value = "norton@teste.com", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void residentCondominiumViewShouldLoad() throws Exception {
		mockMvc.perform(get("/resident/condominium").param("id", "1")).andExpect(status().isOk())
				.andExpect(view().name("resident/condominium"));
	}

	@Test
	@WithUserDetails(value = "norton@teste.com", setupBefore = TestExecutionEvent.TEST_EXECUTION)
	public void residentListOfCondominiunsViewShouldLoad() throws Exception {
		mockMvc.perform(get("/resident/condominiuns").param("id", "1")).andExpect(status().isOk())
				.andExpect(view().name("resident/condominiuns"));
	}

}
