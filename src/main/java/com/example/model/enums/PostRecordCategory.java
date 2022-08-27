package com.example.model.enums;

public enum PostRecordCategory {
	
	MORNIBG(1,"朝食"),
	LUNCH(2,"昼食"),
	DINNER(3,"夕食");
	
	private int categoryId;
	private String categoryName;
	
	private PostRecordCategory(int categoryId,String categoryName) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}
	
	public int getCategoryId() {
		return categoryId;
	}
	
	public String getCategoryName() {
		return categoryName;
	}

}
