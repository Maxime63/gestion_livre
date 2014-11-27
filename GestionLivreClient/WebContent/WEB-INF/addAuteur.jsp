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
			<h1>Ajout d'un auteur</h1>
			<div id="form-main">
				<div id="form-div">
					<form class="form" id="form1" method="post" action="/addAuteur">
						<p class="name">
							<input  name="<%out.print(ConstanteMetier.AUTEUR_LASTNAME_PARAM);%>" 
									type="text" class="feedback-input" 
									placeholder="Nom de l'auteur" 
									id="name"/>
						</p>
						<p class="firstname">
							<input 	name="<%out.print(ConstanteMetier.AUTEUR_FIRSTNAME_PARAM);%>" 
									type="text" 
									class="feedback-input" 
									placeholder="Prénom de l'auteur" 
									id="name" />
						</p>
						<p class="domicile">
							<input 	name="<%out.print(ConstanteMetier.AUTEUR_HOME_PARAM);%>" 
									type="text" 
									class="feedback-input" 
									id="domicile" 
									placeholder="Domicile" />
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