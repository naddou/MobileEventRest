package com.android.mobileevent;

import java.util.ArrayList;

public class Utilisateur {
	private TypeUtilisateur type;
	private String nom;
	private String courriel;
	private ArrayList<Sondage> sondage;
	
	Utilisateur(TypeUtilisateur type)
	{
		this.nom = null;
		this.courriel = null;
		this.type = type;
		this.sondage = new ArrayList<Sondage>();
	}
	
	Utilisateur(String nom, String courriel, TypeUtilisateur type)
	{
		this.nom = nom;
		this.courriel = courriel;
		this.type = type;
		this.sondage = new ArrayList<Sondage>();
	}
	
	/**
	 * Getter
	 */
	
	public String getNom()
	{
		return this.nom;
	}
	
	public String getCourriel()
	{
		return this.courriel;
	}
	
	public TypeUtilisateur getType()
	{
		return this.type;
	}
	
	/**
	 * Setter
	 */
	
	public void setSondage(Sondage sondage)
	{
		this.sondage.add(sondage);
	}
	
	public void setCourriel(String courriel)
	{
		this.courriel = courriel;
	}
	
	public void setNom(String nom)
	{
		this.nom = nom;
	}
}
