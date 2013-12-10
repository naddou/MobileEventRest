package com.android.mobileevent;

public class Utilisateur {
	private String login;
	private String courriel;
	private Sondage sondage;
	
	Utilisateur(String login, String courriel)
	{
		this.login = login;
		this.courriel = courriel;
	}
	
	public String getLogin()
	{
		return this.login;
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
