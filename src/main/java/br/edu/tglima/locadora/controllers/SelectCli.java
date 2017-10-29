package br.edu.tglima.locadora.controllers;

import static br.edu.tglima.locadora.util.FacesUtil.enviarMsgErro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.tglima.locadora.models.pessoa.Cliente;
import br.edu.tglima.locadora.repository.ClienteRepository;

@Named
@ApplicationScoped
public class SelectCli implements Serializable {
	private static final long serialVersionUID = 1L;

	/*
	 * TODO Trocar o scopo por outro mais adequado.
	 *
	 */

	@Inject
	private ClienteRepository repository;;

	private List<Cliente> cliEncontrados = new ArrayList<Cliente>();
	private Cliente selectedClient;
	private String searchParam;
	private boolean resultIsEmpty;
	private byte tipoDeBusca;

	@PostConstruct
	public void init() {
		tipoDeBusca = 0;
		resultIsEmpty = false;
	}

	public void clearInput() {
		this.searchParam = null;
	}

	public void buscarPelaCNH() {
		cliEncontrados.clear();

		try {
			cliEncontrados.add(repository.buscarPorHabilitacao(this.searchParam));
			resultIsEmpty = false;
		} catch (Exception e) {
			if (e.getMessage().contains("No entity found for query")) {
				resultIsEmpty = true;
			} else {
				enviarMsgErro("Erro ao realizar a pesquisa! " + e.getMessage());
			}
		}

	}

	public void buscarPorNome() {
		cliEncontrados.clear();
		try {
			cliEncontrados = repository.buscaPorNome(searchParam.toLowerCase(), searchParam.toLowerCase());
			this.resultIsEmpty = false;
		} catch (Exception e) {
			enviarMsgErro("Erro ao realizar a pesquisa! " + e.getMessage());
		}

		if (cliEncontrados != null && cliEncontrados.size() < 1) {
			resultIsEmpty = true;
		}
	}

	public List<Cliente> getCliEncontrados() {
		return cliEncontrados;
	}

	public boolean isResultIsEmpty() {
		return resultIsEmpty;
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
