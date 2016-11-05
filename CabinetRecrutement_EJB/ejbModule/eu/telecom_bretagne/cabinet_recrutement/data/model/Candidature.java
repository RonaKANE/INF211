package eu.telecom_bretagne.cabinet_recrutement.data.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

import static javax.persistence.FetchType.EAGER;


/**
 * The persistent class for the candidature database table.
 * 
 */
@Entity
@NamedQuery(name="Candidature.findAll", query="SELECT c FROM Candidature c")
public class Candidature implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="candidature_id_seq", sequenceName="candidature_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="candidature_id_seq")
	private Integer id;

	@Column(name="adresse_postale")
	private String adressePostale;

	private String adresseemail;

	private String cv;

	@Temporal(TemporalType.DATE)
	private Date datedepot;

	@Temporal(TemporalType.DATE)
	private Date datenaissance;

	private String nom;

	private String prenom;

	//bi-directional many-to-one association to Niveauqualification
	@ManyToOne
	@JoinColumn(name="id_niv_fk")
	private Niveauqualification niveauqualification;

	//bi-directional many-to-one association to Messagecandidature
	@OneToMany(mappedBy="candidature")
	private List<Messagecandidature> messagecandidatures;

	//bi-directional many-to-one association to Messageoffredemploi
	@OneToMany(mappedBy="candidature")
	private List<Messageoffredemploi> messageoffredemplois;

	//bi-directional many-to-many association to Secteuractivite
	@ManyToMany(mappedBy="candidatures", fetch = EAGER)
	private List<Secteuractivite> secteuractivites;

	public Candidature() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdressePostale() {
		return this.adressePostale;
	}

	public void setAdressePostale(String adressePostale) {
		this.adressePostale = adressePostale;
	}

	public String getAdresseemail() {
		return this.adresseemail;
	}

	public void setAdresseemail(String adresseemail) {
		this.adresseemail = adresseemail;
	}

	public String getCv() {
		return this.cv;
	}

	public void setCv(String cv) {
		this.cv = cv;
	}

	public Date getDatedepot() {
		return this.datedepot;
	}

	public void setDatedepot(Date datedepot) {
		this.datedepot = datedepot;
	}

	public Date getDatenaissance() {
		return this.datenaissance;
	}

	public void setDatenaissance(Date datenaissance) {
		this.datenaissance = datenaissance;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Niveauqualification getNiveauqualification() {
		return this.niveauqualification;
	}

	public void setNiveauqualification(Niveauqualification niveauqualification) {
		this.niveauqualification = niveauqualification;
	}

	public List<Messagecandidature> getMessagecandidatures() {
		return this.messagecandidatures;
	}

	public void setMessagecandidatures(List<Messagecandidature> messagecandidatures) {
		this.messagecandidatures = messagecandidatures;
	}

	public Messagecandidature addMessagecandidature(Messagecandidature messagecandidature) {
		getMessagecandidatures().add(messagecandidature);
		messagecandidature.setCandidature(this);

		return messagecandidature;
	}

	public Messagecandidature removeMessagecandidature(Messagecandidature messagecandidature) {
		getMessagecandidatures().remove(messagecandidature);
		messagecandidature.setCandidature(null);

		return messagecandidature;
	}

	public List<Messageoffredemploi> getMessageoffredemplois() {
		return this.messageoffredemplois;
	}

	public void setMessageoffredemplois(List<Messageoffredemploi> messageoffredemplois) {
		this.messageoffredemplois = messageoffredemplois;
	}

	public Messageoffredemploi addMessageoffredemploi(Messageoffredemploi messageoffredemploi) {
		getMessageoffredemplois().add(messageoffredemploi);
		messageoffredemploi.setCandidature(this);

		return messageoffredemploi;
	}

	public Messageoffredemploi removeMessageoffredemploi(Messageoffredemploi messageoffredemploi) {
		getMessageoffredemplois().remove(messageoffredemploi);
		messageoffredemploi.setCandidature(null);

		return messageoffredemploi;
	}

	public List<Secteuractivite> getSecteuractivites() {
		return this.secteuractivites;
	}

	public void setSecteuractivites(List<Secteuractivite> secteuractivites) {
		this.secteuractivites = secteuractivites;
	}

}