<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
        <div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${indexLocation}">${labels.site_title}</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
			<ul class="nav navbar-nav">
				<c:forEach var="item"  items="${menu}">
					<li><a href="/page/${item.key}/">${item.value}</a></li>					
				</c:forEach>
			</ul>
        </div><!-- /.navbar-collapse -->
	</div><!-- /.container -->
</nav>

<div class="container">

	<div class="row">
        <div class="col-lg-12 page-content" >
			<jsp:include page="../views/${view}.jsp" />
        </div>
	</div>

</div><!-- /.container -->