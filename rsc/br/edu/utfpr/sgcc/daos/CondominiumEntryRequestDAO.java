package br.edu.utfpr.sgcc.daos;

import java.util.List;

import javax.persistence.Query;
import javax.sql.DataSource;

import org.hibernate.Session;

import br.edu.utfpr.sgcc.models.Condominium;
import br.edu.utfpr.sgcc.models.CondominiumEntryRequest;

public interface CondominiumEntryRequestDAO {

	public CondominiumEntryRequest returnById(int id);

	public List<CondominiumEntryRequest> list(int idUser);

	public boolean insert(CondominiumEntryRequest request);

}
