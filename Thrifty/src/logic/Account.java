package logic;

import java.util.ArrayList;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import DataAccess.AccountData;
import DataAccess.AdData;
import controller.ClientController;

public class Account {
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ArrayList<Ad> getAds() {
		return ads;
	}

	public void setAds(ArrayList<Ad> ads) {
		this.ads = ads;
	}

	private User user;
	private String email;
	private String password;
	private String username;
	private ArrayList<Ad> ads;
	
	public Account(String username, String email,String  password, String name,String cnic,String age,String mobile, String Email, String city,String area, String house ) {
		this.setUsername(username);
		this.setEmail(email);
		this.setPassword(password);
		Address adr = new Address(city, area, house);
		ContactInfo cont = new ContactInfo(mobile, Email);
		setUser(new User(name, cnic, age, adr, cont));
		AccountData ad = new AccountData(username, email, password,  name, cnic, age, mobile,  Email,  city, area,  house);//, user);
		try {
			sendAccountToServer(ad);
		}
		catch(Exception e) {
			
		}
		
		
	}
	
	private void sendAccountToServer(AccountData acc)  throws IOException{
		Socket socket = ClientController.getInstance().getClient();
		
		OutputStream outToServer = socket.getOutputStream();
    	DataOutputStream out = new DataOutputStream(outToServer);
    	out.writeUTF("SignUp");
    	
    	InputStream inFromServer = socket.getInputStream();
    	DataInputStream in = new DataInputStream(inFromServer);
    	if (in.readUTF().equals("True")){
    		// get the output stream from the socket.
            OutputStream outputStream = socket.getOutputStream();
            // create an object output stream from the output stream so we can send an object through it
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            
            objectOutputStream.writeObject(acc);
            if(in.readUTF().equals("True")) {
            	
            }
    	}
		
	}
	
	public Account(AccountData acc) {
		Address adr = new Address(acc.getCityName(), acc.area, acc.houseNo);
		ContactInfo cont = new ContactInfo(acc.getMobile(), acc.getContEmail());
		setUser(new User(acc.getName(), acc.getCnic(), Integer.toString(acc.getAge()), adr, cont));
		setEmail(acc.getEmail());
		setPassword(acc.getPassword());
		setUsername(acc.getUsername());
		
		ArrayList<Ad> temp = new ArrayList<Ad>();
		for(int i=0;i<acc.ads.size();i++) {
			Product prod = new Product(acc.ads.get(i).getName(), Float.toString(acc.ads.get(i).getPrice()));
			Category cate = new Category(acc.ads.get(i).getCatName(), acc.ads.get(i).getSubCat());
			
			Ad ad = new Ad(acc.ads.get(i).getTitle(), acc.ads.get(i).getDescription(), prod, cate, acc.ads.get(i).isSold(), acc.ads.get(i).getId());
			temp.add(ad);
			
		}
		setAds(temp);
		
	}
	
	public void postAd(String title, String dec, String prodName, String price, String cat, String subCat) {
		Product prod = new Product(prodName, price);
		Category cate = new Category(cat, subCat);
		
		Ad ad = new Ad(title, dec, prod, cate);
		if(getAds() == null) {
			setAds(new ArrayList<Ad>());
		}
		getAds().add(ad);
		
		AdData Add = new AdData(title, dec,prodName, price,cat, subCat, getEmail(), false);
		Socket socket = ClientController.getInstance().getClient();
		try {
			//System.out.println("Email: "+email);
			OutputStream outToServer = socket.getOutputStream();
	    	DataOutputStream out = new DataOutputStream(outToServer);
	    	out.writeUTF("PostAd");
	    	
            OutputStream outputStream = socket.getOutputStream();
            // create an object output stream from the output stream so we can send an object through it
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            
            objectOutputStream.writeObject(Add);
            
            //getting id from server
            InputStream inFromServer = socket.getInputStream();
        	DataInputStream in = new DataInputStream(inFromServer);
        	getAds().get(getAds().size()-1).id = Integer.parseInt(in.readUTF());
            
            
		}
		catch(Exception e) {
			
		}
		
		
		
		
	}
	
	
	
}
