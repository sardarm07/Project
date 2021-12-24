package logic;

public class Product {
	private String Name;
	public float price;
	
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

	public Product(String name, String price) {
		setName(name);
		this.price = Float.parseFloat(price);
	}
}
