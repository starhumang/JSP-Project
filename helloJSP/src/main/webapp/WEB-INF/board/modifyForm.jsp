<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../layout/menu.jsp"></jsp:include>
<jsp:include page="../layout/header.jsp"></jsp:include>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modifyForm.jsp</title>
</head>
<body>

<h3>게시글 수정화면</h3>
	<form action="modifyBoard.do" method="post">
		<input type="hidden" name="bno" value="${vo.boardNo }">
		<table border="1" class ="table">
			<tr>
				<th>제목</th>
				<td><input type ="text" name="title" value="${vo.title }"></td>
			</tr>
			
			<tr>
				<th>작성자</th>
				<td><input type ="text" name="writer" value="${vo.writer }"></td>
			</tr>
			
			<tr>
				<td colspan ="2"><textarea cols="40" rows="5" name="content" class = "form-control">${vo.content }</textarea></td>
			</tr>
			<tr>
				<th>파일명</th>
				<td><img src="image/${vo.image }" width="80px"></td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
				<input type="submit" value="수정"> <!-- 버튼 누르면 위의 액션 실행 -->
				<input type="reset" value="초기화">
				</td>
			</tr>
		</table>
	</form>

</body>
</html>
<jsp:include page="../layout/footer.jsp"></jsp:include>