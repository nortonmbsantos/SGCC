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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.edu.utfpr.sgcc.models.User;
import br.edu.utfpr.sgcc.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.hamcrest.Matchers;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = br.edu.utfpr.sgcc.Application.class)
@AutoConfigureMockMvc
public class LoginControllerTest {

	private UserService userServiceTest = new UserService();
	
	@Autowired
	private MockMvc mockMvc;

	private String contextPath = "http://localhost";

	@Before
	public void setup() {
		User user = new User();
		user.setActive(true);
		user.setFirstName("Norton");
		user.setEmail("norton@teste.com");
		user.setPassword("norton123");
		user.setRoles("ROLE_USER");
		user.setUserName("norton");
		userServiceTest.insert(user);
		User user2 = new User();
		user2.setActive(true);
		user2.setFirstName("Norton");
		user2.setEmail("norton@teste.com");
		user2.setPassword("123456789");
		user2.setRoles("ROLE_RESIDENT");
		user2.setUserName("norton@teste.com");
		userServiceTest.insert(user2);
	}
	
	@Test
	public void registerFormLoads() throws Exception {
		mockMvc.perform(get("/register"))
		.andExpect(status().isOk())
		.andExpect(view().name("registerview"));
	}

	@Test
	public void loginFormLoads() throws Exception {
		mockMvc.perform(get("/login"))
		.andExpect(status().isOk())
		.andExpect(view().name("loginview"))
		;
	}

	@Test
	public void registerShouldWorkWithValidUser() throws Exception {
		mockMvc.perform(post("/register")
				.param("userName", "nortonteste")
				.param("email", "norton@teste.com")
				.param("password", "nortonteste")
				.param("confirmPassword","nortonteste")
				.param("firstName", "Norton")
				.param("roles", "ROLE_USER")
				.sessionAttr("user", new User())
				.sessionAttr("condominiunsSideBar", "")
				)
        .andExpect(status().is(200))
        .andExpect(forwardedUrl("/login"))
//        .andExpect(forwardedUrl("/login"))
//		.andExpect(view().name("loginview"))
		;
	}

	@Test
	public void registerShouldNotWorkWithInvalidUser() throws Exception {
		mockMvc.perform(post("/register")
				.param("userName", "nortonteste")
				.param("password", "nortonteste")
				.param("confirmPassword","")
				.param("firstName", "Norton")
				.param("roles", "ROLE_USER")
				.sessionAttr("user", new User())
				.sessionAttr("condominiunsSideBar", "")
				)
		.andExpect(status().isOk())
		.andExpect(forwardedUrl("registerview"))
//		.andExpect(view().name("registerview"))
//		.andReturn()
		;
	}

	
	@Test
	public void loginShouldWorkWithValidUser() throws Exception {
		mockMvc.perform(post("/login").param("username", "norton").param("password", "norton123"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/user/dashboard"))
		;
	}

	@Test
	public void loginShouldNotWorkWithInvalidUser() throws Exception {
		mockMvc.perform(post("/login").param("username", "norton").param("password", "123"))
		.andExpect(status().is3xxRedirection())
//		.andExpect(view().name("loginview"))
		.andExpect(redirectedUrl("/login?error=true"));
		;
	}
	
	@Test
	public void loginShouldWorkWithValidResident() throws Exception {
		mockMvc.perform(post("/login").param("username", "norton@teste.com").param("password", "123456789"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/resident/dashboard"))
		;
	}
	
	@Test
	public void loginShouldNotWorkWithInvalidResident() throws Exception {
		mockMvc.perform(post("/login").param("username", "norton@teste.com").param("password", "123"))
		.andExpect(status().is3xxRedirection())
//		.andExpect(view().name("loginview"))
		.andExpect(redirectedUrl("/login?error=true"));
		;
	}

	@Test
	public void shouldRedirectToLoginWhenUserIsAnonymous() throws Exception {
		mockMvc.perform(get("/user/dashboard"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl(contextPath + "/login"));
	}
	
	@Test
	public void shouldRedirectToLoginWhenLogOut() throws Exception {
		mockMvc.perform(get("/logout"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/login"));
	}
	
}
