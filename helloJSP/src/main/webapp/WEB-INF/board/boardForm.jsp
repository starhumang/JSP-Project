<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../layout/menu.jsp"></jsp:include>
<jsp:include page="../layout/header.jsp"></jsp:include>

<body>
	<h3>게시글 등록화면</h3>
	<form action="addBoard.do" method="post" enctype="multipart/form-data"><!-- 멀티파트 아니면 www.뭐시기 방식으로 키와 값을 가져옴(이게 디폴트) -->
		<table class = "table" border="1">
			<tr>
				<th>제목</th>
				<td><input type ="text" name="title" class = "form-control"></td>
			</tr>
			
			<tr>
				<th>작성자</th>
				<td><input type ="text" readonly name="writer" class = "form-control" value="${logId }"></td>
			</tr>
			
			<tr>
				<td colspan ="2"><textarea cols="40" rows="5" name="content" class = "form-control"></textarea></td>
			</tr>
			<tr>
				<th>파일명</th>
				<td><input type ="file" name="img" class = "form-control"></td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
				<input type="submit" class="btn btn-primary" value="저장">
				<input type="reset" class="btn btn-warning" value="초기화">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>

<include page="../layout/footer.jsp"></include>