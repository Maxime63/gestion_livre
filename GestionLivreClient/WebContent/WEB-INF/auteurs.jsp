<%@page import="com.gestion.livre.metier.ConstanteMetier"%>
<%@page import="com.gestion.livre.persistence.TAuteur"%>
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
	<div class="page">
		<div class="menu">
			<h2>Menu</h2>
			<ul>
				<li>
					<a href="/getAllAuteur">Liste des auteurs</a><br/>
				</li>
				<li>
					<a href="/getAllLivre">Liste des livres</a><br/>
				</li>
				<li>
					<a href="/addAuteur">Ajouter un auteur</a>
				</li>
			</ul>
		</div>
		<div class="contenu">
			<h1>Liste des auteurs</h1>
			<table>
				<thead>
					<tr>
						<th>N°</th>
						<th>Auteur</th>
						<th>Domicile</th>
					</tr>
				</thead>
				<tbody>
				<%
				List<TAuteur> auteurs = (List<TAuteur>) request.getAttribute(ConstanteMetier.AUTEURS_LIST_PARAM);
			
				int nbAuteur=0;
				for(TAuteur auteur  : auteurs){
				%>
					<%String url = "/getAuteur?" + ConstanteMetier.AUTEUR_ID_PARAM + "=" + auteur.getId();%>
					<tr>
						<td><a href="<% out.print(url); %>"><% out.print(++nbAuteur); %></a></td>
						<td><a href="<% out.print(url); %>"><% out.print(auteur.getNom()); %> <% out.print(auteur.getPrenom()); %></a></td>
						<td><a href="<% out.print(url); %>"><% out.print(auteur.getDomicile()); %></a></td>
					</tr>
				<%
				}
				%>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>