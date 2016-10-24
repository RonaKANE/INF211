package eu.telecom_bretagne.cabinet_recrutement.data.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eu.telecom_bretagne.cabinet_recrutement.data.model.Secteuractivite;

/**
 * Session Bean implementation class SecteuractiviteDAO
 */
@Stateless
@LocalBean
public class SecteuractiviteDAO {

	//-----------------------------------------------------------------------------
		/**
		 * Référence vers le gestionnaire de persistance.
		 */
		@PersistenceContext
		EntityManager entityManager;
    /**
     * Default constructor. 
     */
    public SecteuractiviteDAO() {
        // TODO Auto-generated constructor stub
    }

    public Secteuractivite findById(Integer id)
	{
		return entityManager.find(Secteuractivite.class, id);
	}
	//----------------------------------------------------------------------------
  public List<Secteuractivite> findAll()
	{
		Query query = entityManager.createQuery("select secteuractivite from Secteuractivite secteuractivite order by secteuractivite.id desc");
		List<Secteuractivite> l = query.getResultList();

		return (List<Secteuractivite>) l;
	}
	//-----------------------------------------------------------------------------
  public Secteuractivite persist(Secteuractivite secteuractivite)
  { //stockera  dans  la  base  de  données  les informations d’une Secteuractivite (présent sous la forme d’une instance d’Secteuractivite
	  try {
		  if (secteuractivite == null)
				new Exception("Secteuractivite is null");
		  
			entityManager.persist(secteuractivite);
		} catch (Exception e) {
			System.err.println("Problem when saving ");
			e.printStackTrace();
		}
		return secteuractivite;
  }
  	//-----------------------------------------------------------------------------
  public Secteuractivite update(Secteuractivite secteuractivite)
  {//rend persistant les mises à jour opérées sur une instance d’Secteuractivite passée en paramètre
	  try {
		  if (secteuractivite == null)
			  new Exception("Secteuractivite is null");
		  
			return entityManager.merge(secteuractivite);
		} catch (Exception e) {
			System.err.println("Problem when updating ");
			e.printStackTrace();
			return null;
		}
	  
	  //return Secteuractivite;
  }
  
  
  
}
