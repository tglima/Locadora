package br.edu.tglima.locadora.models.veiculo;

public enum OpCores {
	
	AMARELO("Amarelo"),
	BRANCO("Branco"),
	PRATA("Prata"),
	PRETO("Preto"),
	VERMELHO("Vermelho");
	
	private String label;

	public String getLabel() {
		return label;
	}
	
	private OpCores(String label) {
		this.label = label;
	}	

}
