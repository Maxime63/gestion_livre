package com.gestion.livre.service;

import com.gestion.livre.service.jaxws.CreateAuteur;
import com.gestion.livre.service.jaxws.CreateAuteurResponse;

public class SoapAdapter {
	private WebServiceGestionLivre ws = new WebServiceGestionLivre();
	
	public CreateAuteurResponse adapterCreateAuteur(CreateAuteur request){
		Integer numero = request.getArg0();
		String nom = request.getArg1();
		String prenom = request.getArg2();
		String domicile = request.getArg3();
		
		Integer num = ws.createAuteur(numero, nom, prenom, domicile);
		
		CreateAuteurResponse auteurResponse = new CreateAuteurResponse();
		auteurResponse.setReturn(num);
		
		return auteurResponse;
	}

}
