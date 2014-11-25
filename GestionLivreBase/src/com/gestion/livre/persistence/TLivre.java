package com.gestion.livre.persistence;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="Livre")
public class TLivre {
	
	private long id;
	
	private String titre;
	
	private double prix;

	private String description;
	
	private long auteurId;
	
	private String auteurNomPrenom;
	
	public TLivre(){}
	
	public TLivre(long id, String titre, double prix, String description, long auteurId, String auteurNomPrenom){
		this.id = id;
		this.titre = titre;
		this.prix = prix;
		this.description = description;
		this.auteurId = auteurId;
		this.auteurNomPrenom = auteurNomPrenom;
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
	
	@XmlElement (name="AuteurId")
	public long getAuteurId() {
		return auteurId;
	}
	
	@XmlElement (name="AuteurNomPrenom")
	public String getAuteurNomPrenom() {
		return auteurNomPrenom;
	}
}
