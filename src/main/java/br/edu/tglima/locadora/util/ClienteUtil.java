package br.edu.tglima.locadora.util;


import java.util.Date;

import br.edu.tglima.locadora.models.pessoa.Cliente;

public class ClienteUtil {
		
	public final static Cliente fmtToSave(Cliente c){
		c.setNome(c.getNome().toLowerCase());
		c.setSobrenome(c.getSobrenome().toLowerCase());
		
		if (c.getDataCadastro() == null) {
			c.setDataCadastro(new Date());
		} 
		
		return c;
	}

}
