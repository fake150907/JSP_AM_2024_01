<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.KoreaIT.java.Jsp_AM.dto.Article"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
List<Article> articleRows = (List<Article>) request.getAttribute("articleRows");
int cPage = (int) request.getAttribute("page");
int totalPage = (int) request.getAttribute("totalPage");
int pageNumber = (cPage - 1) / 10;
int pageStartNum = pageNumber * 10 + 1;
int pageEndNum = Math.min(pageStartNum + 9, totalPage);

boolean isLogined = (boolean) request.getAttribute("isLogined");
int loginedMemberId = (int) request.getAttribute("loginedMemberId");
Map<String, Object> loginedMember = (Map<String, Object>) request.getAttribute("loginedMember");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 목록</title>
</head>
<body>
	<div>
		<a href="../home/main">메인으로 이동</a>
	</div>
	<div>
		<a href="write">글쓰기</a>
	</div>
	<h2>게시물 목록</h2>

	<table style="border-collapse: collapse; border-color: green"
		border="1px">
		<thead>
			<tr>
				<th>번호</th>
				<th>작성날짜</th>
				<th>제목</th>
				<th>작성자</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (Article articleRow : articleRows) {
			%>
			<tr style="text-align: left;">
				<td><%=articleRow.getId()%></td>
				<td><%=articleRow.getRegDate()%></td>
				<td><a
					href="detail?id=<%=articleRow.getId()%>&memberId=<%=articleRow.getMemberId()%>"><%=articleRow.getTitle()%></a></td>
				<td><%=articleRow.getExtra_writer()%></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>

	<style type="text/css">
.page {
	font-size: 1.4rem;
}

.page>a {
	color: black;
	text-decoration: none;
}

.page>a.cPage {
	color: red;
	text-decoration: underline;
}
</style>

	<div class="page">
		<%
		if (cPage > 1) {
		%>
		<a href="list?page=1">◀◀</a>
		<%
		}
		%>
		<%
		for (int i = pageStartNum; i <= pageEndNum; i++) {
		%>
		<a class="<%=cPage == i ? "cPage" : ""%>" href="list?page=<%=i%>"><%=i%></a>
		<%
		}
		%>
		<%
		if (totalPage > cPage) {
		%>
		<a href="list?page=<%=Math.min(pageStartNum + 10, totalPage)%>">▶▶</a>
		<%
		}
		%>
	</div>

</body>
</html>