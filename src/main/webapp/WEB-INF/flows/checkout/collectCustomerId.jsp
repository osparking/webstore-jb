<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>
					<spring:message code="collectCustomerId.h1" />
				</h1>
				<p>
					<spring:message code="collectCustomerId.h1.p" />
				</p>
			</div>
		</div>
	</section>
	<section class="container">
		<form:form method="POST" enctype="multipart/form-data"
			onsubmit="return checkId()" modelAttribute="order.customer"
			class="form-horizontal">
			<form:errors path="*" cssClass="alert alert-danger" element="div" />
			<fieldset>
				<legend></legend>
				<div class="form-group">
					<label class="control-label col-lg-2" for="customerId">고객ID</label>
					<div class="col-lg-10">
						<form:input id="customerId" path="customerId" type="text"
							class="form:input-large" />
						<form:errors path="customerId" cssClass="text-danger" />
					</div>
				</div>
				<input type="hidden" name="_flowExecutionKey"
					value="${flowExecutionKey}" />
				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						<input type="submit" id="btnAdd" class="btn btn-primary"
							value="ID 확인" name="_eventId_customerIdConfirmed" />
						<button id="btnCancel" class="btn btn-default"
							name="_eventId_newCustomer">신규고객</button>
						<button id="btnCancel" class="btn btn-default"
							name="_eventId_cancel">Cancel</button>
					</div>
				</div>
			</fieldset>
		</form:form>
	</section>
	<script>
		function checkId(e) {
			var $btn = $(document.activeElement);
			var revisitingCustomer = $btn.attr('id') === 'btnOldCust';
			var custId = document.getElementById("customerId").value;
			var custIdEmpty = custId.trim().length === 0;
			if (revisitingCustomer && custIdEmpty) {
				alert("고객ID를 입력하세요");
				return false;
			} else {
				return true;
			}
		}
	</script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
		
	</script>
</body>
</html>
