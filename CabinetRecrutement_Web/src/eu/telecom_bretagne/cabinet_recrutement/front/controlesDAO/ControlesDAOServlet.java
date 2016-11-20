package eu.telecom_bretagne.cabinet_recrutement.front.controlesDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.telecom_bretagne.cabinet_recrutement.data.dao.CandidatureDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.dao.EntrepriseDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.dao.MessageCandidatureDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.dao.MessageOffreEmploiDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.dao.NiveauQualificationDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.dao.OffreEmploiDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.dao.SecteuractiviteDAO;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Messagecandidature;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Messageoffredemploi;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Niveauqualification;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Offreemploi;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Secteuractivite;
import eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator;
import eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocatorException;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/ControlesDAO")
public class ControlesDAOServlet extends HttpServlet
{
	//-----------------------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	//-----------------------------------------------------------------------------
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControlesDAOServlet()
	{
		super();
	}
	//-----------------------------------------------------------------------------
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// Flot de sortie pour écriture des résultats.
    PrintWriter out = response.getWriter();
    
    // Récupération de la réféence vers le(s) DAO(s)
		EntrepriseDAO entrepriseDAO = null;
		CandidatureDAO candidatureDAO = null;
		OffreEmploiDAO offreEmploiDAO = null;
		NiveauQualificationDAO niveauqualificationDAO = null;
		SecteuractiviteDAO secteuractiviteDAO = null;
		MessageOffreEmploiDAO messageOffreEmploiDAO = null;
		MessageCandidatureDAO messageCandidatureDAO = null;
    try
    {
	    entrepriseDAO = (EntrepriseDAO) ServicesLocator.getInstance().getRemoteInterface("EntrepriseDAO");  
	    candidatureDAO = (CandidatureDAO) ServicesLocator.getInstance().getRemoteInterface("CandidatureDAO");
	    offreEmploiDAO = (OffreEmploiDAO) ServicesLocator.getInstance().getRemoteInterface("OffreEmploiDAO");
	    niveauqualificationDAO = (NiveauQualificationDAO) ServicesLocator.getInstance().getRemoteInterface("NiveauQualificationDAO");
	    secteuractiviteDAO = (SecteuractiviteDAO) ServicesLocator.getInstance().getRemoteInterface("SecteuractiviteDAO");	
	    messageOffreEmploiDAO = (MessageOffreEmploiDAO) ServicesLocator.getInstance().getRemoteInterface("MessageOffreEmploiDAO");
	    messageCandidatureDAO= (MessageCandidatureDAO) ServicesLocator.getInstance().getRemoteInterface("MessageCandidatureDAO");
    }
    catch (ServicesLocatorException e)
    {
    	e.printStackTrace();
    }
		out.println("Contrôles de fonctionnement du DAO EntrepriseDAO");
		out.println();
		
		// Contrôle(s) de fonctionnalités.
		out.println("Liste des entreprises :");
		List<Entreprise> entreprises = entrepriseDAO.findAll();
		
		for(Entreprise entreprise : entreprises)
		{
			out.println(entreprise.getNom());
		}
		out.println();
		
		out.println("Obtention de l'entreprise n° 1 :");
		Entreprise e = entrepriseDAO.findById(1);
		out.println(e.getId());
		out.println(e.getNom());
		out.println(e.getDescriptif());
		out.println(e.getAdressePostale());
		out.println();

		out.println("Obtention de l'entreprise n° 2 :");
		e = entrepriseDAO.findById(2);
		out.println(e.getId());
		out.println(e.getNom());
		out.println(e.getDescriptif());
		out.println(e.getAdressePostale());
		out.println();
		
		// ---------------------------------------------------------
		
		out.println("Contrôles de fonctionnement du DAO CandidatureDAO");
		out.println();
		
		// Contrôle(s) de fonctionnalités.
		out.println("Liste des Candidatures :");
		List<Candidature> candidatures = candidatureDAO.findAll();
		
		for(Candidature Candidature : candidatures)
		{
			out.println(Candidature.getNom());
		}
		out.println();
		
		out.println("Obtention de la Candidature n° 1 :");
		Candidature c = candidatureDAO.findById(1);
		out.println(c.getId());
		out.println(c.getNom());
		out.println(c.getPrenom());
		out.println(c.getDatenaissance());
		out.println(c.getAdressePostale());
		out.println(c.getAdresseemail());
		out.println(c.getCv());
		out.println(c.getDatedepot());
		out.println();

		out.println("Obtention de la Candidature n° 2 :");
		c = candidatureDAO.findById(2);
		out.println(c.getId());
		out.println(c.getNom());
		out.println(c.getPrenom());
		out.println(c.getDatenaissance());
		out.println(c.getAdressePostale());
		out.println(c.getAdresseemail());
		out.println(c.getCv());
		out.println(c.getDatedepot());
		out.println();
		
		out.println("Obtention de la Candidature n° 3 :");
		c = candidatureDAO.findById(3);
		out.println(c.getId());
		out.println(c.getNom());
		out.println(c.getPrenom());
		out.println(c.getDatenaissance());
		out.println(c.getAdressePostale());
		out.println(c.getAdresseemail());
		out.println(c.getCv());
		out.println(c.getDatedepot());
		out.println();
		
