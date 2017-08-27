package br.edu.tglima.locadora.models.veiculo;

import java.io.Serializable;
import java.util.Date;

public class Veiculo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Date dataCadastro;
	private String placa;
	private OpMarcas marca;
	private String modelo;
	private Integer ano;
	private OpCategorias categoria;
	private OpCores cor;
	private OpCombustiveis combustivel;
	private Integer kmInicial;
	private Integer kmAtual;
	private OpStatus status;
	private Double valorDiaria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public OpMarcas getMarca() {
		return marca;
	}

	public void setMarca(OpMarcas marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public OpCategorias getCategoria() {
		return categoria;
	}

	public void setCategoria(OpCategorias categoria) {
		this.categoria = categoria;
	}

	public OpCores getCor() {
		return cor;
	}

	public void setCor(OpCores cor) {
		this.cor = cor;
	}

	public OpCombustiveis getCombustivel() {
		return combustivel;
	}

	public void setCombustivel(OpCombustiveis combustivel) {
		this.combustivel = combustivel;
	}

	public Integer getKmInicial() {
		return kmInicial;
	}

	public void setKmInicial(Integer kmInicial) {
		this.kmInicial = kmInicial;
	}

	public Integer getKmAtual() {
		return kmAtual;
	}

	public void setKmAtual(Integer kmAtual) {
		this.kmAtual = kmAtual;
	}

	public OpStatus getStatus() {
		return status;
	}

	public void setStatus(OpStatus status) {
		this.status = status;
	}

	public Double getValorDiaria() {
		return valorDiaria;
	}

	public void setValorDiaria(Double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((placa == null) ? 0 : placa.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Veiculo other = (Veiculo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (placa == null) {
			if (other.placa != null)
				return false;
		} else if (!placa.equals(other.placa))
			return false;
		return true;
	}

}
