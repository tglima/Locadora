package br.edu.tglima.locadora.controllers;

import static br.edu.tglima.locadora.util.FacesUtil.enviarMsgErro;
import static br.edu.tglima.locadora.util.FacesUtil.enviarMsgSucesso;
import static br.edu.tglima.locadora.util.Util.fmtToSave;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
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
	private Veiculo veicEmEdicao;

	@PostConstruct
	public void init() {
		/*
		 * TODO O veículo editado, será passado por parâmetro e carregado aqui. Por
		 * enquanto será carregado um veículo específico do BD.
		 */
		try {
			this.veicEmEdicao = repository.buscarPorPlaca("pkm-2001");
		} catch (Exception e) {
			System.out.println("Erro lançado: " + e.getCause());
		}
	}

	public void salvarEdicao() {
		try {
			repository.salvarEdicao(fmtToSave(veicEmEdicao));
			enviarMsgSucesso("Alterações salvas com sucesso!");
			init();
		} catch (Exception e) {
			enviarMsgErro("Erro, as alterações não foram salvas!");
			if (e.getMessage().contains("ConstraintViolationException")) {
				enviarMsgErro("A placa informada já pertence a outro veículo.");
			} else {
				enviarMsgErro(e.getMessage());
			}
		}
	}

	public void kmInicialAlterada(ValueChangeEvent event) {
		this.veicEmEdicao.setKmInicial((Integer) event.getNewValue());
		FacesContext.getCurrentInstance().renderResponse();
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
		return veicEmEdicao;
	}

}
