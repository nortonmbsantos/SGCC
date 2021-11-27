package br.edu.utfpr.sgcc.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;

import br.edu.utfpr.sgcc.config.*;
import br.edu.utfpr.sgcc.controllers.IndexController;

@WebMvcTest
@ContextConfiguration
public class IndexControllerTest {
	
	@Autowired
	private IndexController controller;
	
	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
	}
}
