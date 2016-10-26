package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import com.sun.tools.ws.wsdl.document.Message;

import eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Niveauqualification;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Secteuractivite;

@Remote
public interface IServiceCandidature {

			public Candidature newCandidature(String nom, String prenom, Date date, String adressePostale, String mail, Date dateDepot, String CV, List <Secteuractivite> secteur, Niveauqualification niveau);
			
			public Candidature getCandidature(int id);
	
			public Candidature updateCandidature(int id, String nom, String prenom, Date date, String adressePostale, String mail, String CV, List <Secteuractivite> secteur, Niveauqualification niveau);
			
			public boolean removeCandidature(int id);
			
			public List<Candidature> listCandidature();
			
			public List<Candidature>  candidatureByOffreList(int id);
			
			public List<Message> MessagesRecusList();
			
			public List<Message> MessagesEnvoyesList();
	
}
