package com.gestion.livre.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class AddLivreServlet extends HttpServlet {

	/***/
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String sNumero = req.getParameter("Num");
		int valeurNumero = Integer.parseInt(sNumero);
		Integer valeurEntiere = new Integer(valeurNumero);
		
		String sTitre = req.getParameter("Titre");
		
		String sPrix = req.getParameter("Prix");
		double valeurReel = new Double(sPrix);
		
		String sDesc = req.getParameter("Desc");
		
		DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
		Entity monLivre = new Entity("TLivre");
		monLivre.setProperty("Titre", sTitre);
		monLivre.setProperty("Prix", valeurReel);
		monLivre.setProperty("Description", sDesc);
		monLivre.setProperty("Numero", valeurEntiere);
		
		datastoreService.put(monLivre);
		
		resp.setContentType("text/plain");
		resp.getWriter().println("AddLivreServlet : Paramètre reçu  = "
				+sNumero+" / "+sTitre+" / "+sPrix+" / 	"+sDesc);

	}
}
