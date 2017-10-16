package br.edu.tglima.locadora.repository;

import java.util.List;

public interface IRepository<T> {

	public void salvarNovo(T entity) throws Exception;;

	public void salvarEdicao(T entity) throws Exception;;

	public T buscarPorId(Long id) throws Exception;

	public void remover(T entity) throws Exception;

	public List<T> listarTodos() throws Exception;
}
