<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import='_02.Account'%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel='stylesheet' href='${pageContext.request.contextPath}/css/style.css' type="text/css" />
<style type="text/css">
span.error {
	color: red;
	display: inline-block;
	font-size: 5pt;
}

.fieldset-auto-width {
	display: inline-block;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
	       <h3>新增帳戶資料</h3>
	       <hr>
		<form:form method='POST' modelAttribute="account" >
			<fieldset class="fieldset-auto-width">
				<legend>請輸入下列資料：</legend>
				<font color='red'>${insertErrorMessage}</font><br>
				<table>
					<tr>
						<td align='right'>帳戶編號：<br>&nbsp;</td>
						<td><form:input path="accountNo" /><br>
							<form:errors path="accountNo" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td align='right'>帳戶類型：<br>&nbsp;</td>
						<td><form:input path="accountType" /><br>
							<form:errors path="accountType" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td align='right'>姓名：<br>&nbsp;</td>
						<td><form:input path="name" /><br>&nbsp;
							<form:errors path="name" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td align='right'>餘額：<br>&nbsp;
						</td>
						<td><form:input path="balance" /><br>&nbsp;
						    <form:errors path="balance" cssClass="error" />
						</td>
					</tr>
					
					<tr>
						<td colspan='2' align='center'>
						<input type='submit' value='提交'></td>
					</tr>
				</table>
			</fieldset>
		</form:form>
		<br> <a href="<c:url value='/_02/index' />">回首頁</a>
	</div>
</body>
</html>
<%-- 			<c:if test='${account.accountNo != null}'> --%>
<%--                  <form:hidden path="accountNo" /><br>&nbsp; --%>
<%--                  <form:hidden path="accountType" /><br>&nbsp; --%>
<%-- 			</c:if> --%>