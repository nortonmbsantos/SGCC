package br.edu.utfpr.sgcc.daos;

import br.edu.utfpr.sgcc.models.Resident;

public interface ResidentDAO {
	public Resident returnById(int id);

	public Resident returnByLogin(String login);

	public Resident login(Resident resident);

	public boolean insert(Resident resident);
}
