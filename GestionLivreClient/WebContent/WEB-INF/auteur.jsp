<%@page import="com.gestion.livre.metier.ConstanteMetier"%>
<%@page import="com.gestion.livre.persistence.TAuteur"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="style.css" />
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Gestion livre</title>
</head>
<body>
	<div class="menu">
		<a href="/getAllAuteur">Liste des auteurs</a><br/>
		<a href="/getAllLivre">Liste des livres</a><br/>
		<a href="/addAuteur">Ajouter un auteur</a>
	</div>
	<div class="contenu">
		<% TAuteur auteur = (TAuteur) request.getAttribute(ConstanteMetier.AUTEUR_PARAM); %>
		<%
		String updatedUrl = "/updateAuteur?" + ConstanteMetier.AUTEUR_ID_PARAM + "=" + auteur.getId();
		String deletedUrl = "/deleteAuteur?" + ConstanteMetier.AUTEUR_ID_PARAM + "=" + auteur.getId();
		String getAllLivreUrl = "/getLivresByAuteur?" + ConstanteMetier.AUTEUR_ID_PARAM + "=" + auteur.getId();
		String addLivreUrl = "/addLivre?" + ConstanteMetier.AUTEUR_ID_PARAM + "=" + auteur.getId();
		%>
		<h1>Auteur : <% out.print(auteur.getNom() + " " + auteur.getPrenom()); %></h1>
		
		<a href="<% out.print(getAllLivreUrl); %>">Voir ses livres</a> | <a href="<% out.print(addLivreUrl); %>">Ajouter un livre</a> | <a href="<% out.print(updatedUrl); %>">Modifier l'auteur</a> | <a href="<% out.print(deletedUrl); %>">Supprimer</a>
		
		<p>Nom : <% out.print(auteur.getNom()); %></p>
		<p>Prenom : <% out.print(auteur.getPrenom()); %></p>
		<p>Domicile : <% out.print(auteur.getDomicile()); %></p>
	</div>
</body>
</html>