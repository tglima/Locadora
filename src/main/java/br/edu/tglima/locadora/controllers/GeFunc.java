package br.edu.tglima.locadora.controllers;

import static br.edu.tglima.locadora.util.FacesUtil.enviarMsgErro;
import static br.edu.tglima.locadora.util.FacesUtil.enviarMsgSucesso;
import static br.edu.tglima.locadora.util.Util.fmtToSave;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.tglima.locadora.models.pessoa.Funcionario;
import br.edu.tglima.locadora.repository.FuncionarioRepository;
import br.edu.tglima.locadora.util.FacesUtil;

@Named
@ApplicationScoped
public class GeFunc implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * TODO Trocar escopo da aplicação por outro mais adequado
	 */

	@Inject
	private FuncionarioRepository repository;

	private List<Funcionario> funcEncontrados = new ArrayList<Funcionario>();
	private Funcionario selectedFunc;
	private String searchParam;
	private boolean resultEmpty;
	private byte tipoDeBusca = 0;

	public void clearInput() {
		searchParam = null;
		resultEmpty = false;
	}

	public void buscarPeloCPF() {
		System.out.println("CPF Informado: " + searchParam);
		funcEncontrados.clear();
		try {
			funcEncontrados.add(repository.buscarPorCPF(searchParam));
		} catch (Exception e) {
			if (e.getMessage().contains("No entity found for query")) {
				resultEmpty = true;
			} else {
				FacesUtil.enviarMsgErro("Erro ao realizar a pesquisa! \n" + e.getMessage());
			}
		}
	}

	public void buscarPeloNome() {
		funcEncontrados.clear();
		try {
			funcEncontrados = repository.buscaPorNome(searchParam.toLowerCase(), searchParam.toLowerCase());
			if (funcEncontrados.isEmpty()) {
				resultEmpty = true;
			}
		} catch (Exception e) {
			enviarMsgErro("Erro ao realizar a pesquisa! " + e.getMessage());
		}
	}

	public void salvar() {
		try {
			repository.salvarEdicao(fmtToSave(selectedFunc));
			enviarMsgSucesso("Alterações salvas com sucesso!");

		} catch (Exception e) {
			enviarMsgErro("Erro, as alterações não foram salvas!");
			if (e.getMessage().contains("ConstraintViolationException")) {
				enviarMsgErro("O Nº do CPF informado já pertence a outra pessoa.");
			} else {
				enviarMsgErro(e.getMessage());
			}
		}
	}

	public byte getTipoDeBusca() {
		return tipoDeBusca;
	}

	public void setTipoDeBusca(byte tipoDeBusca) {
		this.tipoDeBusca = tipoDeBusca;
	}

	public String getSearchParam() {
		return searchParam;
	}

	public void setSearchParam(String searchParam) {
		this.searchParam = searchParam;
	}

	public boolean isResultEmpty() {
		return resultEmpty;
	}

	public List<Funcionario> getFuncEncontrados() {
		return funcEncontrados;
	}

	public Funcionario getSelectedFunc() {
		return selectedFunc;
	}

	public void setSelectedFunc(Funcionario selectedFunc) {
		this.selectedFunc = selectedFunc;
	}

}
