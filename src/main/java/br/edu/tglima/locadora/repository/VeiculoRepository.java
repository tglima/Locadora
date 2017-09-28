package br.edu.tglima.locadora.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import br.edu.tglima.locadora.models.veiculo.Veiculo;
import br.edu.tglima.locadora.util.FacesUtil;
import br.edu.tglima.locadora.util.JpaUtil;
import br.edu.tglima.locadora.util.VeiculoUtil;

public class VeiculoRepository {

	private EntityManager entityManager;

	public boolean salvarNovo(Veiculo novoVeiculo) {
		novoVeiculo = VeiculoUtil.setDefaultValues(novoVeiculo);
		novoVeiculo = VeiculoUtil.fmtVeicToSave(novoVeiculo);

		entityManager = JpaUtil.getEntityManagerRequest();
		Veiculo veicJaCadastrado = buscarPorPlaca(novoVeiculo.getPlaca());

		if (veicJaCadastrado != null) {
			FacesUtil.enviarMsgErro(null,
					"Cadastro não realizado, já existe um veículo com esta placa " + novoVeiculo.getPlaca() + " nos registros.");
			return false;
		} else {
			try {
				entityManager.persist(novoVeiculo);
				FacesUtil.enviarMsgSucesso(null, "Veículo cadastrado com sucesso!");
				return true;
			} catch (Exception e) {
				FacesUtil.enviarMsgErro(null, "Veículo não cadastrado, erro durante a operação!");
				e.printStackTrace();
				return false;
			}
		}
	}

	public Veiculo salvarEdicao(Veiculo veicEditado) {
		entityManager = JpaUtil.getEntityManagerRequest();
		veicEditado = VeiculoUtil.fmtVeicToSave(veicEditado);
		try {
			entityManager.merge(veicEditado);
			FacesUtil.enviarMsgSucesso(null, "As edições feitas foram salvas com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.enviarMsgErro(null, "Erro, as alterações feitas podem não ter sido salvas!");
		}

		return veicEditado;
	}

	public Veiculo buscarPorPlaca(String placa) {
		entityManager = JpaUtil.getEntityManagerRequest();
		String sql = "SELECT v FROM Veiculo v WHERE v.placa = :placa";
		TypedQuery<Veiculo> query = entityManager.createQuery(sql, Veiculo.class);
		try {
			query.setParameter("placa", placa);
			return query.getSingleResult();

		} catch (NoResultException e) {
			return null;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Veiculo buscarPorId(Long id) {
		entityManager = JpaUtil.getEntityManagerRequest();

		try {
			return entityManager.find(Veiculo.class, id);

		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
