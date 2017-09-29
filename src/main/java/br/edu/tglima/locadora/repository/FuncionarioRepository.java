package br.edu.tglima.locadora.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import br.edu.tglima.locadora.models.pessoa.Funcionario;
import br.edu.tglima.locadora.util.FacesUtil;
import br.edu.tglima.locadora.util.JpaUtil;

public class FuncionarioRepository {
	
	private EntityManager entityManager;
	
	public boolean salvarNovo(Funcionario novoFunc){
		entityManager = JpaUtil.getEntityManagerRequest();
		boolean cadastroEfetuado = false;
		
		Funcionario funcJaCadastrado = buscarPorCPF(novoFunc.getCpf());
		
		if (funcJaCadastrado != null) {
			FacesUtil.enviarMsgErro(null, "Cadastro não realizado, já existe um funcionário com este número de CPF "
									+ novoFunc.getCpf() + " nos registros.");
		} else {
			try {
				entityManager.persist(novoFunc);
				FacesUtil.enviarMsgSucesso(null, "Funcionário cadastrado com sucesso!");
				cadastroEfetuado = true;
			} catch (Exception e) {
				FacesUtil.enviarMsgErro(null, "Funcionário não cadastrado, erro durante a operação!");
				e.printStackTrace();
			}

		}
		
		return cadastroEfetuado;
		
	}
	
	public Funcionario salvarEdicao(Funcionario funcEditado){
		entityManager = JpaUtil.getEntityManagerRequest();
		
		try {
			entityManager.merge(funcEditado);
			FacesUtil.enviarMsgSucesso(null, "As edições feitas foram salvas com sucesso!");
		} catch (Exception e) {
			FacesUtil.enviarMsgErro(null, "Erro, as alterações feitas podem não ter sido salvas!");
			e.printStackTrace();			
		}
		
		return funcEditado;
	}
	
	public Funcionario buscarPorId(Long id){
		entityManager = JpaUtil.getEntityManagerRequest();
		
		try {
			return entityManager.find(Funcionario.class, id);
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Funcionario buscarPorCPF(String cpf){
		entityManager = JpaUtil.getEntityManagerRequest();
		String sql = "SELECT f FROM Funcionario f WHERE f.cpf = :cpf";
		TypedQuery<Funcionario> query = entityManager.createQuery(sql, Funcionario.class);
		query.setParameter("cpf", cpf);
		
		try {
			return query.getSingleResult();
			
		} catch (NoResultException e) {
			return null;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	
	public List<Funcionario> buscaPorNome(String nome, String sobrenome){
		entityManager = JpaUtil.getEntityManagerRequest();
		String sql = "SELECT f from Funcionario f WHERE f.nome LIKE :nome AND f.sobrenome LIKE :sobrenome";
		TypedQuery<Funcionario> query = entityManager.createQuery(sql, Funcionario.class);
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
	
	public List<Funcionario> listarTodos(){
		entityManager = JpaUtil.getEntityManagerRequest();
		TypedQuery<Funcionario> query = entityManager.createQuery("SELECT f from Funcionario f", Funcionario.class);

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
