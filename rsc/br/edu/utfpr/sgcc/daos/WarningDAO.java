package br.edu.utfpr.sgcc.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import br.edu.utfpr.sgcc.models.Admin;
import br.edu.utfpr.sgcc.models.Resident;
import br.edu.utfpr.sgcc.models.Warning;

public interface WarningDAO {
	public Warning returnById(int id);

	public List<Warning> returnByResident(int id_resident);

	public boolean insert(Warning warning);
}
