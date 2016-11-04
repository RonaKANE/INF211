<%@page import="eu.telecom_bretagne.cabinet_recrutement.data.model.Secteuractivite"%>
<%@page import="eu.telecom_bretagne.cabinet_recrutement.data.model.Niveauqualification"%>
<%@page import="eu.telecom_bretagne.cabinet_recrutement.service.ServiceIndexation"%>
<%@page import="eu.telecom_bretagne.cabinet_recrutement.service.ServiceCandidature"%>
<%@page import="eu.telecom_bretagne.cabinet_recrutement.service.IServiceCandidature"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@page import="eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator,
                eu.telecom_bretagne.cabinet_recrutement.front.utils.Utils,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceEntreprise,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Candidature,
                java.util.Date;
                "%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cabinet de recrutement : Nouvelle candidature</title>
</head>
<%
ServiceIndexation services = new ServiceIndexation();
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
					<td><input name="nom" size="20" maxlength="50" type="text">
					</td>
				</tr>
				<tr>
					<th>Prénom :</th>
					<td><input name="prenom" size="20" maxlength="50" type="text">
					</td>
				</tr>
				<tr>
					<th>Date de naissance<br>(format jj/mm/aaaa) :
					</th>
					<td><input name="date_naissance" size="10" maxlength="10"
						type="text"></td>
				</tr>
				<tr>
					<th>Adresse postale (ville) :</th>
					<td><input name="adresse_postale" size="20" maxlength="30"
						type="text"></td>
				</tr>
				<tr>
					<th>Adresse email :</th>
					<td><input name="adresse_email" size="30" maxlength="100"
						type="text"></td>
				</tr>
				<tr>
					<th>Curriculum vitæ :</th>
					<td><textarea rows="7" cols="70" name="cv"></textarea></td>
				</tr>
				<tr>
					<th>Niveau de qualification :</th>
					<td>
						<table id="tab_interne">
							<tbody>
								<tr>
								<% for (Niveauqualification niv : services.niveauQualificationList()) {
									%><td><input name="niveau" value="<%=niv.getId() %>" type="radio"><%=niv.getIntitule() %><br><%
								}
								%>

									</td>
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
								
								<% for (Secteuractivite sec : services.secteursActiviteList()) {
									%><td><input name="niveau" value="<%=sec.getId() %>" type="radio"><%=sec.getIntitule() %><br><%
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
  		<p class="erreur">Impossible de référencer une entreprise sans saisir le nom</p>
  		<%
  	}
 	else
  	{
      // Récupération des autres paramétres
      String prenom     = request.getParameter("prenom");
      String date  = request.getParameter("date_naissance");
      String adressePostale = request.getParameter("adresse_postale");
	  String mail = request.getParameter("adresse_email");
	  String CV = request.getParameter("cv");
	  String [] secteur = request.getParameterValues("secteur");
	  String [] niveau = request.getParameterValues("niveau");
	  
	  
      IServiceCandidature serviceCandidature = (IServiceCandidature) ServicesLocator.getInstance().getRemoteInterface("ServiceCandidature");
      //Candidature candidature = serviceCandidature.newCandidature(nom, prenom, date, adressePostale, mail, new Date(), CV, secteur, niveau);
  	}

}
%>
</html>