package br.edu.tglima.locadora.controllers;

import static br.edu.tglima.locadora.util.FacesUtil.enviarMsgErro;
import static br.edu.tglima.locadora.util.FacesUtil.enviarMsgSucesso;
import static br.edu.tglima.locadora.util.FuncUtil.fmtFuncToSave;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.tglima.locadora.models.pessoa.Funcionario;
import br.edu.tglima.locadora.models.pessoa.OpGeneros;
import br.edu.tglima.locadora.models.pessoa.TiposCargo;
import br.edu.tglima.locadora.repository.FuncionarioRepository;

@Named
@RequestScoped
public class EditFunc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private Funcionario funcEmEdicao;

	@Inject
	private FuncionarioRepository repository;

	@PostConstruct
	public void init() {
		/*
		 * TODO O Funcionário a ser editado será passado por parâmetro e carregado aqui.
		 * Por enquanto será carregado um funcionário específico do BD.
		 */
		try {
			funcEmEdicao = repository.buscarPorId(0l);

		} catch (Exception e) {
			System.out.println("Erro lançado: " + e.getCause());
		}
	}

	public void salvarEdicao() {
		try {
			repository.salvarEdicao(fmtFuncToSave(this.funcEmEdicao));
			enviarMsgSucesso(null, "Alterações salvas com sucesso!");
			init();
		} catch (Exception e) {
			if (e.getMessage().contains("ConstraintViolationException")) {
				enviarMsgErro(null, "As alterações não foram salvas!"
						+ "O Nº do CPF informado já pertence a outra pessoa.");

			} else {
				enviarMsgErro(null, "Erro desconhecido ao salvar as alterações." + e.getMessage());
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

	public Funcionario getFuncEmEdicao() {
		return funcEmEdicao;
	}

}
