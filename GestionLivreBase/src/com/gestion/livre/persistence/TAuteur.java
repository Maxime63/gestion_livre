package com.gestion.livre.persistence;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class TAuteur {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private long id;
	
	@Persistent
	private Integer numero;
	
	@Persistent
	private String nom;
	
	@Persistent
	private String prenom;
	
	@Persistent
	private String domicile;
	
	public TAuteur (Integer numero, String nom, String prenom, String domicile){
		this.numero = numero;
		this.nom = nom;
		this.prenom = prenom;
		this.domicile = domicile;
	}

	public long getId() {
		return id;
	}

	public Integer getNumero() {
		return numero;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getDomicile() {
		return domicile;
	}
}
