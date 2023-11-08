<%@page import="co.yedam.board.service.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm" %>
<jsp:include page="../layout/menu.jsp"></jsp:include>
<jsp:include page="../layout/header.jsp"></jsp:include>


<style>
	#list span {
		margin: 8px;
	}

	.pagination {
		display: inline-block;
	}

	.pagination a {
		color: black;
		float: left;
		padding: 8px 16px;
		text-decoration: none;
	}

	.pagination a.active {
		background-color: #4CAF50;
		color: white;
	}

	.pagination a:hover:not(.active) {
		background-color: #ddd;
	}

	.pagination {
		display: inline-block;
	}

	.pagination a {
		color: black;
		float: left;
		padding: 8px 16px;
		text-decoration: none;
	}

	.pagination a.active {
		background-color: #4CAF50;
		color: white;
	}

	.pagination a:hover:not(.active) {
		background-color: #ddd;
	}
</style>


<form action="modifyForm.do" name="myFrm">
	<input type="hidden" name="bno" value="${bno.boardNo }>">
	<table class="table" border="1">
		<tr>
			<th>글번호</th>
			<td class="boardNo">${bno.boardNo }</td>
			<th>작성일시</th>
			<td>
				<fm:formatDate value="${bno.writeDate }" pattern="yyyy-MM-dd HH:mm:ss"></fm:formatDate>
			</td>
		</tr>


		<tr>
			<th>글제목</th>
			<td colspan="3">${bno.title }</td>
		</tr>


		<tr>
			<td colspan="4"><textarea rows="5" cols="40" class="form-control">${bno.content }</textarea></td>
		</tr>

		<tr>
			<th>이미지</th>
			<c:if test="${!empty bno.image }">
				<td colspan="3"><img style="align:center" width="80px" src="image/${bno.image }"></td>
			</c:if>
		</tr>

		<tr>
			<th>작성자</th>
			<td>${bno.writer }</td>
			<th>조회수</th>
			<td>${bno.viewCnt }</td>
		</tr>

		<tr>
			<td colspan="4" align="center">
				<c:choose>

					<c:when test="${!empty logId && logId == bno.writer }">
						<input type="submit" value="수정">
						<input type="button" value="삭제">
					</c:when>

					<c:otherwise>
						<input disabled type="submit" value="수정">
						<input disabled type="button" value="삭제">
					</c:otherwise>

				</c:choose>
			</td>
		</tr>
	</table>
</form>

<h3>댓글등록</h3>
<table class="table">
	<tr>
		<th>댓글내용</th>
		<td><input type="text" id="content"></td>
		<td><button id="addReply">댓글등록</button></td>
	</tr>
</table>

<h3>댓글목록</h3>
<ul id="list">
	<li id="template" style="display : none;">
		<span>00</span><b>첫번째글입니다.</b>
		<span>user01</span>
		<span>2023.10.10</span>
		<button>삭제</button>
	</li>
</ul>

<!-- 댓글 페이지-->
<div class="pagination"></div>

<p><a href="boardList.do">목록으로</a></p>

