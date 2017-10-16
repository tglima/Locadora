package br.edu.tglima.locadora.controllers;

import static br.edu.tglima.locadora.util.ClienteUtil.fmtToSave;
import static br.edu.tglima.locadora.util.FacesUtil.enviarMsgErro;
import static br.edu.tglima.locadora.util.FacesUtil.enviarMsgSucesso;

import java.io.Serializable;

import javax.annotation.PostConstruct;
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
public class EditCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteRepository repository;

	@Inject
	private ClienteValidator validator;

	@Inject
	private Cliente clienteEmEdicao;

	@PostConstruct
	public void init() {
		/*
		 * TODO O cliente a ser editado será passado por parâmetro e carregado aqui. Por
		 * enquanto será carregado um cliente específico do BD.
		 */

		try {
			clienteEmEdicao = repository.buscarPorId(102l);
		} catch (Exception e) {
			System.out.println("Erro lançado: " + e.getCause());
		}

	}

	public void salvarEdicao() {
		if (validarCadastro()) {
			try {
				repository.salvarEdicao(fmtToSave(this.clienteEmEdicao));
				enviarMsgSucesso(null, "Alterações salvas com sucesso!");

			} catch (Exception e) {

				if (e.getMessage().contains("ConstraintViolationException")) {
					init();
					enviarMsgErro(null,
							"As alterações não foram salvas! O Nº Registro da CNH informado já pertence a outra pessoa.");
				} else {
					enviarMsgErro(null, "Erro desconhecido ao salvar as alterações." + e.getMessage());
				}

			}

		}

	}

	private boolean validarCadastro() {
		boolean cadValido = false;
		if (validator.idadeParaDirigir(this.clienteEmEdicao.getDataNasc())) {
			if (validator.cnhNaValidade(this.clienteEmEdicao.getValidadeHab())) {
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

	public Cliente getClienteEmEdicao() {
		return clienteEmEdicao;
	}

	// Getters de acesso aos Enums
	public OpGeneros[] getGeneros() {
		return OpGeneros.values();
	}

}
