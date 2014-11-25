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
		<h1>Liste des livres</h1>
		<table>
			<thead>
				<tr>
					<th>N°</th>
					<th>Titre</th>
					<th>Auteur</th>
					<th>Prix</th>
					<th>Description</th>
				</tr>
			</thead>
			<tbody>
		<%
		List<TLivre> livres = (List<TLivre>) request.getAttribute(ConstanteMetier.LIVRES_LIST_PARAM);
	
		int nbLivres=0;
		for(TLivre livre  : livres){
		%>
				<tr>
					<td><% out.print(++nbLivres); %></td>
					<%
					String getLivreUrl = "/getLivre?" + ConstanteMetier.LIVRE_ID_PARAM + "=" + livre.getId() + "&" + ConstanteMetier.AUTEUR_ID_PARAM + "=" + livre.getAuteurId();
					%>
					<td><a href="<%out.print(getLivreUrl);%>"><% out.print(livre.getTitre()); %></a></td>
					<td>
						<%
							String url = "/getAuteur?" + ConstanteMetier.AUTEUR_ID_PARAM + "=" + livre.getAuteurId();
						%>
						<a href="<% out.print(url); %>"><% out.print(livre.getAuteurNomPrenom()); %></a>
					</td>
					<td><% out.print(livre.getPrix()); %></td>
					<td><% out.print(livre.getDescription()); %></td>
				</tr>		
		<%
		}
		%>
			</tbody>
		</table>
	</div>
</body>
</html>