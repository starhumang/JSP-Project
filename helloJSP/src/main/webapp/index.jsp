<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<!--  자바코드랑 html을 같이 사용할 수 있는게 jsp 최고의 장점! -->
<body>
	<a href="student/studentInfo.html">학생정보</a>
	<%
		String name = "홍길동";
		int age = 20;
		for(int i = 0; i < 5; i++){
	%>
	<p><%=i %>번째 이름은 <%=name %>이고, 나이는 <%=age %>살 입니다 <p>
	<%
		}
	%>
	<a href="./FirstServlet.jsp">서블릿 링크</a>
	
	<%
	response.sendRedirect("boardList.do"); //원래는 xml파일에서 재시작을 시키면 여기 (index.jsp)를 맨 처음 시작한다고 적어놨으나 내가 지금 여기 이 주소를 적음으로서 이게 먼저 시작됨
	%>
	
	
	
</body>
</html>