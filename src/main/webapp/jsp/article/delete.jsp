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
	<h2><%=id%>�� �Խñ��� ���� �Ǿ����ϴ�.
	</h2>
	<div>
		<a style="color: green" href="list">����Ʈ�� ���ư���</a>
	</div>

</body>
</html>