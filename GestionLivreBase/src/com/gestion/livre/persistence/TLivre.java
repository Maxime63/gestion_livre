package com.gestion.livre.persistence;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="Livre")
public class TLivre {
	
	private long id;
	
	private String titre;
	
	private double prix;

	private String description;
	
	private Integer numeroAuteur;
	
	public TLivre(){}
	
	public TLivre(long id, String titre, double prix, String description){
		this.id = id;
		this.titre = titre;
		this.prix = prix;
		this.description = description;
	}

	@XmlAttribute(name="id")
	public long getId() {
		return id;
	}

	@XmlAttribute(name="titre")
	public String getTitre() {
		return titre;
	}

	@XmlAttribute(name="prix")
	public double getPrix() {
		return prix;
	}

	@XmlAttribute(name="description")
	public String getDescription() {
		return description;
	}

	public Integer getNumeroAuteur() {
		return numeroAuteur;
	}
}
