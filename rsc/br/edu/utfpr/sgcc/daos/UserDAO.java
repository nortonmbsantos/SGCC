package br.edu.utfpr.sgcc.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import br.edu.utfpr.sgcc.models.User;

public interface UserDAO {
	public User returnById(int id);

	public User returnByUserName(String userName);

	public User login(User user);

	public boolean insert(User user);
}
