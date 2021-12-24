package logic;

import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import DataAccess.AdData;
import controller.ClientController;

public class Ad {
	public Product getProd() {
		return prod;
	}

	public void setProd(Product prod) {
		this.prod = prod;
	}

	public Category getCat() {
		return cat;
	}

	public void setCat(Category cat) {
		this.cat = cat;
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

	private Product prod;
	private Category cat;
	private String Title;
	private String Description;
	private boolean sold;
	public int id;
	
	public Ad(String title, String des, Product pro, Category ca) {
		setProd(pro);
		setCat(ca);
		setTitle(title);
		setDescription(des);
		setSold(false);
		
	}
	
	public Ad(String title, String des, Product pro, Category ca, boolean sold) {
		setProd(pro);
		setCat(ca);
		setTitle(title);
		setDescription(des);
		this.setSold(sold);
		
	}
	
	public Ad(String title, String des, Product pro, Category ca, boolean sold, int id) {
		setProd(pro);
		setCat(ca);
		setTitle(title);
		setDescription(des);
		this.setSold(sold);
		this.id = id;
		
	}
	
	public void UpdateAd(String title, String dec, String prodName, String price, String cat, String subCat, String email) {
		Product prod = new Product(prodName, price);
		Category cate = new Category(cat, subCat);
		setTitle(title);
		setDescription(dec);
		this.setProd(prod);
		this.setCat(cate);
		
		//updating in server
		AdData Add = new AdData(title, dec,prodName, price,cat, subCat, email, isSold(), id);
		
		Socket socket = ClientController.getInstance().getClient();
		try {
			//System.out.println("Email: "+email);
			OutputStream outToServer = socket.getOutputStream();
	    	DataOutputStream out = new DataOutputStream(outToServer);
	    	out.writeUTF("UpdateAd");
	    	
            OutputStream outputStream = socket.getOutputStream();
            // create an object output stream from the output stream so we can send an object through it
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            
            objectOutputStream.writeObject(Add);
		}
		catch(Exception e) {
			
		}
		
	}
}
