<%@page import="eu.telecom_bretagne.cabinet_recrutement.front.rss.GenerationFluxRSS"%><%@ page language="java" contentType="text/xml; charset=UTF-8" pageEncoding="ISO-8859-1"%><%GenerationFluxRSS.offresEmploi(out, getServletContext().getInitParameter("URL_BASE"));%>
