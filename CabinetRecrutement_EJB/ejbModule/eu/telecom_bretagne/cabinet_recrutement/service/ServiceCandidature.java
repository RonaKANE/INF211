package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import eu.telecom_bretagne.cabinet_recrutement.data.dao.CandidatureDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.dao.NiveauQualificationDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.dao.OffreEmploiDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.dao.SecteuractiviteDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Niveauqualification;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Offreemploi;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Secteuractivite;

/**
 * Session Bean implementation class ServiceCandidatureBean
 */
@Stateless
@LocalBean
public class ServiceCandidature implements IServiceCandidature {

	@EJB
	private CandidatureDAO candidatureDAO;
	@EJB 
	private SecteuractiviteDAO secteurDAO;
	@EJB
	private NiveauQualificationDAO niveauDAO;
	
	@EJB
	private OffreEmploiDAO offreEmploiDAO;
	
    /**
     * Default constructor. 
     */
    public ServiceCandidature() {
    }

	@Override
	public Candidature newCandidature(String nom, String prenom, Date datenaissance,
			String adressePostale, String mail, Date dateDepot, String cv,
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
	
	@Override
	public List<Candidature> listCandidature() {
		return candidatureDAO.findAll();
	}

	@Override
	public Candidature getCandidature(int id) {
		return candidatureDAO.findById(id);
	}
	
	private List <Secteuractivite> findSecteurbyId (int [] ids) {
		List <Secteuractivite> list = new LinkedList <Secteuractivite>();
		for (int id : ids) {
			list.add(secteurDAO.findById(id));
			}
	return list;
	}
	
	private Niveauqualification findNiveaubyId (int id) {
		return niveauDAO.findById(id);
	}
	
	@Override
	public List<Candidature> candidaturesByOffre(int id) {
			
		Offreemploi oe = new Offreemploi();
		oe = offreEmploiDAO.findById(id);
		
		if (oe != null) {
			Niveauqualification niveau = oe.getNiveauqualification();
			List <Secteuractivite> secteursList = oe.getSecteuractivites();
			
			List <Candidature> cList = new LinkedList<Candidature>();
			for (Secteuractivite s : secteursList){
				List <Candidature> l= candidatureDAO.findBySecteurActiviteAndNiveauQualification(s.getId(), niveau.getId());
				if (l != null)
					cList.addAll(l);
			}
			return cList;
		}
		
		return null;
	}
	
	
	//Envoyer message
	
	//Lister messages reçus
	
	//Lister messages envoyés

}
