package eu.telecom_bretagne.cabinet_recrutement.data.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sun.tools.ws.wsdl.document.Message;

import eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise;

/**
 * Session Bean implementation class EntrepriseDAO
 * @author Philippe TANGUY
 */
@Stateless
@LocalBean
public class EntrepriseDAO
{
	//-----------------------------------------------------------------------------
	/**
	 * Référence vers le gestionnaire de persistance.
	 */
	@PersistenceContext
	EntityManager entityManager;
	//-----------------------------------------------------------------------------
	/**
	 * Default constructor.
	 */
	public EntrepriseDAO()
	{
		// TODO Auto-generated constructor stub
	}
	//-----------------------------------------------------------------------------
	public Entreprise findById(Integer id)
	{
		return entityManager.find(Entreprise.class, id);
	}
	
	//-----------------------------------------------------------------------------
	public Entreprise findByName(String name){
    String req = "select nom from Entreprise e where e.nom=:nom";
    Query q = entityManager.createQuery(req);
    q.setParameter("nom", name);

    try {
        Entreprise res = (Entreprise) q.getSingleResult();
        return res;
    } catch (NoResultException ex) {
        return null;
    }
}
	
	//----------------------------------------------------------------------------
  public List<Entreprise> findAll()
	{
		Query query = entityManager.createQuery("select entreprise from Entreprise entreprise order by entreprise.id");
		List l = query.getResultList();

		return (List<Entreprise>) l;
	}
	//-----------------------------------------------------------------------------
  public Entreprise persist(Entreprise entreprise)
  { //stockera  dans  la  base  de  données  les informations d’une entreprise (présent sous la forme d’une instance d’Entreprise
	  try {
		  if (entreprise == null)
				new Exception("Entreprise is null");
		  
			entityManager.persist(entreprise);
		} catch (Exception e) {
			System.err.println("Problem when saving ");
			e.printStackTrace();
		}
		return entreprise;
  }
  	//-----------------------------------------------------------------------------
  public Entreprise update(Entreprise entreprise)
  {//rend persistant les mises à jour opérées sur une instance d’Entreprise passée en paramètre
	  try {
		  if (entreprise == null)
			  new Exception("Entreprise is null");
		  
			return entityManager.merge(entreprise);
		} catch (Exception e) {
			System.err.println("Problem when updating ");
			e.printStackTrace();
			return null;
		}
	  
	  //return entreprise;
  }
	//-----------------------------------------------------------------------------  
  public Entreprise remove(Entreprise entreprise)
  {//supprime les données d’une entreprise passée en paramètre
	try {
		if (entreprise == null)
			  new Exception("Entreprise is null");
		entityManager.remove(entreprise);
	} catch (Exception pe) {
		System.err.println("Problem when deleting an entity ");
		pe.printStackTrace();
		return null;
		}
	return entreprise;
	  
  }
  
	//----------------------------------------------------------------------------  
}
