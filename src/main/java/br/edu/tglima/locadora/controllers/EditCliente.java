package br.edu.tglima.locadora.controllers;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.tglima.locadora.models.pessoa.Cliente;
import br.edu.tglima.locadora.models.pessoa.OpGeneros;
import br.edu.tglima.locadora.util.ClienteUtil;
import br.edu.tglima.locadora.util.FacesUtil;

@Named
@ViewScoped
public class EditCliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Cliente sc;

	@PostConstruct
	public void init() {
		sc = ClienteUtil.criarClienteExemplo(sc);
		System.out.println("::::::::::::::::::::::::::::::::::: CLIENTE CARREGADO :::::::::::::::::::::::::::::::::::");
		ClienteUtil.exibirDadosNoConsole(sc);
	}

	public void salvarEdicao() {
		FacesUtil.enviarMsgSucesso(null, "Alterações no Cliente salvas com sucesso!");
		System.out.println("::::::::::::::::::::::::::::::::::::: CLIENTE SALVO :::::::::::::::::::::::::::::::::::::");
		ClienteUtil.exibirDadosNoConsole(sc);
	}

	// Getters de acesso aos Enums
	public OpGeneros[] getGeneros() {
		return OpGeneros.values();
	}

	public Cliente getSc() {
		return sc;
	}

}
