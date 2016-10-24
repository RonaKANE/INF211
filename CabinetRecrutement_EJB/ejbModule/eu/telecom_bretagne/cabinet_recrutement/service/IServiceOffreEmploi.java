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

	//-----------------------------------------------------------------------------
		/**
		 * 
		 * @param 
		 * @return 
		 */
		//-----------------------------------------------------------------------------
		public Offreemploi getOffreEmploi(int id);
		/**
		 * 
		 * @return 
		 */
		public List<Offreemploi> listOffreemploi();
		//-----------------------------------------------------------------------------
		/**
		 * 
		 * @return
		 */
		public Offreemploi updateOffreemploi(int id, String titre, String descriptif, String profil, Niveauqualification niveau, List<Secteuractivite> secteur);
		
		//-----------------------------------------------------------------------------
		/**
		 * 
		 * @return
		 */
		public boolean removeOffreEmploi(int id);
		
		//----------------------------------------------------------------------------
		/**
		 * 
		 * @return
		 */
		public Offreemploi newOffreEmploi(String titre, String descriptif, String profil, Niveauqualification niveau, 
				List <Secteuractivite> secteur, Date date);
		
		//----------------------------------------------------------------------------
		/**
		 * 
		 * @return
		 */
		public List<Offreemploi>  offreByEntrepriseList(int id);
		
		//----------------------------------------------------------------------------
		/**
		 * 
		 * @return
		 */
		public List<Message> offreByCandidatureList();
}
