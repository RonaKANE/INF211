package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import eu.telecom_bretagne.cabinet_recrutement.data.model.Offreemploi;

@Remote
public interface IServiceOffreEmploi {

		public Offreemploi newOffreEmploi(int entId, String titre, String descriptif, String profil, 
				int nivId, int[] sectIds, Date date);
	
		public List<Offreemploi> listOffreemploi();
		
		public Offreemploi getOffreEmploi(int id);

		public List<Offreemploi>  offresByEntreprise(int id);

		public List<Offreemploi> offresByCandidature(int id);
		
		//Mettre à jour
		
		//Supprimmer
}
