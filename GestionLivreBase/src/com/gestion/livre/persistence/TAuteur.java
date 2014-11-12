package com.gestion.livre.persistence;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Auteur")
public class TAuteur {
	
	private long id;
	private Integer numero;
	private String nom;
	private String prenom;
	private String domicile;
	
	public TAuteur(){}
	
	public TAuteur (long id, Integer numero, String nom, String prenom, String domicile){
		this.id = id;
		this.numero = numero;
		this.nom = nom;
		this.prenom = prenom;
		this.domicile = domicile;
	}
	
	@XmlAttribute(name="id")
	public long getId() {
		return id;
	}

	@XmlAttribute(name="numero")
	public Integer getNumero() {
		return numero;
	}

	@XmlAttribute(name="nom")
	public String getNom() {
		return nom;
	}

	@XmlAttribute(name="prenom")
	public String getPrenom() {
		return prenom;
	}

	@XmlAttribute(name="domicile")
	public String getDomicile() {
		return domicile;
	}
}
