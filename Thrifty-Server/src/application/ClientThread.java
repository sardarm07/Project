package application;
import DataAccess.*;

import java.net.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientThread extends Thread{
	private Socket client;
	private DataInputStream in;
	private DataOutputStream out;
	private boolean connected;
	
	public ClientThread (Socket c) {
		
		client = c;
		try {
			in = new DataInputStream(client.getInputStream());
			out = new DataOutputStream(client.getOutputStream());
		}
		catch (Exception e) { 
		System.out.println (e); 
		}
	}
	
	public void run() {
		try { 
			while (true) { 
				String msg = in.readUTF(); 
				System.out.println (msg);
				System.out.println (ConnectionServer.clients.size());
				String[] strArray = msg.split("\\s+");
				
				//if exit request recieved
				if(strArray[0].equals("exit")) {
					break;
				}
				
				//if Login request is recieved
				else if (strArray[0].equals("Login")) {
					Statement stmt = ConnectionServer.con.createStatement();  
					ResultSet rs=stmt.executeQuery("SELECT * FROM thrifty.Account WHERE email = '" + strArray[1] + "' AND password = '" + strArray[2] + "'");
					
					if (rs.next()) {
						System.out.println("found");
						out.writeUTF("True");
						
					}
					else {
						System.out.println("not found");
						out.writeUTF("False");
						continue;
					}
					in.readUTF(); 
					
					rs=stmt.executeQuery("SELECT * FROM thrifty.Account WHERE email = '" + strArray[1] + "'");
					rs.next();
					//making account data object
					int accID= rs.getInt("AccountID");
					String email;
					 String password;
					 String username;
					 String name;
					 String cnic;
					 int age;
					 String Email;
					 String mobile;
					 String cityName;
					 String area;
					 String street;
					 String houseNo;
					 
					 username = rs.getString("username");
					 password = rs.getString("password");
					 email = rs.getString("email");
					 
					rs=stmt.executeQuery("SELECT * FROM thrifty.User WHERE AccountID = '" + accID + "'");
					rs.next();
					 
					 name = rs.getString("name");
					 cnic = rs.getString("cnic");
					 age = rs.getInt("age");
					 
					 rs=stmt.executeQuery("SELECT * FROM thrifty.Address WHERE userID = '" + accID + "'");
					rs.next();
					 
					 cityName = rs.getString("city_name");
					 area = rs.getString("area");
					 houseNo = rs.getString("houseNo");
					 
					 rs=stmt.executeQuery("SELECT * FROM thrifty.ContactInfo WHERE userID = '" + accID + "'");
						rs.next();
					 
					 mobile = rs.getString("mobileNo");
					 Email = rs.getString("email");
					 
					 ArrayList<AdData> ads = new ArrayList<AdData>();
					 //getting all the ads
					 rs=stmt.executeQuery("SELECT * FROM thrifty.Ad WHERE AccountID = '" + accID + "'");
					 while(rs.next()) {
						 String Name;
						 float price;
						 String Catname;
						 String subCat;
						 String Title;
						 String Description;
						 Statement stmt2 = ConnectionServer.con.createStatement();  
						 boolean sold=false;
						 //String Ademail;
						 int AdID = rs.getInt("AdID");
						 Title = rs.getString("Title");
						 sold = rs.getBoolean("sold");
						 Description = rs.getString("Description");
						 ResultSet rs2 = stmt2.executeQuery("SELECT * FROM thrifty.Product WHERE AdID = '" + AdID + "'");
						 rs2.next();
						 Name = rs2.getString("Name");
						 price = Float.parseFloat(rs2.getString("Price"));
						 
						 
						 Statement stmt3 = ConnectionServer.con.createStatement();
						 ResultSet rs3 = stmt3.executeQuery("SELECT * FROM thrifty.Category WHERE AdID = '" + AdID + "'");
						 rs3.next();
						 Catname = rs3.getString("Name");
						 subCat = rs3.getString("subCategoryName");
						 
						 AdData tempAd = new AdData(Title, Description,Name, Float.toString(price),Catname, subCat, email, sold, AdID); 
						 
						 
						 ads.add(tempAd);
						 
						 //rs2 = stmt.executeQuery("SELECT * FROM thrifty.Feedback WHERE AdID = '" + AdID + "'");
						 
						  
					 }
					
					AccountData acc = new AccountData(username, email, password,  name, cnic, Integer.toString(age), mobile,  Email,  cityName, area,  houseNo);
					acc.ads = ads;
					
					// get the output stream from the socket.
		            OutputStream outputStream = client.getOutputStream();
		            // create an object output stream from the output stream so we can send an object through it
		            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
		            
		            objectOutputStream.writeObject(acc);
		            
				}
				
				//if signUp request is recieved
				else if(strArray[0].equals("SignUp")) {
					out.writeUTF("True");
					//System.out.println("wORKED");
					
					// get the input stream from the connected socket
			        InputStream inputStream = client.getInputStream();
			        // create a DataInputStream so we can read data from it.
			        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
			        
			        AccountData ad = (AccountData) objectInputStream.readObject();
			        Statement stmt2 = ConnectionServer.con.createStatement();  
					int res=stmt2.executeUpdate("Insert into thrifty.Account (email, password, username) values ('" + ad.getEmail()+ "'" + " , "+ "'" + ad.getPassword()+ "'" + " , " +"'" + ad.getUsername()+"')");
					
					ResultSet rs=stmt2.executeQuery("SELECT AccountID FROM thrifty.Account WHERE email = '" + ad.getEmail() + "'");
					rs.next();
					int accID = rs.getInt("AccountID");
					
					res=stmt2.executeUpdate("Insert into thrifty.User (AccountID, name, cnic, age) values ('" + accID+ "'" + " , "+ "'" + ad.getName()+ "'" + " , " +"'" + ad.getCnic() + "','"+ ad.getAge()+"')");
					rs=stmt2.executeQuery("SELECT userID FROM thrifty.User WHERE AccountID = '" + accID + "'");
					rs.next();
					int userID = rs.getInt("userID");
					
					res=stmt2.executeUpdate("Insert into thrifty.Address (userID, city_name,area, houseNo) values ('" + userID+ "'" + " , "+ "'" + ad.getCityName()+ "'" + " , " +"'" + ad.area+ "'" + " , " +"'" + ad.houseNo+"')");
					res=stmt2.executeUpdate("Insert into thrifty.ContactInfo (userID, mobileNo,email) values ('" + userID+ "'" + " , "+ "'" + ad.getMobile()+ "'" + " , " +"'" + ad.getContEmail()+"')");
					
					if(res>0)
						out.writeUTF("True");
					else
						out.writeUTF("error");
			        
				}
				
				//if user logsout
				else if(strArray[0].equals("Logout")) {

				}
				
				else if(strArray[0].equals("PostAd")) {
					
					// get the input stream from the connected socket
			        InputStream inputStream = client.getInputStream();
			        // create a DataInputStream so we can read data from it.
			        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
			        
			        AdData ad = (AdData) objectInputStream.readObject();
			        Statement stmt = ConnectionServer.con.createStatement();
			        ResultSet rs=stmt.executeQuery("SELECT * FROM thrifty.Account WHERE email = '" + ad.getEmail() + "'");
					rs.next();
					//making account data object
					int accID= rs.getInt("AccountID");
					
					
					//adding ad in the database
					int res=stmt.executeUpdate("Insert into thrifty.Ad (AccountID, Title, Description, sold) values ('" + accID+ "'" + " , "+ "'" + ad.getTitle()+ "'" + " , " +"'" + ad.getDescription() +"',"+0+")");
					
					//getting AdID
					rs=stmt.executeQuery("SELECT * FROM thrifty.Ad WHERE AccountID = '" + accID + "' AND Title = '"+ad.getTitle()+"' AND Description = '"+ad.getDescription()+"'");
					rs.next();
					//making account data object
					int AdID= rs.getInt("AdID");
					
					//adding product
					res=stmt.executeUpdate("Insert into thrifty.Product (AdID, Name, Price) values ('" + AdID+ "'" + " , "+ "'" + ad.getName()+ "'" + " , " +"'" + Float.toString(ad.getPrice()) +"')");
					
					//adding Category
					res=stmt.executeUpdate("Insert into thrifty.Category (AdID, Name, subCategoryName ) values ('" + AdID+ "'" + " , "+ "'" + ad.getCatName()+ "'" + " , " +"'" + ad.getSubCat() +"')");
					
					out.writeUTF(Integer.toString(AdID));
				}
				
				else if(strArray[0].equals("Search")) {
					Statement stmt = ConnectionServer.con.createStatement();  
						ResultSet rs=stmt.executeQuery("SELECT * FROM thrifty.Ad WHERE Title like '%" + strArray[1]+ "%'");
						ArrayList<AdData> ads = new ArrayList<AdData>();
					while(rs.next()) {
						 String Name;
						 float price;
						 String Catname;
						 String subCat;
						 String Title;
						 String Description;
						 boolean sold;
						 Statement stmt2 = ConnectionServer.con.createStatement();  
						 //String Ademail;
						 int AdID = rs.getInt("AdID");
						 int accID = rs.getInt("AccountID");
						 sold = rs.getBoolean("sold");
						 Title = rs.getString("Title");
						 Description = rs.getString("Description");
						 ResultSet rs2 = stmt2.executeQuery("SELECT * FROM thrifty.Product WHERE AdID = '" + AdID + "'");
						 rs2.next();
						 Name = rs2.getString("Name");
						 price = Float.parseFloat(rs2.getString("Price"));
						 
						 
						 Statement stmt3 = ConnectionServer.con.createStatement();
						 ResultSet rs3 = stmt3.executeQuery("SELECT * FROM thrifty.Category WHERE AdID = '" + AdID + "'");
						 rs3.next();
						 Catname = rs3.getString("Name");
						 subCat = rs3.getString("subCategoryName");
						 rs3 = stmt3.executeQuery("SELECT * FROM thrifty.Account WHERE AccountId = '" + accID + "'");
						 rs3.next();
						 String email = rs3.getString("email");
						 AdData tempAd = new AdData(Title, Description,Name, Float.toString(price),Catname, subCat, email, sold); 
						 
						 
						 ads.add(tempAd);
					}
					
					OutputStream outputStream = client.getOutputStream();
		            // create an object output stream from the output stream so we can send an object through it
		            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
		            
		            objectOutputStream.writeObject(ads);
				}
				
				else if(strArray[0].equals("Browse")){
					Statement stmt = ConnectionServer.con.createStatement();  
					ResultSet rs=stmt.executeQuery("SELECT * FROM thrifty.Category WHERE Name like '%" + strArray[1]+ "%' And subCategoryName like '%"+strArray[2]+"%'");
					
					ArrayList<AdData> ads = new ArrayList<AdData>();
					while(rs.next()) {
						int tempId = rs.getInt("AdID");
						Statement stmt2 = ConnectionServer.con.createStatement();
						ResultSet rs2=stmt2.executeQuery("SELECT * FROM thrifty.Ad WHERE AdID = '" + tempId+"'");
						rs2.next();
						 String Name;
						 float price;
						 String Catname;
						 String subCat;
						 String Title;
						 String Description;
						 boolean sold;
						// Statement stmt2 = ConnectionServer.con.createStatement();  
						 //String Ademail;
						 int AdID = rs2.getInt("AdID");
						 int accID = rs2.getInt("AccountID");
						 Title = rs2.getString("Title");
						 sold = rs2.getBoolean("sold");
						 Description = rs2.getString("Description");
						  rs2 = stmt2.executeQuery("SELECT * FROM thrifty.Product WHERE AdID = '" + AdID + "'");
						 rs2.next();
						 Name = rs2.getString("Name");
						 price = Float.parseFloat(rs2.getString("Price"));
						 
						 
						 Statement stmt3 = ConnectionServer.con.createStatement();
						 ResultSet rs3 = stmt3.executeQuery("SELECT * FROM thrifty.Category WHERE AdID = '" + AdID + "'");
						 rs3.next();
						 Catname = rs3.getString("Name");
						 subCat = rs3.getString("subCategoryName");
						 rs3 = stmt3.executeQuery("SELECT * FROM thrifty.Account WHERE AccountId = '" + accID + "'");
						 rs3.next();
						 String email = rs3.getString("email");
						 AdData tempAd = new AdData(Title, Description,Name, Float.toString(price),Catname, subCat, email, sold); 
						 
						 
						 ads.add(tempAd);
					}
					
					OutputStream outputStream = client.getOutputStream();
		            // create an object output stream from the output stream so we can send an object through it
		            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
		            
		            objectOutputStream.writeObject(ads);
				}
				else if(strArray[0].equals("Sold")) {
					
					Statement stmt = ConnectionServer.con.createStatement();  
					int res=stmt.executeUpdate("update thrifty.Ad set sold = 1 where AdID = "+strArray[1]);
				}
				
				else if(strArray[0].equals("UpdateAd")) {
					InputStream inputStream = client.getInputStream();
			        // create a DataInputStream so we can read data from it.
			        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
			        
			        AdData ad = (AdData) objectInputStream.readObject();
			        
					Statement stmt = ConnectionServer.con.createStatement();  
					int res=stmt.executeUpdate("update thrifty.Ad set Title = '"+ad.getTitle()+"', Description = '"+ad.getDescription()+"' where AdID = "+ad.getId());
					res=stmt.executeUpdate("update thrifty.Product set Name = '"+ad.getName()+"', Price = '"+Float.toString(ad.getPrice())+"' where AdID = "+ad.getId());
					res=stmt.executeUpdate("update thrifty.Category set Name = '"+ad.getCatName()+"', subCategoryName = '"+ad.getSubCat()+"' where AdID = "+ad.getId());
					
					
					
				}
				
				
				
			}
		}																																																															
		catch (Exception e) 
		{ 
			System.out.println("This one called");
			System.out.println (e); 
		}
	}
}
