<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%
int id = (int) request.getAttribute("id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h2><%=id%>번 게시글이 삭제 되었습니다.
	</h2>
	<div>
		<a style="color: green" href="list">리스트로 돌아가기</a>
	</div>

</body>
</html>