package com.gestion.livre.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gestion.livre.metier.ConstanteMetier;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class GetAllAuteurServlet extends HttpServlet implements ConstanteMetier{

	/***/
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
		
		Query q = new Query(ENTITY_TAUTEUR);
		PreparedQuery pq = datastoreService.prepare(q);
		
		resp.setContentType("text/plain");
		
		for(Entity auteur : pq.asIterable()){
			String nom = (String) auteur.getProperty(TAUTEUR_COLUMN_NOM);			
			String prenom = (String) auteur.getProperty(TAUTEUR_COLUMN_PRENOM);			
			String domicile = (String) auteur.getProperty(TAUTEUR_COLUMN_DOMICILE);
			
			StringBuilder monAuteur = new StringBuilder();
			monAuteur.append(nom).append(" ").append(prenom).append(" (").append(domicile).append(")");
			resp.getWriter().println(monAuteur);
		}
	}
}
