<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/style.css" />
<title>Virement entre les comptes</title>
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


				<p>Virement entre les comptes du client n°${ client.idClient },${ client.prenom }
					${ client.nom } :</p>
				<br />

				<form method="post" action="GestionCompte?action=EffectuerVirementInterne&idClient=${client.idClient }">
				

						<p>Compte à débiter</p>
						<table>
							<tr>
								<th>Sélectionner un compte</th>
								<th>N° Compte</th>
								<th>Solde</th>
							</tr>
							
							<c:forEach items="${ comptes }" var="compte">
							<tr>
								<td><input type="radio" name="compteADebiter" id="compteADebiter" value="${compte.numeroCompte}"/></td>
								<td>${compte.numeroCompte }</td>
								<td>${compte.solde }€</td>
							</tr>
							</c:forEach>
						</table>
						<br />
						
						<p>Compte à créditer</p>
						<table>
							<tr>
								<th>Sélectionner un compte</th>
								<th>N° Compte</th>
								<th>Solde</th>
							</tr>
							
							<c:forEach items="${ comptes }" var="compte">
							<tr>
								<td><input type="radio" name="compteACrediter" id="compteACrediter" value="${compte.numeroCompte}"/></td>
								<td>${compte.numeroCompte }</td>
								<td>${compte.solde }€</td>
							</tr>
							</c:forEach>
						</table>
						<br />
						
						<label for="montant">Montant : </label>
						<input class="montant" type="text" name="montant" id="montant" /><br />
						
						<input type="submit" value="Valider" />

				</form>

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