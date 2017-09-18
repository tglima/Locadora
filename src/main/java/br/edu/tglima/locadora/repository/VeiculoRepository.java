package br.edu.tglima.locadora.repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.tglima.locadora.models.veiculo.Veiculo;
import br.edu.tglima.locadora.util.FacesUtil;
import br.edu.tglima.locadora.util.JpaUtil;
import br.edu.tglima.locadora.util.VeiculoUtil;

public class VeiculoRepository {

	@Inject
	Veiculo veiculo;

	EntityManager entityManager = JpaUtil.getEntityManager();

	public void cadastrarNovo(Veiculo novoVeiculo) {
		novoVeiculo = VeiculoUtil.setDefaultValues(novoVeiculo);
		novoVeiculo = VeiculoUtil.fmtVeicToSave(novoVeiculo);
		veiculo = null;

		veiculo = buscarPorPlaca(novoVeiculo.getPlaca());

		if (veiculo != null) {

			FacesUtil.enviarMsgErro(null, "Cadastro não realizado, já existe um veículo com a placa "
					+ novoVeiculo.getPlaca() + " nos registros.");

		} else {

			if (!entityManager.isOpen()) {
				entityManager = JpaUtil.getEntityManager();
			}

			try {
				entityManager.getTransaction().begin();
				entityManager.persist(novoVeiculo);
				entityManager.getTransaction().commit();
				FacesUtil.enviarMsgSucesso(null, "Veículo cadastrado com sucesso!");
			} catch (Exception e) {
				FacesUtil.enviarMsgErro(null,
						"Erro ao realizar o cadastro, as informações fornecidas não foram salvas nos registros.");
				e.printStackTrace();
				if (entityManager.isOpen()) {
					entityManager.getTransaction().rollback();
				}
			}

			finally {
				if (entityManager.isOpen()) {
					entityManager.close();
				}
			}
		}

	}

	public Veiculo buscarPorPlaca(String placa) {
		Veiculo veicBuscado = null;

		if (!entityManager.isOpen()) {
			entityManager = JpaUtil.getEntityManager();
		}

		try {
			entityManager.getTransaction().begin();
			String sql = "SELECT v FROM Veiculo v WHERE v.placa = :placa";
			TypedQuery<Veiculo> query = entityManager.createQuery(sql, Veiculo.class);
			query.setParameter("placa", placa);
			veicBuscado = query.getSingleResult();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		}

		if (entityManager.isOpen()) {
			entityManager.close();
		}
		return veicBuscado;
	}

}
