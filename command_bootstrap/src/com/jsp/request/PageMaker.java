package com.jsp.request;

public class PageMaker {

   private int totalCount;     // 전체 행의 개수
   private int startPage = 1;      // 현재 화면에서 보이는 시작 페이지 번호
   private int endPage = 1;        // 현재 화면에 보이는 마지막 페이지 번호
   private int realEndPage;   // 실제 마지막 페이지
   private boolean prev;       // 페이징 이전 버튼 활성화 여부
   private boolean next;       // 페이징 다음 버튼 활서화 여부
   
   private int displayPageNum = 10;   // 게시판 화면에서 한번에 보여질 페이지 번호의 개수 (1,2,3,4,5,6,7,9,10)

   private SearchCriteria cri = new SearchCriteria();   // 앞서 생성한 Criteria를 주입받는다. 객체를 생성해야 널포인트가 안 터짐.

   public int getTotalCount() {
      return totalCount;
   }

   public void setTotalCount(int totalCount) {
      this.totalCount = totalCount;
      
      calcData();
   }
   
   private void calcData() {
      // 사용자에게 받아오는 getPage = 13
      // 자르는 페이지 단위 = 10
      // 계산해보면,  (int) (13 / (double) 10) * 10 = 20 
      endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);
      
      startPage = (endPage - displayPageNum) + 1;
      
      realEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));
      
      if (startPage < 0) {
         startPage = 1;
      }
      
      if(endPage > realEndPage) {
         endPage = realEndPage;
      }
      
      prev = startPage == 1 ? false : true;
      next = endPage >= realEndPage ? false : true;
      //next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
   }

   public int getStartPage() {
      return startPage;
   }

   public void setStartPage(int startPage) {
      this.startPage = startPage;
   }

   public int getEndPage() {
      return endPage;
   }

   public void setEndPage(int endPage) {
      this.endPage = endPage;
   }

   public boolean isPrev() {
      return prev;
   }

   public void setPrev(boolean prev) {
      this.prev = prev;
   }

   public boolean isNext() {
      return next;
   }

   public void setNext(boolean next) {
      this.next = next;
   }

   public int getDisplayPageNum() {
      return displayPageNum;
   }

   public void setDisplayPageNum(int displayPageNum) {
      this.displayPageNum = displayPageNum;
   }

   public SearchCriteria getCri() {
      return cri;
   }

   public void setCri(SearchCriteria cri) {
      this.cri = cri;
   }

   public int getRealEndPage() {
      return realEndPage;
   }

   public void setRealEndPage(int realEndPage) {
      this.realEndPage = realEndPage;
   }
   
}