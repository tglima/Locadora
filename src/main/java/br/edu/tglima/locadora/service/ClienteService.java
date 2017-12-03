package br.edu.tglima.locadora.service;

import static br.edu.tglima.locadora.util.FacesUtil.enviarMsgErro;
import static br.edu.tglima.locadora.util.FacesUtil.enviarMsgSucesso;
import static br.edu.tglima.locadora.util.Util.fmtToSave;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.edu.tglima.locadora.models.pessoa.Cliente;
import br.edu.tglima.locadora.repository.ClienteRepository;

public class ClienteService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteRepository repository;

	public Cliente cadastrar(Cliente cli) {
		try {
			repository.salvarNovo(fmtToSave(cli));
			enviarMsgSucesso("Cliente cadastrado com sucesso!");
			cli = null;
		} catch (Exception e) {
			enviarMsgErro("Erro, cliente não cadastrado!");

			if (e.getMessage().contains("ConstraintViolationException")) {

				enviarMsgErro("O Nº de Registro da CNH informada já pertence a outra pessoa.");

			} else {
				enviarMsgErro(e.getMessage());
			}

		}
		return cli;
	}

	public Cliente salvarEdicao(Cliente cli) {
		try {
			repository.salvarEdicao(fmtToSave(cli));
			enviarMsgSucesso("Alterações salvas com sucesso!");
		} catch (Exception e) {
			enviarMsgErro("Erro, as alterações não foram salvas!");

			if (e.getMessage().contains("ConstraintViolationException")) {

				enviarMsgErro("O Nº da CNH informada já pertence a outra pessoa.");

			} else {
				enviarMsgErro(e.getMessage());
			}
		}

		return cli;
	}

	public List<Cliente> buscarPelaCNH(String cnh) {
		List<Cliente> result = new ArrayList<Cliente>();

		try {
			result.add(repository.buscarPorHabilitacao(cnh));
		} catch (Exception e) {
			if (e.getMessage().contains("No entity found for query")) {
				result.clear();
			} else {
				enviarMsgErro("Erro ao realizar a pesquisa! \n" + e.getMessage());
				result = null;
			}
		}
		return result;
	}

	public List<Cliente> buscarPeloNome(String name) {
		List<Cliente> result = new ArrayList<Cliente>();

		try {
			result = repository.buscaPorNome(name.toLowerCase(), name.toLowerCase());
		} catch (Exception e) {
			enviarMsgErro("Erro ao realizar a pesquisa! " + e.getMessage());
			result = null;
		}

		return result;
	}

}
