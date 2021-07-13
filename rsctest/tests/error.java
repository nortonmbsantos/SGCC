package tests;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class error {

	@Bean
	public SimpleMappingExceptionResolver exceptionResolver() {
	    SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
	 
	    Properties exceptionMappings = new Properties();
	 
	    exceptionMappings.put("net.petrikainulainen.spring.testmvc.todo.exception.TodoNotFoundException", "error/404");
	    exceptionMappings.put("java.lang.Exception", "error/error");
	    exceptionMappings.put("java.lang.RuntimeException", "error/error");
	 
	    exceptionResolver.setExceptionMappings(exceptionMappings);
	 
	    Properties statusCodes = new Properties();
	 
	    statusCodes.put("error/404", "404");
	    statusCodes.put("error/error", "500");
	 
	    exceptionResolver.setStatusCodes(statusCodes);
	 
	    return exceptionResolver;
	}
	
}
