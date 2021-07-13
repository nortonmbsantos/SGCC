package br.edu.utfpr.sgcc.config;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import br.edu.utfpr.sgcc.daos.*;

@Configuration
public class DataBaseConfig {

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/tcc?useSSL=false");
		dataSource.setUsername("root");
		dataSource.setPassword("");

		return dataSource;
	}

	@Bean(name = "DAOBean")
	public DataSource getDataSourceBean() {
		return getDataSource();
	}

	@ExceptionHandler(value = NullPointerException.class)
	public ModelAndView handlerExceptionResolver() {
		return new ModelAndView("error/exception");
	}

	@Bean
	public JavaMailSender getMailSender() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

//		spring.mail.host=smtp.gmail.com
//				spring.mail.port=587
//				spring.mail.username=norton@alunos.utfpr.edu.br
//				spring.mail.password=izettg14
//				spring.mail.properties.mail.smtp.auth=true
//				spring.mail.properties.mail.smtp.starttls.enable=true
//				spring.mail.properties.mail.smtp.starttls.required=true
//				spring.mail.properties.mail.smtp.ssl.enable=false
//				spring.mail.test-connection=true
		
		javaMailSender.setProtocol("SMTP");
		javaMailSender.setHost("smtp.gmail.com");
		javaMailSender.setPort(587);
		javaMailSender.setUsername("norton@alunos.utfpr.edu.br");
		javaMailSender.setPassword("izettg14");

		return javaMailSender;

	}
}
