<%@page import="co.yedam.board.service.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/menu.jsp"%>
<%@include file="../layout/header.jsp"%>
	
<!DOCTYPE html>
<html>
<head>
<style>
	#list span{
		margin:8px;
	}
</style>
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
			<td class = "boardNo"><%=vo.getBoardNo()%></td>
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
				<td colspan ="3"><img style="align:center" width="80px" src="image/<%=vo.getImage()%>"></td>
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
	
	<h3>댓글등록</h3>
	<table class = "table">
		<tr>
			<th>댓글내용</th>
			<td><input type="text" id ="content"></td>
			<td><button id = "addReply">댓글등록</button></td>
		</tr>
	</table>
	
	<h3>댓글목록</h3>
	<ul id="list">
	 <li id="template" style= "display : none;" >
	 <span>00</span><b>첫번째글입니다.</b>
	 <span>user01</span>
	 <span>2023.10.10</span>
	 <button>삭제</button>
	 </li>
	
	<!-- 이렇게 만들거임-->
	</ul>
	
	
	<p><a href="boardList.do">목록으로</a>
<script> //삭제버튼을 눌렀을때 액션 속성을 바꿔서 삭제기능 넣기
		document.querySelector('input[type=button]')//
		.addEventListener('click', function(e){
			document.forms.myFrm.action = 'removeForm.do';
			document.forms.myFrm.submit();
		});
		
		
		//댓글목록.
		//방법1
		let bno = "<%=vo.getBoardNo()%>"
		let writer = "<%=logId%>"
		//방법2
		bno = document.querySelector('.boardNo').innerHTML;
		fetch('replyList.do?bno='+ bno)
		.then(resolve => resolve.json())
		.then(result=> {
			console.log(result);
			
			result.forEach(reply => {
				let li = makeRow(reply);
				// ul>li 생성
				document.querySelector('#list').append(li);
			})	
		})
		.catch(err => console.log(err));
		
		
		//댓글 등록버튼 이벤트
		document.querySelector('#addReply').addEventListener('click', function (e){
			let reply = document.querySelector('#content').value;
			console.log(writer);
			if( !bno || writer=='null' || !reply ){
				alert("값을 확인하세요.");
				return;//함수종료
			}			
			
			// ajax호출 bno/writer/reply => 전달.
			fetch('addReply.do',{
				method: 'post',
				headers: {'Content-Type': 'application/x-www-form-urlencoded'},
				body: 'bno='+bno+'&reply='+reply+'&replyer='+writer
			})
			.then(resolve => resolve.json())
			.then(result =>{
				if(result.retCode=='OK'){
				document.querySelector('#list').append(makeRow(result.vo));
				}else{
					alert('Error~');
				}
			})
		})
		
		//함수
		function makeRow(reply) {
			let temp = document.querySelector("#template").cloneNode(true);//cloneNode(복제해서 새로운 요소를 만듦)
			temp.style.display = 'block';
			console.log(temp);
			console.log("replyNo : ", reply.replyNo)
			temp.querySelector('span:nth-of-type(1)').innerHTML = reply.replyNo; //pk번호
			temp.querySelector('b').innerHTML = reply.reply; // 내용
			temp.querySelector('span:nth-of-type(2)').innerHTML = reply.replyer; //작성자
			temp.querySelector('span:nth-of-type(3)').innerHTML = reply.replyDate; //날짜
			return temp;
		}
		
</script>
</body>
</html>
<%@include file="../layout/footer.jsp"%>