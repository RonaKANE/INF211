package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import eu.telecom_bretagne.cabinet_recrutement.data.dao.CandidatureDAO;
<<<<<<< HEAD
import eu.telecom_bretagne.cabinet_recrutement.data.dao.NiveauQualificationDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.dao.SecteuractiviteDAO;
=======
>>>>>>> eefb531271eda9f868ecaf377f1e3e0468f9941f
import eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Niveauqualification;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Secteuractivite;

/**
 * Session Bean implementation class ServiceCandidatureBean
 */
@Stateless
@LocalBean
public class ServiceCandidature implements IServiceCandidature {

	@EJB
	private CandidatureDAO candidatureDAO;
<<<<<<< HEAD
	@EJB 
	private SecteuractiviteDAO secteurDAO;
	@EJB
	private NiveauQualificationDAO niveauDAO;
=======
>>>>>>> eefb531271eda9f868ecaf377f1e3e0468f9941f
	
    /**
     * Default constructor. 
     */
    public ServiceCandidature() {
    }

	@Override
	public Candidature newCandidature(String nom, String prenom, Date datenaissance,
			String adressePostale, String mail, Date dateDepot, String cv,
<<<<<<< HEAD
			int[] secteurs, int niveau) {

		//Trouver comment être sur d'avoir une seul candidature 
		Candidature c = new Candidature() ;
		
		c.setNom(nom);
		c.setPrenom(prenom);
		c.setDatenaissance(datenaissance);
		c.setAdressePostale(adressePostale);
		c.setAdresseemail(mail);
		c.setDatedepot(dateDepot);
		c.setCv(cv);
		c.setSecteuractivites(findSecteurbyId(secteurs));
		c.setNiveauqualification(findNiveaubyId(niveau));
		
		candidatureDAO.persist(c);
		return c;
			
	}
	
	private List <Secteuractivite> findSecteurbyId (int [] ids) {
		List <Secteuractivite> list = null;
		for (int id : ids) {
			list.add(secteurDAO.findById(id));
	}
	return list;
		
=======
			List<Secteuractivite> secteurs, Niveauqualification niveau) {

		//Trouver comment être sur d'avoir une seul candidature 
		Candidature c = new Candidature() ;
		
		c.setNom(nom);
		c.setPrenom(prenom);
		c.setDatenaissance(datenaissance);
		c.setAdressePostale(adressePostale);
		c.setAdresseemail(mail);
		c.setDatedepot(dateDepot);
		c.setCv(cv);
		c.setSecteuractivites(secteurs);
		c.setNiveauqualification(niveau);
		
		candidatureDAO.persist(c);
		return c;
			
	}
	
	@Override
	public List<Candidature> listCandidature() {
		return candidatureDAO.findAll();
	}

	@Override
	public Candidature getCandidature(int id) {
		return candidatureDAO.findById(id);
	}

	//Lister offre emplois pour une candidature donnée
	
	@Override
	public Candidature updateCandidature(int id, String nom, String prenom,
			Date date, String adressePostale, String mail, String cv,
			List<Secteuractivite> secteurs, Niveauqualification niveau) {
		
		Candidature c = candidatureDAO.findById(id);
		if(c != null){
			
			c.setNom(nom);
			c.setPrenom(prenom);
			c.setAdressePostale(adressePostale);
			c.setAdresseemail(mail);
			c.setCv(cv);
			c.setSecteuractivites(secteurs);
			c.setNiveauqualification(niveau);
			
			candidatureDAO.update(c);
			return c;
		}
		
		return null;
	}

	@Override
	public boolean removeCandidature(int id) {
		
		Candidature c = candidatureDAO.findById(id);
		if (c != null){
			candidatureDAO.remove(c);
			return true;
		}
		return false;
>>>>>>> eefb531271eda9f868ecaf377f1e3e0468f9941f
	}
	
	private Niveauqualification findNiveaubyId (int id) {
		return niveauDAO.findById(id);

<<<<<<< HEAD
	}
	
	@Override
	public List<Candidature> listCandidature() {
		return candidatureDAO.findAll();
	}

	@Override
	public Candidature getCandidature(int id) {
		return candidatureDAO.findById(id);
	}

	//Lister offre emplois pour une candidature donnée
	
	@Override
	public Candidature updateCandidature(int id, String nom, String prenom,
			Date date, String adressePostale, String mail, String cv,
			int[] secteurs, int niveau) {
		
		Candidature c = candidatureDAO.findById(id);
		if(c != null){
			
			c.setNom(nom);
			c.setPrenom(prenom);
			c.setAdressePostale(adressePostale);
			c.setAdresseemail(mail);
			c.setCv(cv);
			c.setSecteuractivites(findSecteurbyId(secteurs));
			c.setNiveauqualification(findNiveaubyId(niveau));
			
			candidatureDAO.update(c);
			return c;
		}
		
		return null;
	}

	@Override
	public boolean removeCandidature(int id) {
		
		Candidature c = candidatureDAO.findById(id);
		if (c != null){
			candidatureDAO.remove(c);
			return true;
		}
		return false;
	}
=======
	//Envoyer message
	
	//Lister messages reçus
	
	//Lister messages envoyés
>>>>>>> eefb531271eda9f868ecaf377f1e3e0468f9941f

	//Envoyer message
	
	//Lister messages reçus
	
	//Lister messages envoyés

}