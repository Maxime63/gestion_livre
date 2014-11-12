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

public class AddLivreServlet extends HttpServlet implements ConstanteMetier {

	/***/
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		
		String sTitre = req.getParameter("Titre");
		
		String sPrix = req.getParameter("Prix");
		double valeurReel = new Double(sPrix);
		
		String sDesc = req.getParameter("Desc");
		
		DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
		Entity monLivre = new Entity(ENTITY_TLIVRE);
		monLivre.setProperty(TLIVRE_COLUMN_TITRE, sTitre);
		monLivre.setProperty(TLIVRE_COLUMN_PRIX, valeurReel);
		monLivre.setProperty(TLIVRE_COLUMN_DESC, sDesc);
		
		datastoreService.put(monLivre);
		
		resp.setContentType("text/plain");
		resp.getWriter().println("AddLivreServlet : Paramètre reçu  = "
				+" / "+sTitre+" / "+sPrix+" / 	"+sDesc);

	}
}
