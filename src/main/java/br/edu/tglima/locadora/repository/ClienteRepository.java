package br.edu.tglima.locadora.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.tglima.locadora.models.pessoa.Cliente;

public class ClienteRepository extends AbstractRepository<Cliente> {

	public ClienteRepository() {
		super(Cliente.class);
	}

	@Inject
	private EntityManager entityManager;

	public List<Cliente> buscaPorNome(String nome, String sobrenome) throws Exception {
		String sql = "SELECT c from Cliente c WHERE c.nome LIKE :nome OR c.sobrenome LIKE :sobrenome";
		TypedQuery<Cliente> query = entityManager.createQuery(sql, Cliente.class);
		query.setParameter("nome", "%" + nome.toLowerCase() + "%");
		query.setParameter("sobrenome", "%" + sobrenome.toLowerCase() + "%");
		return query.getResultList();
	}

	public Cliente buscarPorHabilitacao(String habilitacao) throws Exception {
		String sql = "SELECT c FROM Cliente c WHERE c.habilitacao = :habilitacao";
		TypedQuery<Cliente> query = entityManager.createQuery(sql, Cliente.class);
		query.setParameter("habilitacao", habilitacao);
		return query.getSingleResult();
	}

}
