<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/style.css" />
<title>Modifier informations client</title>
</head>
<body>
	<div class="container-fluid">

		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 titre">
				<%@ include file="/WEB-INF/titre.jsp"%>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 connection">
				<%@ include file="/WEB-INF/connectioninf.jsp"%>
			</div>
		</div>

		<div class="row">
			<div class="col-xs-12 col-sm-4 col-md-3 col-lg-3 menu">
				<%@ include file="/WEB-INF/menu.jsp"%>
			</div>

			<div class="col-xs-12 col-sm-8 col-md-9 col-lg-9 content">

				<c:if test="${ sessionScope.droits !='CONSEILLER' }">
					<p>
						Vous n'avez pas les droits necessaires pour cette action
					</p>
					<p>
						<a href="Authentification?action=deconnecter">Se déconnecter</a>
					</p>

				</c:if>

				<c:if test="${ sessionScope.droits =='CONSEILLER' }">

					<form method="post" action="GestionClient?action=Modifier">
						<fieldset>
							<legend>Modifier informations client</legend>
							<label for="idClient">Id : </label><input type="text" name="idClient" id="idClient" value="${client.idClient }"/><br />
							<label for="nom">Nom : </label><input type="text" name="nom" id="nom" value="${client.nom }"/><br />
							<label for="prenom">Prénom : </label><input type="text" name="prenom" id="prenom" value="${client.prenom }"/><br />
							<label for="adresse">Adresse : </label><input type="text" name="adresse" id="adresse" value="${client.adresse }"/><br />
							<label for="ville">Ville : </label><input type="text" name="ville" id="ville" value="${client.ville }"/><br />
							<label for="cp">Code postal : </label><input type="text" name="cp" id="cp" value="${client.codePostal }"/><br />
							<label for="email">E-mail : </label><input type="text" name="email" id="email" value="${client.email }"/><br />
							<label for="telephone">Téléphone : </label><input type="text" name="telephone" id="telephone" value="${client.telephone }"/><br />
												
							<label for="typeClient">Type de client :</label>
							<input type="radio" name="typeClient" id="typeClient" value="PARTICULIER" ${ client.typeClient=='PARTICULIER' ? "checked" : "" }/><span>Particulier</span>
							<input type="radio" name="typeClient" id="typeClient" value="ENTREPRISE" ${ client.typeClient=='ENTREPRISE' ? "checked" : "" }/><span>Entreprise</span><br/>
							<label for="idConseiller">Id Conseiller : </label><input type="text" name="idConseiller" id="idConseiller" value="${sessionScope.idConseiller}"/><br />
							
						</fieldset>
						<br /> <input type="submit" value="Modifier" />
					</form>
					
					<c:if test="${ modified }">
					<p>Le client à été modifé.
					</p>
					</c:if>
					
				</c:if>




			</div>
		</div>

		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 footer">
				<%@ include file="/WEB-INF/footer.jsp"%>
			</div>
		</div>

	</div>
</body>
</html>