package br.edu.utfpr.sgcc.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import br.edu.utfpr.sgcc.models.Admin;
import br.edu.utfpr.sgcc.models.Resident;

public interface ResidentDAO {
	public Resident returnById(int id);

	public Resident returnByLogin(String login);

	public Resident login(Resident resident);

	public boolean insert(Resident resident);
}
