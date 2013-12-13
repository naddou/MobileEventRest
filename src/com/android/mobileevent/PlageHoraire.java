package com.android.mobileevent;

public class PlageHoraire {
	private String jour;
	private String heureDebut;
	private String heureFin;
	
	PlageHoraire(String jour)
	{
		this.jour = jour;
		this.heureDebut = null;
		this.heureFin = null;
	}
	
	/**
	 * Setters
	 */
	
	public String getJour()
	{
		return this.jour;
	}
	
	/**
	 * Setters
	 */
	
	public void setHeureDebut(String heureDebut)
	{
		this.heureDebut = heureDebut;
	}
	
	public void setHeureFin(String heureFin)
	{
		this.heureFin = heureFin;
	}
	
	/**
	 * Serialiser la plage horaire
	 * @return
	 */
	public String serialiserPlageHoraire()
	{
		return null;
	}
}
