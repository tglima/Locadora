package br.edu.tglima.locadora.models.pessoa;

import java.util.Date;

public class Cliente extends Pessoa {

	private static final long serialVersionUID = 1L;
	private String habilitacao;
	private Date validadeHab;

	public String getHabilitacao() {
		return habilitacao;
	}

	public void setHabilitacao(String habilitacao) {
		this.habilitacao = habilitacao;
	}

	public Date getValidadeHab() {
		return validadeHab;
	}

	public void setValidadeHab(Date validadeHab) {
		this.validadeHab = validadeHab;
	}

}
