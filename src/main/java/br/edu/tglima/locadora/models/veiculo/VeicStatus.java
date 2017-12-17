package br.edu.tglima.locadora.models.veiculo;

public enum VeicStatus {

	ALUGADO("Alugado"), DISPONIVEL("Disponível"), INOPERANTE("Inoperante"), MANUTENCAO("Manutenção");

	private String label;

	private VeicStatus(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
