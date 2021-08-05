package br.edu.utfpr.sgcc.daos;

import java.util.List;

import br.edu.utfpr.sgcc.models.FeeType;

public interface FeeTypeDAO {
	public FeeType returnById(int id);
	
	public List<FeeType> list();

}
