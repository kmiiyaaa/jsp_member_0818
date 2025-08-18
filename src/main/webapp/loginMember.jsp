<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 로그인</title>
</head>
<body>
		<h2>회원 로그인</h2>
		<hr>
		<form action="loginMemberOk" method="post" name="loginForm">
			아이디 : <input type="text" name="loginid"><br><br>
			비밀번호 : <input type="password" name="loginpw"><br><br>
			<input type="submit" value="로그인">
		</form>
	
	
</body>
</html>