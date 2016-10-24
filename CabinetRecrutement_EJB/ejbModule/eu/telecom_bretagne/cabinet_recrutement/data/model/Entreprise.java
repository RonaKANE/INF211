package eu.telecom_bretagne.cabinet_recrutement.data.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the entreprise database table.
 * 
 */
@Entity
@NamedQuery(name="Entreprise.findAll", query="SELECT e FROM Entreprise e")
public class Entreprise implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="adresse_postale")
	private String adressePostale;

	private String descriptif;

	private String nom;

	//bi-directional many-to-one association to Offreemploi
	@OneToMany(mappedBy="entreprise")
	private List<Offreemploi> offreemplois;

	public Entreprise() {
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

	public String getDescriptif() {
		return this.descriptif;
	}

	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Offreemploi> getOffreemplois() {
		return this.offreemplois;
	}

	public void setOffreemplois(List<Offreemploi> offreemplois) {
		this.offreemplois = offreemplois;
	}

	public Offreemploi addOffreemploi(Offreemploi offreemploi) {
		getOffreemplois().add(offreemploi);
		offreemploi.setEntreprise(this);

		return offreemploi;
	}

	public Offreemploi removeOffreemploi(Offreemploi offreemploi) {
		getOffreemplois().remove(offreemploi);
		offreemploi.setEntreprise(null);

		return offreemploi;
	}

}