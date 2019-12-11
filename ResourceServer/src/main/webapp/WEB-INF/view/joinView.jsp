<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script
  src="https://code.jquery.com/jquery-3.4.1.min.js"
  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
  crossorigin="anonymous"></script>
<script>
	function jf_joinValidate() {
		if($("#uid").val()=="") {
			alert('아이디를 입력해주세요.');
			$("#uid").focus();
			return false;
		} else if($("#name").val()=="") {
			alert('이름를 입력해주세요.');
			$("#name").focus();
			return false;
		} else if($("#password").val()=="") {
			alert('패스워드를 입력해주세요.');
			$("#password").focus();
			return false;
		} else if($("#password_chk").val()=="") {
			alert('패스워드 확인을 입력해주세요.');
			$("#password_chk").focus();
			return false;
		} else if($("#password").val()!= $("#password_chk").val()) {
			alert('패스워드를 확인해주세요.');
			$("#password_chk").focus();
			return false;
		}
	}
</script>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<form name="joinForm" action="/join" method="post" onsubmit="return jf_joinValidate();">
		<label for="uid">ID</label>
		<br>
		<input type="text" name="uid" id="uid">
		<br>
		<label for="name">NAME</label>
		<br>
		<input type="text" name="name" id="name">
		<br>
		<label for="password">PASSWORD</label>
		<br>
		<input type="password" name="password" id="password">
		<br>
		<label for="password_chk">PASSWORD CHECK</label>
		<br>
		<input type="password" id="password_chk">
		<br>
		<br>
		<input type="submit" value="JOIN" style="width:174px;height:30px;background-color:aquamarine;">
		<br>
		<br>
		<input type="button" value="BACK" style="width:174px;height:30px;background:lavender" onclick="javascript:history.back();">
	</form>
</body>
</html>