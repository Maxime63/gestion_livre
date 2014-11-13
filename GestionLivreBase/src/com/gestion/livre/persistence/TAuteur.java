package com.gestion.livre.persistence;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Auteur")
public class TAuteur {
	
	private long id;
	private String nom;
	private String prenom;
	private String domicile;
	
	public TAuteur(){}
	
	public TAuteur (long id, String nom, String prenom, String domicile){
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.domicile = domicile;
	}
	
	@XmlElement(name="Id")
	public long getId() {
		return id;
	}

	@XmlElement(name="Nom")
	public String getNom() {
		return nom;
	}

	@XmlElement(name="Prenom")
	public String getPrenom() {
		return prenom;
	}

	@XmlElement(name="Domicile")
	public String getDomicile() {
		return domicile;
	}
}
