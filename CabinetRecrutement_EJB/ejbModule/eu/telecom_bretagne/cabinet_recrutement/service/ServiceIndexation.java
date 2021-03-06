package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import eu.telecom_bretagne.cabinet_recrutement.data.dao.NiveauQualificationDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.dao.SecteuractiviteDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Niveauqualification;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Offreemploi;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Secteuractivite;

/**
 * Session Bean implementation class ServiceIndexationBean
 */
@Stateless
@LocalBean
public class ServiceIndexation implements IServiceIndexation {

	@EJB
	private SecteuractiviteDAO secteuractiviteDAO;

	@EJB
	private NiveauQualificationDAO niveauQualificationDAO;
	
    /**
     * Default constructor. 
     */
    public ServiceIndexation() {
    }

	@Override
	public List<Secteuractivite> secteursActiviteList() {
		return secteuractiviteDAO.findAll();
	}

	@Override
	public List<Niveauqualification> niveauQualificationList() {
		return niveauQualificationDAO.findAll();
	}

	@Override
	public void indexerCandidat(Candidature candidat, int secteurId) {
		Secteuractivite s = secteuractiviteDAO.findById(secteurId);
		s.getCandidatures().add(candidat);
	}

	@Override
	public void indexerOffre(Offreemploi oe, int secteurId) {
		Secteuractivite s = secteuractiviteDAO.findById(secteurId);
		s.getOffreemplois().add(oe);
	}

	@Override
	public void retirerOffre(int id) {
		Offreemploi oeToRemove = null;
		List <Secteuractivite> secteurs = secteuractiviteDAO.findAll();
		for (Secteuractivite s : secteurs) {
			for (Offreemploi oe : s.getOffreemplois()) {
				if (oe.getId() == id) oeToRemove = oe;
			}
			s.getOffreemplois().remove(oeToRemove);
				
		}
	}
	
	
}
