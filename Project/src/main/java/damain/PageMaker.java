package damain;

public class PageMaker {

	
	private int totalCount; // 전체 데이터 개수.
	private int startPage;   // 첫번째 번호
	private int endPage; // 마지막 번호
	private boolean prev; // 이전버튼
	private boolean next; // 다음 버튼.
	private int displayPageNum=10; // 밑에 표시되는 페이징
	private SearchCriteria scri; // 변수 가져온거
	
	
	
	
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
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
	public SearchCriteria getScri() {
		return scri;
	}
	public void setScri(SearchCriteria scri) {
		this.scri = scri;
	}
	
	public void calcData() {
		
		
		
		
		
		
		
		endPage = (int)(Math.ceil(scri.getPage()/(double)displayPageNum)*displayPageNum);
		System.out.println("endPage ="+endPage);
		
		startPage = (endPage-displayPageNum)+1;
		System.out.println("staryPage ="+startPage);
		
		int tempEndPage = (int)(Math.ceil(totalCount/(double)scri.getPerpageNum()));
		System.out.println("tempEndPage ="+tempEndPage);
		
		if(endPage > tempEndPage) {
			endPage = tempEndPage;
		};
		
		//이전페이지  == > 1일때 거짓 아니면 진실
		prev = startPage ==1 ? false:true;
		// 다음페이지 == > 
		next = endPage*scri.getPerpageNum() >=totalCount ? false:true;
	}
	
	
}
