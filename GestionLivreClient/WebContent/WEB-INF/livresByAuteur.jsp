<%@page import="com.gestion.livre.metier.ConstanteMetier"%>
<%@page import="com.gestion.livre.persistence.TLivre"%>
<%@page import="java.util.List"%>
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
		<%
		List<TLivre> livres = (List<TLivre>) request.getAttribute(ConstanteMetier.LIVRES_LIST_PARAM); 
		String auteur = livres.get(0).getAuteurNomPrenom();
		String titre = "Livre de l'auteur " + auteur;
		%>
		<h1><% out.print(titre); %></h1>
		<table>
			<thead>
				<tr>
					<th>N�</th>
					<th>Titre</th>
					<th>Prix</th>
					<th>Description</th>
				</tr>
			</thead>
			<tbody>
			<%
			int nbLivres=0;
			for(TLivre livre  : livres){
			%>
				<tr>
					<td><% out.print(++nbLivres); %></td>
					<td><% out.print(livre.getTitre()); %></td>
					<td><% out.print(livre.getPrix()); %></td>
					<td><% out.print(livre.getDescription()); %></td>
					<td></td>
				</tr>		
			<%
			}
			%>
			</tbody>
		</table>
	</div>
</body>
</html>