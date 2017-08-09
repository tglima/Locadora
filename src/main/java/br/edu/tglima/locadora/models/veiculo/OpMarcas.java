package br.edu.tglima.locadora.models.veiculo;

public enum OpMarcas {

	AUDI("Audi"),
	CITROEN("Citroen"),
	FIAT("Fiat"),
	FORD("Ford"),
	HYUNDAI("Hyundai"), 
	RENAULT("Renault"), 
	VOLKSWAGEN("Volkswagen");

	private String label;

	private OpMarcas(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}	
	
}
