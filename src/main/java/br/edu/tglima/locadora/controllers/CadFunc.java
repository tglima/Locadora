package br.edu.tglima.locadora.controllers;

import static br.edu.tglima.locadora.util.FacesUtil.enviarMsgErro;
import static br.edu.tglima.locadora.util.FacesUtil.enviarMsgSucesso;
import static br.edu.tglima.locadora.util.Util.fmtToSave;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.tglima.locadora.models.pessoa.Funcionario;
import br.edu.tglima.locadora.models.pessoa.OpGeneros;
import br.edu.tglima.locadora.models.pessoa.TiposCargo;
import br.edu.tglima.locadora.repository.FuncionarioRepository;

@Named
@RequestScoped
public class CadFunc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private Funcionario novoFunc;

	@Inject
	private FuncionarioRepository repository;

	public void cadastrar() {
		this.novoFunc = fmtToSave(this.novoFunc);
		try {
			repository.salvarNovo(this.novoFunc);
			enviarMsgSucesso("Funcionário cadastrado com sucesso!");
			this.novoFunc = null;
		} catch (Exception e) {
			enviarMsgErro("Erro, funcionário não cadastrado!");
			if (e.getMessage().contains("ConstraintViolationException")) {
				enviarMsgErro("O Nº do CPF informado já pertence a outra pessoa.");
			} else {
				enviarMsgErro(e.getMessage());
			}

		}

	}

	// Getters de acesso aos Enums
	public OpGeneros[] getGeneros() {
		return OpGeneros.values();
	}

	public TiposCargo[] getCargos() {
		return TiposCargo.values();
	}

	// Getter e Setters Padrões
	public Funcionario getNovoFunc() {
		return novoFunc;
	}

}
