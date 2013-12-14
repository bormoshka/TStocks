<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>${title}</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="${description}">
		<meta name="keywords" content="${keywords}">

		<!-- Bootstrap -->
		<link href="${resourcePrefix}css/bootstrap.min.css" rel="stylesheet">
		<link href="${resourcePrefix}css/bootstrap-theme.min.css" rel="stylesheet">
		<c:forEach var="style" items="${styles}">
			<link href="${resourcePrefix}css/${style}.css" rel="stylesheet">
		</c:forEach>
		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		  <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
		<![endif]-->
	</head>
	<body>

		<jsp:include page="layout/${layout}" />

		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="${resourcePrefix}js/bootstrap.min.js"></script>
		<c:forEach var="script" items="${scripts}">
			<script src="${resourcePrefix}js/${script}.js"></script>
		</c:forEach>
	</body>
</html>