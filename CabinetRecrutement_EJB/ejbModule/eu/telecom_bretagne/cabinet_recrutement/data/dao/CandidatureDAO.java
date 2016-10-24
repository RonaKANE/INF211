package eu.telecom_bretagne.cabinet_recrutement.data.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature;

@Stateless
@LocalBean
public class CandidatureDAO {

	// -----------------------------------------------------------------------------
	/**
	 * Référence vers le gestionnaire de persistance.
	 */
	@PersistenceContext
	EntityManager entityManager;

	// -----------------------------------------------------------------------------
	/**
	 * Default constructor.
	 */
	public CandidatureDAO() {
		// TODO Auto-generated constructor stub
	}

	// -----------------------------------------------------------------------------
	public Candidature findById(Integer id) {
		return entityManager.find(Candidature.class, id);
	}

	// ----------------------------------------------------------------------------
	@SuppressWarnings("unchecked")
	public List<Candidature> findAll() {
		javax.persistence.Query query = entityManager
				.createQuery("select candidature from Candidature candidature order by candidature.id desc");
		List<Candidature> l = query.getResultList();

		return (List<Candidature>) l;
	}

	// -----------------------------------------------------------------------------
	public Candidature persist(Candidature candidature) { // stockera dans la
															// base de données
															// les informations
															// d’une Candidature
															// (présent sous la
															// forme d’une
															// instance
															// d’Candidature
		try {
			if (candidature == null)
				new Exception("Candidature is null");

			entityManager.persist(candidature);
		} catch (Exception e) {
			System.err.println("Problem when saving ");
			e.printStackTrace();
		}
		return candidature;
	}

	// -----------------------------------------------------------------------------
	public Candidature update(Candidature candidature) {// rend persistant les
														// mises à jour opérées
														// sur une instance
														// d’Candidature passée
														// en paramètre
		try {
			if (candidature == null)
				new Exception("Candidature is null");

			return entityManager.merge(candidature);
		} catch (Exception e) {
			System.err.println("Problem when updating ");
			e.printStackTrace();
			return null;
		}

		// return Candidature;
	}

	// -----------------------------------------------------------------------------
	public Candidature remove(Candidature candidature) {// supprime les données
														// d’une Candidature
														// passée en paramètre
		try {
			if (candidature == null)
				new Exception("Candidature is null");
			entityManager.remove(candidature);
		} catch (Exception pe) {
			System.err.println("Problem when deleting an entity ");
			pe.printStackTrace();
			return null;
		}
		return candidature;

	}

	// Ajout d’une méthode pour l’obtention de la liste des candidatures qui
	// correspondent à un secteur
	// d’activité et un niveau de qualification donnés

	@SuppressWarnings("unchecked")
	public List<Candidature> findBySecteurActiviteAndNiveauQualification(
			int idSecteurActivite, int idNiveauQualification)

	{

		Query query = entityManager
				.createQuery("select c from Candidature c join c.secteuractivites secteur "
						+ "where secteur.id = :idSA and c.niveauqualification.id = :idNQ "
						+ "order by c.id desc");

		query.setParameter("idSA", idSecteurActivite);

		query.setParameter("idNQ", idNiveauQualification);

		List<Candidature> l = query.getResultList();

		return l;

	}
}
