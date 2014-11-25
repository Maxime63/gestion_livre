package com.gestion.livre.service;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
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


@WebService
public class WebServiceGestionLivre {
	DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
	
	@WebMethod
	@WebResult(name="Auteur")
	public TAuteur createAuteur(@WebParam(name="Nom") String nom, 
								@WebParam(name="Prenom") String prenom, 
								@WebParam(name="Domicile") String domicile){
		Entity monAuteur = new Entity(ConstanteMetier.ENTITY_TAUTEUR);
		monAuteur.setProperty(ConstanteMetier.TAUTEUR_COLUMN_NOM, nom);
		monAuteur.setProperty(ConstanteMetier.TAUTEUR_COLUMN_PRENOM, prenom);
		monAuteur.setProperty(ConstanteMetier.TAUTEUR_COLUMN_DOMICILE, domicile);
			
		datastoreService.put(monAuteur);
		
		TAuteur auteur = new TAuteur(monAuteur.getKey().getId(), nom, prenom, domicile);
		return auteur;
	}
	
	@WebMethod
	@WebResult(name="Auteur")
	public List<TAuteur> getAllAuteur(){
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
	@WebResult(name="Auteur")
	public TAuteur getAuteur(@WebParam(name="Id") long id)
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
	@WebResult(name="IsDeleted")
	public boolean deleteAuteur(@WebParam(name="Id") long id)
	{
		boolean res = false;
		Key auteurKey = KeyFactory.createKey(ConstanteMetier.ENTITY_TAUTEUR, id);
		if(auteurKey.isComplete())
		{
			Query q = new Query(ConstanteMetier.ENTITY_TLIVRE).setAncestor(auteurKey);
			PreparedQuery pq = datastoreService.prepare(q);
			
			for (Entity livre : pq.asIterable()) {
				datastoreService.delete(livre.getKey()); 
			}
			
			datastoreService.delete(auteurKey);
			res = true;
		}
		return res;
	}
	
	@WebMethod
	@WebResult(name="Auteur")
	public TAuteur updateAuteur(@WebParam(name="Nom") String nom, 
								@WebParam(name="Prenom") String prenom, 
								@WebParam(name="Domicile") String domicile, 
								@WebParam(name="Id")long id){
		TAuteur auteur = null;
		
		Key key = KeyFactory.createKey(ConstanteMetier.ENTITY_TAUTEUR, id);

		try {
			Entity monAuteur = datastoreService.get(key);
			monAuteur.setProperty(ConstanteMetier.TAUTEUR_COLUMN_NOM, nom);
			monAuteur.setProperty(ConstanteMetier.TAUTEUR_COLUMN_PRENOM, prenom);
			monAuteur.setProperty(ConstanteMetier.TAUTEUR_COLUMN_DOMICILE, domicile);
			datastoreService.put(monAuteur);
			
			auteur = new TAuteur(key.getId(), nom, prenom, domicile);
			
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return auteur;
	}
	
	
	/****************** LIVRES *******************/
	
	
	@WebMethod
	@WebResult(name="Livre")
	public TLivre createLivre(@WebParam(name="Titre") String titre, 
							  @WebParam(name="Prix") double prix, 
							  @WebParam(name="Description") String description,
							  @WebParam(name="AuteurId") long auteurId){
		Entity monLivre = new Entity(ConstanteMetier.ENTITY_TLIVRE, KeyFactory.createKey(ConstanteMetier.ENTITY_TAUTEUR, auteurId));
		monLivre.setProperty(ConstanteMetier.TLIVRE_COLUMN_TITRE, titre);
		monLivre.setProperty(ConstanteMetier.TLIVRE_COLUMN_PRIX, prix);
		monLivre.setProperty(ConstanteMetier.TLIVRE_COLUMN_DESC, description);
		monLivre.setProperty(ConstanteMetier.TLIVRE_COLUMN_AUTEUR_ID, auteurId);
		
		TAuteur auteur = getAuteur(auteurId);
		String auteurNomPrenom = auteur.getNom() + " " + auteur.getPrenom();
		monLivre.setProperty(ConstanteMetier.TLIVRE_COLUMN_AUTEUR_NOM_PRENOM , auteurNomPrenom);

		
		datastoreService.put(monLivre);
		
		TLivre livre = new TLivre(monLivre.getKey().getId(), titre, prix, description, auteurId, auteurNomPrenom);
		System.out.println("Ma clé : " + monLivre.getKey());
		return livre;
	}
	
	
	@WebMethod
	@WebResult(name="Livre")
	public List<TLivre> getAllLivres(){
		Query q = new Query(ConstanteMetier.ENTITY_TLIVRE);
		PreparedQuery pq = datastoreService.prepare(q);
		
		List<TLivre> livres = new ArrayList<TLivre>();
		
		for (Entity livre : pq.asIterable()) {
			String titre = (String) livre.getProperty(ConstanteMetier.TLIVRE_COLUMN_TITRE);			
			double prix = (Double) livre.getProperty(ConstanteMetier.TLIVRE_COLUMN_PRIX);			
			String desc = (String) livre.getProperty(ConstanteMetier.TLIVRE_COLUMN_DESC);
			long auteurId = livre.getKey().getParent().getId();
			String auteurNomPrenom = (String) livre.getProperty(ConstanteMetier.TLIVRE_COLUMN_AUTEUR_NOM_PRENOM);
			
			TLivre newLivre = new TLivre(livre.getKey().getId(), titre, prix, desc, auteurId, auteurNomPrenom);
			
			livres.add(newLivre);
		}
		
		return livres;
	}
	
	@WebMethod
	@WebResult(name="Livre")
	public List<TLivre> getAllLivresByAuteur(@WebParam(name="AuteurId") long auteurId){
		Query q = new Query(ConstanteMetier.ENTITY_TLIVRE).setAncestor(KeyFactory.createKey(ConstanteMetier.ENTITY_TAUTEUR, auteurId));
		PreparedQuery pq = datastoreService.prepare(q);
		
		List<TLivre> livres = new ArrayList<TLivre>();
		
		for (Entity livre : pq.asIterable()) {
			String titre = (String) livre.getProperty(ConstanteMetier.TLIVRE_COLUMN_TITRE);			
			double prix = (double) livre.getProperty(ConstanteMetier.TLIVRE_COLUMN_PRIX);			
			String desc = (String) livre.getProperty(ConstanteMetier.TLIVRE_COLUMN_DESC);
			String auteurNomPrenom = (String) livre.getProperty(ConstanteMetier.TLIVRE_COLUMN_AUTEUR_NOM_PRENOM);
			TLivre newLivre = new TLivre(livre.getKey().getId(), titre, prix, desc, auteurId, auteurNomPrenom);
			
			livres.add(newLivre);
		}
		
		return livres;
	}
	

	@WebMethod
	@WebResult(name="Livre")
	public TLivre getLivre(@WebParam(name="Id") long id,
						   @WebParam(name="AuteurId") long auteurId){
		TLivre newLivre = null;
		Key auteurKey = KeyFactory.createKey(ConstanteMetier.ENTITY_TAUTEUR, auteurId);
		
		Key key = KeyFactory.createKey(auteurKey, ConstanteMetier.ENTITY_TLIVRE, id);
		try {
			Entity livre = datastoreService.get(key);
			String titre = (String) livre.getProperty(ConstanteMetier.TLIVRE_COLUMN_TITRE);			
			double prix = (double) livre.getProperty(ConstanteMetier.TLIVRE_COLUMN_PRIX);			
			String desc = (String) livre.getProperty(ConstanteMetier.TLIVRE_COLUMN_DESC);
			String auteurNomPrenom = (String) livre.getProperty(ConstanteMetier.TLIVRE_COLUMN_AUTEUR_NOM_PRENOM);
			newLivre = new TLivre(livre.getKey().getId(), titre, prix,desc, auteurId, auteurNomPrenom);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
	
		return newLivre;
	}

	
	@WebMethod
	@WebResult(name="IsDeleted")
	public boolean deleteLivre(@WebParam(name="Id") long id,
							   @WebParam(name="AuteurId") long auteurId){
		boolean res = false;
		
		Key auteurKey = KeyFactory.createKey(ConstanteMetier.ENTITY_TAUTEUR, auteurId);
		Key key = KeyFactory.createKey(auteurKey, ConstanteMetier.ENTITY_TLIVRE, id);
		if(key.isComplete())
		{
			datastoreService.delete(key);
			res = true;
		}
		return res;
	}
	
	
	@WebMethod
	@WebResult(name="Livre")
	public TLivre updateLivre(@WebParam(name="Id") long id,
							  @WebParam(name="Titre") String titre, 
							  @WebParam(name="Prix") double prix, 
							  @WebParam(name="Description") String desc,
							  @WebParam(name="AuteurId") long auteurId
							  ){
		TLivre livre = null;
		Key auteurKey = KeyFactory.createKey(ConstanteMetier.ENTITY_TAUTEUR, auteurId);
		Key key = KeyFactory.createKey(auteurKey, ConstanteMetier.ENTITY_TLIVRE, id);

		try {
			Entity monLivre = datastoreService.get(key);
			monLivre.setProperty(ConstanteMetier.TLIVRE_COLUMN_TITRE, titre);
			monLivre.setProperty(ConstanteMetier.TLIVRE_COLUMN_PRIX, prix);
			monLivre.setProperty(ConstanteMetier.TLIVRE_COLUMN_DESC, desc);

			TAuteur auteur = getAuteur(auteurId);
			String auteurNomPrenom = auteur.getNom() + " " + auteur.getPrenom();
			monLivre.setProperty(ConstanteMetier.TLIVRE_COLUMN_AUTEUR_NOM_PRENOM , auteurNomPrenom);
			datastoreService.put(monLivre);

			livre = new TLivre(key.getId(), titre, prix, desc, auteurId, auteurNomPrenom);
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return livre;
	}
	
}
