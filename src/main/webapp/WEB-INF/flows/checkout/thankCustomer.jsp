<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>감사 인사</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1 class="alert alert-danger">고맙습니다</h1>
				<p>
					Thanks for the order. your order will be delivered to you on
					<fmt:formatDate type="date"
						value="${order.shippingDetail.shippingDate}" />
					!
				</p>
				<p>Your Order Number is ${order.orderId}</p>
			</div>
		</div>
	</section>
	<section>
		<div class="container">
			<p>
				<a href="<spring:url value="/market/products" />"
					class="btn btn-primary"> <span
					class="glyphicon-hand-left glyphicon"></span>상품 목록
				</a>
			</p>
		</div>
	</section>
</body>
</html>