package br.edu.tglima.locadora.controllers;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.edu.tglima.locadora.models.veiculo.OpCategorias;
import br.edu.tglima.locadora.models.veiculo.OpCombustiveis;
import br.edu.tglima.locadora.models.veiculo.OpCores;
import br.edu.tglima.locadora.models.veiculo.OpMarcas;
import br.edu.tglima.locadora.models.veiculo.OpStatus;
import br.edu.tglima.locadora.models.veiculo.Veiculo;
import br.edu.tglima.locadora.repository.VeiculoRepository;

@Named
@RequestScoped
public class EditVeic implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private VeiculoRepository repository;

	@Inject
	private Veiculo veicParaEdicao;

	@PostConstruct
	public void init() {
		// TODO O veículo a ser editado, deve ser passado por parâmetro.
		// Nesta fase, irei carregar um veículo específico do BD.
		// Futuramente isto será substituído.
		veicParaEdicao = repository.buscarPorId((long) 1000);
	}

	public void salvarEdicao() {
		veicParaEdicao = repository.salvarEdicao(veicParaEdicao);
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

	public OpStatus[] getSituacoes() {
		OpStatus[] opcoes = { OpStatus.DISPONIVEL, OpStatus.INOPERANTE, OpStatus.MANUTENCAO };
		return opcoes;
	}

	// Getters e Setters padrões
	public Veiculo getVeicParaEdicao() {
		return veicParaEdicao;
	}

}
