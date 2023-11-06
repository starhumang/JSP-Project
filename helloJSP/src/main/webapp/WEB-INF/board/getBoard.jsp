<%@page import="co.yedam.board.service.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/menu.jsp"%>
<%@include file="../layout/header.jsp"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세화면(조회화면)</title>
</head>
<body>
	<%
	BoardVO vo = (BoardVO) request.getAttribute("bno");
	%>
	
	<form action ="modifyForm.do" name="myFrm">
	<input type="hidden" name="bno" value="<%=vo.getBoardNo() %>">
		<table class = "table" border="1">
			<tr>
			<th>글번호</th>
			<td><%=vo.getBoardNo()%></td>
			<th>작성일시</th>
			<td><%=vo.getWriteDate()%></td>
			</tr>
			
			
			<tr>
			<th>글제목</th>
			<td colspan="3"><%=vo.getTitle()%></td>
			</tr>
			
			
			<tr>
			<td colspan="4"><textarea rows ="5" cols="40" class = "form-control"><%=vo.getContent()%></textarea></td>
			</tr>
			<tr>
				<th>이미지</th>
				<td colspan ="3"><img style="align:center; "width="80px" src="image/<%=vo.getImage()%>"></td>
			</tr>
	
			<tr>
			<th>작성자</th>
			<td><%=vo.getWriter()%></td>
			<th>조회수</th>
			<td><%=vo.getViewCnt()%></td>
			</tr>
			
			<tr>
			<td colspan="4" align="center">
			<% if(logId != null && logId.equals(vo.getWriter())){ %>
			<input type="submit" value="수정">
			<input type="button" value="삭제">
			<%}else{ %>
			<input disabled type="submit" value="수정">
			<input disabled type="button" value="삭제">
			<%} %>
			</td>
			</tr>
			
			
		</table>
	</form>
	<p><a href="boardList.do">목록으로</a>
		<script> //삭제버튼을 눌렀을때 액션 속성을 바꿔서 삭제기능 넣기
		document.querySelector('input[type=button]')//
		.addEventListener('click', function(e){
			document.forms.myFrm.action = 'removeForm.do';
			document.forms.myFrm.submit();
		})
		</script>
</body>
</html>
<%@include file="../layout/footer.jsp"%>