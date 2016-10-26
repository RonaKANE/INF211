package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.List;

import javax.ejb.Remote;

import com.sun.tools.ws.wsdl.document.Message;

import eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise;

/**
 * Interface du service gérant les entreprises.
 * @author Philippe TANGUY
 */
@Remote
public interface IServiceEntreprise
{
	// Vitale -----------------------------------------------------------------------------

	public Entreprise newEntreprise(String name, String descriptive, String postalAddress);
	
	/**
	 * Obtention d'une entreprise suivant son id.
	 * @param id id de l'entreprise.
	 * @return l'instance de l'entreprise.
	 */

	public Entreprise getEntreprise(int id);
	
	/**
	 * Obtention de la liste de toutes les entreprises référencées dans le système.
	 * @return la liste des candidatures dans une {@code List<Entreprise>}.
	 */
	public List<Entreprise> listEntreprises();
	
	// Mineure --------------------------------------------------------------------------
	
	public Entreprise updateEntreprise(int id, String name, String descriptive, String postalAddress);
	
	public boolean removeEntreprise(int id);
	
	//Envoyer message à un candidat pour une offre donnée
	//Lister messages reçus des candidats pour une offre de l'entreprise
	//Lister messages envoyés à des candidats pour une offre de l'entreprise
	
	
}
