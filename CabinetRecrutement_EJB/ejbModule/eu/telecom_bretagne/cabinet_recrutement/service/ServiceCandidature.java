package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.sun.tools.ws.wsdl.document.Message;

import eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Niveauqualification;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Secteuractivite;

/**
 * Session Bean implementation class ServiceCandidatureBean
 */
@Stateless
@LocalBean
public class ServiceCandidature implements IServiceCandidature {

    /**
     * Default constructor. 
     */
    public ServiceCandidature() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Candidature newCandidature(String nom, String prenom, Date date,
			String adressePostale, String mail, Date dateDepot, String CV,
			List<Secteuractivite> secteur, Niveauqualification niveau) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Candidature getCandidature(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Candidature updateCandidature(int id, String nom, String prenom,
			Date date, String adressePostale, String mail, String CV,
			List<Secteuractivite> secteur, Niveauqualification niveau) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeCandidature(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Candidature> listCandidature() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Candidature> candidatureByOffreList(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> MessagesRecusList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> MessagesEnvoyesList() {
		// TODO Auto-generated method stub
		return null;
	}

}
