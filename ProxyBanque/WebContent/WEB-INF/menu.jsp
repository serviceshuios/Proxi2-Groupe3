<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<ul>
	<li><a href="/ProxiBanque/">Accueil</a></li>
	<!-- renvoit à l'index -->
	<li><c:if test="${ empty sessionScope.droits }">
			<a href=Authentification>Authentification</a>
		</c:if> <c:if test="${ sessionScope.droits =='CONSEILLER' }">
			<a href="Authentification?action=deconnecter">Deconnection</a>
		</c:if></li>
	<!-- renvoit à la page d'autentification -->

	<c:if test="${ sessionScope.droits =='CONSEILLER' }">

		<li><a href=GestionClient?action=ListerClients>Liste des
				clients</a></li>
		<li><a href=GestionClient?action=Editer>Editer un client</a></li>

	</c:if>

</ul>
