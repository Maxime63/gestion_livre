package com.gestion.livre.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.gestion.livre.metier.ConstanteMetier;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;


@WebService
public class WebServiceGestionLivre {
	DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
	
	@WebMethod
	public Integer createAuteur(Integer numero, String nom, String prenom, String domicile){
		Entity monAuteur = new Entity(ConstanteMetier.ENTITY_TAUTEUR);
		monAuteur.setProperty(ConstanteMetier.TAUTEUR_COLUMN_NOM, nom);
		monAuteur.setProperty(ConstanteMetier.TAUTEUR_COLUMN_PRENOM, prenom);
		monAuteur.setProperty(ConstanteMetier.TAUTEUR_COLUMN_DOMICILE, domicile);
		monAuteur.setProperty(ConstanteMetier.TAUTEUR_COLUMN_NUM, numero);
		
		datastoreService.put(monAuteur);
		
		System.out.println(monAuteur.getKey());
		return numero;
	}
	
	@WebMethod
	public List<String> getAllAuteur(){
		Query q = new Query(ConstanteMetier.ENTITY_TAUTEUR);
		PreparedQuery pq = datastoreService.prepare(q);
			
		return null;
	}
}
