package com.gestion.livre.service;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.gestion.livre.metier.ConstanteMetier;
import com.gestion.livre.persistence.TAuteur;
import com.gestion.livre.persistence.TLivre;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;


@WebService
public class WebServiceGestionLivre {
	DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
	
	@WebMethod
	public TAuteur createAuteur(String nom, String prenom, String domicile){
		Entity monAuteur = new Entity(ConstanteMetier.ENTITY_TAUTEUR);
		monAuteur.setProperty(ConstanteMetier.TAUTEUR_COLUMN_NOM, nom);
		monAuteur.setProperty(ConstanteMetier.TAUTEUR_COLUMN_PRENOM, prenom);
		monAuteur.setProperty(ConstanteMetier.TAUTEUR_COLUMN_DOMICILE, domicile);
			
		datastoreService.put(monAuteur);
		
		TAuteur auteur = new TAuteur(monAuteur.getKey().getId(), nom, prenom, domicile);
		System.out.println(monAuteur.getKey());
		return auteur;
	}
	
	@WebMethod
	public List<TAuteur> getAllAuteur(){
		System.out.println("Get All Auteur !");
		
		Query q = new Query(ConstanteMetier.ENTITY_TAUTEUR);
		PreparedQuery pq = datastoreService.prepare(q);
		
		List<TAuteur> auteurs = new ArrayList<TAuteur>();
		
		for (Entity auteur : pq.asIterable()) {
			String nom = (String) auteur.getProperty(ConstanteMetier.TAUTEUR_COLUMN_NOM);			
			String prenom = (String) auteur.getProperty(ConstanteMetier.TAUTEUR_COLUMN_PRENOM);			
			String domicile = (String) auteur.getProperty(ConstanteMetier.TAUTEUR_COLUMN_DOMICILE);
			TAuteur newAuteur = new TAuteur(auteur.getKey().getId(), nom, prenom, domicile);
			
			auteurs.add(newAuteur);
		}
		
		return auteurs;
	}
	
	

