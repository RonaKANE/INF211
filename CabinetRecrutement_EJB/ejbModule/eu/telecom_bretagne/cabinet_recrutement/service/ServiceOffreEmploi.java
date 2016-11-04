package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.sun.tools.ws.wsdl.document.Message;

import eu.telecom_bretagne.cabinet_recrutement.data.dao.OffreEmploiDAO;
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
import eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise;
>>>>>>> b8ac9b61f1a85616050e1689b45cceeddc770cd3
>>>>>>> eefb531271eda9f868ecaf377f1e3e0468f9941f
import eu.telecom_bretagne.cabinet_recrutement.data.model.Niveauqualification;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Offreemploi;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Secteuractivite;

/**
 * Session Bean implementation class ServiceOffreEmploiBean
 */
@Stateless
@LocalBean
public class ServiceOffreEmploi implements IServiceOffreEmploi {

	//-----------------------------------------------------------------------------
	@EJB
	private OffreEmploiDAO OffreEmploiDAO;
	//-----------------------------------------------------------------------------
	
    /**
     * Default constructor. 
     */
    public ServiceOffreEmploi() {
    }

	@Override
	public Offreemploi newOffreEmploi(String titre, String descriptif, String profil, Niveauqualification niveau, 
			List <Secteuractivite> secteur, Date datedepot){
		
		if (OffreEmploiDAO.findByTitre(titre) == null){
			Offreemploi e = new Offreemploi() ;
			
			e.setTitre(titre);
			e.setDescriptionmission(descriptif);
			e.setProfilrecherche(profil);
			e.setNiveauqualification(niveau);
			e.setSecteuractivites(secteur);	
			e.setDatedepot(datedepot);

			OffreEmploiDAO.persist(e);
			return e;
		}
		return null;
	}
	
	@Override
	public List<Offreemploi> listOffreemploi() {
		return OffreEmploiDAO.findAll();
	}
	
	@Override
	public Offreemploi getOffreEmploi(int id) {
		return OffreEmploiDAO.findById(id);
	}

	@Override
	public Offreemploi updateOffreemploi(int id, String titre,
			String descriptif, String profil, Niveauqualification niveau, List<Secteuractivite> secteur) {
		
		Offreemploi e = OffreEmploiDAO.findById(id);
		if(e != null){
			e.setTitre(titre);
			e.setDescriptionmission(descriptif);
			e.setProfilrecherche(profil);
			e.setNiveauqualification(niveau);
			e.setSecteuractivites(secteur);	
			OffreEmploiDAO.update(e);
			
			return e;
		}
		
		return null;
	}

	@Override
	public boolean removeOffreEmploi(int id) {
		Offreemploi e = OffreEmploiDAO.findById(id);
		if (e!=null){
			OffreEmploiDAO.remove(e);
			return true;
		}
		return false;
	}

	@Override
	public List<Offreemploi> offreByEntrepriseList(int id) {
		return OffreEmploiDAO.findByEntreprise(id);
	}

	@Override
	public List<Message> offreByCandidatureList() {
		// TODO TO DO TO DO TO DO
		return null;
	}

}
