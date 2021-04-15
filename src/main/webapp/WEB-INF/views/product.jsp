<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular.min.js">
</script>
<script src="<%=request.getContextPath()%>/resources/js/controller.js"></script>
<title>ID로 찾은 상품</title>
</head>
<body>
	<section>
		<div class="pull-right" style="padding-right: 50px">
			<a href="?language=ko">한글</a>|<a href="?language=en">English</a> <a
				href="<c:url value="/logout" />">로그아웃</a>
		</div>
	</section>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>상품 검색 결과</h1>
			</div>
		</div>
	</section>
	<section class="container" ng-app="cartApp">
		<div class="row">
			<div class="col-md-5">
				<img src="<c:url value='/img/${product.productId}.png'></c:url>"
					alt="상품 사진" style="width: 100%" />
			</div>
			<div class="col-md-5">
				<h3>${product.name}</h3>
				<p>${product.description}</p>
				<p>
					<strong>상품ID : </strong> <span class="label label-warning">${product.productId}</span>
				</p>
				<p>
					<strong>제조사</strong> : ${product.manufacturer}
				</p>
				<p>
					<strong>상품범주</strong> : ${product.category}
				</p>
				<p>
					<strong>재고 수량 </strong> : ${product.unitsInStockStr}
				</p>
				<p>
					<strong><a
						href="/webstore-jb/pdf/${product.productId}.pdf"> [설명서
							내려받기(${product.productId}.pdf)]</a></strong>
				</p>
				<p>
					<strong>상품 단가 </strong> : ${product.unitPriceStr}원
				</p>
				<p ng-controller="cartCtrl">
					<a href="<spring:url value='/market/products' />"
						class="btn btn-default"> <span
						class="glyphicon-hand-left glyphicon"></span>뒤로 가기
					</a> <a href="#" class="btn btn-warning btn-large"
						ng-click="addToCart('${product.productId}')"> <span
						class="glyphicon-shopping-cart glyphicon"></span>주문하기
					</a> <a href="<spring:url value='/cart' />"
						class="btn btn-default"> <span
						class="glyphicon-hand-right glyphicon"></span>카트보기
					</a>
				</p>
			</div>
		</div>
	</section>
</body>
</html>