package br.edu.tglima.locadora.controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.tglima.locadora.models.pessoa.Cliente;
import br.edu.tglima.locadora.service.ClienteService;

@Named
@ViewScoped
public class GeCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteService service;

	private List<Cliente> cliEncontrados;
	private Cliente selectedCli;
	private String searchParam;
	private boolean resultEmpty;
	private byte tipoDeBusca = 0;

	public void clearInput() {
		searchParam = null;
		resultEmpty = false;
	}

	public void buscarPelaCNH() {
		cliEncontrados = service.buscarPelaCNH(searchParam);

		if (cliEncontrados.isEmpty()) {
			resultEmpty = true;
		}
	}

	public void buscarPeloNome() {
		cliEncontrados = service.buscarPeloNome(searchParam);

		if (cliEncontrados.isEmpty()) {
			resultEmpty = true;
		}

	}

	public void salvar() {
		selectedCli = service.salvarEdicao(selectedCli);
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

	public List<Cliente> getCliEncontrados() {
		return cliEncontrados;
	}

	public Cliente getSelectedCli() {
		return selectedCli;
	}

	public void setSelectedCli(Cliente selectedCli) {
		this.selectedCli = selectedCli;
	}

}