<script>
	//삭제버튼을 눌렀을때 액션 속성을 바꿔서 삭제기능 넣기
	document.querySelector('input[type=button]') //
		.addEventListener('click', function (e) {
			document.forms.myFrm.action = 'removeForm.do';
			document.forms.myFrm.submit();
		});


	//댓글목록.
	//방법1 
	let bno = "${bno.boardNo }";
	//방법2
	//bno = document.querySelector('.boardNo').innerHTML;
	let writer = "${logId }";
	let page = 1;


	function showList(pg = 1) { //page라는 값이 없으면 초기값으로 1을 주겠다.
		document.querySelectorAll('#list li:not(:nth-of-type(1))') //
			.forEach(li => li.remove()); //첫번째 li는 template 용도라서 안 지움.


		fetch('replyList.do?bno=' + bno + '&page=' + pg)
			.then(resolve => resolve.json())
			.then(result => {
				console.log(result);
				if (result.dto.total < 1) {
					let nothing = document.createElement('p');
					nothing.innerHTML = "아직 댓글이 달리지 않았습니다.";
					document.querySelector('.pagination').append(nothing);
				}
				
				
				
				if (pg < 0) {//마지막페이지에 댓글 넣는 기능
					page = Math.ceil(result.dto.total / 5);
					showList(page);
					return;
				}

				result.list.forEach(reply => {
					let li = makeRow(reply);
					// ul>li 생성
					document.querySelector('#list').append(li);
				})
				//페이지 생성
				makePaging(result.dto);
			})
			.catch(err => console.log(err));
	} // end showList
	showList();


	//페이지링크 생성.
	function makePaging(dto = {}) {
		document.querySelector('.pagination').innerHTML = '';
		// 			if(result.dto.total)
		if (dto.prev) {
			let aTag = document.createElement('a');
			aTag.setAttribute('href', dto.startPage - 1);
			aTag.innerHTML = "&laquo;";
			document.querySelector('.pagination').append(aTag);
		}

		for (let i = dto.startPage; i <= dto.endPage; i++) {
			let aTag = document.createElement('a');
			aTag.setAttribute('href', i);
			aTag.innerHTML = i;
			//active 녹색
			if (i == page) {
				aTag.className = 'active'; // class 속성을 지정할때는 className을 사용
			}
			document.querySelector('.pagination').append(aTag);
		}


		if (dto.next) {
			let aTag = document.createElement('a');
			aTag.setAttribute('href', dto.endPage + 1);
			aTag.innerHTML = "&raquo;";
			document.querySelector('.pagination').append(aTag);
		}

		//a에 클릭이벤트 등록
		document.querySelectorAll('.pagination a').forEach(ele => {
			ele.addEventListener('click', function (e) {
				e.preventDefault(); // 기본기능(링크기능)을 차단하고 페이지를 유지하겠습니다.
				page = ele.getAttribute('href');
				showList(page);
			})
		})
	} // end makePaging


	//댓글 등록버튼 이벤트
	document.querySelector('#addReply').addEventListener('click', function (e) {
		let reply = document.querySelector('#content').value;
		console.log(writer);
		if (!bno || writer == 'null' || !reply) {
			alert("값을 확인하세요.");
			return; //함수종료
		}

		// ajax호출 bno/writer/reply => 전달.
		fetch('addReply.do', {
				method: 'post',
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded'
				},
				body: 'bno=' + bno + '&reply=' + reply + '&replyer=' + writer
			})
			.then(resolve => resolve.json())
			.then(result => {
				if (result.retCode == 'OK') {
					// 				document.querySelector('#list').append(makeRow(result.vo));
					showList(-1); //마지막페이지에 댓글 넣는 기능
				} else {
					alert('Error~');
				}
			})
	})



	//함수
	function makeRow(reply) {


		function deleteCallback(e) { //이벤트가 들어감
			console.log(e.target.parentElement) //이벤트 받는 대상의 상위요소
			if (writer != reply.replyer) {
				alert('권한이 없습니다')
				return;
			}

			//삭제하는 함수
			fetch('removeReply.do?rno=' + reply.replyNo)
				.then(resolve => resolve.json())
				.then(result => {

					if (result.retCode == 'OK') {
						alert('Success!')
						e.target.parentElement.remove();
					} else {
						alert('Error!');
					}

				})
				.catch(err => console.log(err))
		} // end deleteCallback

		//내용 집어넣는것
		let temp = document.querySelector("#template").cloneNode(true); //cloneNode(복제해서 새로운 요소를 만듦)
		temp.style.display = 'block';
		temp.querySelector('span:nth-of-type(1)').innerHTML = reply.replyNo; //pk번호
		temp.querySelector('b').innerHTML = reply.reply; // 내용
		temp.querySelector('span:nth-of-type(2)').innerHTML = reply.replyer; //작성자
		temp.querySelector('span:nth-of-type(3)').innerHTML = reply.replyDate; //날짜
		temp.querySelector('button').addEventListener('click', deleteCallback);
		return temp;
	} // end makeRow
</script>
</body>

</html>
<jsp:include page="../layout/footer.jsp"></jsp:include>