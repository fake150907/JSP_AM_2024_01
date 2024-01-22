<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
List<Map<String, Object>> articleRows = (List<Map<String, Object>>) request.getAttribute("articleRows");
int page1 = (int) request.getAttribute("page1");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"">
<title>게시물 목록</title>
</head>
<body>

	<a href="../home/main" target="_blank">메인으로 이동</a>


	<h2>게시물 목록</h2>
	<table style="border-collapse: collapse; border-color: green"
		; border="1px">
		<thead>
			<tr>
				<th>번호</th>
				<th>작성날짜</th>
				<th>제목</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (Map<String, Object> articleRow : articleRows) {
			%>
			<tr style="text-align: center">
				<td><%=articleRow.get("id")%></td>
				<td><%=articleRow.get("regDate")%></td>
				<td><a href="detail?id=<%=articleRow.get("id")%>"><%=articleRow.get("title")%></a></td>
				<td><a href="doDelete?id=<%=articleRow.get("id")%>">del</a></td>
			</tr>
			<tr>
				<tb></tb>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
	<table style="border-collapse: collapse; border-color: green"
		; border="1px">
		<thead>
			<tr>
				<th>페이지</th>
			</tr>
		</thead>
		<tbody>
			<tr style="text-align: center">
				<td><a href="list?page=<%=(page1 - 1)%>">이전</a></td>
				<td><a href="list?page=<%=(page1 + 1)%>">다음</a></td>
			</tr>
		</tbody>
	</table>

</body>
</html>