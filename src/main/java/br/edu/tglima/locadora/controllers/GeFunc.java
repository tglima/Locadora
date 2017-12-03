package br.edu.tglima.locadora.controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.tglima.locadora.models.pessoa.Funcionario;
import br.edu.tglima.locadora.service.FuncionarioService;

@Named
@ViewScoped
public class GeFunc implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FuncionarioService service;

	private List<Funcionario> funcEncontrados;
	private Funcionario selectedFunc;
	private String searchParam;
	private boolean resultEmpty;
	private byte tipoDeBusca = 0;

	public void clearInput() {
		searchParam = null;
		resultEmpty = false;
	}

	public void buscarPeloCPF() {
		funcEncontrados = service.buscarPorCpf(searchParam);

		if (funcEncontrados.isEmpty()) {
			resultEmpty = true;
		}

	}

	public void buscarPeloNome() {

		funcEncontrados = service.buscarPorNome(searchParam);

		if (funcEncontrados.isEmpty()) {
			resultEmpty = true;
		}
	}

	public void salvar() {
		selectedFunc = service.salvarEdicao(selectedFunc);
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
