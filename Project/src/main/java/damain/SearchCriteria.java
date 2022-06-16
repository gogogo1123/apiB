package damain;

public class SearchCriteria extends Criteria {

	
	private String SearchType;
	private String keyword;
	
	
	
	public SearchCriteria() {
		
	 this.SearchType=SearchType;
	 this.keyword = keyword;
	}



	public String getSearchType() {
		return SearchType;
	}



	public void setSearchType(String searchType) {
		SearchType = searchType;
	}



	public String getKeyword() {
		return keyword;
	}



	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
