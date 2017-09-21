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
	Veiculo veicJaCadastrado;

	EntityManager entityManager;

	public void cadastrarNovo(Veiculo novoVeiculo) {
		entityManager = JpaUtil.getEntityManagerRequest();
		veicJaCadastrado = null;

		novoVeiculo = VeiculoUtil.setDefaultValues(novoVeiculo);
		
		novoVeiculo = VeiculoUtil.fmtVeicToSave(novoVeiculo);

		veicJaCadastrado = buscarPorPlaca(novoVeiculo.getPlaca());

		if (veicJaCadastrado != null) {

			FacesUtil.enviarMsgErro(null, "Cadastro não realizado, já existe um veículo com a placa "
					+ novoVeiculo.getPlaca() + " nos registros.");

		} else {

			try {
				entityManager.persist(novoVeiculo);
				FacesUtil.enviarMsgSucesso(null, "Veículo cadastrado com sucesso!");
			} catch (Exception e) {
				FacesUtil.enviarMsgErro(null,
						"Erro ao realizar o cadastro, as informações fornecidas não foram salvas nos registros.");
				e.printStackTrace();
			}
		}

	}

	public Veiculo buscarPorPlaca(String placa) {
		entityManager = JpaUtil.getEntityManagerRequest();
		Veiculo veicBuscado = null;

		String sql = "SELECT v FROM Veiculo v WHERE v.placa = :placa";

		try {
			TypedQuery<Veiculo> query = entityManager.createQuery(sql, Veiculo.class);
			query.setParameter("placa", placa);
			veicBuscado = query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return veicBuscado;
	}

}
