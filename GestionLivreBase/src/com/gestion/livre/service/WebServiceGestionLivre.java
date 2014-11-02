package com.gestion.livre.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class WebServiceGestionLivre {
	@WebMethod
	public int createAuteur(Integer numero, String nom, String prenom, String domicile){
		return 1;
	}
}
