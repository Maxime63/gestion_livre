package com.gestion.livre.metier;

public interface ConstanteMetier {
	public static final String NAMESPACE_PREFIX = "ser";
	public static final String NAMESPACE_URI = "http://service.livre.gestion.com/";

	public static final String URL_WS = "http://localhost:8888/ws";	
	
	public static final String XML_ELEMENT_GET_ALL_AUTEUR = "getAllAuteur";
	public static final String XML_ELEMENT_GET_ALL_LIVRE = "getAllLivres";
	public static final String XML_ELEMENT_GET_ALL_LIVRE_BY_AUTEUR = "getAllLivresByAuteur";
	public static final String XML_ELEMENT_GET_AUTEUR = "getAuteur";	
	public static final String XML_ELEMENT_ADD_AUTEUR = "createAuteur";	
	public static final String XML_ELEMENT_UPDATE_AUTEUR = "updateAuteur";	
	public static final String XML_ELEMENT_ADD_LIVRE = "createLivre";	
	public static final String XML_ELEMENT_DELETE_AUTEUR = "deleteAuteur";	
	public static final String XML_ELEMENT_DELETE_LIVRE = "deleteLivre";	
	public static final String XML_ELEMENT_UPDATE_LIVRE = "updateLivre";	
	public static final String XML_ELEMENT_GET_LIVRE = "getLivre";

	public static final String XML_ELEMENT_AUTEUR = "Auteur";
	public static final String XML_ELEMENT_ID = "Id";
	public static final String XML_ELEMENT_DOMICILE = "Domicile";
	public static final String XML_ELEMENT_NOM = "Nom";
	public static final String XML_ELEMENT_PRENOM = "Prenom";
	public static final String XML_ELEMENT_IS_DELETED = "IsDeleted";
	
	public static final String XML_ELEMENT_LIVRE = "Livre";
	public static final String XML_ELEMENT_AUTEUR_ID = "AuteurId";
	public static final String XML_ELEMENT_TITRE = "Titre";
	public static final String XML_ELEMENT_PRIX = "Prix";
	public static final String XML_ELEMENT_DESCRIPTION = "Description";
	public static final String XML_ELEMENT_AUTEUR_NOM_PRENOM = "AuteurNomPrenom";
	
	public static final String AUTEURS_LIST_PARAM = "auteursList";
	public static final String AUTEUR_PARAM = "auteur";
	public static final String AUTEUR_ID_PARAM = "auteurId";
	public static final String AUTEUR_FIRSTNAME_PARAM = "firstname";
	public static final String AUTEUR_LASTNAME_PARAM = "lastname";
	public static final String AUTEUR_HOME_PARAM = "home";
	public static final String IS_DELETED_PARAM = "isDeleted";
	public static final String LIVRE_PARAM = "livre";
	public static final String LIVRE_ID_PARAM = "livreId";
	public static final String LIVRES_LIST_PARAM = "livresList";
	public static final String LIVRE_TITLE_PARAM = "title";
	public static final String LIVRE_PRICE_PARAM = "price";
	public static final String LIVRE_DESCRIPTION_PARAM = "description";
}
