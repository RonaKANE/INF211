package eu.telecom_bretagne.cabinet_recrutement.data.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eu.telecom_bretagne.cabinet_recrutement.data.model.Niveauqualification;

/**
 * Session Bean implementation class NiveauQualificationDAO
 */
@Stateless
@LocalBean
public class NiveauQualificationDAO {
	//-----------------------------------------------------------------------------
	/**
	 * Référence vers le gestionnaire de persistance.
	 */
	@PersistenceContext
	EntityManager entityManager;
	
    /**
     * Default constructor. 
     */
    public NiveauQualificationDAO() {
        // TODO Auto-generated constructor stub
    }
	//-----------------------------------------------------------------------------
	public Niveauqualification findById(Integer id)
	{
		return entityManager.find(Niveauqualification.class, id);
	}
	//----------------------------------------------------------------------------
  @SuppressWarnings("unchecked")
public List<Niveauqualification> findAll()
	{
		Query query = entityManager.createQuery("select niveauqualification from Niveauqualification niveauqualification order by niveauqualification.id");
		List<Niveauqualification> l = query.getResultList();

		return (List<Niveauqualification>) l;
	}
	//-----------------------------------------------------------------------------
  public Niveauqualification persist(Niveauqualification Niveauqualification)
  { //stockera  dans  la  base  de  données  les informations d’une Niveauqualification (présent sous la forme d’une instance d’Niveauqualification
	  try {
		  if (Niveauqualification == null)
				new Exception("Niveauqualification is null");
		  
			entityManager.persist(Niveauqualification);
		} catch (Exception e) {
			System.err.println("Problem when saving ");
			e.printStackTrace();
		}
		return Niveauqualification;
  }
  	//-----------------------------------------------------------------------------
  public Niveauqualification update(Niveauqualification niveauqualification)
  {//rend persistant les mises à jour opérées sur une instance de Niveauqualification passée en paramètre
	  try {
		  if (niveauqualification == null)
			  new Exception("Niveauqualification is null");
		  
			return entityManager.merge(niveauqualification);
		} catch (Exception e) {
			System.err.println("Problem when updating ");
			e.printStackTrace();
			return null;
		}
	  
	  //return Niveauqualification;
  }
}
