<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>

<%@page import="eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator,
                eu.telecom_bretagne.cabinet_recrutement.front.utils.Utils,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceOffreEmploi,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceIndexation,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Niveauqualification,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Secteuractivite,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Offreemploi,
                java.util.Date"%>
<%
// Récupération du service (bean session)
	IServiceIndexation serviceIndexation = (IServiceIndexation) ServicesLocator.getInstance().getRemoteInterface("ServiceIndexation");
%>

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
  String titre = request.getParameter("titre");
  if(titre == null) // Pas de paramétre "nom" => affichage du formulaire
  {
  	%>
  	
    <h2>Référencer une nouvelle offre d'emploi</h2>
    
    <form action="template.jsp" method="get">
      <input name="action" value="nouvelle_offre" type="hidden">
	  	<table id="affichage">
	  	  <tbody><tr>
          <th style="width: 170px">Titre de l'offre :</th>
	        <td>
	          <input name="titre" size="20" maxlength="50" type="text" required>
	        </td>
	  	  </tr>
	      <tr>
          <th>Descriptif de la mission :</th>
	        <td>
	          <textarea rows="7" cols="70" name="descriptif_mission" required></textarea>
	        </td>
	      </tr>
        <tr>
          <th>Profil recherché :</th>
          <td>
            <textarea rows="7" cols="70" name="profil_recherche" required></textarea>
          </td>
        </tr>
        <tr>
          <th>Niveau de qualification :</th>
          <td>
            <table id="tab_interne"><tbody><tr><td>
	            
			<% for (Niveauqualification niv : serviceIndexation.niveauQualificationList()) { 
					%><td><input name="niveau" value="<%=niv.getId() %>" type="radio"><%=niv.getIntitule() %><br><% 
					}
				%> 
	            	  
            </td></tr></tbody></table>
          </td>
        </tr>
        <tr>
          <th>Secteur(s) d'activité :</th>
          <td>
            <table id="tab_interne">
              <tbody><tr>
                
        	 								<% for (Secteuractivite sec : serviceIndexation.secteursActiviteList()) { 
 									%><td><input name="secteurs" value="<%=sec.getId() %>" type="checkbox"><%=sec.getIntitule() %><br><% 
 								}
 								%> 
        	

			</td>
              </tr>
            </tbody></table>
          </td>
        </tr>
	  	</tbody></table>
		  <p>
		    <input value="Enregistrer" type="submit">
		  </p>
		</form>

  	<%
  }
  else                    // Paramétre "nom" existant => stockage des données et affichage du résultat
  {
  	if(titre.equals(""))
  	{
  		%>
  		<p class="erreur">Impossible de référencer une entreprise sans saisir le nom</p>
  		<%
  	}
  	else
  	{
      // Récupération des autres paramétres
      String descriptif = request.getParameter("descriptif_mission");
      String profil = request.getParameter("profil_recherche");
      int nivId = Integer.parseInt(request.getParameter("niveau"));	    
	  int[] secteurIds = parseStringArrayToIntArray(request.getParameterValues("secteurs"));
	Entreprise e = (Entreprise) session.getAttribute("utilisateur");
	IServiceOffreEmploi serviceOffre = (IServiceOffreEmploi) ServicesLocator.getInstance().getRemoteInterface("ServiceOffreEmploi");
 	Offreemploi offre = serviceOffre.newOffreEmploi(e.getId(), titre, descriptif, profil, nivId, secteurIds, new Date() );
 	
 	for (int i : secteurIds)
 		serviceIndexation.indexerOffre(offre, i);

      %>
      <h2>Nouvelle offre référencée :</h2>
      
      <table id="affichage">
        <tr>
          <th style="width: 170px;">Numéro :</th>
          <td>
             ENT_<%=offre.getId()%> 
          </td>
        </tr>
        <tr>
          <th>Titre :</th>
          <td>
             <%=offre.getTitre()%> 
          </td>
        </tr>
        <tr>
          <th>Descriptif :</th>
          <td>
             <%=Utils.text2HTML(offre.getDescriptionmission())%> 
          </td>
        </tr>
         <tr>
          <th>Secteurs:</th>
          <td>
            <% for (Secteuractivite s : offre.getSecteuractivites())
		        { %>
		          <%= s.getIntitule()%> <br>
		          <%} %>
          </td>
        </tr>
            <tr>
          <th>Niveau de qualification :</th>
          <td><%= offre.getNiveauqualification().getIntitule() %></td>
        </tr>
      
      </table>
      <%
  	}
  }
%>
