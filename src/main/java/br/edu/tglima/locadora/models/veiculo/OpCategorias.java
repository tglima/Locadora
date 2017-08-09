package br.edu.tglima.locadora.models.veiculo;

public enum OpCategorias {

	HATCH("Hatch"), 
	SEDA("Sedã"), 
	SUV("SUV"),
	PREMIUM("Premium");

	private String label;

	OpCategorias(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
