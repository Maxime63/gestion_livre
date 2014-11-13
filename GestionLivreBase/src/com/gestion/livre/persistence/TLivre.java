package com.gestion.livre.persistence;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="Livre")
public class TLivre {
	
	private long id;
	
	private String titre;
	
	private double prix;

	private String description;
	
	private long idAuteur;
	
	public TLivre(){}
	
	public TLivre(long id, String titre, double prix, String description){
		this.id = id;
		this.titre = titre;
		this.prix = prix;
		this.description = description;
	}

	@XmlElement(name="Id")
	public long getId() {
		return id;
	}

	@XmlElement(name="Titre")
	public String getTitre() {
		return titre;
	}

	@XmlElement(name="Prix")
	public double getPrix() {
		return prix;
	}

	@XmlElement(name="Description")
	public String getDescription() {
		return description;
	}
	
	public long getNumeroAuteur() {
		return idAuteur;
	}
}
