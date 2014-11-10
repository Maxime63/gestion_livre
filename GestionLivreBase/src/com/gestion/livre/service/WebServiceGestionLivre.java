package com.gestion.livre.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.gestion.livre.metier.ConstanteMetier;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

@WebService
public class WebServiceGestionLivre {
	@WebMethod
	public Integer createAuteur(Integer numero, String nom, String prenom, String domicile){
		DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
		Entity monAuteur = new Entity(ConstanteMetier.ENTITY_TAUTEUR);
		monAuteur.setProperty(ConstanteMetier.TAUTEUR_COLUMN_NOM, nom);
		monAuteur.setProperty(ConstanteMetier.TAUTEUR_COLUMN_PRENOM, prenom);
		monAuteur.setProperty(ConstanteMetier.TAUTEUR_COLUMN_DOMICILE, domicile);
		monAuteur.setProperty(ConstanteMetier.TAUTEUR_COLUMN_NUM, numero);
		
		datastoreService.put(monAuteur);
		
		// !!!!!!!!!! gérer les numéros d'auteur dynamiquement !!!!!!!!!!!
		
		return numero;
	}
}
