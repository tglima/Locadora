package br.edu.tglima.locadora.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.tglima.locadora.models.veiculo.OpCategorias;
import br.edu.tglima.locadora.models.veiculo.OpMarcas;
import br.edu.tglima.locadora.models.veiculo.VeicStatus;
import br.edu.tglima.locadora.models.veiculo.Veiculo;

public class VeiculoRepository extends AbstractRepository<Veiculo> {

	public VeiculoRepository() {
		super(Veiculo.class);
	}

	@Inject
	private EntityManager entityManager;

	public Veiculo buscarPorPlaca(String placa) throws Exception {
		String sql = "SELECT v FROM Veiculo v WHERE v.placa = :placa";
		TypedQuery<Veiculo> query = entityManager.createQuery(sql, Veiculo.class);
		query.setParameter("placa", placa);
		return query.getSingleResult();
	}

	public List<Veiculo> buscaPorStatus(VeicStatus status) throws Exception {
		String sql = "SELECT v FROM Veiculo v WHERE v.status = :status";
		TypedQuery<Veiculo> query = entityManager.createQuery(sql, Veiculo.class);
		query.setParameter("status", status);
		return query.getResultList();
	}

	public List<Veiculo> buscarPorMarca(OpMarcas marca, VeicStatus status, boolean statusEqual)
			throws Exception {
		String sql;
		if (statusEqual) {
			sql = "SELECT v FROM Veiculo v WHERE v.marca = :marca AND v.status = :status";
		} else {
			sql = "SELECT v FROM Veiculo v WHERE v.marca = :marca AND v.status != :status";
		}
		TypedQuery<Veiculo> query = entityManager.createQuery(sql, Veiculo.class);
		query.setParameter("marca", marca);
		query.setParameter("status", status);
		return query.getResultList();
	}

	public List<Veiculo> buscaPorCategoria(OpCategorias cat, VeicStatus status, boolean statusEqual)
			throws Exception {
		String sql;
		if (statusEqual) {
			sql = "SELECT v FROM Veiculo v WHERE v.categoria = :categoria AND v.status = :status";
		} else {
			sql = "SELECT v FROM Veiculo v WHERE v.categoria = :categoria AND v.status != :status";
		}

		TypedQuery<Veiculo> query = entityManager.createQuery(sql, Veiculo.class);
		query.setParameter("categoria", cat);
		query.setParameter("status", status);
		return query.getResultList();
	}

	public List<Veiculo> buscaPorParametros(VeicStatus status, OpMarcas marca) throws Exception {
		String sql = "SELECT v FROM Veiculo	v WHERE v.status = :status AND v.marca = :marca";
		TypedQuery<Veiculo> query = entityManager.createQuery(sql, Veiculo.class);
		query.setParameter("status", status);
		query.setParameter("marca", marca);
		return query.getResultList();
	}

	public List<Veiculo> buscaPorParametros(VeicStatus status, OpCategorias cat) throws Exception {
		String sql = "SELECT v FROM Veiculo v WHERE v.status = :status AND v.categoria = :categoria";
		TypedQuery<Veiculo> query = entityManager.createQuery(sql, Veiculo.class);
		query.setParameter("status", status);
		query.setParameter("categoria", cat);
		return query.getResultList();
	}

}
