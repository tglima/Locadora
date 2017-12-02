package br.edu.tglima.locadora.service;

import static br.edu.tglima.locadora.util.FacesUtil.enviarMsgErro;
import static br.edu.tglima.locadora.util.FacesUtil.enviarMsgSucesso;
import static br.edu.tglima.locadora.util.Util.fmtToSave;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.edu.tglima.locadora.models.pessoa.Funcionario;
import br.edu.tglima.locadora.repository.FuncionarioRepository;

public class FuncionarioService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private FuncionarioRepository repository;

	public Funcionario cadastrar(Funcionario func) {

		try {
			repository.salvarNovo(fmtToSave(func));
			enviarMsgSucesso("Funcionário cadastrado com sucesso!");
			func = null;
		} catch (Exception e) {

			enviarMsgErro("Erro, funcionário não cadastrado!");

			if (e.getMessage().contains("ConstraintViolationException")) {

				enviarMsgErro("O Nº do CPF informado já pertence a outra pessoa.");

			} else {

				enviarMsgErro(e.getMessage());
			}
		}
		return func;
	}

	public Funcionario salvarEdicao(Funcionario func) {
		try {
			repository.salvarEdicao(fmtToSave(func));
			enviarMsgSucesso("Alterações salvas com sucesso!");

		} catch (Exception e) {
			enviarMsgErro("Erro, as alterações não foram salvas!");
			if (e.getMessage().contains("ConstraintViolationException")) {
				enviarMsgErro("O Nº do CPF informado já pertence a outra pessoa.");
			} else {
				enviarMsgErro(e.getMessage());
			}
		}

		return func;
	}

	public List<Funcionario> buscarPorCpf(String cpf) {
		List<Funcionario> result = new ArrayList<Funcionario>();

		try {
			result.add(repository.buscarPorCPF(cpf));
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

	public List<Funcionario> buscarPorNome(String name) {
		List<Funcionario> resultado = new ArrayList<Funcionario>();

		try {
			resultado = repository.buscaPorNome(name.toLowerCase(), name.toLowerCase());

		} catch (Exception e) {
			enviarMsgErro("Erro ao realizar a pesquisa! " + e.getMessage());
			resultado = null;
		}

		return resultado;

	}

}
