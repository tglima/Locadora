package br.edu.tglima.locadora.controllers;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.tglima.locadora.models.veiculo.OpCategorias;
import br.edu.tglima.locadora.models.veiculo.OpCombustiveis;
import br.edu.tglima.locadora.models.veiculo.OpCores;
import br.edu.tglima.locadora.models.veiculo.OpMarcas;
import br.edu.tglima.locadora.models.veiculo.OpSituacao;
import br.edu.tglima.locadora.models.veiculo.Veiculo;
import br.edu.tglima.locadora.util.FacesUtil;

@Named
@ViewScoped
public class CadVeic implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Veiculo nv;
	
	public void cadastrar(){
		this.nv.setKmInicial(getNv().getKmAquisicao());
		this.nv.setSituacao(OpSituacao.INOPERANTE);
		
		FacesUtil.enviarMsgSucesso(null, "Veículo cadastrado com sucesso!");
		exibirDadosInformados();
		this.nv = new Veiculo();
	}
	
	
	
	private void exibirDadosInformados(){
		System.out.println("###########################################################");
		System.out.println("                   Dados informados");
		System.out.println("Placa: " + this.nv.getPlaca());
		System.out.println("Marca: " + this.nv.getMarca());
		System.out.println("Modelo: "+ this.nv.getModelo());
		System.out.println("Categoria: "+ this.nv.getCategoria());
		System.out.println("Cor: "+ this.nv.getCor());
		System.out.println("Ano de fabricação: "+ this.nv.getAno());
		System.out.println("Combustível: "+ this.nv.getCombustivel());
		System.out.println("Km aquisição: "+ this.nv.getKmAquisicao());
		System.out.println("Km inicial: "+ this.nv.getKmInicial());
		System.out.println("###########################################################\n");
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
