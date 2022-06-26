<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<link rel='stylesheet'
	href="<c:url value='/css/style.css' />" type="text/css" />
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align='center'>
		<h3>帳戶資料</h3>
		<hr>
		<table border='1'>
			<tr>
				<th width='80'>帳戶編號</th>
				<th width='80'>帳戶類型</th>
				<th width='160'>客戶姓名</th>
				<th width='160'>餘額</th>
			</tr>
			<c:choose>
				<c:when test="${not empty accountList}">
					<c:forEach var='account' items="${accountList}">
						<tr>
							<td align='center'>
							<a	href='modifyAccount/${account.accountNo}/${account.accountType}'>${account.accountNo}</a></td>
							<td align='center'>${account.accountType}</td>
							<td align='center'>${account.name}</td>
							<td align='right'><fmt:formatNumber pattern='#,###' value='${account.balance}' /></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
				查無帳戶資料
			</c:otherwise>
			</c:choose>
		</table>
		<br> <a href="<c:url value='/_02/index' />">回首頁</a>
	</div>
	
</body>
</html>