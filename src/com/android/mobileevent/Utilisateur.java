package com.android.mobileevent;

public class Utilisateur {
	private String login;
	private String courriel;
	
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
}
