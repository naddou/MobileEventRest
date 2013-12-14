package com.android.mobileevent;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Sondage {
	public ArrayList<PlageHoraire> plageHoraire;
	public ArrayList<String> optionDeChoix;
	public TypeSondage typeSondage;
	public String titreSondage;
	public Utilisateur initiateur;
	public ArrayList<Utilisateur> participant;
	
	Sondage()
	{
		this.plageHoraire = new ArrayList<PlageHoraire>();
		this.optionDeChoix = new ArrayList<String>();
		this.typeSondage = null;
		this.titreSondage = null;
		this.initiateur = null;
		this.participant = new ArrayList<Utilisateur>();
	}
	
	public ArrayList<PlageHoraire> getPlageHoraire() {
		return plageHoraire;
	}

	public void setPlageHoraire(ArrayList<PlageHoraire> plageHoraire) {
		this.plageHoraire = plageHoraire;
	}

	public ArrayList<String> getOptionDeChoix() {
		return optionDeChoix;
	}

	public void setOptionDeChoix(ArrayList<String> optionDeChoix) {
		this.optionDeChoix = optionDeChoix;
	}

	public String getTitreSondage() {
		return titreSondage;
	}

	public void setTitreSondage(String titreSondage) {
		this.titreSondage = titreSondage;
	}

	public Utilisateur getInitiateur() {
		return initiateur;
	}

	public void setInitiateur(Utilisateur initiateur) {
		this.initiateur = initiateur;
	}

	public ArrayList<Utilisateur> getParticipant() {
		return participant;
	}

	public void setParticipant(ArrayList<Utilisateur> participant) {
		this.participant = participant;
	}

	public void setTypeSondage(TypeSondage typeSondage) {
		this.typeSondage = typeSondage;
	}

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
	public void deserialiserSondage(Document doc)
	{
		// Recuperer le type du sondage
		doc.getDocumentElement().normalize(); 
		Node noeudType = doc.getElementsByTagName("type").item(0);
		
		// Recuperer les options du sondage (plage horaire ou option de choix)
		Node noeudInitiateur = doc.getElementsByTagName("initiator").item(0);
		NodeList listParametres = noeudInitiateur.getChildNodes();
		
		this.initiateur = new Utilisateur( ((Element)listParametres.item(0)).getTextContent(), ((Element)listParametres.item(2)).getTextContent(), TypeUtilisateur.INITIATEUR);
		
		if(noeudType.getTextContent().equals("DATE"))
		{
			this.typeSondage = TypeSondage.DATE;
		}
		else
		{
			this.typeSondage = TypeSondage.TEXT;
		}
		
		// Recuperer le titre du sondage
		Node noeudTitre = doc.getElementsByTagName("title").item(0);	
		this.titreSondage = noeudTitre.getTextContent();
		
		// Recuperer les options du sondage (plage horaire ou option de choix)
		Node options = doc.getElementsByTagName("options").item(0);
		NodeList optionList = options.getChildNodes();
		
		int tailleNodeList = optionList.getLength();
		
		if(this.typeSondage == TypeSondage.DATE)
		{
			for(int i = 0; i < tailleNodeList; i += 1)
			{
				Element element = (Element)optionList.item(i);
				this.plageHoraire.add(new PlageHoraire(element.getAttribute("date")));
			}
		}
		else
		{
			for(int i = 0; i < tailleNodeList; i += 1)
			{
				Node node = optionList.item(i);
				this.optionDeChoix.add(node.getTextContent());
			}
		}
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Titre: " + this.titreSondage + "\n\n");
		
		if(this.typeSondage == TypeSondage.DATE)
		{
			int taillePlageHoraire = this.plageHoraire.size();
			buffer.append("Dates :");
			
			for(int i = 0; i < taillePlageHoraire; i += 1)
			{
				buffer.append("   " + this.plageHoraire.get(i).getJour());
			}
		}
		else
		{
			int tailleOptionDeChoix = this.optionDeChoix.size();
			buffer.append("Options: ");
			
			for(int i = 0; i < tailleOptionDeChoix; i += 1)
			{
				buffer.append(" " + this.optionDeChoix.get(i));
			}
		}
		
		return buffer.toString();
	}
	
	
}
