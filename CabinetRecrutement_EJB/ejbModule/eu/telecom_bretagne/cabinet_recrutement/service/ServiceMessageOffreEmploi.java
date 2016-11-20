package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import eu.telecom_bretagne.cabinet_recrutement.data.dao.MessageOffreEmploiDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Messageoffredemploi;

/**
 * Session Bean implementation class ServiceMessageOffreEmploi
 */
@Stateless
@LocalBean
public class ServiceMessageOffreEmploi implements IServiceMessageOffreEmploi {

	@EJB
	private MessageOffreEmploiDAO messageOffreEmploiDAO;
	
    /**
     * Default constructor. 
     */
    public ServiceMessageOffreEmploi() {
    }

	@Override
	public void removeMessageOffreEmploi(int id) {
		Messageoffredemploi messageoffredemploi = messageOffreEmploiDAO.findById(id);
		if (messageoffredemploi!=null)
			messageOffreEmploiDAO.remove(messageoffredemploi);
	}

	@Override
	public List<Messageoffredemploi> messagesByOffre(int id) {
		return messageOffreEmploiDAO.findByOffre(id);
	}


}
