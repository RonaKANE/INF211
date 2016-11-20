package eu.telecom_bretagne.cabinet_recrutement.data.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eu.telecom_bretagne.cabinet_recrutement.data.model.Messagecandidature;;

/**
 * Session Bean implementation class MessageCandidature
 */
@Stateless
@LocalBean
public class MessageCandidatureDAO {

	/**
	 * Référence vers le gestionnaire de persistance.
	 */
	@PersistenceContext
	EntityManager entityManager;
    /**
     * Default constructor. 
     */
    public MessageCandidatureDAO() {
        // TODO Auto-generated constructor stub
    }

	public Messagecandidature findById(Integer id) {
		return entityManager.find(Messagecandidature.class, id);
	}

	public List<Messagecandidature> findAll() {
		Query query = entityManager
				.createQuery("select Messagecandidature from Messagecandidature order by Messagecandidature.id desc");
		List<Messagecandidature> l = query.getResultList();

		return (List<Messagecandidature>) l;
	}

	public Messagecandidature persist(Messagecandidature messageCandidature) {
		try {
			if (messageCandidature == null)
				new Exception("MessageCandidature is null");

			entityManager.persist(messageCandidature);

		} catch (Exception e) {
			System.err.println("Problem when saving ");
			e.printStackTrace();
		}
		return messageCandidature;
	}

	public Messagecandidature update(Messagecandidature messageCandidature) {
		try {
			if (messageCandidature == null)
				new Exception("MessageCandidature is null");

			return entityManager.merge(messageCandidature);

		} catch (Exception e) {
			System.err.println("Problem when updating ");
			e.printStackTrace();
			return null;
		}
	}

	public Messagecandidature remove(Messagecandidature messageCandidature) {
		try {
			if (messageCandidature == null)
				new Exception("messageCandidature is null");

			entityManager.remove(messageCandidature);

		} catch (Exception pe) {

			System.err.println("Problem when deleting an entity ");
			pe.printStackTrace();
			return null;
		}
		return messageCandidature;

	}
	
	 @SuppressWarnings("unchecked")
	 public List<Messagecandidature> findByOffre(int idOffre)

	   {

	   Query query = entityManager.createQuery("select m from Messagecandidature m " +

	   "where m.offreemploi.id = :idE " +

	   "order by m.id desc");

	   query.setParameter("idE", idOffre);

	   List<Messagecandidature> l = query.getResultList();

	   return l;

	   }

}
