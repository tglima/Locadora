package br.edu.tglima.locadora.util;

import java.util.Date;

import br.edu.tglima.locadora.models.pessoa.Cliente;
import br.edu.tglima.locadora.models.pessoa.Funcionario;
import br.edu.tglima.locadora.models.veiculo.OpStatus;
import br.edu.tglima.locadora.models.veiculo.Veiculo;

public class Util {

	public final static Cliente fmtToSave(Cliente c) {
		c.setNome(c.getNome().toLowerCase());
		c.setSobrenome(c.getSobrenome().toLowerCase());

		if (c.getDataCadastro() == null) {
			c.setDataCadastro(new Date());
		}
		return c;
	}

	public final static Funcionario fmtToSave(Funcionario f) {
		f.setNome(f.getNome().toLowerCase());
		f.setSobrenome(f.getSobrenome().toLowerCase());

		if (f.getDataCadastro() == null) {
			f.setDataCadastro(new Date());
		}
		return f;
	}

	public final static Veiculo fmtToSave(Veiculo v) {
		v.setPlaca(v.getPlaca().toLowerCase());
		v.setModelo(v.getModelo().trim());
		v.setModelo(v.getModelo().toLowerCase());

		if (v.getDataCadastro() == null && v.getKmAtual() == null) {
			v.setDataCadastro(new Date());
			v.setKmAtual(v.getKmInicial());
			v.setStatus(OpStatus.INOPERANTE);
		}

		return v;
	}

}
