package com.happydog.service;

public class Page {

	private int postLength;		//한 페이지당 글 수
	private int startPost;		//시작 글 번호
	private int endPost;		//끝 글 번호
	
	private int cPage;			//현재 페이지 번호
	private int currentPage;
	private int startPage;		//시작 페이지 번호
	private int endPage;		//끝 페이지 번호
	
	private int pageLength;		//한 블록당 페이지 수
	private int currentBlock;	//현재 블록
	private int totalPages;		//전체 페이지수
	private int totalRows;		//전체 글 수
	
	public int getPostLength() {
		return postLength;
	}

	public void setPostLength(int postLength) {
		this.postLength = postLength;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageLength() {
		return pageLength;
	}

	public void setPageLength(int pageLength) {
		this.pageLength = pageLength;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	//현재 페이지 번호 숫자로 변환
	public int setCPage(String tempPage){
		if (tempPage == null || tempPage.length() == 0) {
		    cPage = 1;
		}
		try {
		    cPage = Integer.parseInt(tempPage);
		} catch (NumberFormatException e) {
		    cPage = 1;
		}
		return cPage;
	}

	public int getCPage(){
		return cPage;
	}
	
	//페이징 처리
	public void rePaging(){
		String tempPage = cPage + "";
		setCPage(tempPage);
		
		if(cPage==0){ cPage = 1; }
		if(postLength==0){ postLength = 10; }
		if(totalRows==0) { totalRows = 10; }
		if(pageLength==0) { pageLength = 10; }
		//현재 페이지의 시작 글 번호 구하기
		startPost = (cPage - 1) * postLength + 1;
		if(startPost<1){
			startPost = 1;
		}
		
		//현재 페이지의 끝 글 번호 구하기
		endPost = cPage * postLength;
		if(endPost>totalRows){
			endPost = totalRows;
		}
		
		//전체 페이지수 구하기
		int len = postLength;
		totalPages = totalRows % len == 0 ? totalRows / len : (totalRows / len) + 1;
		if (totalPages == 0) {
		    totalPages = 1;
		}
		
		//현재 블록 구하기
		currentBlock = cPage % pageLength == 0 ? cPage / pageLength : (cPage / pageLength) + 1;
		
		//시작 페이지 번호 구하기
		startPage = (currentBlock - 1) * pageLength + 1;
		if(startPage<1){
			startPage = 1;
		}
		
		//끝 페이지 번호 구하기
		endPage = startPage + pageLength - 1;
		if(endPage > totalPages){
			endPage = totalPages;
		}
	}
	
	public void rePaging(int cPage){
		String tempPage = cPage + "";
		setCPage(tempPage);
		
		if(postLength==0){ postLength = 10; }
		if(totalRows==0) { totalRows = 10; }
		if(pageLength==0) { pageLength = 10; }
		
		//현재 페이지의 시작 글 번호 구하기
		startPost = (cPage - 1) * postLength + 1;
		if(startPost<1){
			startPost = 1;
		}
		
		//현재 페이지의 끝 글 번호 구하기
		endPost = cPage * postLength;
		if(endPost>totalRows){
			endPost = totalRows;
		}
		
		//전체 페이지수 구하기
		int len = postLength;
		totalPages = totalRows % len == 0 ? totalRows / len : (totalRows / len) + 1;
		if (totalPages == 0) {
		    totalPages = 1;
		}
		
		//현재 블록 구하기
		currentBlock = cPage % pageLength == 0 ? cPage / pageLength : (cPage / pageLength) + 1;
		
		//시작 페이지 번호 구하기
		startPage = (currentBlock - 1) * pageLength + 1;
		if(startPage<1){
			startPage = 1;
		}
		
		//끝 페이지 번호 구하기
		endPage = startPage + pageLength - 1;
		if(endPage > totalPages){
			endPage = totalPages;
		}
	}
	
	public void rePaging(int cPage, int totalRows){
		String tempPage = cPage + "";
		setCPage(tempPage);
		
		if(postLength==0) { postLength = 10; }
		if(pageLength==0) { pageLength = 10; }
		
		//현재 페이지의 시작 글 번호 구하기
		startPost = (cPage - 1) * postLength + 1;
		if(startPost<1){
			startPost = 1;
		}
		
		//현재 페이지의 끝 글 번호 구하기
		endPost = cPage * postLength;
		if(endPost>totalRows){
			endPost = totalRows;
		}
		
		//전체 페이지수 구하기
		int len = postLength;
		totalPages = totalRows % len == 0 ? totalRows / len : (totalRows / len) + 1;
		if (totalPages == 0) {
		    totalPages = 1;
		}
		
		//현재 블록 구하기
		currentBlock = cPage % pageLength == 0 ? cPage / pageLength : (cPage / pageLength) + 1;
		
		//시작 페이지 번호 구하기
		startPage = (currentBlock - 1) * pageLength + 1;
		if(startPage<1){
			startPage = 1;
		}
		
		//끝 페이지 번호 구하기
		endPage = startPage + pageLength - 1;
		if(endPage > totalPages){
			endPage = totalPages;
		}
	}
	
	public void rePaging(int cPage, int postLength, int pageLength){
		String tempPage = cPage + "";
		setCPage(tempPage);
		
		if(totalRows==0) { totalRows = 10; }
		
		//현재 페이지의 시작 글 번호 구하기
		startPost = (cPage - 1) * postLength + 1;
		if(startPost<1){
			startPost = 1;
		}
		
		//현재 페이지의 끝 글 번호 구하기
		endPost = cPage * postLength;
		if(endPost>totalRows){
			endPost = totalRows;
		}
		
		//전체 페이지수 구하기
		int len = postLength;
		totalPages = totalRows % len == 0 ? totalRows / len : (totalRows / len) + 1;
		if (totalPages == 0) {
		    totalPages = 1;
		}
		
		//현재 블록 구하기
		currentBlock = cPage % pageLength == 0 ? cPage / pageLength : (cPage / pageLength) + 1;
		
		//시작 페이지 번호 구하기
		startPage = (currentBlock - 1) * pageLength + 1;
		if(startPage<1){
			startPage = 1;
		}
		
		//끝 페이지 번호 구하기
		endPage = startPage + pageLength - 1;
		if(endPage > totalPages){
			endPage = totalPages;
		}
	}
	
	public void rePaging(int cPage, int postLength, int pageLength, int totalRows){
		String tempPage = cPage + "";
		setCPage(tempPage);
		//현재 페이지의 시작 글 번호 구하기
		startPost = (cPage - 1) * postLength + 1;
		if(startPost<1){
			startPost = 1;
		}
		
		//현재 페이지의 끝 글 번호 구하기
		endPost = cPage * postLength;
		if(endPost>totalRows){
			endPost = totalRows;
		}
		
		//전체 페이지수 구하기
		int len = postLength;
		totalPages = totalRows % len == 0 ? totalRows / len : (totalRows / len) + 1;
		if (totalPages == 0) {
		    totalPages = 1;
		}
		
		//현재 블록 구하기
		currentBlock = cPage % pageLength == 0 ? cPage / pageLength : (cPage / pageLength) + 1;
		
		//시작 페이지 번호 구하기
		startPage = (currentBlock - 1) * pageLength + 1;
		if(startPage<1){
			startPage = 1;
		}
		
		//끝 페이지 번호 구하기
		endPage = startPage + pageLength - 1;
		if(endPage > totalPages){
			endPage = totalPages;
		}
	}

	public int getStartPost() {
		return startPost;
	}

	public int getEndPost() {
		return endPost;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public int getCurrentBlock() {
		return currentBlock;
	}

	public int getTotalPages() {
		return totalPages;
	}
}
