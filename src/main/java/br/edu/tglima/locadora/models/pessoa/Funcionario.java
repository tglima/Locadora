package br.edu.tglima.locadora.models.pessoa;

public class Funcionario extends Pessoa {

	private static final long serialVersionUID = 1L;
	private TiposCargo cargo;
	private String password;
	private Boolean online;

	public TiposCargo getCargo() {
		return cargo;
	}

	public void setCargo(TiposCargo cargo) {
		this.cargo = cargo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getOnline() {
		return online;
	}

	public void setOnline(Boolean online) {
		this.online = online;
	}

}