		out.println("Liste des candidats pour secteur 1 et niveau de qualification 1");
		
		List<Candidature> myCList = candidatureDAO.findBySecteurActiviteAndNiveauQualification(1, 1);
		
		for(Candidature candidature : myCList)
		{
			out.println(candidature.getNom());
		}
		out.println();
		
		//------------------------------------------------------------------------------------
		
		out.println("Contrôles de fonctionnement du DAO OffreEmploiDAO");
		out.println();
		
		// Contrôle(s) de fonctionnalités.
		out.println("Liste des OffreEmplois :");
		List<Offreemploi> OffreEmplois = offreEmploiDAO.findAll();
		
		for(Offreemploi offreEmploi : OffreEmplois)
		{
			out.println(offreEmploi.getTitre());
		}
		out.println();
		
		out.println("Obtention de l'OffreEmploi n° 1 :");
		Offreemploi oe = offreEmploiDAO.findById(1);
		out.println(oe.getId());
		out.println(oe.getTitre());
		out.println(oe.getDescriptionmission());
		out.println(oe.getProfilrecherche());
		out.println(oe.getDatedepot());
		out.println();

		out.println("Obtention de l'OffreEmploi n° 2 :");
		oe = offreEmploiDAO.findById(2);
		out.println(oe.getId());
		out.println(oe.getTitre());
		out.println(oe.getDescriptionmission());
		out.println(oe.getProfilrecherche());
		out.println(oe.getDatedepot());
		out.println();
		
		out.println("Obtention de l'OffreEmploi n° 3 :");
		oe = offreEmploiDAO.findById(3);
		out.println(oe.getId());
		out.println(oe.getTitre());
		out.println(oe.getDescriptionmission());
		out.println(oe.getProfilrecherche());
		out.println(oe.getDatedepot());
		out.println();		
		
		List <Offreemploi> myOEList1 = offreEmploiDAO.findByEntreprise(0);
		for(Offreemploi myOE : myOEList1)
		{
			out.println(myOE.getTitre());
		}
		out.println();
		
		List <Offreemploi> myOEList2 = offreEmploiDAO.findBySecteurActiviteAndNiveauQualification(1, 1);
		for(Offreemploi myOE : myOEList2)
		{
			out.println(myOE.getTitre());
		}
		out.println();
		
		
		//------------------------------------------------------------------------------------
		
		out.println("Contrôles de fonctionnement du DAO NiveauQualificationDAO");
		out.println();
		
		// Contrôle(s) de fonctionnalités.
		out.println("Liste des NiveauQualifications :");
		List<Niveauqualification> niveauQualifications = niveauqualificationDAO.findAll();
		
		for(Niveauqualification NiveauQualification : niveauQualifications)
		{
			out.println(NiveauQualification.getIntitule());
		}
		out.println();
		
		out.println("Obtention du NiveauQualification n° 1 :");
		Niveauqualification niv = niveauqualificationDAO.findById(1);
		out.println(niv.getId());
		out.println(niv.getIntitule());
		out.println();

		out.println("Obtention du NiveauQualification n° 2 :");
		niv = niveauqualificationDAO.findById(2);
		out.println(niv.getId());
		out.println(niv.getIntitule());
		out.println();
		
		out.println("Obtention du NiveauQualification n° 3 :");
		niv = niveauqualificationDAO.findById(3);
		out.println(niv.getId());
		out.println(niv.getIntitule());
		out.println();
		
	//------------------------------------------------------------------------------------
		
		out.println("Contrôles de fonctionnement du DAO SecteuractiviteDAO");
		out.println();
		
		// Contrôle(s) de fonctionnalités.
		out.println("Liste des Secteuractivites :");
		List<Secteuractivite> secteuractivites = secteuractiviteDAO.findAll();
		
		for(Secteuractivite secteuractivite : secteuractivites)
		{
			out.println(secteuractivite.getIntitule());
		}
		out.println();
		
		out.println("Obtention du Secteur d'activite n° 1 :");
		Secteuractivite sec = secteuractiviteDAO.findById(1);
		out.println(sec.getId());
		out.println(sec.getIntitule());
		out.println();

		out.println("Obtention du Secteur d'activite n° 2 :");
		sec = secteuractiviteDAO.findById(2);
		out.println(sec.getId());
		out.println(sec.getIntitule());
		out.println();
		
		out.println("Obtention du Secteur d'activite n° 3 :");
		sec = secteuractiviteDAO.findById(3);
		out.println(sec.getId());
		out.println(sec.getIntitule());
		out.println();
		
		// Contrôle(s) de fonctionnalités de MessageOffreEmploiDAO.
		out.println("Liste des :");
		List<Messageoffredemploi> messageoffredemplois = messageOffreEmploiDAO.findByOffre(3);
		for(Messageoffredemploi m : messageoffredemplois)
		{
			out.println(m.getId());
		}
		
		// Contrôle(s) de fonctionnalités de MessageCandidatureDAO.
		out.println("Liste des :");
		List<Messagecandidature> messagecandidatures = messageCandidatureDAO.findByOffre(3);
		for(Messagecandidature m : messagecandidatures)
		{
			out.println(m.getId());
		}		
	}
	//-----------------------------------------------------------------------------

}
