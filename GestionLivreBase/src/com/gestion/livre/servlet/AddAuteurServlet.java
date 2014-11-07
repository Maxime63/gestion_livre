package com.gestion.livre.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;



public class AddAuteurServlet extends HttpServlet {

	/***/
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String sNumero = req.getParameter("Num");
		int valeurNumero = Integer.parseInt(sNumero);
		Integer valeurEntiere = new Integer(valeurNumero);
		
		String sNom = req.getParameter("Nom");
		String sPrenom = req.getParameter("Prenom");
		String sDomicile = req.getParameter("Domicile");
		
		DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
		Entity monAuteur = new Entity("TAuteur");
		monAuteur.setProperty("Nom", sNom);
		monAuteur.setProperty("Prenom", sNom);
		monAuteur.setProperty("Domicile", sDomicile);
		monAuteur.setProperty("Numero", valeurEntiere);
		
		datastoreService.put(monAuteur);
		
		resp.setContentType("text/plain");
		resp.getWriter().println("AddAuteurServlet : Param�tre re�u  = "
				+sNumero+" / "+sNom+" / "+sPrenom+" / 	"+sDomicile);
	}
}
