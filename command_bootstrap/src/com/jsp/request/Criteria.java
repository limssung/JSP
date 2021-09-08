package com.jsp.request;

public class Criteria {

	private int page = 1;
	private int perPageNum = 10;
	private int startRowNum;
	private int endRowNum;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if (page < 1)
			page = 1;
		this.page = page;
	}

	public void setPage(String page) { // 넘어오는 데이터는 스트링으로 넘어오기에 오버로딩된 메소드를 만든다.
		if (page != null && !page.isEmpty()) {
			try {
				setPage(Integer.parseInt(page));
			} catch (NumberFormatException e) { // page 번호가 파싱이 안되는 경우는 그냥 1로 처리하자.
				this.page = 1;
			}
		} else {
			this.page = 1;
		}
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		if (perPageNum < 1)
			perPageNum = 1; // 잘못된 페이징처리가 된 부분이 있다면 확인하기 위해 숫자를 1로 정함
		this.perPageNum = perPageNum;
	}

	public void setPerPageNum(String perPageNum) { // setPage와 마찬가지
		if (perPageNum != null && !perPageNum.isEmpty()) {
			try {
				setPerPageNum(Integer.parseInt(perPageNum));
			} catch (NumberFormatException e) {
				this.perPageNum = 10;
			}
		} else {
			this.perPageNum = 10;
		}
	}

	public int getStartRowNum() {
		this.startRowNum = (this.page - 1) * this.perPageNum;
		return this.startRowNum;
	}

	public int getEndRowNum() {
		this.endRowNum = this.startRowNum + this.perPageNum - 1;
		return this.endRowNum;
	}

}