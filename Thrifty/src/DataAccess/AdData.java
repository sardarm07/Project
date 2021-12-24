package DataAccess;
import java.io.Serializable;

public class AdData implements Serializable{
	private String Name;
	private float price;
	private String name;
	private String subCat;
	private String Title;
	private String Description;
	private String email;
	private boolean sold;
	private int id;
	
	public AdData(String title, String des,String name, String  price,String cat, String subCat, String email, boolean sold) {
		setTitle(title);
		setDescription(des);
		this.setCatName(cat);
		this.setSubCat(subCat);
		setName(name);
		this.setPrice(Float.parseFloat(price));
		this.setEmail(email);
	}
	
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getCatName() {
		return name;
	}

	public void setCatName(String name) {
		this.name = name;
	}

	public String getSubCat() {
		return subCat;
	}

	public void setSubCat(String subCat) {
		this.subCat = subCat;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isSold() {
		return sold;
	}

	public void setSold(boolean sold) {
		this.sold = sold;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AdData(String title, String des,String name, String  price,String cat, String subCat, String email, boolean sold, int id) {
		setTitle(title);
		setDescription(des);
		this.setCatName(cat);
		this.setSubCat(subCat);
		setName(name);
		this.setPrice(Float.parseFloat(price));
		this.setEmail(email);
		this.setId(id);
	}
	
	
}
