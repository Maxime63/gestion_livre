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
		<% String url = "/addLivre?" + ConstanteMetier.AUTEUR_ID_PARAM + "=" + request.getParameter(ConstanteMetier.AUTEUR_ID_PARAM); %>
		<form method="post" action="<% out.print(url); %>">
			<table>
				<tr>
					<td><label for="<%out.print(ConstanteMetier.LIVRE_TITLE_PARAM);%>">Titre*</label></td>
					<td><input type="text" name="<%out.print(ConstanteMetier.LIVRE_TITLE_PARAM);%>"/></td>
				</tr>
				<tr>
					<td><label for="<%out.print(ConstanteMetier.LIVRE_PRICE_PARAM);%>">Prix*</label></td>
					<td><input type="text" name="<%out.print(ConstanteMetier.LIVRE_PRICE_PARAM);%>"/>&euro;</td>
				</tr>
				<tr>
					<td><label for="<%out.print(ConstanteMetier.LIVRE_DESCRIPTION_PARAM);%>">Description*</label></td>
					<td><input type="text" name="<%out.print(ConstanteMetier.LIVRE_DESCRIPTION_PARAM);%>"/></td>
				</tr>
			</table>
			<input type="submit" value="Ajouter"/>
		</form>
	</div>
</body>
</html>