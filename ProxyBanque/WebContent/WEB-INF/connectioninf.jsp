<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<c:if test="${ !empty sessionScope.droits }">Connecté : ${ sessionScope.prenom } ${ sessionScope.nom }</c:if>
<c:if test="${ empty sessionScope.droits }">Non connecté</c:if>