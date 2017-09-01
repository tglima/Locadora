package br.edu.tglima.locadora.controllers;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.edu.tglima.locadora.models.veiculo.OpCategorias;
import br.edu.tglima.locadora.models.veiculo.OpCombustiveis;
import br.edu.tglima.locadora.models.veiculo.OpCores;
import br.edu.tglima.locadora.models.veiculo.OpMarcas;
import br.edu.tglima.locadora.models.veiculo.OpStatus;
import br.edu.tglima.locadora.models.veiculo.Veiculo;
import br.edu.tglima.locadora.util.FacesUtil;
import br.edu.tglima.locadora.util.VeiculoUtil;

@Named
@ViewScoped
public class EditVeic implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Veiculo sv;
	
	@PostConstruct
	public void init(){
		sv = VeiculoUtil.criarVeicExemplo(this.sv);
		sv = VeiculoUtil.fmtVeicToShow(this.sv);
		System.out.println("::::::::::::::::::::::::::::::::::: VEÍCULO CARREGADO :::::::::::::::::::::::::::::::::::");
		VeiculoUtil.exibirDadosNoConsole(this.sv);
	}
	
	public void salvarEdicao(){
		FacesUtil.enviarMsgSucesso(null, "Alterações no Veículo salvas com sucesso!");
		System.out.println("::::::::::::::::::::::::::::::::::::: VEÍCULO SALVO :::::::::::::::::::::::::::::::::::::");
		sv = VeiculoUtil.fmtVeicToSave(this.sv);
		VeiculoUtil.exibirDadosNoConsole(this.sv);
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
	
	public OpStatus[] getSituacoes(){
		OpStatus[] opcoes = {OpStatus.DISPONIVEL, OpStatus.INOPERANTE, OpStatus.MANUTENCAO};
		return opcoes;
	}
	
//	Getters e Setters padrões
	public Veiculo getSv() {
		return sv;
	}

}
