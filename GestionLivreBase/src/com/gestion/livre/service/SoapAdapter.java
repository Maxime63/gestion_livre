package com.gestion.livre.service;

import java.util.List;

import com.gestion.livre.persistence.TAuteur;
import com.gestion.livre.service.jaxws.CreateAuteur;
import com.gestion.livre.service.jaxws.CreateAuteurResponse;
import com.gestion.livre.service.jaxws.GetAllAuteur;
import com.gestion.livre.service.jaxws.GetAllAuteurResponse;

public class SoapAdapter {
	private WebServiceGestionLivre ws = new WebServiceGestionLivre();
	
	public CreateAuteurResponse adapterCreateAuteur(CreateAuteur request){
		Integer numero = request.getArg0();
		String nom = request.getArg1();
		String prenom = request.getArg2();
		String domicile = request.getArg3();
		
		Integer num = ws.createAuteur(numero, nom, prenom, domicile);
		
		CreateAuteurResponse response = new CreateAuteurResponse();
		response.setReturn(num);
		
		return response;
	}
	
	public GetAllAuteurResponse adapterGetAllAuteur(GetAllAuteur request){
		List<TAuteur> auteurs = ws.getAllAuteur();
		
		GetAllAuteurResponse response = new GetAllAuteurResponse();
		response.setReturn(auteurs);
		
		return response;
	}
}
