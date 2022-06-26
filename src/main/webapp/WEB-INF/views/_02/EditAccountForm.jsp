<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel='stylesheet'
	href='${pageContext.request.contextPath}/css/style.css' type="text/css" />
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
<script type="text/javascript">
	function confirmDelete(name, id) {
		var result = confirm("確定刪除此筆記錄(姓名：" + name.trim() + ", 帳號：" + id + ")?");
		if (result) {
			document.forms[0].putOrDelete.name = "_method";
			document.forms[0].putOrDelete.value = "DELETE";
			return true;
		}
		return false;
	}
	function confirmUpdate(id) {
		var result = confirm("確定送出此筆記錄(帳號:" + id.trim() + ")?");
		if (result) {
			// 		  document.forms[0].putOrDelete.name = "_method";
			// 		  document.forms[0].putOrDelete.value = "PUT";
			return true;
		}
		return false;
	}
</script>

</head>
<body>
	<div align="center">
	    <c:url var='post_url' value='/_02/modifyAccount' />
		<form:form method='POST' modelAttribute="account"
			action="${post_url}"  >
			<input type="hidden" name="noname"  id='putOrDelete'   value="" >
			<c:if test='${account.accountNo != null}'>
				<form:hidden path="accountNo" />
				<br>&nbsp;
               <form:hidden path="accountType" />
				<br>&nbsp;
			</c:if>
			<fieldset class="fieldset-auto-width">
				<legend>客戶資料</legend>
				<table>
					<tr>
						<td align='center'>帳戶編號：${account.accountNo}</td>
						<td align='center'>帳戶類型：${account.accountType}</td>
					</tr>
					<tr><td>&nbsp;</td><td></td></tr>
					<tr>
						<td align='right'>姓名：<br>&nbsp;
						</td>
						<td><form:input path="name" /><br>&nbsp; <form:errors
								path="name" cssClass="error" /></td>
					</tr>

					<tr>
						<td align='right'>餘額：<br>&nbsp;
						</td>
						<td><form:input path="balance" /><br>&nbsp; <form:errors
								path="balance" cssClass="error" /></td>
					</tr>
					<tr>
						<td colspan='2' align='center'>
							<input type='submit' value='修改' name='updateBtn' onclick="return confirmUpdate('${account.name}', '${account.accountNo}');">&nbsp; 	
							<input type='submit' value='刪除' name='deleteBtn' onclick="return confirmDelete('${account.name}', '${account.accountNo}');" >
						</td>
					</tr>
				</table>
			</fieldset>
		</form:form>
		<a href="<c:url value='/_02/index' />">回首頁</a>
	</div>
</body>
</html>