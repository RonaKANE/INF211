<%@page import="eu.telecom_bretagne.cabinet_recrutement.data.model.Secteuractivite"%>
<%@page import="eu.telecom_bretagne.cabinet_recrutement.service.IServiceEntreprise"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator,
            eu.telecom_bretagne.cabinet_recrutement.front.utils.Utils,
            eu.telecom_bretagne.cabinet_recrutement.service.IServiceOffreEmploi,
            eu.telecom_bretagne.cabinet_recrutement.service.IServiceCandidature,
            eu.telecom_bretagne.cabinet_recrutement.data.model.Offreemploi,
            eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature,
            java.util.List"%>
            
<%
  // Récupération du paramètre (id) passé par l'URL : http://localhost:8080/infos_entreprises.jsp?id=1
  // Attention : la valeur récupérée, même numérique, est sous la forme d'une chaîne de caractères.
  String idString = request.getParameter("id");
%>
            
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cabinet de recrutement : liste des offres d'emplois référencées</title>
<link rel="stylesheet" href="styles.css" type="text/css" />
</head>

<body>
<%
		  // Test en cas d'appel incorrect
		  if(idString == null)
		  {
		    %>
		    <p class="erreur">Erreur : il n'y a aucune entreprise qui corresponde à cette recherche.</p>
		    <%
		    	}
		    		  else // C'est à priori correct...
		    		  {
		    		  	// Transformation de la chaine "idString" en un entier
		            int id = Integer.parseInt(idString);
		    		  	// Récupération du service (bean session)
		    		    IServiceOffreEmploi serviceOffreEmploi = (IServiceOffreEmploi) ServicesLocator.getInstance().getRemoteInterface("ServiceOffreEmploi");
		    		  	// Appel de la fonctionnalité désirée auprès du service
		    		    Offreemploi oe = serviceOffreEmploi.getOffreEmploi(id);
		    		  	

		    %>
		    
		    <!-- Affichage des information récupérées -->
		    
		    <h2>Infos Offre Emploi :</h2>

		    <table id="affichage">
		      <tr>
		        <th style="width: 170px;">Numéro :</th>
		        <td>
		          <%=oe.getId()%>
		        </td>
		      </tr>
		      <tr>
		        <th>Titre :</th>
		        <td>
		          <%=oe.getTitre()%>
		        </td>
		      </tr>
		      <tr>
		        <th>Entreprise :</th>
		        <td>
		          <%=oe.getEntreprise().getNom()%>
		        </td>
		      </tr>
		      <tr>
		        <th>Descriptif mission :</th>
		        <td>
		          <%=oe.getDescriptionmission()%>
		        </td>
		      </tr>
		       <tr>
		        <th>Profil recherché :</th>
		        <td>
		          <%=oe.getProfilrecherche()%>
		        </td>
		      </tr>
		       <tr>
		        <th>Niveau de qualification :</th>
		        <td>
		          <%=oe.getNiveauqualification().getIntitule()%>
		        </td>
		      </tr>
		       <tr>
		        <th>Secteurs d'activité :</th>
		         <td>
		        <% for (Secteuractivite s : oe.getSecteuractivites())
		        { %>
		       
		          <%= s.getIntitule()%> <br>
		          <%} %>
		        </td>
		      </tr>
		       <tr>
		        <th>Date dépôt :</th>
		        <td>
		          <%=oe.getDatedepot()%>
		        </td>
		      </tr>
		    </table>
        
        
        <%
  // Récupération du service (bean session)
	IServiceCandidature serviceCandidature = (IServiceCandidature) ServicesLocator.getInstance().getRemoteInterface("ServiceCandidature");
// Appel de la fonctionnalité désirée auprès du service
	List<Candidature> candidatures = serviceCandidature.candidaturesByOffre(id);
%>    
    

<h2>Liste des candidatures compatibles avec l'offre :</h2>

		<table id="affichage">
		<tr>
				  <th>Identifiant</th>
				  <th>Nom</th>
				  <th>Prénom</th>
				  <th>Date de naissance</th>
				  <th>Adresse postale</th>
				  <th>Adresse email</th>
				  <th>CV</th>
				  <th>Niveau de qualification</th>
				  <th>Liste des secteurs d'activité</th>
				  <th>Date de dépôt</th>
		</tr>
		  <%
		  for(Candidature c : candidatures)
		  {
		    %>

		<tr>
			     <td>CAND_<%=c.getId()%> </td>
			     <td><a href="template.jsp?action=infos_candidature&id=<%=c.getId()%>"><%=c.getNom()%></a></td>
			     <td><%=c.getPrenom()%></td>
			     <td><%=c.getDatenaissance()%></td>
			     <td><%=c.getAdressePostale()%></td>
			     <td><%=c.getAdresseemail()%></td>
			     <td><%=c.getCv()%></td>
			     <td><%=c.getNiveauqualification().getIntitule()%></td>
			     <td>
			      <% for (Secteuractivite s : c.getSecteuractivites())
		        { %>
		          <%= s.getIntitule()%> <br>
		          <%} %>
			     </td>
			     <td><%=c.getDatedepot()%></td>
	    </tr>
		    <%
		  }
		  %>
		</table>
		
        <a href="template.jsp?action=liste_offres">Retour à la liste des offres d'emploi</a>

		    <%
		  }
		%>
		
</body>
</html>