package br.edu.tglima.locadora.models.veiculo;

public enum OpCombustiveis {
		
	ALCOOL("Álcool"),
	GASOLINA("Gasolina"),
	FLEX("Flex"),
	DIESEL("Diesel");
	
	private String label;

	public String getLabel() {
		return label;
	}
	
	private OpCombustiveis(String label) {
		this.label = label;
	}	

}
