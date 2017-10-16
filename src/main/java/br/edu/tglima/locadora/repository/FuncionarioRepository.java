package br.edu.tglima.locadora.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import br.edu.tglima.locadora.models.pessoa.Funcionario;
import br.edu.tglima.locadora.util.JpaUtil;

public class FuncionarioRepository extends AbstractRepository<Funcionario> {

	public FuncionarioRepository() {
		super(Funcionario.class);
		entityManager = JpaUtil.getEntityManager();
	}

	public Funcionario buscarPorCPF(String cpf) throws Exception {
		String sql = "SELECT f FROM Funcionario f WHERE f.cpf = :cpf";
		TypedQuery<Funcionario> query = entityManager.createQuery(sql, Funcionario.class);
		query.setParameter("cpf", cpf);
		return query.getSingleResult();
	}

	public List<Funcionario> buscaPorNome(String nome, String sobrenome) throws Exception {
		String sql = "SELECT f from Funcionario f WHERE f.nome LIKE :nome AND f.sobrenome LIKE :sobrenome";
		TypedQuery<Funcionario> query = entityManager.createQuery(sql, Funcionario.class);
		query.setParameter("nome", "%" + nome.toLowerCase() + "%");
		query.setParameter("sobrenome", "%" + sobrenome.toLowerCase() + "%");
		return query.getResultList();
	}

}
