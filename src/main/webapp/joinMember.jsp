<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>회원 가입</h2>
	<hr>
	<form action="memberJoinOk.jsp" method="post" name="joinForm">
			아이디 : <input type="text" name="memberid"><br><br>
			비밀번호 : <input type="password" name="memberpw"><br><br>
			회원 이름 : <input type="text" name="membername"><br><br>
			회원 나이 : <input type="text" name="memberage"><br><br>
			이메일 : <input type="text" name="memberemail"><br><br>
			<input type="submit" value="회원가입">
			<input type="reset" value="다시작성">
	</form>

</body>
</html>