<%@page import="eu.telecom_bretagne.cabinet_recrutement.service.IServiceIndexation"%>
<%@page import="eu.telecom_bretagne.cabinet_recrutement.data.model.Messagecandidature"%>
<%@page import="eu.telecom_bretagne.cabinet_recrutement.service.IServiceMessageCandidature"%>
<%@page import="eu.telecom_bretagne.cabinet_recrutement.data.model.Messageoffredemploi"%>
<%@page import="eu.telecom_bretagne.cabinet_recrutement.service.IServiceMessageOffreEmploi"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="eu.telecom_bretagne.cabinet_recrutement.front.utils.ServicesLocator,
                eu.telecom_bretagne.cabinet_recrutement.front.utils.Utils,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceEntreprise,
                eu.telecom_bretagne.cabinet_recrutement.service.IServiceOffreEmploi,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Entreprise,
                eu.telecom_bretagne.cabinet_recrutement.data.model.Offreemploi,
                java.util.List"%>
                
<%
Entreprise e = (Entreprise) session.getAttribute("utilisateur");
int id = e.getId();


IServiceOffreEmploi serviceOffreEmploi = (IServiceOffreEmploi) ServicesLocator.getInstance().getRemoteInterface("ServiceOffreEmploi");
List <Offreemploi> offresEmploi = serviceOffreEmploi.offresByEntreprise(id);

IServiceIndexation serviceIndexation = (IServiceIndexation) ServicesLocator.getInstance().getRemoteInterface("ServiceIndexation");
IServiceMessageOffreEmploi serviceMessageOffreEmploi = (IServiceMessageOffreEmploi) ServicesLocator.getInstance().getRemoteInterface("ServiceMessageOffreEmploi");
IServiceMessageCandidature seviceMessageCandidature = (IServiceMessageCandidature) ServicesLocator.getInstance().getRemoteInterface("ServiceMessageCandidature");

for (Offreemploi oe : offresEmploi) {

	List <Messageoffredemploi> mo = serviceMessageOffreEmploi.messagesByOffre(oe.getId());
	
	for (Messageoffredemploi m: mo ){
		serviceMessageOffreEmploi.removeMessageOffreEmploi(m.getId());
	}
		
	
	List <Messagecandidature> mc = seviceMessageCandidature.messagesByOffre(oe.getId());

	for (Messagecandidature m : mc)
		seviceMessageCandidature.removeMessageCandidature(m.getId());
	
	serviceIndexation.retirerOffre(oe.getId());
	serviceOffreEmploi.removeOffreEmploi(oe.getId());
}

IServiceEntreprise serviceEntreprise = (IServiceEntreprise) ServicesLocator.getInstance().getRemoteInterface("ServiceEntreprise");
serviceEntreprise.removeEntreprise(id);


%>    