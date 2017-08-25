package br.edu.tglima.locadora.controllers;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.tglima.locadora.models.pessoa.Cliente;
import br.edu.tglima.locadora.models.pessoa.OpGeneros;
import br.edu.tglima.locadora.util.ClienteUtil;
import br.edu.tglima.locadora.util.FacesUtil;

@Named
@ViewScoped
public class CadCliente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Cliente nc;
	
	public void cadastrar(){
		
		FacesUtil.enviarMsgSucesso(null, "Cliente cadastrado com sucesso!");
		System.out.println("::::::::::::::::::::::::::::::::::::: CLIENTE SALVO :::::::::::::::::::::::::::::::::::::");
		ClienteUtil.exibirDadosNoConsole(nc);
		this.nc = new Cliente();
	}
	
	//Getters de acesso aos Enums
	public OpGeneros[] getGeneros(){
		return OpGeneros.values();
	}
	
	public Cliente getNc() {
		return nc;
	}
}
