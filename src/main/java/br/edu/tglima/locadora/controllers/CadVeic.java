package br.edu.tglima.locadora.controllers;

import static br.edu.tglima.locadora.util.FacesUtil.enviarMsgErro;
import static br.edu.tglima.locadora.util.FacesUtil.enviarMsgSucesso;
import static br.edu.tglima.locadora.util.Util.fmtToSave;

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
			repository.salvarNovo(fmtToSave(novoVeiculo));
			enviarMsgSucesso("Veículo cadastrado com sucesso!");
			novoVeiculo = null;
		} catch (Exception e) {
			enviarMsgErro("Erro, veículo não cadastrado!");
			if (e.getMessage().contains("ConstraintViolationException")) {
				enviarMsgErro("A placa informada já pertence a outro veículo.");
			} else {
				enviarMsgErro(e.getMessage());
			}

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

}
