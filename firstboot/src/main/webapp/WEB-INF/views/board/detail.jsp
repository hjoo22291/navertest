<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/js/jquery-3.6.4.min.js"></script>
<script>
$(document).ready(function(){
	$("#deletebtn").on('click', function(){
		let inputpw = prompt("암호를 입력하세요.");
		//let dbpw = ${detaildto.pw};//el표현식
		let dbpw = $("input:hidden").val(); //밑에 hidden타입으로 불러왔으니까 그 값을 가져와도됨. 위 아래 같은결과.
		if(inputpw == dbpw){
			location.href = "boarddelete?seq=${detaildto.seq}";
		}
		else {
			alert("암호를 확인하세요.");
		}	
	});//delete
	
	$("#updatebtn").on('click', function(){
		let inputpw = prompt("암호를 입력하세요.");
		let dbpw = $("input:hidden").val();
		if(inputpw == dbpw) {
			//$("form").attr("action","boardupdate") //form에 바로 action안주고 이렇게 추가해줘도됨
			//$("form").attr("method","post") //위와 마찬가지.
			$("form").submit(); //form을 submit해라
		}
		else {
			alert("암호를 확인하세요.");
		}	
	});//update
	
});//ready


</script>
</head>

<body>
<h2>글 상세조회</h2>
<form action="boardupdate" method=post><!-- textarea가 글이 길기때문에 post로 보내야함 -->
번호 : <input type=text name="seq" value="${detaildto.seq}" readonly><br>
제목 : <input type=text name="title" value="${detaildto.title}" id="title"><br>
내용 : <br><textarea rows="5" cols="50" name="contents" id="contents" >
${detaildto.contents}
</textarea><br>
작성자 : <input type=text name="writer" value="${detaildto.writer}" readonly><br>
조회수 : <input type=text name="viewcount" value="${detaildto.viewcount}" readonly><br>
작성시간 : <input type=text name="writingtime" value="${detaildto.writingtime}" readonly><br>
<input type=hidden name="pw" value="${detaildto.pw}"><br> 
<input type=button id="updatebtn" value="수정하기">
<input type=button id="deletebtn" value="삭제하기">
</form>

</body>
</html>