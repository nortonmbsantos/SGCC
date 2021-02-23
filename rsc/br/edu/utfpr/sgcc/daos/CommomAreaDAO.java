package br.edu.utfpr.sgcc.daos;

import java.util.List;

import javax.persistence.Query;
import javax.sql.DataSource;

import org.hibernate.Session;

import br.edu.utfpr.sgcc.models.CommomArea;
import br.edu.utfpr.sgcc.models.Condominium;

public interface CommomAreaDAO {

	public CommomArea returnById(int id);

	public List<CommomArea> list(int idAdmin);

	public boolean insert(CommomArea area);

}
