package br.edu.tglima.locadora.controllers;

import static br.edu.tglima.locadora.util.FacesUtil.enviarMsgErro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.tglima.locadora.models.pessoa.Cliente;
import br.edu.tglima.locadora.repository.ClienteRepository;

@Named
@ApplicationScoped
public class SelectCli implements Serializable {
	private static final long serialVersionUID = 1L;

	// TODO Trocar o scopo por outro mais adequado.

	@Inject
	private ClienteRepository repository;;

	private List<Cliente> cliEncontrados = new ArrayList<Cliente>();
	private Cliente selectedClient;
	private String searchParam;
	private boolean resultEmpty;
	private byte tipoDeBusca = 0;

	public void clearInput() {
		searchParam = null;
		resultEmpty = false;
	}

	public void buscarPelaCNH() {
		cliEncontrados.clear();

		try {
			cliEncontrados.add(repository.buscarPorHabilitacao(this.searchParam));
		} catch (Exception e) {
			if (e.getMessage().contains("No entity found for query")) {
				resultEmpty = true;
			} else {
				enviarMsgErro("Erro ao realizar a pesquisa! " + e.getMessage());
			}
		}

	}

	public void buscarPorNome() {
		cliEncontrados.clear();
		try {
			cliEncontrados = repository.buscaPorNome(searchParam.toLowerCase(), searchParam.toLowerCase());
			if (cliEncontrados.isEmpty()) {
				resultEmpty = true;
			}
		} catch (Exception e) {
			enviarMsgErro("Erro ao realizar a pesquisa! " + e.getMessage());
		}

	}

	public List<Cliente> getCliEncontrados() {
		return cliEncontrados;
	}

	public boolean isResultEmpty() {
		return resultEmpty;
	}

	public Cliente getSelectedClient() {
		return selectedClient;
	}

	public void setSelectedClient(Cliente selectedClient) {
		this.selectedClient = selectedClient;
	}

	public String getSearchParam() {
		return searchParam;
	}

	public void setSearchParam(String searchParam) {
		this.searchParam = searchParam;
	}

	public byte getTipoDeBusca() {
		return tipoDeBusca;
	}

	public void setTipoDeBusca(byte tipoDeBusca) {
		this.tipoDeBusca = tipoDeBusca;
	}

}
