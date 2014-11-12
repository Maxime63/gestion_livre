package com.gestion.livre.service;

import java.util.List;

import com.gestion.livre.persistence.TAuteur;
import com.gestion.livre.persistence.TLivre;
import com.gestion.livre.service.jaxws.CreateAuteur;
import com.gestion.livre.service.jaxws.CreateAuteurResponse;
import com.gestion.livre.service.jaxws.CreateLivre;
import com.gestion.livre.service.jaxws.CreateLivreResponse;
import com.gestion.livre.service.jaxws.DeleteAuteur;
import com.gestion.livre.service.jaxws.DeleteAuteurResponse;
import com.gestion.livre.service.jaxws.DeleteLivre;
import com.gestion.livre.service.jaxws.DeleteLivreResponse;
import com.gestion.livre.service.jaxws.GetAllAuteur;
import com.gestion.livre.service.jaxws.GetAllAuteurResponse;
import com.gestion.livre.service.jaxws.GetAllLivres;
import com.gestion.livre.service.jaxws.GetAllLivresResponse;
import com.gestion.livre.service.jaxws.GetAuteur;
import com.gestion.livre.service.jaxws.GetAuteurResponse;
import com.gestion.livre.service.jaxws.GetLivre;
import com.gestion.livre.service.jaxws.GetLivreResponse;
import com.gestion.livre.service.jaxws.UpdateAuteur;
import com.gestion.livre.service.jaxws.UpdateAuteurResponse;
import com.gestion.livre.service.jaxws.UpdateLivre;
import com.gestion.livre.service.jaxws.UpdateLivreResponse;

public class SoapAdapter {
	private WebServiceGestionLivre ws = new WebServiceGestionLivre();
	
	public CreateAuteurResponse adapterCreateAuteur(CreateAuteur request){
		String nom = request.getArg0();
		String prenom = request.getArg1();
		String domicile = request.getArg2();
		
		TAuteur auteur = ws.createAuteur(nom, prenom, domicile);
		
		CreateAuteurResponse response = new CreateAuteurResponse();
		response.setReturn(auteur);
		
		return response;
	}
	
	
	public GetAllAuteurResponse adapterGetAllAuteur(GetAllAuteur request){
		List<TAuteur> auteurs = ws.getAllAuteur();
		
		GetAllAuteurResponse response = new GetAllAuteurResponse();
		response.setReturn(auteurs);
		
		return response;
	}
	
	
	public GetAuteurResponse adapterGetAuteur(GetAuteur request)
	{
		TAuteur auteur = ws.getAuteur(request.getArg0());
		GetAuteurResponse response = new GetAuteurResponse();
		response.setReturn(auteur);
		return response;
		
	}
	
	
	public DeleteAuteurResponse adapterDeleteAuteur(DeleteAuteur request)
	{
		int res = ws.deleteAuteur(request.getArg0());
		DeleteAuteurResponse response = new DeleteAuteurResponse();
		response.setReturn(res);
		return response;
	}

	public UpdateAuteurResponse adapterUpdateAuteur(UpdateAuteur request)
	{
		TAuteur auteur = ws.updateAuteur(request.getArg0(), request.getArg1(), request.getArg2(), request.getArg3());
		UpdateAuteurResponse response = new UpdateAuteurResponse();
		response.setReturn(auteur);
		return response;
	}
	
	
	public CreateLivreResponse adapterCreateLivre(CreateLivre request)
	{
		TLivre livre = ws.createLivre(request.getArg0(), request.getArg1(), request.getArg2());
		CreateLivreResponse response = new CreateLivreResponse();
		response.setReturn(livre);
		return response;
	}
	
	public GetAllLivresResponse adapterGetAllLivres(GetAllLivres request){
		List<TLivre> livres = ws.getAllLivres();
		
		GetAllLivresResponse response = new GetAllLivresResponse();
		response.setReturn(livres);
		
		return response;
	}

	
	public GetLivreResponse adapterGetLivre(GetLivre request)
	{
		TLivre livre = ws.getLivre(request.getArg0());
		GetLivreResponse response = new GetLivreResponse();
		response.setReturn(livre);
		return response;
		
	}

	
	public DeleteLivreResponse adapterDeleteLivre(DeleteLivre request)
	{
		int res = ws.deleteLivre(request.getArg0());
		DeleteLivreResponse response = new DeleteLivreResponse();
		response.setReturn(res);
		return response;
	}
		

	public UpdateLivreResponse adapterUpdateLivre(UpdateLivre request)
	{
		TLivre livre = ws.updateLivre(request.getArg0(), request.getArg1(), request.getArg2(), request.getArg3());
		UpdateLivreResponse response = new UpdateLivreResponse();
		response.setReturn(livre);
		return response;
	}
	
}
