/**
 *  service.js
 */

 export default{
	 // 전체목록 조회기능
	 async studentList(successCallback, errorCallback){//성공시 실행할 함수, 에러시 실행할 함수
		 let req = await fetch('../studentList.do');
		 let json = await req.json();
		 try{
			 successCallback(json);
		 }catch (err){
			 errorCallback(err);
		 }
	 },
	 
	 
	 //단건 조회기능.
	 async getStudent(id, successCallback, errorCallback){//넘겨줄 파라미터, 성공시 실행할 함수, 에러시 실행할 함수
		 let req = await fetch('../getStudent.do?id='+ id);
		 let json = await req.json();
		 try{
			 successCallback(json);
		 }catch (err){
			 errorCallback(err);
		 }
	 },
	 
	 
	 //등록기능
	 async addStudent(optObj, successCallback, errorCallback){//넘겨줄 파라미터, 성공시 실행할 함수, 에러시 실행할 함수
		 let req = await fetch('../addStudent.do', optObj);
		 let json = await req.json();
		 try{
			 successCallback(json);
		 }catch (err){
			 errorCallback(err);
		 }
	 },
	 
	 
	 //수정기능
	 async editStudent(optObj, successCallback, errorCallback){//넘겨줄 파라미터, 성공시 실행할 함수, 에러시 실행할 함수
		 let req = await fetch('../editStudent.do', optObj);
		 let json = await req.json();
		 try{
			 successCallback(json);
		 }catch (err){
			 errorCallback(err);
		 }
	 },
	 
	 
	 //삭제기능
	 async removeStudent(id, successCallback, errorCallback){//넘겨줄 파라미터, 성공시 실행할 함수, 에러시 실행할 함수
		 let req = await fetch('../delStudent.do?sid='+ id);
		 let json = await req.json();
		 try{
			 successCallback(json);
		 }catch (err){
			 errorCallback(err);
		 }
	 }

 }