package br.edu.utfpr.sgcc.daos;

import java.util.List;

import br.edu.utfpr.sgcc.models.Fee;

public interface FeeDAO {
	public Fee returnById(int id);
	
	public List<Fee> returnByIdCondominium(int id);

	public boolean insert(Fee fee);
}
