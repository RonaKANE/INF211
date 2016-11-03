<%@page import="eu.telecom_bretagne.cabinet_recrutement.data.model.Niveauqualification"%>
<%@page import="java.sql.Date"%>
<%@page import="eu.telecom_bretagne.cabinet_recrutement.data.model.Offreemploi"%>
<%@page import="eu.telecom_bretagne.cabinet_recrutement.service.IServiceOffreEmploi"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>

<%@page import="eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator,
                eu.telecom_bretagne.cabinet_recrutement.front.utils.Utils,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceEntreprise,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise"%>

<%
  String id = request.getParameter("id");
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
	          <input name="titre" size="20" maxlength="50" type="text">
	        </td>
	  	  </tr>
	      <tr>
          <th>Descriptif de la mission :</th>
	        <td>
	          <textarea rows="7" cols="70" name="descriptif_mission"></textarea>
	        </td>
	      </tr>
        <tr>
          <th>Profil recherché :</th>
          <td>
            <textarea rows="7" cols="70" name="profil_recherche"></textarea>
          </td>
        </tr>
        <tr>
          <th>Niveau de qualification :</th>
          <td>
            <table id="tab_interne"><tbody><tr><td>
	            
	            	  <input name="niveau" value="1" type="radio">CAP/BEP<br>
	            	  
	            	  <input name="niveau" value="2" type="radio">Bac<br>
	            	  
	            	  <input name="niveau" value="3" type="radio">Bac+3<br>
	            	  
	            	  <input name="niveau" value="4" type="radio">Bac+5<br>
	            	  
	            	  <input name="niveau" value="5" type="radio">Doctorat<br>
	            	  
            </td></tr></tbody></table>
          </td>
        </tr>
        <tr>
          <th>Secteur(s) d'activité :</th>
          <td>
            <table id="tab_interne">
              <tbody><tr>
                
                  <td><input name="secteur" value="1" type="checkbox">Achats/Logistique</td>
                  
                  <td><input name="secteur" value="2" type="checkbox">Assistanat/Secrétariat</td>
                  
                  <td><input name="secteur" value="3" type="checkbox">Agriculture</td>
                  
              </tr>
              <tr>
                
                  <td><input name="secteur" value="4" type="checkbox">Agroalimentaire</td>
                  
                  <td><input name="secteur" value="5" type="checkbox">Assurance</td>
                  
                  <td><input name="secteur" value="6" type="checkbox">Audit/Conseil/Expertises</td>
                  
              </tr>
              <tr>
                
                  <td><input name="secteur" value="7" type="checkbox">BTP/Immobilier</td>
                  
                  <td><input name="secteur" value="8" type="checkbox">Commercial</td>
                  
                  <td><input name="secteur" value="9" type="checkbox">Communication/Art/Média/Mode</td>
                  
              </tr>
              <tr>
                
                  <td><input name="secteur" value="10" type="checkbox">Comptabilité</td>
                  
                  <td><input name="secteur" value="11" type="checkbox">Direction Générale/Executive</td>
                  
                  <td><input name="secteur" value="12" type="checkbox">Distribution/Commerce</td>
                  
              </tr>
              <tr>
                
                  <td><input name="secteur" value="13" type="checkbox">Electronique/Microélectronique</td>
                  
                  <td><input name="secteur" value="14" type="checkbox">Environnement</td>
                  
                  <td><input name="secteur" value="15" type="checkbox">Finance/Banque</td>
                  
              </tr>
              <tr>
                
                  <td><input name="secteur" value="16" type="checkbox">Formation/Enseignement</td>
                  
                  <td><input name="secteur" value="17" type="checkbox">Hôtellerie/Restauration/Tourisme</td>
                  
                  <td><input name="secteur" value="18" type="checkbox">Industrie/Ingénierie/Production</td>
                  
              </tr>
              <tr>
                
                  <td><input name="secteur" value="19" type="checkbox">Informatique</td>
                  
                  <td><input name="secteur" value="20" type="checkbox">Juridique/Fiscal/Droit</td>
                  
                  <td><input name="secteur" value="21" type="checkbox">Marketing</td>
                  
              </tr>
              <tr>
                
                  <td><input name="secteur" value="22" type="checkbox">Public/Parapublic</td>
                  
                  <td><input name="secteur" value="23" type="checkbox">Ressources Humaines</td>
                  
                  <td><input name="secteur" value="24" type="checkbox">Santé/Social/Biologie/Humanitaire</td>
                  
              </tr>
              <tr>
                
                  <td><input name="secteur" value="25" type="checkbox">Télécom/Réseaux</td>
                  
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
      String descriptif     = request.getParameter("descriptif_mission");
      String profil = request.getParameter("profil_recherche");
      Niveauqualification niveau = (Niveauqualification) request.getParameter("niveau");
      String secteur = request.getParameter("secteur");

      IServiceOffreEmploi serviceOffre = (IServiceOffreEmploi) ServicesLocator.getInstance().getRemoteInterface("ServiceOffreEmploi");
     Offreemploi offre= serviceOffre.newOffreEmploi(titre, descriptif, profil, niveau, secteur, new Date());

      %>
      <h2>Nouvelle entreprise référencée :</h2>
      
      <table id="affichage">
        <tr>
          <th style="width: 170px;">Identifiant :</th>
          <td>
            ENT_<%=entreprise.getId()%>
          </td>
        </tr>
        <tr>
          <th>Nom :</th>
          <td>
            <%=entreprise.getNom()%>
          </td>
        </tr>
        <tr>
          <th>Descriptif :</th>
          <td>
            <%=Utils.text2HTML(entreprise.getDescriptif())%>
          </td>
        </tr>
        <tr>
          <th>Adresse postale (ville) :</th>
          <td>
            <%=entreprise.getAdressePostale()%>
          </td>
        </tr>
      </table>
      <%
  	}
  }
%>
