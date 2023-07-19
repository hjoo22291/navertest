<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="boardsearchlist">
<select name="item">
	<option value="all">모두</option>
	<option value="title">제목</option>
	<option value="contents">내용</option>
	<option value="writer">작성자</option>
</select>
검색어 : <input type=text name="searchword">
<input type=submit value="검색">
</form>

<h2>게시물 리스트입니다.</h2>
<table border=2>
<tr><th>번호</th><th>제목</th><th>작성자</th><th>조회수</th></tr>
<c:forEach items="${boardList }" var="dto">
	<tr><td>${dto.seq}</td><td><a href="boarddetail?seq=${dto.seq}">${dto.title}</a></td><td>${dto.writer}</td><td>${dto.viewcount}</td></tr>
</c:forEach>
</table>

<h3>페이지를 선택하세요.</h3>

<%
int totalBoard = (Integer)request.getAttribute("totalBoard");
int totalPage = 0;
if(totalBoard % 4 == 0){
	totalPage = totalBoard / 4;
}
else {
	totalPage = totalBoard / 4 + 1;
}
for(int i = 1; i <= totalPage; i++){
	%>
	<a href="boardsearchlist?page=<%=i %>"> <%=i %>페이지 </a>
<%
}
%>

</body>
</html>



