package br.edu.tglima.locadora.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import br.edu.tglima.locadora.models.pessoa.Cliente;
import br.edu.tglima.locadora.util.ClienteUtil;
import br.edu.tglima.locadora.util.FacesUtil;
import br.edu.tglima.locadora.util.JpaUtil;

public class ClienteRepository {

	private EntityManager entityManager;

	public boolean salvarNovo(Cliente novoCliente) {
		novoCliente = ClienteUtil.fmtToSave(novoCliente);

		entityManager = JpaUtil.getEntityManagerRequest();
		Cliente clienteJaCadastrado = buscarPorHabilitacao(novoCliente.getHabilitacao());

		if (clienteJaCadastrado != null) {
			FacesUtil.enviarMsgErro(null, "Cadastro não realizado, já existe um cliente com " + "este número de habilitação nos registros.");
			return false;
		} else {
			try {
				entityManager.persist(novoCliente);
				FacesUtil.enviarMsgSucesso(null, "Cliente cadastrado com sucesso!");
				return true;
			} catch (Exception e) {
				FacesUtil.enviarMsgErro(null, "Cliente não cadastrado, erro durante a operação!");
				e.printStackTrace();
				return false;
			}
		}

	}

	public Cliente salvarEdicao(Cliente clienteEditado) {
		entityManager = JpaUtil.getEntityManagerRequest();
		clienteEditado = ClienteUtil.fmtToSave(clienteEditado);

		try {
			entityManager.merge(clienteEditado);
			FacesUtil.enviarMsgSucesso(null, "As edições feitas foram salvas com sucesso!");
		} catch (Exception e) {
			FacesUtil.enviarMsgErro(null, "Erro, as alterações feitas podem não ter sido salvas!");
			e.printStackTrace();
		}

		return clienteEditado;
	}

	public Cliente buscarPorHabilitacao(Long habilitacao) {
		entityManager = JpaUtil.getEntityManagerRequest();
		String sql = "SELECT c FROM Cliente c WHERE c.habilitacao = :habilitacao";
		TypedQuery<Cliente> query = entityManager.createQuery(sql, Cliente.class);
		query.setParameter("habilitacao", habilitacao);

		try {
			return query.getSingleResult();

		} catch (NoResultException e) {
			return null;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public Cliente buscarPorId(Long id) {
		entityManager = JpaUtil.getEntityManagerRequest();
		
		try {
			return entityManager.find(Cliente.class, id);
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public List<Cliente> buscaPorNome(String nome, String sobrenome) {
		entityManager = JpaUtil.getEntityManagerRequest();
		String sql = "SELECT c from Cliente c WHERE c.nome LIKE :nome AND c.sobrenome LIKE :sobrenome";
		TypedQuery<Cliente> query = entityManager.createQuery(sql, Cliente.class);
		query.setParameter("nome", "%" + nome.toLowerCase() + "%");
		query.setParameter("sobrenome", "%" + sobrenome.toLowerCase() + "%");

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Cliente> listarTodos() {
		entityManager = JpaUtil.getEntityManagerRequest();
		TypedQuery<Cliente> query = entityManager.createQuery("SELECT c from Cliente c", Cliente.class);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
