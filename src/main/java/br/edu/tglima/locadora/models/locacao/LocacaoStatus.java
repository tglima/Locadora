package br.edu.tglima.locadora.models.locacao;

public enum LocacaoStatus {

	ATIVO("Ativo"),

	CANCELADO("Cancelado"),

	FINALIZADO("Finalizado");

	private String label;

	LocacaoStatus(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
