<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/bootstrap.min.css" />
<title>ProxyBanque Authentification</title>
</head>
<body>

<%@ include file="/menu.jsp" %>

<div class="content">
	<c:if test="${ sessionScope.droits } = CONSEILLER">
		<p>
			Bonjour "${ sessionScope.prenom }" "${ sessionScope.nom }" </br>
			Vous êtes connectés en tant que conseiller.
		</p>
		
	</c:if>

	<c:if test="${ empty sessionScope.droits }">
	
	<form method="post" action="Authentification?action=authentifier">
		<fieldset id="section-1">
			<legend>Authentification</legend>
			<label for="login">Login : </label><input type="text" name="login"
				id="login" /><br /> <label for="mdp">Mot de passe : </label><input
				type="password" name="mdp" id="mdp" /><br />
		</fieldset>
		<br /> <input type="submit" value="valider" />
	</form>
	</c:if>
</div>

</body>
</html>