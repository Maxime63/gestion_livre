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

public class GetAllLivreServlet extends HttpServlet implements ConstanteMetier {

	/***/
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
		
		Query q = new Query(ENTITY_TLIVRE);
		PreparedQuery pq = datastoreService.prepare(q);
		
		resp.setContentType("text/plain");
		
		for(Entity auteur : pq.asIterable()){
			String titre = (String) auteur.getProperty(TLIVRE_COLUMN_TITRE);			
			double prix = (double) auteur.getProperty(TLIVRE_COLUMN_PRIX);			
			String desc = (String) auteur.getProperty(TLIVRE_COLUMN_DESC);
			
			StringBuilder monLivre = new StringBuilder();
			monLivre.append(titre).append(" : ").append(prix).append("€\n")
					 .append(desc);
			resp.getWriter().println(monLivre);
		}

	}
}