	@WebMethod
	public TAuteur getAuteur(long id)
	{

		TAuteur newAuteur = null;
		
		Key key = KeyFactory.createKey(ConstanteMetier.ENTITY_TAUTEUR, id);
		try {
			Entity auteur = datastoreService.get(key);
			String nom = (String) auteur.getProperty(ConstanteMetier.TAUTEUR_COLUMN_NOM);			
			String prenom = (String) auteur.getProperty(ConstanteMetier.TAUTEUR_COLUMN_PRENOM);			
			String domicile = (String) auteur.getProperty(ConstanteMetier.TAUTEUR_COLUMN_DOMICILE);
			newAuteur = new TAuteur(auteur.getKey().getId(), nom, prenom, domicile);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
	
		return newAuteur;
	}
	
	/*
	 * 
	 * Pour l'instant return 0 si ok -1 si ko
	 */
	@WebMethod
	public int deleteAuteur(long id)
	{
		TAuteur newAuteur = null;
		int res;
		Key key = KeyFactory.createKey(ConstanteMetier.ENTITY_TAUTEUR, id);
		if(!key.isComplete())
			res= -1;
		else
		{
			datastoreService.delete(key);
			res = 0;
		}
		return res;
	}
	
	@WebMethod
	public TAuteur updateAuteur(String nom, String prenom, String domicile, long id){
		
		Key key = KeyFactory.createKey(ConstanteMetier.ENTITY_TAUTEUR, id);

		datastoreService.delete(key);
		Entity monAuteur = new Entity(ConstanteMetier.ENTITY_TAUTEUR);
		monAuteur.setProperty(ConstanteMetier.TAUTEUR_COLUMN_NOM, nom);
		monAuteur.setProperty(ConstanteMetier.TAUTEUR_COLUMN_PRENOM, prenom);
		monAuteur.setProperty(ConstanteMetier.TAUTEUR_COLUMN_DOMICILE, domicile);
		monAuteur.setProperty(ConstanteMetier.TAUTEUR_COLUMN_ID, key.getId());	
		
		datastoreService.put(monAuteur);
		System.out.println(monAuteur.getKey());
		
		TAuteur auteur = new TAuteur(key.getId(), nom, prenom, domicile);
	
		return auteur;
	}
	
	
	/****************** LIVRES *******************/
	
	
	@WebMethod
	public TLivre createLivre(String titre, double prix, String description){
		Entity monLivre = new Entity(ConstanteMetier.ENTITY_TLIVRE);
		monLivre.setProperty(ConstanteMetier.TLIVRE_COLUMN_TITRE, titre);
		monLivre.setProperty(ConstanteMetier.TLIVRE_COLUMN_PRIX, prix);
		monLivre.setProperty(ConstanteMetier.TLIVRE_COLUMN_DESC, description);
			
		datastoreService.put(monLivre);
		
		TLivre livre = new TLivre(monLivre.getKey().getId(), titre, prix, description);
		System.out.println(monLivre.getKey());
		return livre;
	}
	
	
	@WebMethod
	public List<TLivre> getAllLivres(){
		System.out.println("Get All Livres !");
		
		Query q = new Query(ConstanteMetier.ENTITY_TLIVRE);
		PreparedQuery pq = datastoreService.prepare(q);
		
		List<TLivre> livres = new ArrayList<TLivre>();
		
		for (Entity livre : pq.asIterable()) {
			String titre = (String) livre.getProperty(ConstanteMetier.TLIVRE_COLUMN_TITRE);			
			double prix = (double) livre.getProperty(ConstanteMetier.TLIVRE_COLUMN_PRIX);			
			String desc = (String) livre.getProperty(ConstanteMetier.TLIVRE_COLUMN_DESC);
			TLivre newLivre = new TLivre(livre.getKey().getId(), titre, prix, desc);
			
			livres.add(newLivre);
		}
		
		return livres;
	}
	
	

	@WebMethod
	public TLivre getLivre(long id)
	{

		TLivre newLivre = null;
		
		Key key = KeyFactory.createKey(ConstanteMetier.ENTITY_TLIVRE, id);
		try {
			Entity livre = datastoreService.get(key);
			String titre = (String) livre.getProperty(ConstanteMetier.TLIVRE_COLUMN_TITRE);			
			double prix = (double) livre.getProperty(ConstanteMetier.TLIVRE_COLUMN_PRIX);			
			String desc = (String) livre.getProperty(ConstanteMetier.TLIVRE_COLUMN_DESC);
			newLivre = new TLivre(livre.getKey().getId(), titre, prix,desc);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
	
		return newLivre;
	}

	
	@WebMethod
	public int deleteLivre(long id)
	{
		TLivre newLivre = null;
		int res;
		Key key = KeyFactory.createKey(ConstanteMetier.ENTITY_TLIVRE, id);
		if(!key.isComplete())
			res= -1;
		else
		{
			datastoreService.delete(key);
			res = 0;
		}
		return res;
	}
	
	
	@WebMethod
	public TLivre updateLivre(String titre, double prix, String desc, long id){
		
		Key key = KeyFactory.createKey(ConstanteMetier.ENTITY_TLIVRE, id);

		datastoreService.delete(key);
		Entity monLivre = new Entity(ConstanteMetier.ENTITY_TLIVRE);
		monLivre.setProperty(ConstanteMetier.TLIVRE_COLUMN_TITRE, titre);
		monLivre.setProperty(ConstanteMetier.TLIVRE_COLUMN_PRIX, prix);
		monLivre.setProperty(ConstanteMetier.TLIVRE_COLUMN_DESC, desc);
				
		datastoreService.put(monLivre);
		System.out.println(monLivre.getKey());
		
		TLivre livre = new TLivre(key.getId(), titre, prix, desc);
	
		return livre;
	}
	
}
