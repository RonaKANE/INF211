package eu.telecom_bretagne.cabinet_recrutement.data.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eu.telecom_bretagne.cabinet_recrutement.data.model.Messageoffredemploi;

/**
 * Session Bean implementation class MessageOffreEmploiDAO
 */
@Stateless
@LocalBean
public class MessageOffreEmploiDAO {

	/**
	 * Référence vers le gestionnaire de persistance.
	 */
	@PersistenceContext
	EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public MessageOffreEmploiDAO() {
	}

	public Messageoffredemploi findById(Integer id) {
		return entityManager.find(Messageoffredemploi.class, id);
	}

	public List<Messageoffredemploi> findAll() {
		Query query = entityManager
				.createQuery("select Messageoffredemploi from Messageoffredemploi order by Messageoffredemploi.id desc");
		List<Messageoffredemploi> l = query.getResultList();

		return (List<Messageoffredemploi>) l;
	}

	public Messageoffredemploi persist(Messageoffredemploi messageoffredemploi) {
		try {
			if (messageoffredemploi == null)
				new Exception("MessageOffreemploi is null");

			entityManager.persist(messageoffredemploi);

		} catch (Exception e) {
			System.err.println("Problem when saving ");
			e.printStackTrace();
		}
		return messageoffredemploi;
	}

	public Messageoffredemploi update(Messageoffredemploi messageoffredemploi) {
		try {
			if (messageoffredemploi == null)
				new Exception("MessageOffreemploi is null");

			return entityManager.merge(messageoffredemploi);

		} catch (Exception e) {
			System.err.println("Problem when updating ");
			e.printStackTrace();
			return null;
		}
	}

	public Messageoffredemploi remove(Messageoffredemploi messageoffredemploi) {
		try {
			if (messageoffredemploi == null)
				new Exception("Offreemploi is null");

			entityManager.remove(messageoffredemploi);

		} catch (Exception pe) {

			System.err.println("Problem when deleting an entity ");
			pe.printStackTrace();
			return null;
		}
		return messageoffredemploi;

	}
	
	 @SuppressWarnings("unchecked")
	 public List<Messageoffredemploi> findByOffre(int idOffre)

	   {

	   Query query = entityManager.createQuery("select m from Messageoffredemploi m " +

	   "where m.offreemploi.id = :idE " +

	   "order by m.id desc");

	   query.setParameter("idE", idOffre);

	   List<Messageoffredemploi> l = query.getResultList();

	   return l;

	   }
}
