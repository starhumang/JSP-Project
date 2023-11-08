package co.yedam.common;

public class PageDTO {
	//필드
	int total;//전체 댓글건수
	int currentPage; // 현재페이지
	boolean next, prev; //이전, 이후 페이지
	int startPage, endPage;//시작페이지 끝페이지
	int boardNo; //게시글 번호
	
	//생성자
	public PageDTO(int boardNo, int total, int currentPage) {
		//게시글 1개에 댓글은 5개씩 페이지는 10page씩 단위로 잘라서 보여주기 
		this.boardNo = boardNo;
		this.total = total;
		this.currentPage = currentPage;
		
		//완전 끝 페이지
		int realEnd = (int)Math.ceil(total / 5.0);
		
		//10page 중 마지막 페이지
		this.endPage = (int)Math.ceil(currentPage / 10.0) * 10;
		this.endPage = this.endPage > realEnd ? realEnd : this.endPage;

		//10page 중 첫번째 페이지
		this.startPage = this.endPage - 9;
		
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}
	
	
}
