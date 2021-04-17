<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Insert title here</title>
</head>
<body>
	<section>
		<div class="pull-right" style="padding-right: 50px">
			<a href="?lang=ko">한글</a>|<a href="?lang=en">Eng</a> <a
				href="<c:url value='/logout'/>"> <spring:message
					code="logout.anchor.text" /></a>
		</div>
	</section>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>
					<spring:message code="customers.h1" />
				</h1>
				<p>
					<spring:message code="customers.h1.p1" />
				</p>
			</div>
		</div>
	</section>
	<section class="container">
		<div class="row">
			<c:forEach items="${customers}" var="customer">
				<div class="col-sm-6 col-md-3">
					<div class="thumbnail">
						<div class="caption">
							<h3>${customer.name}</h3>
							<p>${customer.customerId}</p>
							<p>${customer.billingAddress.zipCode}</p>
							<p>${customer.billingAddress.wideCiDo}</p>
							<p>${customer.billingAddress.ciGoonGu}</p>
							<p>${customer.billingAddress.streetName}</p>
							<p>${customer.billingAddress.buildingNo}</p>
							<p>${customer.billingAddress.unitNo}</p>
							<p>${customer.phoneNumber}</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</section>
</body>
</html>