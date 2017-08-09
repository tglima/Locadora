package br.edu.tglima.locadora.models.veiculo;

public enum OpSituacao {

	ALUGADO("Alugado"),
	DISPONIVEL("Disponível"),
	INOPERANTE("Inoperante"),
	MANUTENCAO("Manutenção");
	
	
	private String label;
	
	private OpSituacao(String label){
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
	
	
	
}
