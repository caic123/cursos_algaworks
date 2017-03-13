package br.com.vendapedido.util.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


@ApplicationScoped
public class EntityManagerProducer {

	private EntityManagerFactory factory;
	
	public EntityManagerProducer(){
		factory = Persistence.createEntityManagerFactory("VendaPU");
	}
	
	// O resultado da produção desse metodo tem o scopo de requisição
	@Produces @RequestScoped
	public EntityManager createEntyManager(){
		return factory.createEntityManager();
	}
	
	//@Disposes -> quando o escopo de resquisição finalizar, este metodo é chamado, assim fechando o EntityManager
	public void closeEntityManager(@Disposes EntityManager manager){
		
		manager.close();
	}
}
