package br.edu.tglima.locadora.controllers;

import static br.edu.tglima.locadora.util.FacesUtil.enviarMsgErro;
import static br.edu.tglima.locadora.util.Util.fmtToSave;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.tglima.locadora.models.pessoa.Cliente;
import br.edu.tglima.locadora.models.pessoa.OpGeneros;
import br.edu.tglima.locadora.repository.ClienteRepository;
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
			this.novoCliente = fmtToSave(novoCliente);
			try {
				repository.salvarNovo(this.novoCliente);
				FacesUtil.enviarMsgSucesso("Cliente cadastrado com sucesso!");
				this.novoCliente = null;
			} catch (Exception e) {
				enviarMsgErro("Erro, cliente não cadastrado!");
				if (e.getMessage().contains("ConstraintViolationException")) {
					enviarMsgErro("O Nº de Registro da CNH informada já pertence a outra pessoa.");
				} else {
					enviarMsgErro(e.getMessage());
				}

			}

		}

	}

	private boolean validarCadastro() {
		boolean cadValido = false;
		if (validator.idadeParaDirigir(this.novoCliente.getDataNasc())) {
			if (validator.cnhNaValidade(this.novoCliente.getValidadeHab())) {
				cadValido = true;
			} else {
				enviarMsgErro("Habilitação vencida!");
			}
		} else {
			enviarMsgErro("A idade do cliente é inválida! Só serão aceitas idades entre 23 e 70 anos.");
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
