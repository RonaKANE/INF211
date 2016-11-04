package eu.telecom_bretagne.cabinet_recrutement.data.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import static javax.persistence.FetchType.EAGER;


/**
 * The persistent class for the offreemploi database table.
 * 
 */
@Entity
@NamedQuery(name="Offreemploi.findAll", query="SELECT o FROM Offreemploi o")
public class Offreemploi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date datedepot;

	private String descriptionmission;

	private String profilrecherche;

	private String titre;

	//bi-directional many-to-one association to Messagecandidature
	@OneToMany(mappedBy="offreemploi")
	private List<Messagecandidature> messagecandidatures;

	//bi-directional many-to-one association to Messageoffredemploi
	@OneToMany(mappedBy="offreemploi")
	private List<Messageoffredemploi> messageoffredemplois;

	//bi-directional many-to-one association to Entreprise
	@ManyToOne
	@JoinColumn(name="id_ent_fk")
	private Entreprise entreprise;

	//bi-directional many-to-one association to Niveauqualification
	@ManyToOne
	@JoinColumn(name="id_niv_fk")
	private Niveauqualification niveauqualification;

	//bi-directional many-to-many association to Secteuractivite
	@ManyToMany(mappedBy="offreemplois", fetch = EAGER)
	private List<Secteuractivite> secteuractivites;

	public Offreemploi() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDatedepot() {
		return this.datedepot;
	}

	public void setDatedepot(Date datedepot) {
		this.datedepot = datedepot;
	}

	public String getDescriptionmission() {
		return this.descriptionmission;
	}

	public void setDescriptionmission(String descriptionmission) {
		this.descriptionmission = descriptionmission;
	}

	public String getProfilrecherche() {
		return this.profilrecherche;
	}

	public void setProfilrecherche(String profilrecherche) {
		this.profilrecherche = profilrecherche;
	}

	public String getTitre() {
		return this.titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public List<Messagecandidature> getMessagecandidatures() {
		return this.messagecandidatures;
	}

	public void setMessagecandidatures(List<Messagecandidature> messagecandidatures) {
		this.messagecandidatures = messagecandidatures;
	}

	public Messagecandidature addMessagecandidature(Messagecandidature messagecandidature) {
		getMessagecandidatures().add(messagecandidature);
		messagecandidature.setOffreemploi(this);

		return messagecandidature;
	}

	public Messagecandidature removeMessagecandidature(Messagecandidature messagecandidature) {
		getMessagecandidatures().remove(messagecandidature);
		messagecandidature.setOffreemploi(null);

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
		messageoffredemploi.setOffreemploi(this);

		return messageoffredemploi;
	}

	public Messageoffredemploi removeMessageoffredemploi(Messageoffredemploi messageoffredemploi) {
		getMessageoffredemplois().remove(messageoffredemploi);
		messageoffredemploi.setOffreemploi(null);

		return messageoffredemploi;
	}

	public Entreprise getEntreprise() {
		return this.entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public Niveauqualification getNiveauqualification() {
		return this.niveauqualification;
	}

	public void setNiveauqualification(Niveauqualification niveauqualification) {
		this.niveauqualification = niveauqualification;
	}

	public List<Secteuractivite> getSecteuractivites() {
		return this.secteuractivites;
	}

	public void setSecteuractivites(List<Secteuractivite> secteuractivites) {
		this.secteuractivites = secteuractivites;
	}

}