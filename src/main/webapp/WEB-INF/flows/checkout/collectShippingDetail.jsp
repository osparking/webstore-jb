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
<title>배송 정보</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>배송</h1>
				<p>배송 상세정보</p>
			</div>
		</div>
	</section>
	<section class="container">
		<form:form modelAttribute="order.shippingDetail"
			class="form-horizontal">
			<fieldset>
				<legend>배송 상세정보</legend>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="name" />Name</label>
					<div class="col-lg-10">
						<form:input id="name" path="name" type="text"
							class="form:input-large" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="shippingDate" />shipping
					Date (dd/mm/yyyy)</label>
					<div class="col-lg-10">
						<form:input id="shippingDate" path="shippingDate" type="text"
							class="form:input-large" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2" for="zipCode">우편번호</label>
					<div class="col-lg-10">
						<form:input id="zipCode" path="shippingAddress.zipCode"
							type="text" class="form:input-large" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2" for="wideCiDo">광역시도</label>
					<div class="col-lg-10">
						<form:input id="wideCiDo" path="shippingAddress.wideCiDo"
							type="text" class="form:input-large" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2" for="ciGoonGu">시군구</label>
					<div class="col-lg-10">
						<form:input id="ciGoonGu" path="shippingAddress.ciGoonGu."
							type="text" class="form:input-large" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2" for="streetName">도로명</label>
					<div class="col-lg-10">
						<form:input id="streetName" path="shippingAddress.streetName."
							type="text" class="form:input-large" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2" for="buildingNo">건물번호</label>
					<div class="col-lg-10">
						<form:input id="buildingNo" path="shippingAddress.buildingNo"
							type="text" class="form:input-large" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2" for="unitNo">동호수</label>
					<div class="col-lg-10">
						<form:input id="unitNo" path="shippingAddress.unitNo" type="text"
							class="form:input-large" />
					</div>
				</div>
				<input type="hidden" name="_flowExecutionKey"
					value="${flowExecutionKey}" />
				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						<button id="back" class="btn btn-default"
							name="_eventId_backToCollectCustomerInfo">back</button>
						<input type="submit" id="btnAdd" class="btn btn-primary"
							value="Add" name="_eventId_shippingDetailCollected" />
						<button id="btnCancel" class="btn btn-default"
							name="_eventId_cancel">Cancel</button>
					</div>
				</div>
			</fieldset>
		</form:form>
	</section>
</body>
</html>