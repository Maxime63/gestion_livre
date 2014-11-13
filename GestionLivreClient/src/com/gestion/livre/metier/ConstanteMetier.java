package com.gestion.livre.metier;

public interface ConstanteMetier {
	public static final String NAMESPACE_PREFIX = "ser";
	public static final String NAMESPACE_URI = "http://service.livre.gestion.com/";

	public static final String URL_WS = "http://localhost:8888/ws";	
	
	public static final String GET_ALL_AUTEUR_NODE = "getAllAuteur";
	
	public static final String XML_ELEMENT_AUTEUR = "Auteur";
	public static final String XML_ELEMENT_ID = "Id";
	public static final String XML_ELEMENT_DOMICILE = "Domicile";
	public static final String XML_ELEMENT_NOM = "Nom";
	public static final String XML_ELEMENT_PRENOM = "Prenom";
	
	public static final String AUTEURS_LIST_PARAM = "auteursList";
}
