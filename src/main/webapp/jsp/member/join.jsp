<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%
List<Map<String, Object>> memberRows = (List<Map<String, Object>>) request.getAttribute("memberRows");
String loginId = "";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
</head>
<body>
	<script type="text/javascript">
		function passConfirm() {
			var loginPw = document.getElementById('loginPw'); //비밀번호 
			var loginPwchk = document.getElementById('loginPwchk'); //비밀번호 확인 값
			var confrimMsg = document.getElementById('confirmMsg'); //확인 메세지
			var correctColor = "#00ff00"; //맞았을 때 출력되는 색깔.
			var wrongColor = "#ff0000"; //틀렸을 때 출력되는 색깔

			if (loginPw.value == loginPwchk.value) {//loginPw 변수의 값과 loginPwchk 변수의 값과 동일하다.
				confirmMsg.style.color = correctColor;/* span 태그의 ID(confirmMsg) 사용  */
				confirmMsg.innerHTML = "비밀번호 일치";/* innerHTML : HTML 내부에 추가적인 내용을 넣을 때 사용하는 것. */
			} else {
				confirmMsg.style.color = wrongColor;
				confirmMsg.innerHTML = "비밀번호 불일치";
			}
		}

		function checkid() {
			var loginId = document.getElementById('loginId').value;
			if (loginId ==
	<%for (Map<String, Object> memberRow : memberRows) {
	loginId = (String) memberRow.get("loginId");
}%>
		) {
				
			}

		}
	</script>

	<h2>회원가입</h2>

	<form method="POST" action="dojoin">
		<div>
			아이디 : <input type="text" placeholder="사용할 아이디를 입력해주세요."
				name="loginId" style="width: 200px" />
		</div>
		<div>
			<input type="hidden" name="decide_id" id="decide_id">
			<p>
				<span id="decide" style='color: red;'>ID 중복 여부를 확인해주세요.</span> <input
					type="button" id="check_button" value="ID 중복 검사"
					onclick="checkid();">
			</p>
		</div>
		<div>
			비밀번호 : <input type="text" placeholder="사용할 비밀번호를 입력해주세요."
				name="loginPw" class="loginPw" />
		</div>
		<div>
			비밀번호 확인 : <input type="text" placeholder="위의 비밀번호가 같은지 확인해주세요."
				name="loginPwchk" class="loginPwchk" />
			<div class="confirmMsg"></div>
		</div>
		<div>
			이름 : <input type="text" placeholder="성함을 입력해주세요." name="name" />
		</div>
		<button type="submit">회원가입</button>
	</form>

</body>
</html>