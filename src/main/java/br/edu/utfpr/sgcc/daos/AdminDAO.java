package br.edu.utfpr.sgcc.daos;

import br.edu.utfpr.sgcc.models.Admin;

public interface AdminDAO {
	public Admin returnById(int id);

	public Admin returnByEmail(String email);

	public Admin login(Admin admin);

	public boolean insert(Admin admin);
}
