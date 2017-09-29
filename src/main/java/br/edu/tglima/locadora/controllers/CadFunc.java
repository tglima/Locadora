package br.edu.tglima.locadora.controllers;

import java.io.Serializable;
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
public class CadFunc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private Funcionario novoFunc;

	@Inject
	private FuncionarioRepository repository;
	
	public void cadastrar() {
		this.novoFunc = FuncUtil.fmtFuncToSave(novoFunc);
		if (repository.salvarNovo(this.novoFunc)) {
			this.novoFunc = null;
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
