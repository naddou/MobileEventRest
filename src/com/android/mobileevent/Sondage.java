package com.android.mobileevent;

import java.util.ArrayList;
import java.util.Date;

public class Sondage {
	private ArrayList<Date> plageHoraire;
	private ArrayList<String> optionDeChoix;
	private String typeSondage;
	private String titreSondage;
	private Utilisateur initiateur;
	private ArrayList<Utilisateur> participant;
	
	Sondage(String typeSondage, String titreSondage, Utilisateur initiateur)
	{
		this.plageHoraire = new ArrayList<Date>();
		this.optionDeChoix = new ArrayList<String>();
		this.typeSondage = typeSondage;
		this.titreSondage = titreSondage;
		this.initiateur = initiateur;
		this.participant = new ArrayList<Utilisateur>();
	}
	
	/**
	 * Getters
	 */
	
	public String getTypeSondage()
	{
		return this.typeSondage;
	}
	
	/**
	 * Setters
	 */
	
	public void setPlageHoraire(Date plageHoraire)
	{
		this.plageHoraire.add(plageHoraire);
	}
	
	public void setOptionDeChoix(String option)
	{
		this.optionDeChoix.add(option);
	}
	
	public void setParticipant(Utilisateur participant)
	{
		this.participant.add(participant);
	}
	
	/**
	 * Serialiser le sondage
	 * @return
	 */
	public String serialiserSondage()
	{
		return null;
	}
	
	/**
	 * Deserialiser le sondage
	 * @param docXml
	 */
	public void deserialiserSondage(String docXml)
	{
		
	}
}
