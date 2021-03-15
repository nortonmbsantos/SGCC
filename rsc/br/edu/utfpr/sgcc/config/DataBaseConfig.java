package br.edu.utfpr.sgcc.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

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
	
//	@Bean(name = "AdminDAOBean")
//	public AdminDAOImpl getAdminDAO() {
//		return new AdminDAOImpl(getDataSource());
//	}
//
//	@Bean(name = "CondominiumDAOBean")
//	public CondominiumDAOImpl getCondominiumDAO() {
//		return new CondominiumDAOImpl(getDataSource());
//	}
//
//	@Bean(name = "FeeDAOBean")
//	public FeeDAOImpl getFeeDAO() {
//		return new FeeDAOImpl(getDataSource());
//	}
//
//	@Bean(name = "FeeTypeDAOBean")
//	public FeeTypeDAOImpl getFeeTypeDAO() {
//		return new FeeTypeDAOImpl(getDataSource());
//	}
//
//	@Bean(name = "CondominiumFeeDAOBean")
//	public CondominiumFeeDAOImpl getCondominiumFeeDAO() {
//		return new CondominiumFeeDAOImpl(getDataSource());
//	}
//
//	@Bean(name = "ResidentDAOBean")
//	public ResidentDAOImpl getResidentDAO() {
//		return new ResidentDAOImpl(getDataSource());
//	}
//
//	@Bean(name = "WarningDAOBean")
//	public WarningDAOImpl getWarningDAO() {
//		return new WarningDAOImpl(getDataSource());
//	}
//
//	@Bean(name = "CommomAreaDAOBean")
//	public CommomAreaDAOImpl getCommomAreaDAO() {
//		return new CommomAreaDAOImpl(getDataSource());
//	}
//
//	@Bean(name = "BookingDAOBean")
//	public BookingDAOImpl getBookingDAO() {
//		return new BookingDAOImpl(getDataSource());
//	}
}
