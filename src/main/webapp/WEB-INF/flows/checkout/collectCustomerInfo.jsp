<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Customer</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>
					<spring:message code="collectCustomerInfo.h1" />
				</h1>
				<p>
					<spring:message code="collectCustomerInfo.h1.p" />
				</p>
			</div>
		</div>
	</section>
	<section class="container">
		<form:form modelAttribute="order.customer" class="form-horizontal">
			<fieldset>
				<legend>Customer Details</legend>
				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						<c:if test="${order.customer.wrongId == true}">
							<p cssClass="text-danger">'${order.customer.customerIdLong}' 는
								등록된 사용자가 아닙니다.</p>
							<button id="back" class="btn btn-default"
								name="_eventId_backToCollectCustomerId">ID 새로 입력</button>
						</c:if>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2" for="name">Name</label>
					<div class="col-lg-10">
						<form:input id="name" path="name" type="text"
							class="form:input-large" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2" for="zipCode">우편번호</label>
					<div class="col-lg-10">
						<form:input id="zipCode" path="billingAddress.zipCode" type="text"
							class="form:input-large" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2" for="wideCiDo">광역시도</label>
					<div class="col-lg-10">
						<form:input id="wideCiDo" path="billingAddress.wideCiDo"
							type="text" class="form:input-large" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2" for="ciGoonGu">시군구</label>
					<div class="col-lg-10">
						<form:input id="ciGoonGu" path="billingAddress.ciGoonGu."
							type="text" class="form:input-large" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2" for="streetName">도로명</label>
					<div class="col-lg-10">
						<form:input id="streetName" path="billingAddress.streetName."
							type="text" class="form:input-large" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2" for="buildingNo">건물번호</label>
					<div class="col-lg-10">
						<form:input id="buildingNo" path="billingAddress.buildingNo"
							type="text" class="form:input-large" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2" for="unitNo">동호수</label>
					<div class="col-lg-10">
						<form:input id="unitNo" path="billingAddress.unitNo" type="text"
							class="form:input-large" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2" for="phoneNumber">전화번호</label>
					<div class="col-lg-10">
						<form:input id="phoneNumber" path="phoneNumber" type="text"
							class="form:input-large" />
					</div>
				</div>
				<input type="hidden" name="_flowExecutionKey"
					value="${flowExecutionKey}" />
				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						<input type="submit" id="btnAdd" class="btn btn-primary"
							value="저장" name="_eventId_customerInfoCollected" />
						<button id="btnCancel" class="btn btn-default"
							name="_eventId_cancel">Cancel</button>
					</div>
				</div>
			</fieldset>
		</form:form>
	</section>
</body>
</html>