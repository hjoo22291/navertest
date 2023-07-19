<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="boardwrite" method=post>
제목 : <input type=text name="title"><br>
내용 : <textarea rows="5" cols="50" name="contents">
여기에 내용을 입력하세요.
</textarea><br>
작성자 : <input type=text name="writer" value="${sessionid}" readonly><br>
글암호 : <input type=text name="pw"><br> 
<input type=submit value="글 저장">
</form>
</body>
</html>