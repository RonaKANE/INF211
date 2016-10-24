package eu.telecom_bretagne.cabinet_recrutement.data.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the messagecandidature database table.
 * 
 */
@Entity
@NamedQuery(name="Messagecandidature.findAll", query="SELECT m FROM Messagecandidature m")
public class Messagecandidature implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String corpsmessage;

	@Temporal(TemporalType.DATE)
	private Date dateenvoi;

	//bi-directional many-to-one association to Candidature
	@ManyToOne
	@JoinColumn(name="id_candidature_fk")
	private Candidature candidature;

	//bi-directional many-to-one association to Offreemploi
	@ManyToOne
	@JoinColumn(name="id_offre_fk")
	private Offreemploi offreemploi;

	public Messagecandidature() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCorpsmessage() {
		return this.corpsmessage;
	}

	public void setCorpsmessage(String corpsmessage) {
		this.corpsmessage = corpsmessage;
	}

	public Date getDateenvoi() {
		return this.dateenvoi;
	}

	public void setDateenvoi(Date dateenvoi) {
		this.dateenvoi = dateenvoi;
	}

	public Candidature getCandidature() {
		return this.candidature;
	}

	public void setCandidature(Candidature candidature) {
		this.candidature = candidature;
	}

	public Offreemploi getOffreemploi() {
		return this.offreemploi;
	}

	public void setOffreemploi(Offreemploi offreemploi) {
		this.offreemploi = offreemploi;
	}

}