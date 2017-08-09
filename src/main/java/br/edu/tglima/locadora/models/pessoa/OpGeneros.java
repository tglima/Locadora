package br.edu.tglima.locadora.models.pessoa;

public enum OpGeneros {

	M("Masculino"), F("Feminino");

	private String label;

	OpGeneros(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
