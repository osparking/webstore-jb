<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<div class="jumbotron">
			<div class="container">
				<h1>고객 목록</h1>
				<p>모든 고객 목록</p>
			</div>
		</div>
	</section>
	<section class="container">
			<div class="pull-right" style="padding-right: 50px">
				<a href="<c:url value="/logout" />">로그아웃</a>
			</div>
		</section>
	<section class="container">
		<a href="/webstore-jb">홈으로</a>
		<hr>
	</section>
	<section class="container">
		<div class="row">
			<c:forEach items="${customers}" var="customer">
				<div class="col-sm-6 col-md-3">
					<div class="thumbnail">
						<div class="caption">
							<h3>${customer.name}</h3>
							<p>${customer.address}</p>
							<p>${customer.customerId}</p>
							<p>주문 건수 : ${customer.noOfOrdersMade}</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</section>
</body>
</html>