package br.edu.tglima.locadora.controllers;

import static br.edu.tglima.locadora.util.FacesUtil.enviarMsgErro;
import static br.edu.tglima.locadora.util.FacesUtil.enviarMsgSucesso;
import static br.edu.tglima.locadora.util.Util.fmtToSave;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.tglima.locadora.models.pessoa.Cliente;
import br.edu.tglima.locadora.models.pessoa.OpGeneros;
import br.edu.tglima.locadora.repository.ClienteRepository;

@Named
@RequestScoped
public class CadCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private Cliente novoCliente;

	@Inject
	private ClienteRepository repository;

	public void cadastrar() {
		novoCliente = fmtToSave(novoCliente);
		try {
			repository.salvarNovo(novoCliente);
			enviarMsgSucesso("Cliente cadastrado com sucesso!");
			novoCliente = null;
		} catch (Exception e) {
			enviarMsgErro("Erro, cliente não cadastrado!");
			if (e.getMessage().contains("ConstraintViolationException")) {
				enviarMsgErro("O Nº de Registro da CNH informada já pertence a outra pessoa.");
			} else {
				enviarMsgErro(e.getMessage());
			}

		}

	}

	// Getters de acesso aos Enums
	public OpGeneros[] getGeneros() {
		return OpGeneros.values();
	}

	public Cliente getNovoCliente() {
		return novoCliente;
	}
}
