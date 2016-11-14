package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature;

@Remote
public interface IServiceCandidature {

			public Candidature newCandidature(String nom, String prenom, Date date, String adressePostale, String mail, Date dateDepot, String CV, int[] secteur, int niveau);
			
			public List<Candidature> listCandidature();	
			
			public Candidature getCandidature(int id);

			public List<Candidature> candidaturesByOffre(int id);

			//Mettre à jour
			
			//Supprimmer
			
			//Envoyer message
			
			//Lister messages reçus
			
			//Lister messages envoyés
			
	
}
