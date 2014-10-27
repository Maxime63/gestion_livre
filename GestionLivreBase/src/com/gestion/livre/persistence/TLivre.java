package com.gestion.livre.persistence;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class TLivre {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private long id;
	
	@Persistent
	private Integer numero;
	
	@Persistent
	private String titre;
	
	@Persistent
	private double prix;
	
	@Persistent
	private String description;
	
	public TLivre(Integer numero, String titre, double prix, String description){
		this.numero = numero;
		this.titre = titre;
		this.prix = prix;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public Integer getNumero() {
		return numero;
	}

	public String getTitre() {
		return titre;
	}

	public double getPrix() {
		return prix;
	}

	public String getDescription() {
		return description;
	}
}
