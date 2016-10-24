package eu.telecom_bretagne.cabinet_recrutement.data.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the niveauqualification database table.
 * 
 */
@Entity
@NamedQuery(name="Niveauqualification.findAll", query="SELECT n FROM Niveauqualification n")
public class Niveauqualification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String intitule;

	//bi-directional many-to-one association to Candidature
	@OneToMany(mappedBy="niveauqualification")
	private List<Candidature> candidatures;

	//bi-directional many-to-one association to Offreemploi
	@OneToMany(mappedBy="niveauqualification")
	private List<Offreemploi> offreemplois;

	public Niveauqualification() {
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

	public Candidature addCandidature(Candidature candidature) {
		getCandidatures().add(candidature);
		candidature.setNiveauqualification(this);

		return candidature;
	}

	public Candidature removeCandidature(Candidature candidature) {
		getCandidatures().remove(candidature);
		candidature.setNiveauqualification(null);

		return candidature;
	}

	public List<Offreemploi> getOffreemplois() {
		return this.offreemplois;
	}

	public void setOffreemplois(List<Offreemploi> offreemplois) {
		this.offreemplois = offreemplois;
	}

	public Offreemploi addOffreemploi(Offreemploi offreemploi) {
		getOffreemplois().add(offreemploi);
		offreemploi.setNiveauqualification(this);

		return offreemploi;
	}

	public Offreemploi removeOffreemploi(Offreemploi offreemploi) {
		getOffreemplois().remove(offreemploi);
		offreemploi.setNiveauqualification(null);

		return offreemploi;
	}

}