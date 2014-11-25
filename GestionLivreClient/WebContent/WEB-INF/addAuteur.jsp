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
	<div class="menu">
		<a href="/getAllAuteur">Liste des auteurs</a><br/>
		<a href="/getAllLivre">Liste des livres</a><br/>
		<a href="/addAuteur">Ajouter un auteur</a>
	</div>
	<div class="contenu">
		<h1>Ajout d'un auteur</h1>
		<form method="post" action="/addAuteur">
			<table>
				<tr>
					<td><label for="<%out.print(ConstanteMetier.AUTEUR_LASTNAME_PARAM);%>">Nom</label></td>
					<td><input type="text" name="<%out.print(ConstanteMetier.AUTEUR_LASTNAME_PARAM);%>"/>*</td>
				</tr>
				<tr>
					<td><label for="<%out.print(ConstanteMetier.AUTEUR_FIRSTNAME_PARAM);%>">Prénom</label></td>
					<td><input type="text" name="<%out.print(ConstanteMetier.AUTEUR_FIRSTNAME_PARAM);%>"/>*</td>
				</tr>
				<tr>
					<td><label for="<%out.print(ConstanteMetier.AUTEUR_HOME_PARAM);%>">Domicile</label></td>
					<td><input type="text" name="<%out.print(ConstanteMetier.AUTEUR_HOME_PARAM);%>"/>*</td>
				</tr>
			</table>
			<input type="submit" value="Ajouter"/>
		</form>
	</div>
</body>
</html>