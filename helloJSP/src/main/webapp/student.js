/**
 *  js/student.js
 */

//페이지 로딩되면서 바로 실행
fetch('../studentList.do') //호출
	.then(resolve => resolve.json()) //studentlist json포맷으로
	.then(result => {
		console.log(result); //result 배열임.
		let tbody = document.querySelector('#list');

		result.forEach(student => {
			tbody.append(makeTr(student));
		})
	})
	.catch(err => console.log('error =>', err))


//등록버튼 만들기
//등록버튼 이벤트
document.querySelector('#addBtn').addEventListener('click', addCallback);


//calback함수
function addCallback(e) {
	// 학생아이디에 입력한 값을 찾고싶을때
	let sid = document.querySelector('input[name=sid]').value; // 네임이 아이디라는 인풋상자에 작성한 아이디를 가져오겠다.
	let sname = document.querySelector('input[name=sname]').value;
	let spass = document.querySelector('input[name=spass]').value;
	let sdept = document.querySelector('select[name=sdept]').value;
	let sbirth = document.querySelector('input[name=sbirth]').value;
	//console.log(sid, sname, spass, sdept, sbirth);

	let param = `id=${sid}&name=${sname}&password=${spass}&dept=${sdept}&birthday=${sbirth}`;
	console.log(param);



	//첫번째 방식 get : url패턴, 값의 제한.
	//두번째 방식 post: 주소창에 파라미터 안 나타남, 값의 제한도 없음
	//fetch('../addStudent.do?'+ param) => get방식요청
	fetch('../addStudent.do', {
		method: 'post',//등록방식
		headers: { 'Content-Type': 'application/x-www-form-urlencoded' },//값의 형식
		body: param //넣을 값
	})// => post방식요청
		.then(resolve => resolve.json())
		.then(result => {
			if (result.retCode == "OK") {
				alert('성공')
				makeTr({ studentId: sid, studentName: sname, studentBirthday: sbirth })
				document.querySelector('#list').append(tr);
			} else {
				alert('실패')
			}
		})
		.catch(err => console.log('error => ', err));
} //end of addCallback


//수정버튼 만들기		
document.querySelector("#modBtn").addEventListener('click', modifyCallback);
//수정버튼 이벤트
function modifyCallback(e) {
	let id = document.querySelector('input[name=sid]').value;
	let name = document.querySelector('input[name=sname]').value;
	let pass = document.querySelector('input[name=spass]').value;
	let birth = document.querySelector('input[name=sbirth]').value;
	let param = `id=${id}&name=${name}&password=${pass}&birthday=${birth}`;
	console.log(param);

	// 포스트방식으로 만들기
	fetch('../editStudent.do', {
		method: 'post',
		headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
		body: param
	})
		.then(resolve => resolve.json())
		.then(result => {
			if (result.retCode == "OK") {
				alert('성공')
				//result.vo.studentId;
				let targetTr = document.querySelector('tr[data-sid=' + result.vo.studentId +']')
				let newTr = makeTr(result.vo);//옛Tr을 새Tr로 변경작업
				let parentElem = document.querySelector('#list');
				parentElem.replaceChild(newTr, targerTr);// tbody에서 자식요소들을 바꿀때 사용
				document.getElementById("myModal").style.display = 'none';
			} else {
				alert('실패');
			}
		})
		.catch(err => console.log('error => ', err));
}// end of modifyCallback



//Tr만들기
function makeTr(obj) {
	let showFields = ['studentId', 'studentName', 'studentBirthday']
	let tr = document.createElement('tr');
	tr.setAttribute('data-sid', obj.studentId);//tr을 누르면 그 누른 정보가 뜨도록 하는데 누르는것
	tr.addEventListener('dblclick', showModal);

	for (let prop of showFields) {
		let td = document.createElement('td');
		td.innerHTML = obj[prop];
		tr.append(td);
	}

	//모달 가져온거
	function showModal(e) {
		console.log(e.target.parentElement, this);
		let id = this.children[0].innerHTML;
		id = this.dataset.sid;// 'data-sid : std1' 아이디를 가져오는게 목적

		fetch('../getStudent.do?id=' + id)
			.then(resolve => resolve.json())//resolve를 json폼으로 바꿈
			.then(result => {
				console.log(result)
				if (result.retCode == "OK") {
					modal.querySelector("input[name=sid]").value = result.vo.studentId;
					modal.querySelector("input[name=pass]").value = result.vo.studentPassword;
					modal.querySelector("input[name=name]").value = result.vo.studentName;
					modal.querySelector("input[name=birth]").value = result.vo.studentBirthday;

				} else {
					alert('실패');
				}
			})
			.catch(err => console.log('error =>', err));



		// Get the modal
		var modal = document.getElementById("myModal");
		modal.style.display = "block";

		let data = { id: "std1", name: "홍길동", pass: "1234", birth: "2000-01-04" }

		modal.querySelector("h2").innerHTML = data.name;
		modal.querySelector('input[name=pass]').value = data.pass;
		modal.querySelector('input[name=name]').value = data.name;
		modal.querySelector('input[name=birth]').value = data.birth;

		var span = document.getElementsByClassName("close")[0];

		// When the user clicks on <span> (x), close the modal
		span.onclick = function() {
			modal.style.display = "none";
		}

		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
			if (event.target == modal) {
				modal.style.display = "none";
			}
		}
	}

	//삭제버튼 만들기
	let td = document.createElement('td');
	let btn = document.createElement('button');
	btn.setAttribute('data-sid', obj.studentId);
	btn.innerHTML = '삭제'; // *버튼은 라벨 필요함! 
	btn.addEventListener('click', function(e) {
		//ajax호출(버튼 클릭해야지) : 서버에 정보넘겨주고 처리한다는 의미 => servlet 실행! 
		fetch('../delStudent.do?sid=' + obj.studentId)
			.then(resolve => resolve.json())
			.then(result => {
				console.log(result)
				if (result.retCode == "OK") {
					alert("삭제성공");
					tr.remove();
				} else {
					alert("삭제실패");
				}
			})
			.catch(err => console.log('error :', err));
	})
	td.append(btn);
	tr.append(td);

	return tr;
}//maketr




