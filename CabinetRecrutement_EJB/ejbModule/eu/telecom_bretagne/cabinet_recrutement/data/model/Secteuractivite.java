package eu.telecom_bretagne.cabinet_recrutement.data.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the secteuractivite database table.
 * 
 */
@Entity
@NamedQuery(name="Secteuractivite.findAll", query="SELECT s FROM Secteuractivite s")
public class Secteuractivite implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String intitule;

	//bi-directional many-to-many association to Candidature
	@ManyToMany
	@JoinTable(
		name="secteurcandidature"
		, joinColumns={
			@JoinColumn(name="id_secacti_fk")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_candidature_fk")
			}
		)
	private List<Candidature> candidatures;

	//bi-directional many-to-many association to Offreemploi
	@ManyToMany
	@JoinTable(
		name="secteuremploi"
		, joinColumns={
			@JoinColumn(name="id_secacti_fk")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_offre_fk")
			}
		)
	private List<Offreemploi> offreemplois;

	public Secteuractivite() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIntitule() {
		return this.intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public List<Candidature> getCandidatures() {
		return this.candidatures;
	}

	public void setCandidatures(List<Candidature> candidatures) {
		this.candidatures = candidatures;
	}

	public List<Offreemploi> getOffreemplois() {
		return this.offreemplois;
	}

	public void setOffreemplois(List<Offreemploi> offreemplois) {
		this.offreemplois = offreemplois;
	}

}