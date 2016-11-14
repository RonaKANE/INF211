
<%@page import="java.text.SimpleDateFormat"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page
	import="eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator,
                eu.telecom_bretagne.cabinet_recrutement.front.utils.Utils,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceIndexation,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceCandidature,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Niveauqualification,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Secteuractivite,
                java.util.Date"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cabinet de recrutement : Nouvelle candidature</title>
</head>
<%!
private int [] parseStringArrayToIntArray(String [] strings) {
	int[] intarray = new int[strings.length];
    int i=0;
    for(String str:strings){
        intarray[i] = Integer.parseInt(str.trim());//Exception in this line
        i++;
    }
	    return intarray;
	  
}
%>

<%
// Récupération du service (bean session)
	IServiceIndexation serviceIndexation = (IServiceIndexation) ServicesLocator.getInstance().getRemoteInterface("ServiceIndexation");
%>
<%String nom = request.getParameter("nom");
if(nom == null) // Pas de paramétre "nom" => affichage du formulaire
{
%>
<body>
	<h2>Référencer une nouvelle candidature</h2>

	<form action="template.jsp" method="get">
		<input name="action" value="nouvelle_candidature" type="hidden">
		<table id="affichage">
			<tbody>
				<tr>
					<th style="width: 170px">Nom :</th>
					<td><input name="nom" size="20" maxlength="50" type="text" required>
					</td>
				</tr>
				<tr>
					<th>Prénom :</th>
					<td><input name="prenom" size="20" maxlength="50" type="text" required>
					</td>
				</tr>
				<tr>
					<th>Date de naissance<br>(format jj/mm/aaaa) :
					</th>
					<td><input name="date_naissance" size="10" maxlength="10"
						type="text" required></td>
				</tr>
				<tr>
					<th>Adresse postale (ville) :</th>
					<td><input name="adresse_postale" size="20" maxlength="30"
						type="text" required></td>
				</tr>
				<tr>
					<th>Adresse email :</th>
					<td><input name="adresse_email" size="30" maxlength="100"
						type="email" required></td>
				</tr>
				<tr>
					<th>Curriculum vitæ :</th>
					<td><textarea rows="7" cols="70" name="cv" required></textarea></td>
				</tr>
				<tr>
					<th>Niveau de qualification :</th>
					<td>
						<table id="tab_interne">
							<tbody>
								<tr>
									<% for (Niveauqualification niv : serviceIndexation.niveauQualificationList()) { 
 									%><td><input name="niveau" value="<%=niv.getId() %>"
										type="radio" required><%=niv.getIntitule() %><br>
										<% 
 								}
 								%></td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
				<tr>
					<th>Secteur(s) d'activité :</th>
					<td>
						<table id="tab_interne">
							<tbody>
								<tr>

									<% for (Secteuractivite sec : serviceIndexation.secteursActiviteList()) { 
 									%><td><input name="secteur" value="<%=sec.getId() %>"
										type="checkbox" ><%=sec.getIntitule() %><br>
										<% 
 								}
 								%>
								</tr>

							</tbody>
						</table>
					</td>
				</tr>
			</tbody>
		</table>
		<p>
			<input value="Enregistrer" type="submit">
		</p>

	</form>


</body>
<% }
else {
 	if(nom.equals(""))
  	{
  		%>
<p class="erreur">Impossible de référencer une candidature sans
	saisir le nom</p>
<%
  	}
 	else
  	{
      // Récupération des autres paramétres
      
      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
      String prenom     = request.getParameter("prenom");
      Date date  = formatter.parse(request.getParameter("date_naissance"));
      String adressePostale = request.getParameter("adresse_postale");
	  String mail = request.getParameter("adresse_email");
	  String CV = request.getParameter("cv");
	  int [] secteur = parseStringArrayToIntArray(request.getParameterValues("secteur"));
	  int niveau = Integer.parseInt(request.getParameter("niveau"));
	  
	  
      IServiceCandidature serviceCandidature = (IServiceCandidature) ServicesLocator.getInstance().getRemoteInterface("ServiceCandidature");
      Candidature candidature = serviceCandidature.newCandidature(nom, prenom, date, adressePostale, mail, new Date(), CV, secteur, niveau);
      
      for (int i : secteur)
      	serviceIndexation.indexerCandidat(candidature, i);
      %>
       <table id="affichage">
		      <tr>
		        <th style="width: 170px;">Identifiant :</th>
		        <td>
		          CAND_<%=candidature.getId()%>
		        </td>
		      </tr>
		      <tr>
		        <th>Nom :</th>
		        <td>
		          <%=candidature.getNom()%>
		        </td>
		      </tr>
		      <tr>
		        <th>Prénom :</th>
		        <td>
		          <%=candidature.getPrenom()%>
		        </td>
		      </tr>
		      <tr>
		        <th>Date de naissance :</th>
		        <td>
		          <%=candidature.getDatenaissance()%>
		        </td>
		      </tr>
		       <tr>
		        <th>Adresse Postale :</th>
		        <td>
		          <%=candidature.getAdressePostale()%>
		        </td>
		      </tr>
		       <tr>
		        <th>Adresse mail :</th>
		        <td>
		          <%=candidature.getAdresseemail()%>
		        </td>
		      </tr>
		      <tr>
		        <th>CV :</th>
		        <td>
		          <%=candidature.getCv()%>
		        </td>
		      </tr>
		          <tr>
		        <th>Niveau de qualification :</th>
		        <td>
		          <%=candidature.getNiveauqualification().getIntitule()%>
		        </td>
		      </tr>
		       <tr>
		        <th>Secteurs d'activité :</th>
		        <td>
		          <% for (Secteuractivite s : candidature.getSecteuractivites())
		        { %>
		          <%= s.getIntitule()%> <br>
		          <%} %>
		        </td>
		      </tr>
		       <tr>
		        <th>Date dépôt :</th>
		        <td>
		          <%=candidature.getDatedepot()%>
		        </td>
		      </tr>
		    </table>
      <%
  	
  	}

}
%>
</html>