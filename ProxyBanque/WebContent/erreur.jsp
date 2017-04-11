<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/style.css" />
<title>TODO</title>
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
				<p>Erreur : ${ msgErreur }</p>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 footer">
			<%@ include file="/WEB-INF/footer.jsp"%>
		</div>
	</div>

</body>
</html>
