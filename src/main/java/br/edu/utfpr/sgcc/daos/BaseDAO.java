package br.edu.utfpr.sgcc.daos;

import javax.sql.DataSource;

public class BaseDAO {

	public DataSource dataSource;
	
	public BaseDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
