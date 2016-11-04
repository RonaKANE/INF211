<%@page import="eu.telecom_bretagne.cabinet_recrutement.data.model.Niveauqualification"%>
<%@page import="eu.telecom_bretagne.cabinet_recrutement.data.model.Offreemploi"%>
<%@page import="eu.telecom_bretagne.cabinet_recrutement.service.IServiceOffreEmploi"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceOffreEmploi,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Offreemploi,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Secteuractivite,
                java.util.List"%>
<%
  // Récupération du service (bean session)
	IServiceOffreEmploi serviceOffreEmploi = (IServiceOffreEmploi) ServicesLocator.getInstance().getRemoteInterface("ServiceOffreEmploi");
// Appel de la fonctionnalité désirée auprès du service
	List<Offreemploi> offresEmploi = serviceOffreEmploi.listOffreemploi();
%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cabinet de recrutement : liste des offres d'emploi référencées</title>
<link rel="stylesheet" href="styles.css" type="text/css" />
</head>

<body>

<h2>Liste des offres d'emploi référencées :</h2>

		<table id="affichage">
		<tr>
				  <th>Numéro</th>
				  <th>Titre</th>
				  <th>Entreprise</th>
				  <th>Descriptif mission</th>
				  <th>Profil recherché</th>
				  <th>Niveau de qualification</th>
				  <th>Secteurs d'activité</th>
				  <th>Date de dépôt</th>
		</tr>
		  <%
		  for(Offreemploi oe: offresEmploi)
		  {
		    %>
			<tr>
			     <td> <%=oe.getId()%> </td>
			     <td><a href="template.jsp?action=infos_offre&id=<%=oe.getId()%>"><%=oe.getTitre()%></a></td>
			     <td><%=oe.getEntreprise().getNom()%></td>
			     <td><%=oe.getDescriptionmission()%></td>
			     <td><%=oe.getProfilrecherche()%></td>
			     <td>	<%= oe.getNiveauqualification().getIntitule() %></td>
			     <td>
				<% for (Secteuractivite s : oe.getSecteuractivites())
		        { %>
		          <%= s.getIntitule()%> <br>
		          <%} %>
				</td>
			     <td><%=oe.getDatedepot()%></td>
		    </tr>
		    <%
		  }
		  %>
		</table>

    <a href="template.jsp">Retour au menu</a>




</body>



</html>