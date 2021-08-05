package br.edu.utfpr.sgcc.daos;

import java.util.List;


import br.edu.utfpr.sgcc.models.Condominium;

public interface CondominiumDAO {

	public Condominium returnById(int id);

	public List<Condominium> list(int idUser);

	public boolean insert(Condominium condominium);

}
