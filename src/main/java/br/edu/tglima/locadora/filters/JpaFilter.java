package br.edu.tglima.locadora.filters;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(servletNames = { "Faces Servlet" })
public class JpaFilter implements Filter {

	private final String PERSISTENCE_UNIT_NAME = "LocadoraPU";
	private EntityManagerFactory entityManagerFactory;

	@Override
	public void destroy() {

		/*
		 * Encerramos totalmente o processo de conexão com BD destruindo sua fábrica de
		 * conexões.
		 */
		this.entityManagerFactory.close();

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		/* CRIANDO UM ENTITYMANAGER */
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();

		/* Adicionamos o entityManager na requisição */
		request.setAttribute("entityManager", entityManager);

		/* Iniciamos uma transação */
		entityManager.getTransaction().begin();

		/* Iniciamos o Faces Servlet */
		chain.doFilter(request, response);

		try {

			/* Se tudo correu bem, commit a operação */
			entityManager.getTransaction().commit();

		} catch (Exception e) {

			/* Caso ocorra algum erro, executamos um rollback para preservar os dados */
			if (entityManager.isOpen()) {
				entityManager.getTransaction().rollback();
			}

		} finally {

			/*
			 * No final da operação, indiferente se ele teve sucesso ou não, encerramos o
			 * entityManager
			 */
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}

	}

	@Override
	public void init(FilterConfig config) throws ServletException {

		this.entityManagerFactory = Persistence.createEntityManagerFactory(this.PERSISTENCE_UNIT_NAME);

	}

}
