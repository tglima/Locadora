package br.edu.tglima.locadora.models.pessoa;

public enum TiposCargo {
	GE("Gerente", 1), AT("Atendente", 9);

	private String label;
	private Integer perfil;

	TiposCargo(String label, Integer perfil) {
		this.label = label;
		this.perfil = perfil;
	}

	public String getLabel() {
		return label;
	}

	public Integer getPerfil() {
		return perfil;
	}

}
