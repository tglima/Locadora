package br.edu.tglima.locadora.controllers;

import static br.edu.tglima.locadora.util.FacesUtil.enviarMsgErro;
import static br.edu.tglima.locadora.util.FacesUtil.enviarMsgSucesso;
import static br.edu.tglima.locadora.util.VeiculoUtil.setDefaultValues;

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
		try {
			novoVeiculo = setDefaultValues(novoVeiculo);
			repository.salvarNovo(VeiculoUtil.fmtVeicToSave(this.novoVeiculo));
			enviarMsgSucesso(null, "Veículo cadastrado com sucesso!");
			this.novoVeiculo = null;
		} catch (Exception e) {
			System.out.println("Causa: " + e.getCause());
			enviarMsgErro(null, "Erro, veículo não cadastrado!");
			enviarMsgErro(null, e.getMessage());
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
