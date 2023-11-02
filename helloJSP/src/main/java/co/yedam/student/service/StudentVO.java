package co.yedam.student.service;

import java.util.Date;

import lombok.Data;
@Data
public class StudentVO {

	
	
	private String studentId;
	private String studentName;
	private String studentPassword;
	private String studentDept;
	private Date studentBirthday;
	
	
	 //페이지 로딩되면서 바로 실행
//	.then(resolve => resolve.json())
//	.then(result => {
//		if (result.retCode == "OK") {
//			alert('성공')
//			makeTr({studentId: sid, studentName: sname, studentBirthday: sbirth})
//			document.querySelector('#list').append(tr);
//		} else {
//			alert('실패')
//		}
//	})
//	.catch(err => console.log('error => ', err));
}
