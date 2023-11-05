	/**
	 *  js/student.js
	 */
	
	import svc from './service.js';
	
	//async, await : (비동기방식코드) 순차적으로 배치해서 가독성을 높임
	//규칙1. async는 함수의 앞에 있어야 함
	//규칙2. await는 async 함수 안에 위치해야 함
	//규칙3. await를 처리하려면 반환되는 타입이 promise객체여야 함
	//ex async 함수 ( await처리. (primise객체.) )
	
	//세번째 버전(패치를 import로 받아오고 매개값으로 함수를2개 받아오는 버전)
	//svc.studentList(function(result){}, function(err){});
	svc.studentList(
		(result) => {//성공시 실행할 함수 
			console.log("studentList ~ result : ", result);
			let tbody = document.querySelector('#list');
			result.forEach(student => {
				console.log("forEach : student : ", student);
				tbody.append(makeTr(student));
			})
		},
		//실패시 실행할 함수
		(err) => console.log('error=> ', err)
	);//result를 받아서 이 함수를 실행하겠습니다.
	
	
	//[학생목록 출력] 두번째 버전(패치가독성 높인 버전)
	//페이지 로딩되면서 바로 실행
	//async function studentList() {
	//	let req = await fetch('../studentList.do'); //await는 뒤의 정보가 처리될때까지 다른게 처리되지 않고 기다려 달라는 뜻(순차적처리)
	//	let json = await req.json(); // {"retCode" : "OK"} -> {retCode : "ON"}
	//	let tbody = document.querySelector('#list');
	//	try{
	//		json.forEach(student => {
	//			tbody.append(makeTr(student));
	//		})	
	//	}catch(err){
	//		console.log('error=> ', err);
	//	}
	//}
	
	
	//[학생목록 출력] 첫번째 버전(패치기본)
	//fetch('../studentList.do') //호출
	//	.then(resolve => resolve.json()) //studentlist json포맷으로
	//	.then(result => {
	//		console.log(result); //result 배열임.
	//		let tbody = document.querySelector('#list');
	//
	//		result.forEach(student => {
	//			tbody.append(makeTr(student));
	//		})
	//	})
	//	.catch(err => console.log('error =>', err))
	
	
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
	
	
		svc.addStudent(
			//1. optObj=> 
			{//넘겨줄 파라미터
				method: 'post',//등록방식
				headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
				body: param
			},
			//2. successCallback
			result => {
				if (result.retCode == "OK") {
					alert('성공')
					let tr = makeTr({ studentId: sid, studentName: sname, studentBirthday: sbirth })
					document.querySelector('#list').append(tr);
				}
			},
			//3. errorCallback
			err => console.log('error => ', err)
		);
	}//end addCallback 
	
	
	
	//수정버튼 만들기		
	document.querySelector("#modBtn").addEventListener('click', modifyCallback);
	//수정버튼 이벤트
	function modifyCallback(e) {
		console.log(e.target.parentElement)
		console.log("this : " + this)
		let id = document.querySelector('input[name=id]').value;
		let name = document.querySelector('input[name=name]').value;
		let pass = document.querySelector('input[name=pass]').value;
		let birth = document.querySelector('input[name=birth]').value;
		let param = `id=${id}&name=${name}&password=${pass}&birthday=${birth}`;
		console.log(param);
	
		// 포스트방식으로 만들기
	
		//수정
		svc.editStudent(
			//첫번째 파라미터
			{
				method: 'post',
				headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
				body: param
			},
			//성공시 실행할 함수
			result => {
				if (result.retCode == "OK") {
					alert('성공')
					//result.vo.studentId;
					let targetTr = document.querySelector('tr[data-sid=' + result.vo.studentId + ']')
					let newTr = makeTr(result.vo);//옛Tr을 새Tr로 변경작업
					let parentElem = document.querySelector('#list');
					parentElem.replaceChild(newTr, targetTr);// tbody에서 자식요소들을 바꿀때 사용
					document.getElementById("myModal").style.display = 'none';
				} else {
					alert('실패');
				}
			},
			//실패시 실행할 함수
			err => {
				console.log('error => ', err)
			}
		)
	}//end modifyCallback
	
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
		let td = document.createElement('td');
		let btn = document.createElement('button');
		btn.setAttribute('data-sid', obj.studentId);
		btn.innerHTML = '삭제'; // *버튼은 라벨 필요함! 
		btn.addEventListener('click', function(e) {
	
			//삭제버튼 만들기
			svc.removeStudent(obj.studentId,
				//2. 성공시 함수
				result => {
					console.log(result)
					if (result.retCode == "OK") {
						alert("삭제성공");
						tr.remove();
					} else {
						alert("삭제실패");
					}
				},
				//3.실패시 함수
				err => console.log('error :', err)
			)
		})
		td.append(btn);
		tr.append(td);
	
		return tr;
	} // end makeTr
	
	
	function showModal(e) {
		console.log(e.target.parentElement, this);
		let id = this.children[0].innerHTML;//방법1
		id = this.dataset.sid;//방법2
		//정보 가져오는거
		svc.getStudent(id,
			result => {
				console.log(result)
				if (result.retCode == "OK") {
					modal.querySelector("h2").innerHTML = result.vo.studentName;
					modal.querySelector("input[name=id]").value = result.vo.studentId;
					modal.querySelector("input[name=pass]").value = result.vo.studentPassword;
					modal.querySelector("input[name=name]").value = result.vo.studentName;
					modal.querySelector("input[name=birth]").value = result.vo.studentBirthday;
	
				}
			},
			err => console.log('error =>', err)
		)
		// Get the modal
		var modal = document.getElementById("myModal");
		modal.style.display = "block";
		
		
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
		
	}//end showModal
	
	

	
	
	
	
	
	
