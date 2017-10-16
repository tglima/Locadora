package br.edu.tglima.locadora.controllers;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.tglima.locadora.models.pessoa.Cliente;
import br.edu.tglima.locadora.models.pessoa.OpGeneros;
import br.edu.tglima.locadora.repository.ClienteRepository;
import br.edu.tglima.locadora.util.ClienteUtil;
import br.edu.tglima.locadora.util.FacesUtil;
import br.edu.tglima.locadora.validators.ClienteValidator;

@Named
@RequestScoped
public class CadCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private Cliente novoCliente;

	@Inject
	private ClienteValidator validator;

	@Inject
	private ClienteRepository repository;

	public void cadastrar() {

		if (validarCadastro()) {
			this.novoCliente = ClienteUtil.fmtToSave(novoCliente);
			try {
				repository.salvarNovo(this.novoCliente);
				FacesUtil.enviarMsgSucesso(null, "Cliente cadastrado com sucesso!");
				this.novoCliente = null;
			} catch (Exception e) {
				System.out.println("Causa: " + e.getCause());
				FacesUtil.enviarMsgErro(null, "Erro, cliente não cadastrado!");
				FacesUtil.exibirAlerta(null, e.getMessage());
			}

		}

	}

	private boolean validarCadastro() {
		boolean cadValido = false;
		if (validator.idadeParaDirigir(this.novoCliente.getDataNasc())) {
			if (validator.cnhNaValidade(this.novoCliente.getValidadeHab())) {
				cadValido = true;
			} else {
				FacesUtil.enviarMsgErro(null, "Habilitação vencida!");
			}
		} else {
			FacesUtil.enviarMsgErro(null,
					"A idade do cliente é inválida! Só serão aceitas idades entre 23 e 70 anos.");
		}

		return cadValido;
	}

	// Getters de acesso aos Enums
	public OpGeneros[] getGeneros() {
		return OpGeneros.values();
	}

	public Cliente getNovoCliente() {
		return novoCliente;
	}
}
