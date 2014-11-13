<%@page import="com.gestion.livre.metier.ConstanteMetier"%>
<%@page import="com.gestion.livre.persistence.TAuteur"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gestion livre</title>
</head>
<body>
	<h1>Liste des auteurs</h1>
	<table>
		<thead>
			<tr>
				<th>Id</th>
				<th>Nom</th>
				<th>Prenom</th>
				<th>Domicile</th>
			</tr>
		</thead>
		<tbody>
<%
	List<TAuteur> auteurs = (List<TAuteur>) request.getAttribute(ConstanteMetier.AUTEURS_LIST_PARAM);

	for(TAuteur auteur  : auteurs){
%>
			<tr>
				<td><% out.print(auteur.getId()); %></td>
				<td><% out.print(auteur.getNom()); %></td>
				<td><% out.print(auteur.getPrenom()); %></td>
				<td><% out.print(auteur.getDomicile()); %></td>
			</tr>		
<%
	}
%>
		</tbody>
	</table>
</body>
</html>