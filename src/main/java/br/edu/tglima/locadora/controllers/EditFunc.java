package br.edu.tglima.locadora.controllers;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.edu.tglima.locadora.models.pessoa.Funcionario;
import br.edu.tglima.locadora.models.pessoa.OpGeneros;
import br.edu.tglima.locadora.models.pessoa.TiposCargo;
import br.edu.tglima.locadora.repository.FuncionarioRepository;
import br.edu.tglima.locadora.util.FuncUtil;

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
		funcEmEdicao = repository.buscarPorId(2017001l);
	}

	public void salvarEdicao() {
		funcEmEdicao = FuncUtil.fmtFuncToSave(funcEmEdicao);
		funcEmEdicao = repository.salvarEdicao(funcEmEdicao);
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
