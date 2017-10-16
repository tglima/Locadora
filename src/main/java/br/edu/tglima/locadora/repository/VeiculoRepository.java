package br.edu.tglima.locadora.repository;

import javax.persistence.TypedQuery;

import br.edu.tglima.locadora.models.veiculo.Veiculo;
import br.edu.tglima.locadora.util.JpaUtil;

public class VeiculoRepository extends AbstractRepository<Veiculo> {

	public VeiculoRepository() {
		super(Veiculo.class);
		entityManager = JpaUtil.getEntityManager();
	}

	public Veiculo buscarPorPlaca(String placa) throws Exception {
		String sql = "SELECT v FROM Veiculo v WHERE v.placa = :placa";
		TypedQuery<Veiculo> query = entityManager.createQuery(sql, Veiculo.class);
		query.setParameter("placa", placa);
		return query.getSingleResult();
	}

}
