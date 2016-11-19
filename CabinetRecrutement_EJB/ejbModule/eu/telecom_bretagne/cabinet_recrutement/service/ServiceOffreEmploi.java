package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import eu.telecom_bretagne.cabinet_recrutement.data.dao.CandidatureDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.dao.EntrepriseDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.dao.NiveauQualificationDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.dao.OffreEmploiDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.dao.SecteuractiviteDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise;
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
	private EntrepriseDAO entrepriseDAO;
	
	@EJB
	private CandidatureDAO candidatureDAO;
	
	@EJB
	private NiveauQualificationDAO niveauQualificationDAO;
	
	@EJB
	private SecteuractiviteDAO secteuractiviteDAO;
	
    /**
     * Default constructor. 
     */
    public ServiceOffreEmploi() {
    }

	@Override
	public Offreemploi newOffreEmploi(int entId, String titre, String descriptif, String profil, int nivId, 
			int[] sectIds, Date datedepot){
		
		if (offreEmploiDAO.findByTitre(titre) == null){
			Offreemploi e = new Offreemploi() ;
			
			e.setEntreprise(findEntreprisebyId(entId));
			e.setTitre(titre);
			e.setDescriptionmission(descriptif);
			e.setProfilrecherche(profil);
			e.setNiveauqualification(niveauQualificationDAO.findById(nivId));
			
			e.setSecteuractivites(findSecteurbyId(sectIds));
			e.setNiveauqualification(findNiveaubyId(nivId));	
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
	
	private Entreprise findEntreprisebyId (int id) {
		return entrepriseDAO.findById(id);
	}
	
	private List <Secteuractivite> findSecteurbyId (int [] ids) {
		List <Secteuractivite> list = new LinkedList <Secteuractivite>();
		for (int id : ids) {
			list.add(secteuractiviteDAO.findById(id));
			}
	return list;
	}
	
	private Niveauqualification findNiveaubyId (int id) {
		return niveauQualificationDAO.findById(id);
	}

	@Override
	public void removeOffreEmploi(int id) {
		Offreemploi oe = offreEmploiDAO.findById(id);
		if (oe!=null)
			offreEmploiDAO.remove(oe);
	}

}
