package eu.telecom_bretagne.cabinet_recrutement.front.rss;

import java.io.Writer;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.jsp.JspWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature;
import eu.telecom_bretagne.cabinet_recrutement.data.model.Offreemploi;
import eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator;
import eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocatorException;
import eu.telecom_bretagne.cabinet_recrutement.front.utils.Utils;
import eu.telecom_bretagne.cabinet_recrutement.service.IServiceCandidature;
import eu.telecom_bretagne.cabinet_recrutement.service.IServiceOffreEmploi;

/**
 * Classe permettant la gestion des flux RSS publiant la liste des offres
 * d'emploi et la liste des candidatures. La classe contient deux méthodes
 * statiques utilisables au sein d'un JSP :
 * <ul>
 * 		<li>{@code GenerationFluxRSS.offresEmploi (Writer writer, String urlBase)}</li>
 * 		<li>{@code GenerationFluxRSS.candidatures(Writer writer, String urlBase)}</li>
 * </ul>
 * @author Philippe TANGUY
 */
public class GenerationFluxRSS
{
	//-----------------------------------------------------------------------------
	/**
	 * Construction du flux RSS de la liste des offres d'emploi. Celles-ci sont obtenues
	 * par l'appel de la méthode {@code listeDesOffres()}, voir : {@link IServiceOffreEmploi}.
	 * @param writer  l'instance du {@link Writer} sur lequel sera écrit le flux RSS.
	 *                La méthode étant appelée au sein d'un JSP, celui-ci est l'instance
	 *                de l'objet prédéfini {@code out}, instance de {@link JspWriter}.
	 * @param urlBase l'URL de base (une chaîne de caractères) permettant la récupération
	 *                des éléments du flux.
	 * @throws JAXBException
	 * @throws ServicesLocatorException
	 */
	public static void offresEmploi(Writer writer, String urlBase) throws JAXBException, ServicesLocatorException
	{
		// Récupération du service de gestion des offres d'emploi à l'aide de
		// la classe ServiceLocator.
		// A éventuellement adapter à votre projet.
		IServiceOffreEmploi serviceOffreEmploi = (IServiceOffreEmploi) ServicesLocator.getInstance().getRemoteInterface("ServiceOffreEmploi");
		
		// Récupération des offres d'emploi.
		List<Offreemploi> offres = serviceOffreEmploi.listOffreemploi();
		
		// Création du "contexte" JAXB. Celui-ci est paramétré avec le nom du package
		// contenant les classes générées par l'outil xjc.
		JAXBContext jc = JAXBContext.newInstance("eu.telecom_bretagne.cabinet_recrutement.front.rss");
		
		// Le "marshaller" est la classe permettra de gérer la sérialisation :
		// instances --> flux XML.
		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // Pour que le flux généré soit joli...
		
		// Instance de l'objet ObjectFactory qui permettra de créer les instances qui
		// permettront au marshaller de générer le flux XML.
		ObjectFactory fabrique = new ObjectFactory();

		// Création de l'objet racine (élément <rss>)
		Rss rss = fabrique.createRss();
		// Mise à jour du numéro de version RSS
		rss.setVersion(new BigDecimal(2));
		
		// Création du "channel" (élément <channel>)
		Channel channel = fabrique.createChannel();

		// A compléter : les infos du channel
		
		// Inclusion du channel dans le rss.
		rss.setChannel(channel);
		
		// ---------------------------------------
		// A compléter...
		//
		// Principe, pour chaque offre :
		//   - créer un élement item
		//   - renseigner les infos
		//   - inclusion de l'item dans le channel
		// ---------------------------------------
		
		for (Offreemploi offre : offres) {
			Item item = new Item();
			item.setTitle(offre.getTitre());
			item.setDescription(offre.getDescriptionmission());
			item.setLink("http://localhost:8080/CabinetRecrutement_Web/template.jsp?action=infos_offre&id="+offre.getId());
			channel.getItem().add(item);
		}
		
		// A ce stade l'objet rss est complet (les données ont toutes été incluses), on
		// procède à la sérialisation. La méthode prend en paramètres l'objet à sérialiser
		// (rss), le flux sera écrit sur le writer.
		// Le writer (instance de l'interface Writer) est en fait l'objet out provenant
		// du JSP. ce qui sera écrit sur ce flux sera renvoyé par le serveur Web sur le
		// navigateur (ou l'outil affichant le flux RSS).
		marshaller.marshal(rss, writer);
	}
	//-----------------------------------------------------------------------------
	/**
	 * Construction du flux RSS de la liste des candidature. Celles-ci sont obtenues par
	 * l'appel de la méthode {@code listeDesCandidatures()}, voir : {@link IServiceCandidature}.
	 * @param writer  l'instance du {@link Writer} sur lequel sera écrit le flux RSS.
	 *                La méthode étant appelée au sein d'un JSP, celui-ci est l'instance
	 *                de l'objet prédéfini {@code out}, instance de {@link JspWriter}.
	 * @param urlBase l'URL de base (une chaîne de caractères) permettant la récupération
	 *                des éléments du flux.
	 * @throws JAXBException
	 * @throws ServicesLocatorException
	 */
	public static void candidatures(Writer writer, String urlBase) throws JAXBException, ServicesLocatorException
	{
		// Récupération du service de gestion des offres d'emploi à l'aide de
		// la classe ServiceLocator.
		// A éventuellement adapter à votre projet.
		IServiceCandidature serviceCandidature = (IServiceCandidature) ServicesLocator.getInstance().getRemoteInterface("ServiceCandidature");
		
		// Récupération des offres d'emploi.
		List<Candidature> candidatures = serviceCandidature.listCandidature();
		
		// Création du "contexte" JAXB. Celui-ci est paramétré avec le nom du package
		// contenant les classes générées par l'outil xjc.
		JAXBContext jc = JAXBContext.newInstance("eu.telecom_bretagne.cabinet_recrutement.front.rss");
		
		// Le "marshaller" est la classe permettra de gérer la sérialisation :
		// instances --> flux XML.
		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // Pour que le flux généré soit joli...
		
		// Instance de l'objet ObjectFactory qui permettra de créer les instances qui
		// permettront au marshaller de générer le flux XML.
		ObjectFactory fabrique = new ObjectFactory();

		// Création de l'objet racine (élément <rss>)
		Rss rss = fabrique.createRss();
		// Mise à jour du numéro de version RSS
		rss.setVersion(new BigDecimal(2));
		
		// Création du "channel" (élément <channel>)
		Channel channel = fabrique.createChannel();

		// A compléter : les infos du channel
		
		// Inclusion du channel dans le rss.
		rss.setChannel(channel);
		
		// ---------------------------------------
		// A compléter...
		//
		// Principe, pour chaque offre :
		//   - créer un élement item
		//   - renseigner les infos
		//   - inclusion de l'item dans le channel
		// ---------------------------------------
		
		for (Candidature candidature : candidatures) {
			Item item = new Item();
			item.setTitle(candidature.getPrenom());
			item.setDescription(candidature.getCv());
			item.setLink("http://localhost:8080/CabinetRecrutement_Web/template.jsp?action=infos_candidature&id="+candidature.getId());
			channel.getItem().add(item);
		}
		
		// A ce stade l'objet rss est complet (les données ont toutes été incluses), on
		// procède à la sérialisation. La méthode prend en paramètres l'objet à sérialiser
		// (rss), le flux sera écrit sur le writer.
		// Le writer (instance de l'interface Writer) est en fait l'objet out provenant
		// du JSP. ce qui sera écrit sur ce flux sera renvoyé par le serveur Web sur le
		// navigateur (ou l'outil affichant le flux RSS).
		marshaller.marshal(rss, writer);
	}
	//-----------------------------------------------------------------------------
}
