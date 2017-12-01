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

import br.edu.tglima.locadora.models.pessoa.Cliente;
import br.edu.tglima.locadora.repository.ClienteRepository;

@Named
@ApplicationScoped
public class GeCliente implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * TODO Trocar escopo da aplicação por outro mais adequado
	 */

	@Inject
	private ClienteRepository repository;
	private List<Cliente> cliEncontrados = new ArrayList<Cliente>();
	private Cliente selectedCli;
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
			cliEncontrados.add(repository.buscarPorHabilitacao(searchParam));
		} catch (Exception e) {
			if (e.getMessage().contains("No entity found for query")) {
				resultEmpty = true;
			} else {
				enviarMsgErro("Erro ao realizar a pesquisa! \n" + e.getMessage());
			}
		}

	}

	public void buscarPeloNome() {
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

	public void salvar() {
		try {
			repository.salvarEdicao(fmtToSave(selectedCli));
			enviarMsgSucesso("Alterações salvas com sucesso!");
		} catch (Exception e) {
			enviarMsgErro("Erro, as alterações não foram salvas!");
			if (e.getMessage().contains("ConstraintViolationException")) {
				enviarMsgErro("O Nº da CNH informada já pertence a outra pessoa.");
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
