package eu.telecom_bretagne.cabinet_recrutement.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.sun.tools.ws.wsdl.document.Message;

import eu.telecom_bretagne.cabinet_recrutement.data.dao.EntrepriseDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise;
//import eu.telecom_bretagne.data.model.Service;

/**
 * Session Bean implementation class ServiceEntreprise
 * 
 * @author Philippe TANGUY
 */
@Stateless
@LocalBean
public class ServiceEntreprise implements IServiceEntreprise
{

	@EJB
	private EntrepriseDAO entrepriseDAO;

	/**
	 * Default constructor.
	 */
	public ServiceEntreprise(){
	}
	
	@Override
	public Entreprise newEntreprise(String name, String descriptive, String postalAddress){

		if (entrepriseDAO.findByName(name) == null){
			Entreprise e = new Entreprise() ;
			e.setNom(name) ;
			e.setAdressePostale(postalAddress);
			e.setDescriptif(descriptive);
			entrepriseDAO.persist(e);
			return e;
		}
		return null;
	}
	
	@Override
	public List<Entreprise> listEntreprises(){
		return entrepriseDAO.findAll();
	}	

	@Override
	public Entreprise getEntreprise(int id){
		return entrepriseDAO.findById(id);
	}
	
	@Override
	public Entreprise updateEntreprise(int id, String name, String descriptive, String postalAddress){
		
		Entreprise e = entrepriseDAO.findById(id);
		if(e != null){
			e.setNom(name);
			e.setAdressePostale(postalAddress);
			e.setDescriptif(descriptive);
			entrepriseDAO.update(e);
			return e;
		}
		
		return null;
	}
	
	@Override
	public boolean removeEntreprise(int id){
		
		Entreprise e = entrepriseDAO.findById(id);
		if (e!=null){
			entrepriseDAO.remove(e);
			return true;
		}
		return false;
	}
	
	//Envoyer message
	
	//Lister messages reçus
	
	//Lister messages envoyés
}
