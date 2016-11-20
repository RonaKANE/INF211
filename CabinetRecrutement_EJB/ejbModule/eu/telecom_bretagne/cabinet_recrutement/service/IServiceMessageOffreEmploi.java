package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.List;

import javax.ejb.Remote;

import eu.telecom_bretagne.cabinet_recrutement.data.model.Messageoffredemploi;

@Remote
public interface IServiceMessageOffreEmploi {
	
	public void removeMessageOffreEmploi(int id);
	
	public List<Messageoffredemploi>  messagesByOffre(int id);
	
}
