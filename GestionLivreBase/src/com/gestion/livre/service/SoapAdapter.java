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
import com.gestion.livre.service.jaxws.GetAllLivresByAuteur;
import com.gestion.livre.service.jaxws.GetAllLivresByAuteurResponse;
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
		String nom = request.getNom();
		String prenom = request.getPrenom();
		String domicile = request.getDomicile();
		
		TAuteur auteur = ws.createAuteur(nom, prenom, domicile);
		
		CreateAuteurResponse response = new CreateAuteurResponse();
		response.setAuteur(auteur);
		
		return response;
	}
	
	
	public GetAllAuteurResponse adapterGetAllAuteur(GetAllAuteur request){
		List<TAuteur> auteurs = ws.getAllAuteur();
		
		GetAllAuteurResponse response = new GetAllAuteurResponse();
		response.setAuteur(auteurs);
		
		return response;
	}
	
	
	public GetAuteurResponse adapterGetAuteur(GetAuteur request)
	{
		TAuteur auteur = ws.getAuteur(request.getId());
		GetAuteurResponse response = new GetAuteurResponse();
		response.setAuteur(auteur);
		return response;
		
	}
	
	
	public DeleteAuteurResponse adapterDeleteAuteur(DeleteAuteur request)
	{
		boolean res = ws.deleteAuteur(request.getId());
		DeleteAuteurResponse response = new DeleteAuteurResponse();
		response.setIsDeleted(res);
		return response;
	}

	public UpdateAuteurResponse adapterUpdateAuteur(UpdateAuteur request)
	{
		TAuteur auteur = ws.updateAuteur(request.getNom(), request.getPrenom(), request.getDomicile(), request.getId());
		UpdateAuteurResponse response = new UpdateAuteurResponse();
		response.setAuteur(auteur);
		return response;
	}
	
	
	public CreateLivreResponse adapterCreateLivre(CreateLivre request)
	{
		TLivre livre = ws.createLivre(request.getTitre(), request.getPrix(), request.getDescription(), request.getAuteurId());
		CreateLivreResponse response = new CreateLivreResponse();
		response.setLivre(livre);
		return response;
	}
	
	public GetAllLivresResponse adapterGetAllLivres(GetAllLivres request){
		List<TLivre> livres = ws.getAllLivres();
		
		GetAllLivresResponse response = new GetAllLivresResponse();
		response.setLivre(livres);
		
		return response;
	}

	public GetAllLivresByAuteurResponse adapterGetAllLivresByAuteur(GetAllLivresByAuteur request){
		List<TLivre> livres = ws.getAllLivresByAuteur(request.getAuteurId());
		
		GetAllLivresByAuteurResponse response = new GetAllLivresByAuteurResponse();
		response.setLivre(livres);
		
		return response;
	}

	
	public GetLivreResponse adapterGetLivre(GetLivre request)
	{
		TLivre livre = ws.getLivre(request.getId(), request.getAuteurId());
		GetLivreResponse response = new GetLivreResponse();
		response.setLivre(livre);
		return response;
		
	}

	
	public DeleteLivreResponse adapterDeleteLivre(DeleteLivre request)
	{
		boolean res = ws.deleteLivre(request.getId(), request.getAuteurId());
		DeleteLivreResponse response = new DeleteLivreResponse();
		response.setIsDeleted(res);
		return response;
	}
		

	public UpdateLivreResponse adapterUpdateLivre(UpdateLivre request)
	{
		TLivre livre = ws.updateLivre(request.getId(), request.getTitre(), request.getPrix(), request.getDescription(), request.getAuteurId());
		UpdateLivreResponse response = new UpdateLivreResponse();
		response.setLivre(livre);
		return response;
	}
	
}
