package br.edu.tglima.locadora.controllers;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.edu.tglima.locadora.models.veiculo.OpCategorias;
import br.edu.tglima.locadora.models.veiculo.OpCombustiveis;
import br.edu.tglima.locadora.models.veiculo.OpCores;
import br.edu.tglima.locadora.models.veiculo.OpMarcas;
import br.edu.tglima.locadora.models.veiculo.Veiculo;
import br.edu.tglima.locadora.repository.VeiculoRepository;
import br.edu.tglima.locadora.util.VeiculoUtil;

@Named
@RequestScoped
public class CadVeic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private Veiculo novoVeiculo;

	@Inject
	private VeiculoRepository repository;

	public void cadastrar() {
		novoVeiculo = VeiculoUtil.setDefaultValues(novoVeiculo);
		novoVeiculo = VeiculoUtil.fmtVeicToSave(novoVeiculo);
		if (repository.salvarNovo(this.novoVeiculo)) {
			this.novoVeiculo = null;
		}
	}

	// Getters de acesso aos Enums
	public OpMarcas[] getMarcas() {
		return OpMarcas.values();
	}

	public OpCategorias[] getCategorias() {
		return OpCategorias.values();
	}

	public OpCores[] getCores() {
		return OpCores.values();
	}

	public OpCombustiveis[] getCombustiveis() {
		return OpCombustiveis.values();
	}

	// Getters e Setters padrões
	public Veiculo getNovoVeiculo() {
		return novoVeiculo;
	}

	public void setNovoVeiculo(Veiculo novoVeiculo) {
		this.novoVeiculo = novoVeiculo;
	}

}
