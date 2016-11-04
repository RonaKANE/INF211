package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import com.sun.tools.ws.wsdl.document.Message;

import eu.telecom_bretagne.cabinet_recrutement.data.model.Niveauqualification;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Offreemploi;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Secteuractivite;

@Remote
public interface IServiceOffreEmploi {

		public Offreemploi newOffreEmploi(String titre, String descriptif, String profil, Niveauqualification niveau, 
			List <Secteuractivite> secteur, Date date);
	
		public List<Offreemploi> listOffreemploi();
		
		public Offreemploi getOffreEmploi(int id);

		public Offreemploi updateOffreemploi(int id, String titre, String descriptif, String profil, Niveauqualification niveau, List<Secteuractivite> secteur);

		public boolean removeOffreEmploi(int id);

		public List<Offreemploi>  offreByEntrepriseList(int id);
		
		//TO DO
		public List<Message> offreByCandidatureList();
}