package br.edu.utfpr.sgcc.daos;

import java.util.List;
import br.edu.utfpr.sgcc.models.CommomArea;

public interface CommomAreaDAO {

	public CommomArea returnById(int id);

	public List<CommomArea> list(int idAdmin);

	public boolean insert(CommomArea area);

}
