package br.edu.tglima.locadora.service;

import static br.edu.tglima.locadora.util.FacesUtil.enviarMsgErro;
import static br.edu.tglima.locadora.util.FacesUtil.enviarMsgSucesso;
import static br.edu.tglima.locadora.util.TempoUtil.calcDifDias;
import static br.edu.tglima.locadora.util.TempoUtil.convParaLocalDate;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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

	public Cliente buscarPeloId(Long id) {
		Cliente c = null;

		try {
			c = repository.buscarPorId(id);
		} catch (Exception e) {
			System.err.println("Erro ao realizar a pesquisa");
			c = null;
		}

		return c;
	}

	private Cliente fmtToSave(Cliente c) {
		c.setNome(c.getNome().toLowerCase());
		c.setSobrenome(c.getSobrenome().toLowerCase());

		if (c.getDataCadastro() == null) {
			c.setDataCadastro(new Date());
		}
		return c;
	}

	public boolean cnhVencida(Long idCLi) {
		Cliente cli = buscarPeloId(idCLi);

		LocalDate validadeCnh = convParaLocalDate(cli.getValidadeHab()).plusDays(15);
		LocalDate today = LocalDate.now();
		Long dif = calcDifDias(today, validadeCnh);

		if (dif < 1) {
			return true;
		}

		return false;
	}
}
