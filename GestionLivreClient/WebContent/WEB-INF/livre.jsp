<%@page import="com.gestion.livre.persistence.TLivre"%>
<%@page import="com.gestion.livre.metier.ConstanteMetier"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
	<%
	TLivre livre = (TLivre) request.getAttribute(ConstanteMetier.LIVRE_PARAM);
	String titre = "Livre " + livre.getTitre();
	%>	
	<div class="menu">
		<a href="/getAllAuteur">Liste des auteurs</a><br/>
		<a href="/getAllLivre">Liste des livres</a><br/>
		<a href="/addAuteur">Ajouter un auteur</a>
	</div>
	<div class="contenu">
		<h1><%out.print(titre); %></h1>
		<%
		String urlUpdate = "/updateLivre?" + ConstanteMetier.LIVRE_ID_PARAM + "=" + livre.getId()
							+"&" + ConstanteMetier.AUTEUR_ID_PARAM + "=" + livre.getAuteurId();
		String urlDelete = "/deleteLivre?" + ConstanteMetier.LIVRE_ID_PARAM + "=" + livre.getId()
								+"&" + ConstanteMetier.AUTEUR_ID_PARAM + "=" + livre.getAuteurId();
		%>
		<a href="<%out.print(urlUpdate); %>">Modifier le livre</a> | <a href="<%out.print(urlDelete); %>">Supprimer le livre</a>
		<p>Titre : <% out.print(livre.getTitre()); %></p>
		<p>
			<%
				String url = "/getAuteur?" + ConstanteMetier.AUTEUR_ID_PARAM + "=" + livre.getAuteurId();
			%>
			Auteur : <a href="<% out.print(url); %>"><% out.print(livre.getAuteurNomPrenom()); %></a>
		</p>
		<p>Prix : <% out.print(livre.getPrix()); %>&euro;</p>
		<p>Description : <% out.print(livre.getDescription()); %></p>
	</div>
</body>
</html>