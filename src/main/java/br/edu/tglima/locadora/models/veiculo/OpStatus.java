package br.edu.tglima.locadora.models.veiculo;

public enum OpStatus {

	ALUGADO("Alugado"), DISPONIVEL("Disponível"), INOPERANTE("Inoperante"), MANUTENCAO("Manutenção");

	private String label;

	private OpStatus(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
