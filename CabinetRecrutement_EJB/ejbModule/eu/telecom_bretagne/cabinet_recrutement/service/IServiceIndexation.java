package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.List;

import javax.ejb.Remote;

import eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Niveauqualification;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Offreemploi;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Secteuractivite;

@Remote
public interface IServiceIndexation {

	public List <Secteuractivite> secteursActiviteList();
	
	public List <Niveauqualification> niveauQualificationList();
	
	public void indexerCandidat(Candidature Candidat, int secteurId);
	
	public void indexerOffre(Offreemploi oe, int secteurId);
	
	public void retirerOffre(int id);
}

