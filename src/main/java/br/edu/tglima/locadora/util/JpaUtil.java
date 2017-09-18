package br.edu.tglima.locadora.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class JpaUtil {
	private static final String PERSISTENCE_UNIT_NAME = "LocadoraPU";
	
	private static ThreadLocal<EntityManager> threadEntityManager = 
					new ThreadLocal<EntityManager>();
	
	private static EntityManagerFactory factory;
	
	
	private JpaUtil(){}
	
	public static EntityManagerFactory getEntityManagerFactory() {
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		}
		return factory;
	}
	
	public static EntityManager getEntityManager(){		
		EntityManager entityManager = threadEntityManager.get();
		
		if (entityManager == null || !entityManager.isOpen()) {
			entityManager = getEntityManagerFactory().createEntityManager();
			threadEntityManager.set(entityManager);
		}
		
		
		return entityManager;
	}
	

	public static void shutdown() {
		if (factory != null) {
			factory.close();
		}
	}

}
