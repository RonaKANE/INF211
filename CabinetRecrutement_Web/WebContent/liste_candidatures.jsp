<%@page import="eu.telecom_bretagne.cabinet_recrutement.data.model.Offreemploi"%>
<%@page import="eu.telecom_bretagne.cabinet_recrutement.service.IServiceOffreEmploi"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceCandidature,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Secteuractivite,
                java.util.List"%>
<%
  // Récupération du service (bean session)
	IServiceCandidature serviceCandidature = (IServiceCandidature) ServicesLocator.getInstance().getRemoteInterface("ServiceCandidature");
// Appel de la fonctionnalité désirée auprès du service
	List<Candidature> candidatures = serviceCandidature.listCandidature();
%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cabinet de recrutement : liste des candidatures référencées</title>
<link rel="stylesheet" href="styles.css" type="text/css" />
</head>

<body>

<h2>Liste des Candidatures référencées :</h2>

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

    <a href="template.jsp">Retour au menu</a>




</body>



</html>