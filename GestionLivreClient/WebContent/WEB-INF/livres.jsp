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
	<div class="page">
		<div class="menu">
			<h2>Menu général</h2>
			<ul>
				<li><a href="/getAllAuteur">Liste des auteurs</a></li><br/>
				<li><a href="/getAllLivre">Liste des livres</a></li><br/>
				<li><a href="/addAuteur">Ajouter un auteur</a></li>
			</ul>
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
					<%String getLivreUrl = "/getLivre?" + ConstanteMetier.LIVRE_ID_PARAM + "=" + livre.getId() + "&" + ConstanteMetier.AUTEUR_ID_PARAM + "=" + livre.getAuteurId();%>
					<tr>
						<td><a href="<%out.print(getLivreUrl);%>"><% out.print(++nbLivres); %></a></td>
						<td><a href="<%out.print(getLivreUrl);%>"><% out.print(livre.getTitre()); %></a></td>
						<td><a href="<%out.print(getLivreUrl);%>"><% out.print(livre.getAuteurNomPrenom()); %></a></td>
						<td><a href="<%out.print(getLivreUrl);%>"><% out.print(livre.getPrix()); %></a></td>
						<td><a href="<%out.print(getLivreUrl);%>"><% out.print(livre.getDescription()); %></a></td>
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