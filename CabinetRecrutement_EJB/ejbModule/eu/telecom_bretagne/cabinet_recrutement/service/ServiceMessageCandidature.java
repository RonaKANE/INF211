package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import eu.telecom_bretagne.cabinet_recrutement.data.dao.MessageCandidatureDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Messagecandidature;

/**
 * Session Bean implementation class ServiceMessageCandidature
 */
@Stateless
@LocalBean
public class ServiceMessageCandidature implements IServiceMessageCandidature {

	@EJB
	private MessageCandidatureDAO messageCandidatureDAO;
	
    /**
     * Default constructor. 
     */
    public ServiceMessageCandidature() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void removeMessageCandidature(int id) {
		Messagecandidature m = messageCandidatureDAO.findById(id);
		if (m != null)
			messageCandidatureDAO.remove(m);
	}

	@Override
	public List<Messagecandidature> messagesByOffre(int id) {
		return messageCandidatureDAO.findByOffre(id);
	}
}
