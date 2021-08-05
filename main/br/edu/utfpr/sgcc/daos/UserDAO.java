package br.edu.utfpr.sgcc.daos;


import br.edu.utfpr.sgcc.models.User;

public interface UserDAO {
	public User returnById(int id);

	public User returnByUserName(String userName);

	public User login(User user);

	public boolean insert(User user);
}
