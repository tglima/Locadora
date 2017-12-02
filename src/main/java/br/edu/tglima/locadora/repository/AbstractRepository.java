package br.edu.tglima.locadora.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public abstract class AbstractRepository<T> implements IRepository<T> {

	@Inject
	protected EntityManager entityManager;
	private Class<T> type;

	protected AbstractRepository() {
	}

	protected AbstractRepository(Class<T> type) {
		this.type = type;
	}

	@Override
	public void salvarNovo(T entity) throws Exception {
		this.entityManager.persist(entity);
		this.entityManager.flush();
	}

	@Override
	public void salvarEdicao(T entity) throws Exception {
		this.entityManager.merge(entity);
		this.entityManager.flush();
	}

	@Override
	public T buscarPorId(Long id) throws Exception {
		return this.entityManager.find(type, id);
	}

	@Override
	public void remover(T entity) throws Exception {
		this.entityManager.remove(entity);
		this.entityManager.flush();
	}

	@Override
	public List<T> listarTodos() throws Exception {
		TypedQuery<T> query = this.entityManager.createQuery("SELECT t from " + type.getSimpleName() + " t",
				type);
		return query.getResultList();
	}

}
