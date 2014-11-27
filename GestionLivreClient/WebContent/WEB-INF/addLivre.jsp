	<%@page import="com.gestion.livre.metier.ConstanteMetier"%>
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
			<h1>Ajout d'un livre</h1>
			<% String url = "/addLivre?" + ConstanteMetier.AUTEUR_ID_PARAM + "=" + request.getParameter(ConstanteMetier.AUTEUR_ID_PARAM); %>
			<div id="form-main">
				<div id="form-div">
					<form class="form" id="form1" method="post" action="<%out.print(url);%>">
						<p class="titre">
							<input  name="<%out.print(ConstanteMetier.LIVRE_TITLE_PARAM);%>" 
									type="text" class="feedback-input" 
									placeholder="Titre du livre" 
									id="titre"/>
						</p>
						<p class="prix">
							<input 	name="<%out.print(ConstanteMetier.LIVRE_PRICE_PARAM);%>" 
									type="text" 
									class="feedback-input" 
									placeholder="Prix du livre" 
									id="prix" />
						</p>
						<p class="description">
							<textarea name="<%out.print(ConstanteMetier.LIVRE_DESCRIPTION_PARAM);%>" 
									type="text" 
									class="feedback-input" 
									id="description" 
									placeholder="Description"></textarea>
						</p>
						<div class="submit">
							<input type="submit" value="Ajouter" id="button-blue"/>
							<div class="ease"></div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>