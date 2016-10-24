package eu.telecom_bretagne.cabinet_recrutement.data.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eu.telecom_bretagne.cabinet_recrutement.data.model.Offreemploi;

/**
 * Session Bean implementation class OffreEmploiDAO
 */
@Stateless
@LocalBean
public class OffreEmploiDAO {

	/**
	 * Référence vers le gestionnaire de persistance.
	 */
	@PersistenceContext
	EntityManager entityManager;
	
    /**
     * Default constructor. 
     */
    public OffreEmploiDAO() {
        // TODO Auto-generated constructor stub
    }

    
	//-----------------------------------------------------------------------------
	public Offreemploi findById(Integer id)
	{
		return entityManager.find(Offreemploi.class, id);
	}
	//----------------------------------------------------------------------------
  public List<Offreemploi> findAll()
	{
		Query query = entityManager.createQuery("select Offreemploi from Offreemploi Offreemploi order by Offreemploi.id desc");
		List<Offreemploi> l = query.getResultList();

		return (List<Offreemploi>) l;
	}
	//-----------------------------------------------------------------------------
  public Offreemploi persist(Offreemploi offreemploi)
  { //stockera  dans  la  base  de  données  les informations d’une Offreemploi (présent sous la forme d’une instance d’Offreemploi
	  try {
		  if (offreemploi == null)
				new Exception("Offreemploi is null");
		  
			entityManager.persist(offreemploi);
		} catch (Exception e) {
			System.err.println("Problem when saving ");
			e.printStackTrace();
		}
		return offreemploi;
  }
  	//-----------------------------------------------------------------------------
  public Offreemploi update(Offreemploi offreemploi)
  {//rend persistant les mises à jour opérées sur une instance d’Offreemploi passée en paramètre
	  try {
		  if (offreemploi == null)
			  new Exception("Offreemploi is null");
		  
			return entityManager.merge(offreemploi);
		} catch (Exception e) {
			System.err.println("Problem when updating ");
			e.printStackTrace();
			return null;
		}
	  
	  //return Offreemploi;
  }
	//-----------------------------------------------------------------------------  
  public Offreemploi remove(Offreemploi offreemploi)
  {//supprime les données d’une Offreemploi passée en paramètre
	try {
		if (offreemploi == null)
			  new Exception("Offreemploi is null");
		entityManager.remove(offreemploi);
	} catch (Exception pe) {
		System.err.println("Problem when deleting an entity ");
		pe.printStackTrace();
		return null;
		}
	return offreemploi;
	  
  }
  
  @SuppressWarnings("unchecked")
public List<Offreemploi> findByEntreprise(int idEntreprise)

  {

  Query query = entityManager.createQuery("select oe from Offreemploi oe " +

  "where oe.entreprise.id = :idE " +

  "order by oe.id desc");

  query.setParameter("idE", idEntreprise);

  List<Offreemploi> l = query.getResultList();

  return l;

  }
  
  @SuppressWarnings("unchecked")
public List<Offreemploi> findBySecteurActiviteAndNiveauQualification(int idSecteurActivite, int idNiveauQualification)
  {
  Query query = entityManager.createQuery("select oe from Offreemploi oe join oe.secteuractivites secteurs " +
  "where secteurs.id = :idSA and oe.niveauqualification.id = :idNQ " +
  "order by oe.id desc");
  query.setParameter("idSA", idSecteurActivite);
  query.setParameter("idNQ", idNiveauQualification);
  List<Offreemploi> l = query.getResultList();
  return l;
  }
  
}
