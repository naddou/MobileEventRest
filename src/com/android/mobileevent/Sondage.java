package com.android.mobileevent;

import java.util.ArrayList;

public class Sondage {
	private ArrayList<PlageHoraire> plageHoraire;
	private ArrayList<String> optionDeChoix;
	private TypeSondage typeSondage;
	private String titreSondage;
	private Utilisateur initiateur;
	private ArrayList<Utilisateur> participant;
	
	Sondage(TypeSondage typeSondage, String titreSondage, Utilisateur initiateur)
	{
		this.plageHoraire = new ArrayList<PlageHoraire>();
		this.optionDeChoix = new ArrayList<String>();
		this.typeSondage = typeSondage;
		this.titreSondage = titreSondage;
		this.initiateur = initiateur;
		this.participant = new ArrayList<Utilisateur>();
	}
	
	/**
	 * Getters
	 */
	
	public TypeSondage getTypeSondage()
	{
		return this.typeSondage;
	}
	
	/**
	 * Setters
	 */
	
	public void setPlageHoraire(PlageHoraire plageHoraire)
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
		StringBuffer buffer = new StringBuffer();
		buffer.append("<poll xmlns=\"http://doodle.com/xsd1\">");
		
		buffer.append("<type>");
		
		if(TypeSondage.DATE == this.typeSondage)
		{
			buffer.append("DATE");
		}
		else
		{
			buffer.append("TEXT");
		}
		
		buffer.append("</type>");
		
		buffer.append("<hidden>false</hidden>");
		buffer.append("<levels>2</levels>");
		buffer.append("<title>" + this.titreSondage + "</title>");
		
		buffer.append("<initiator>");
		buffer.append("<name>" + this.initiateur.getNom() + "</name>"); 
		buffer.append("<eMailAddress>" + this.initiateur.getCourriel() + "</eMailAddress>");
		buffer.append("</initiator>");
		
		buffer = serialiserLesBalisesOptions(buffer, this.typeSondage);
		
		buffer.append("</poll>");
		
		return buffer.toString();
	}
	
	private StringBuffer serialiserLesBalisesOptions(StringBuffer buffer, TypeSondage type)
	{
		buffer.append("<options>"); 
		
		if(TypeSondage.DATE == type)
		{
			for(PlageHoraire plage : this.plageHoraire)
			{
				buffer.append("<option date=\"" + plage.getJour() + "\"></option>");
			}
		}
		else
		{
			for(String choix : this.optionDeChoix)
			{
				buffer.append("<option>" + choix + "</option>");
			}
		}
		
		buffer.append("</options>"); 
		
		return buffer;
	}
	
	
	/**
	 * Deserialiser le sondage
	 * @param docXml
	 */
	public void deserialiserSondage(String docXml)
	{
		
	}
}
