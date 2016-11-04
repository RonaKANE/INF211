package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import eu.telecom_bretagne.cabinet_recrutement.data.dao.NiveauQualificationDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.dao.SecteuractiviteDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Niveauqualification;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Secteuractivite;

/**
 * Session Bean implementation class ServiceIndexationBean
 */
@Stateless
@LocalBean
public class ServiceIndexation implements IServiceIndexation {

	@EJB
	private SecteuractiviteDAO secteuractiviteDAO;
<<<<<<< HEAD
	@EJB
=======
>>>>>>> eefb531271eda9f868ecaf377f1e3e0468f9941f
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
}
