package br.edu.utfpr.sgcc.daos;

import java.util.List;
import br.edu.utfpr.sgcc.models.Warning;

public interface WarningDAO {
	public Warning returnById(int id);

	public List<Warning> returnByResident(int id_resident);

	public boolean insert(Warning warning);
}
