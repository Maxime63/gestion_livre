package com.gestion.livre.service;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.gestion.livre.metier.ConstanteMetier;
import com.gestion.livre.persistence.TAuteur;
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
	public List<TAuteur> getAllAuteur(){
		System.out.println("Get All Auteur !");
		
		Query q = new Query(ConstanteMetier.ENTITY_TAUTEUR);
		PreparedQuery pq = datastoreService.prepare(q);
		
		List<TAuteur> auteurs = new ArrayList<TAuteur>();
		
		for (Entity auteur : pq.asIterable()) {
			Integer numero = new Integer(1);
			String nom = (String) auteur.getProperty(ConstanteMetier.TAUTEUR_COLUMN_NOM);			
			String prenom = (String) auteur.getProperty(ConstanteMetier.TAUTEUR_COLUMN_PRENOM);			
			String domicile = (String) auteur.getProperty(ConstanteMetier.TAUTEUR_COLUMN_DOMICILE);
			TAuteur newAuteur = new TAuteur(auteur.getKey().getId(), numero, nom, prenom, domicile);
			
			auteurs.add(newAuteur);
		}
		
		return auteurs;
	}
}
