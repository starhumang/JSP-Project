<%@page import="co.yedam.board.service.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	BoardVO vo = (BoardVO) request.getAttribute("bno");
	%>
	<table border=1>
		<tr>
		<th>글번호</th>
		<td>
			<%=vo.getBoardNo()%>
		</td>
		<th>작성일시</th>
		<td>
			<%=vo.getWriteDate()%>
		</td>
		</tr>
		
		
		<tr>
		<th>글제목</th>
		<td colspan="3">
			<%=vo.getTitle()%>
		</td>
		</tr>
		
		
		<tr>
		<td colsan="4"><textarea rows ="5" cols="40"><%=vo.getContent()%></textarea></td>
		</tr>


		<tr>
		<th>작성자</th>
		<td>
			<%=vo.getWriter()%>
		</td>
		<th>조회수</th>
		<td>
			<%=vo.getViewCnt()%>
		</td>
		</tr>
		
	</table>

</body>
</html>