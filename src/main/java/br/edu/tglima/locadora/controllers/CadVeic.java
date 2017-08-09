package br.edu.tglima.locadora.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
	private Veiculo novoVeic;
	
	//TODO Apagar isto, colocado apenas para teste
	private List<Veiculo> veiCadastrados = new ArrayList<>();
	
	
	
	public void cadastrar(){
		this.novoVeic.setKmInicial(getNovoVeic().getKmAquisicao());
		this.novoVeic.setSituacao(OpSituacao.INOPERANTE);
		
		FacesUtil.enviarMsgSucesso(null, "Veículo cadastrado com sucesso!");
		
		//TODO Apagar isto, colocado apenas para teste
		this.veiCadastrados.add(novoVeic);
		
		this.novoVeic = new Veiculo();
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
	public Veiculo getNovoVeic() {
		return novoVeic;
	}
	
	public List<Veiculo> getVeiCadastrados() {
		return veiCadastrados;
	}

	public void setVeiCadastrados(List<Veiculo> veiCadastrados) {
		this.veiCadastrados = veiCadastrados;
	}
	

}
