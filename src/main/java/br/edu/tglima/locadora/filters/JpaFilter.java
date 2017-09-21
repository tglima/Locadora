package br.edu.tglima.locadora.filters;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import br.edu.tglima.locadora.util.JpaUtil;

@WebFilter(servletNames = { "Faces Servlet" })
public class JpaFilter implements Filter {

	@Override
	public void destroy() {

		/*
		 * Encerramos totalmente o processo de conexão com BD destruindo sua fábrica de
		 * conexões.
		 */
		JpaUtil.shutdown();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
		try {

			/* Adicionamos o entityManager na requisição */
			request.setAttribute("entityManager", entityManager);

			/* Iniciamos uma transação */
			entityManager.getTransaction().begin();

			/* Iniciamos o Faces Servlet */
			chain.doFilter(request, response);

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

	}

}
