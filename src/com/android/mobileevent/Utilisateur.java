package com.android.mobileevent;

public class Utilisateur {
	private String nom;
	private String courriel;
	private Sondage sondage;
	
	Utilisateur(String nom, String courriel)
	{
		this.nom = nom;
		this.courriel = courriel;
	}
	
	public String getNom()
	{
		return this.nom;
	}
	
	public String getCourriel()
	{
		return this.courriel;
	}
	
	public void setSondage(Sondage sondage)
	{
		this.sondage = sondage;
	}
}
