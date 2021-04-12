<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>로그인</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>
					<spring:message code="login.h1" />
				</h1>
				<p>
					<spring:message code="login.h1.p" />
				</p>
			</div>
		</div>
	</section>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<spring:message code="login.h3.top" />
						</h3>
					</div>
					<div class="panel-body">
						<c:url var="loginUrl" value="/login" />
						<form action="${loginUrl}" method="post" class="form-horizontal">
							<c:if test="${param.error != null}">
								<div class="alert alert-danger">
									<p>
										<spring:message code="login.form.error" />
									</p>
								</div>
							</c:if>
							<c:if test="${param.logout != null}">
								<div class="alert alert-success">
									<p>
										<spring:message code="login.form.logout" />
									</p>
								</div>
							</c:if>
							<c:if test="${param.accessDenied != null}">
								<div class="alert alert-danger">
									<p>
										<spring:message code="login.form.denied" />
									</p>
								</div>
							</c:if>
							<div class="input-group input-sm">
								<label class="input-group-addon" for="userId"> <i
									class="fa fa-user"></i></label> <input type="text" class="form-control"
									id="userId" name="userId" placeholder="<spring:message code='login.form.userId'/>"
									required>
							</div>
							<div class="input-group input-sm">
								<label class="input-group-addon" for="password"> <i
									class="fa fa-lock"></i></label> <input type="password" class="form-control" id="password" name="password"
									placeholder="<spring:message code='login.form.password'/>"
									required>
							</div>
							<div class="form-actions">
								<input type="submit" class="btn btn-block btn-primary btn-default" 
									value="<spring:message code='login.form.submit'/>">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>