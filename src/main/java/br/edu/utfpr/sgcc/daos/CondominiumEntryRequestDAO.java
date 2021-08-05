package br.edu.utfpr.sgcc.daos;

import java.util.List;

import br.edu.utfpr.sgcc.models.CondominiumEntryRequest;

public interface CondominiumEntryRequestDAO {

	public CondominiumEntryRequest returnById(int id);

	public List<CondominiumEntryRequest> list(int idUser);

	public boolean insert(CondominiumEntryRequest request);

}
