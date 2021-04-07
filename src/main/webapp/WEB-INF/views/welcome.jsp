<%@ page language="java" contentType="text/html;
charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>Welcome</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body>
	<section>
	<div class="jumbotron">
	<div style="margin-left: 10%;">
		<h1>${greeting}</h1>
		<p>${tagline}</p>
		</div>
	</div>
	</section>
	<section class="container">
	<ul>
		<li><a href="market/products/filter/params;categories=Laptop,Tablet;brands=Google,Dell">
			행렬변수(범주, 상표)</a></li>
		<li><a href="customers/">고객 목록</a></li>
		<li><a href="market/products/add">상품 추가</a></li>
		<li><span><a href="market/products/">상품 목록</a></span>(<span><a
				href="market/products/laptop">랩탑</a></span>,&nbsp;<span><a
				href="market/products/TABLET">태블릿</a></span>)</li>
		<li><a href="market/update/stock/">제고 증가</a></li>
	</ul>
	</section>
</body>
</html>