<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<script
  src="https://code.jquery.com/jquery-3.4.1.min.js"
  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
  crossorigin="anonymous"></script>
<script>
	function jf_joinView() {
		location.href="/joinView";
	}
	function jf_loginSubmit() {
		if($("#uid").val()=="") {
			alert("아이디를 입력하세요.");
			$("#uid").focus();
			return false;
		} else if($("#password").val()=="") {
			alert("비밀번호를 입력하세요.");
			$("#password").focus();
			return false;
		}
		$("#loginForm").attr("action", "/login");
		$("#loginForm").submit();
	}
</script>
<meta charset="UTF-8">
<title>로그인 화면</title>
</head>
<body>
	<h2>Welcome To My Service</h2>
	<form id="loginForm" onsubmit="jf_loginSubmit();" method="post">
	<input type="text" name="uid" id="uid" placeholder="Insert ID">
	<br>
	<input type="password" name="password" id="password" placeholder="Insert PW">
	<br>
	<input type="submit" value="LOGIN" style="width:173px;height:50px;background-color:aquamarine;">
	<br>
	<br>
	<input type="button" value="JOIN" style="width:87;background-color: lavender" onclick="jf_joinView();">
	</form>
</body>
</html>