package br.edu.tglima.locadora.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.tglima.locadora.models.locacao.Locacao;
import br.edu.tglima.locadora.models.locacao.LocacaoStatus;

public class LocacaoRepository extends AbstractRepository<Locacao> {

	public LocacaoRepository() {
		super(Locacao.class);
	}

	private @Inject EntityManager entityManager;

	public Locacao buscarCliente(Long idCli, LocacaoStatus status) throws Exception {
		String sql = "SELECT l FROM Locacao l WHERE l.cli.id = :id AND l.status = :status ";
		TypedQuery<Locacao> query = entityManager.createQuery(sql, Locacao.class);
		query.setParameter("id", idCli);
		query.setParameter("status", status);
		return query.getSingleResult();
	}

	public List<Locacao> buscarPorStatus(LocacaoStatus status) throws Exception {
		String sql = "SELECT l FROM Locacao l WHERE l.status = :status";
		TypedQuery<Locacao> query = entityManager.createQuery(sql, Locacao.class);
		query.setParameter("status", status);
		return query.getResultList();
	}

	public List<Locacao> buscarPelaCNH(String cnhCliente) throws Exception {
		String sql = "SELECT l FROM Locacao l WHERE l.cli.habilitacao = :cnhCliente";
		TypedQuery<Locacao> query = entityManager.createQuery(sql, Locacao.class);
		query.setParameter("cnhCliente", cnhCliente);
		return query.getResultList();
	}

}
