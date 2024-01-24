<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
</head>
<body>
	<h2>회원가입</h2>

	<form method="POST" action="dojoin">
		<div>
			loginId : <input type="text" placeholder="사용할 아이디를 입력해주세요."
				name="loginId" style="width: 200px" />
		</div>
		<div>
			loginPw : <input type="text" placeholder="사용할 비밀번호를 입력해주세요."
				name="loginPw" />
		</div>
		<div>
			name : <input type="text" placeholder="성함을 입력해주세요." name="name" />
		</div>
		<button type="submit">회원가입</button>
	</form>

</body>
</html>