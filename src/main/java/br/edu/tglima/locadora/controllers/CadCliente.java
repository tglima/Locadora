package br.edu.tglima.locadora.controllers;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.tglima.locadora.models.pessoa.Cliente;
import br.edu.tglima.locadora.models.pessoa.OpGeneros;
import br.edu.tglima.locadora.util.FacesUtil;

@Named
@ViewScoped
public class CadCliente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Cliente nc;
	
	public void cadastrar(){
		
		FacesUtil.enviarMsgSucesso(null, "Cliente cadastrado com sucesso!");
		exibirDadosInformados();
		this.nc = new Cliente();
	}
	
//	TODO Eliminar este método depois
	private void exibirDadosInformados(){
		System.out.println("###########################################################");
		System.out.println("                   Dados informados");
		System.out.println("Nome do cliente: " + this.nc.getNome() );
		System.out.println("Sobrenome: " + this.nc.getSobrenome() );
		System.out.println("Gênero: " + this.nc.getGenero() );
		System.out.println("Data de nascimento: " + this.nc.getDataNasc() );
		System.out.println("Telefone: " + this.nc.getTelefone());
		System.out.println("Nº Registro CNH: " + this.nc.getHabilitacao());
		System.out.println("Data de validade da CNH: " + this.nc.getValidadeHab());
		System.out.println("###########################################################\n");
	}

	//Getters de acesso aos Enums
	public OpGeneros[] getGeneros(){
		return OpGeneros.values();
	}
	
	public Cliente getNc() {
		return nc;
	}
}
