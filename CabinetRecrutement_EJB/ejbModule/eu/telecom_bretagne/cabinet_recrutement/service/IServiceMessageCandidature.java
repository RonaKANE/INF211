package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.List;

import javax.ejb.Remote;

import eu.telecom_bretagne.cabinet_recrutement.data.model.Messagecandidature;

@Remote
public interface IServiceMessageCandidature {

	public void removeMessageCandidature(int id);
	
	public List<Messagecandidature> messagesByOffre(int id);
}
