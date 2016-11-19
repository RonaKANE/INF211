<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator,
            eu.telecom_bretagne.cabinet_recrutement.front.utils.Utils,
            eu.telecom_bretagne.cabinet_recrutement.service.IServiceCandidature,
            eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature,
            eu.telecom_bretagne.cabinet_recrutement.data.model.Secteuractivite"
            %>
            
<%
  // Récupération du paramètre (id) passé par l'URL : http://localhost:8080/infos_entreprises.jsp?id=1
  // Attention : la valeur récupérée, même numérique, est sous la forme d'une chaîne de caractères.
  String idString = request.getParameter("id");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cabinet de recrutement : liste des candidatures référencées</title>
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
		    		    IServiceCandidature serviceCandidature = (IServiceCandidature) ServicesLocator.getInstance().getRemoteInterface("ServiceCandidature");
		    		  	// Appel de la fonctionnalité désirée auprès du service
		    		    Candidature c = serviceCandidature.getCandidature(id);
		    %>
		    
		    <!-- Affichage des information récupérées -->
		    
		    <h2>Infos Candidature :</h2>

		    <table id="affichage">
		      <tr>
		        <th style="width: 170px;">Identifiant :</th>
		        <td>
		          CAND_<%=c.getId()%>
		        </td>
		      </tr>
		      <tr>
		        <th>Nom :</th>
		        <td>
		          <%=c.getNom()%>
		        </td>
		      </tr>
		      <tr>
		        <th>Prénom :</th>
		        <td>
		          <%=c.getPrenom()%>
		        </td>
		      </tr>
		      <tr>
		        <th>Date de naissance :</th>
		        <td>
		          <%=c.getDatenaissance()%>
		        </td>
		      </tr>
		       <tr>
		        <th>Adresse Postale :</th>
		        <td>
		          <%=c.getAdressePostale()%>
		        </td>
		      </tr>
		       <tr>
		        <th>Adresse mail :</th>
		        <td>
		          <%=c.getAdresseemail()%>
		        </td>
		      </tr>
		      <tr>
		        <th>CV :</th>
		        <td>
		          <%=c.getCv()%>
		        </td>
		      </tr>
		          <tr>
		        <th>Niveau de qualification :</th>
		        <td>
		          <%=c.getNiveauqualification().getIntitule()%>
		        </td>
		      </tr>
		       <tr>
		        <th>Secteurs d'activité :</th>
		        <td>
		          <% for (Secteuractivite s : c.getSecteuractivites())
		        { %>
		          <%= s.getIntitule()%> <br>
		          <%} %>
		        </td>
		      </tr>
		       <tr>
		        <th>Date dépôt :</th>
		        <td>
		          <%=c.getDatedepot()%>
		        </td>
		      </tr>
		    </table>
        
        <a href="template.jsp?action=liste_candidatures">Retour à la liste des candidatures</a>

		    <%
		  }
		%>
		
</body>

</html>