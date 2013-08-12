package app.support.categories;


public class ElementCategoryList {
	
	String category;
	int id;	
	
	public ElementCategoryList() {
		super();
	}
	
	public ElementCategoryList(String category, int id) {
		super();
		this.category = category;
		this.id = id;
	}

	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
}
