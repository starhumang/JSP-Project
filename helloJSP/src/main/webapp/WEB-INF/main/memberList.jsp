<%@page import="co.yedam.board.service.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/menu.jsp"%>
<%@include file="../layout/header.jsp"%>
<body>
	<h3>가입한 유저목록</h3>
	<%
		List<MemberVO> list = (List<MemberVO>) request.getAttribute("list");
		
		System.out.println(list);
	%>
	
	<table class = "table" border="1">
		<thead>
			<tr>
				<th>아이디</th>
				<th>비밀번호</th>
				<th>이름</th>
				<th>전화번호</th>
				<th>관리자여부</th>
			</tr>
		</thead>
		<tbody>
			<%
			for(MemberVO mv : list){
			%>
			<tr>
				<td><%=mv.getMid() %></td>
				<td><%=mv.getPass() %></td>
				<td><%=mv.getName() %></td>
				<td><%=mv.getPhone() %></td>
				<td><%=mv.getRespon() %></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
	<p><a href="main.do">메인으로 돌아가기</a></p>
</body>
</html>
<%@include file="../layout/footer.jsp"%>