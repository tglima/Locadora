package br.edu.tglima.locadora.repository;

import javax.persistence.TypedQuery;

import br.edu.tglima.locadora.models.locacao.Cancelamento;

public class CancLocacaoRepository extends AbstractRepository<Cancelamento> {

	public CancLocacaoRepository() {
		super(Cancelamento.class);
	}

	public Cancelamento buscarCancPorIdLocacao(Long idLoc) {
		String sql = "SELECT c FROM Cancelamento c WHERE c.locacao.id = :idLoc";
		TypedQuery<Cancelamento> query = entityManager.createQuery(sql, Cancelamento.class);
		query.setParameter("idLoc", idLoc);
		return query.getSingleResult();
	}

}
