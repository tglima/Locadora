package br.edu.tglima.locadora.controllers;

import java.io.Serializable;
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
import br.edu.tglima.locadora.util.TempoUtil;
import br.edu.tglima.locadora.util.VeiculoUtil;

@Named
@ViewScoped
public class CadVeic implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Veiculo nv;
	
	public void cadastrar(){
		this.nv.setId(2l);
		this.nv.setDataCadastro(TempoUtil.setDateNow());
		this.nv.setStatus(OpStatus.INOPERANTE);
		this.nv.setKmAtual(this.nv.getKmInicial());
		
		FacesUtil.enviarMsgSucesso(null, "Veículo cadastrado com sucesso!");
		System.out.println("::::::::::::::::::::::::::::::::::::: VEÍCULO SALVO :::::::::::::::::::::::::::::::::::::");
		VeiculoUtil.exibirDadosNoConsole(this.nv);
		this.nv = new Veiculo();
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
	
//	Getters e Setters padrões
	public Veiculo getNv() {
		return nv;
	}
		

}
