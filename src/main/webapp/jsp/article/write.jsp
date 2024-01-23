<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 작성 페이지</title>
</head>
<body> 

	<h2 style="text-align: center">게시글 작성</h2>
	<div class="ArticleContainer">
		<form method="POST" action="doWrite" style="gap: 1rem">
			<div class="form-group">
				<label for="subject">제목</label> <input type="text" name="title"
					placeholer="제목을 입력하세요." />
			</div>
			<div class="form-group">
				<label for="content">내용</label>
				<textarea name="body" rows="10"></textarea>
			</div>
			<button type="submit" class="btn btn-primary">작성완료</button>
		</form>
	</div>
	<div>
		<a style="color: green" href="list">리스트로 돌아가기</a>
	</div>

</body>
</html>