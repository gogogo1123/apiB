package damain;

public class Criteria {

	
	private int page;  // 페이지
	private int perpageNum; // 한페이지에 보여줄 출력 개수
	
	
	
    public Criteria() {
    	this.page = 1;
    	this.perpageNum = 15;
    }
    
	
	
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		
		if(page <= 1) {
			this.page =1;
			return;
		}
		
		
		this.page = page;
	}
	public int getPerpageNum() {
		return perpageNum;
	}
	public void setPerpageNum(int perpageNum) {
		this.perpageNum = perpageNum;
	}
	
	
}
