package br.edu.tglima.locadora.util;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;

public final class JpaUtil {

	private static final String PERSISTENCE_UNIT_NAME = "LocadoraPU";

	private static EntityManagerFactory entityManagerFactory;

	public JpaUtil() {
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		if (entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		}
		return entityManagerFactory;
	}

	public static EntityManager getEntityManagerRequest() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

		return (EntityManager) request.getAttribute("entityManager");
	}

	public static void shutdown() {
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}
	}

}
