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
	<% 
	TAuteur auteur = (TAuteur) request.getAttribute(ConstanteMetier.AUTEUR_PARAM); 
	String updatedUrl = "/updateAuteur?" + ConstanteMetier.AUTEUR_ID_PARAM + "=" + auteur.getId();
	String deletedUrl = "/deleteAuteur?" + ConstanteMetier.AUTEUR_ID_PARAM + "=" + auteur.getId();
	String getAllLivreUrl = "/getLivresByAuteur?" + ConstanteMetier.AUTEUR_ID_PARAM + "=" + auteur.getId();
	String addLivreUrl = "/addLivre?" + ConstanteMetier.AUTEUR_ID_PARAM + "=" + auteur.getId();
	%>	
	<div class="page">
		<div class="menu">
			<h2>Menu général</h2>
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
			<h2>Menu auteur</h2>
			<ul>
				<li>
					<a href="<% out.print(getAllLivreUrl); %>">Voir ses livres</a><br/>
				</li>
				<li>
					<a href="<% out.print(addLivreUrl); %>">Ajouter un livre</a><br/>
				</li>
				<li>
					<a href="<% out.print(updatedUrl); %>">Modifier l'auteur</a><br/>
				</li>
				<li>
					<a href="<% out.print(deletedUrl); %>">Supprimer l'auteur</a><br/>
				</li>
			</ul>
		</div>
		<div class="contenu">
			<h1>Auteur : <% out.print(auteur.getNom() + " " + auteur.getPrenom()); %></h1>
			<div id="fiche-main">
				<div id="fiche-contour">
					<div id="fiche-div">
						<p class="feedback-input" id="name">Nom : <% out.print(auteur.getNom()); %></p>
						<p class="feedback-input" id="name">Prenom : <% out.print(auteur.getPrenom()); %></p>
						<p class="feedback-input" id="domicile">Domicile : <% out.print(auteur.getDomicile()); %></p>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>