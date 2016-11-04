package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import eu.telecom_bretagne.cabinet_recrutement.data.dao.CandidatureDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.dao.OffreEmploiDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Niveauqualification;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Offreemploi;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Secteuractivite;

/**
 * Session Bean implementation class ServiceOffreEmploiBean
 */
@Stateless
@LocalBean
public class ServiceOffreEmploi implements IServiceOffreEmploi {

	@EJB
	private OffreEmploiDAO offreEmploiDAO;
	
	@EJB
	private CandidatureDAO candidatureDAO;
	
    /**
     * Default constructor. 
     */
    public ServiceOffreEmploi() {
    }

	@Override
	public Offreemploi newOffreEmploi(String titre, String descriptif, String profil, Niveauqualification niveau, 
			List <Secteuractivite> secteur, Date datedepot){
		
		if (offreEmploiDAO.findByTitre(titre) == null){
			Offreemploi e = new Offreemploi() ;
			
			e.setTitre(titre);
			e.setDescriptionmission(descriptif);
			e.setProfilrecherche(profil);
			e.setNiveauqualification(niveau);
			e.setSecteuractivites(secteur);	
			e.setDatedepot(datedepot);

			offreEmploiDAO.persist(e);
			return e;
		}
		return null;
	}
	
	@Override
	public List<Offreemploi> listOffreemploi() {
		return offreEmploiDAO.findAll();
	}
	
	@Override
	public Offreemploi getOffreEmploi(int id) {
		return offreEmploiDAO.findById(id);
	}

	@Override
	public Offreemploi updateOffreemploi(int id, String titre,
			String descriptif, String profil, Niveauqualification niveau, List<Secteuractivite> secteur) {
		
		Offreemploi e = offreEmploiDAO.findById(id);
		if(e != null){
			e.setTitre(titre);
			e.setDescriptionmission(descriptif);
			e.setProfilrecherche(profil);
			e.setNiveauqualification(niveau);
			e.setSecteuractivites(secteur);	
			offreEmploiDAO.update(e);
			
			return e;
		}
		
		return null;
	}

	@Override
	public boolean removeOffreEmploi(int id) {
		Offreemploi e = offreEmploiDAO.findById(id);
		if (e!=null){
			offreEmploiDAO.remove(e);
			return true;
		}
		return false;
	}

	@Override
	public List<Offreemploi> offresByEntreprise(int id) {
		return offreEmploiDAO.findByEntreprise(id);
	}

	@Override
	public List<Offreemploi> offresByCandidature(int id) {
			
		Candidature candidature = new Candidature();
		candidature = candidatureDAO.findById(id);
		
		if (candidature != null) {
			Niveauqualification niveau = candidature.getNiveauqualification();
			List <Secteuractivite> secteursList = candidature.getSecteuractivites();
			
			List <Offreemploi> oeList = new LinkedList<Offreemploi>();
			for (Secteuractivite s : secteursList){
				List <Offreemploi> l= offreEmploiDAO.findBySecteurActiviteAndNiveauQualification(s.getId(), niveau.getId());
				if (l != null)
					oeList.addAll(l);
			}
			return oeList;
		}
		
		return null;
	}

}
