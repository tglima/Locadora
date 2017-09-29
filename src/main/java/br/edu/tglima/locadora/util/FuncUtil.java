package br.edu.tglima.locadora.util;

import java.util.Date;
import br.edu.tglima.locadora.models.pessoa.Funcionario;

public class FuncUtil {
	
	public final static Funcionario fmtFuncToSave(Funcionario f){
		f.setNome(f.getNome().toLowerCase());
		f.setSobrenome(f.getSobrenome().toLowerCase());
		
		if (f.getDataCadastro() == null) {
			f.setDataCadastro(new Date());
		} 
		
		return f;
	}
	
}
