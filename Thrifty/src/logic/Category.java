package logic;

public class Category {
	private String name;
	private String subCat;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubCat() {
		return subCat;
	}

	public void setSubCat(String subCat) {
		this.subCat = subCat;
	}

	public Category(String cat, String subCat) {
		setName(cat);
		this.setSubCat(subCat);
	}
}
