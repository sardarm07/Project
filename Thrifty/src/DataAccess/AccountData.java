package DataAccess;

import java.util.ArrayList;


//import logic.User;
import java.io.Serializable;

public class AccountData implements Serializable{
	//public User user;
	private String email;
	private String password;
	private String username;
	private String name;
	private String cnic;
	private int age;
	private String Email;
	private String mobile;
	private String cityName;
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


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCnic() {
		return cnic;
	}


	public void setCnic(String cnic) {
		this.cnic = cnic;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getContEmail() {
		return Email;
	}


	public void setContEmail(String email) {
		Email = email;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getCityName() {
		return cityName;
	}


	public void setCityName(String cityName) {
		this.cityName = cityName;
	}


	public String getArea() {
		return area;
	}


	public void setArea(String area) {
		this.area = area;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getHouseNo() {
		return houseNo;
	}


	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}


	public ArrayList<AdData> getAds() {
		return ads;
	}


	public void setAds(ArrayList<AdData> ads) {
		this.ads = ads;
	}


	public String area;
	public String street;
	public String houseNo;
	public ArrayList<AdData> ads;
	
	
	//public ArrayList<Ad> ads;
	
	public AccountData(String username, String email,String  password, String name,String cnic,String age,String mobile, String Email, String city,String area, String house) {
		this.setUsername(username);
		this.setEmail(email);
		this.setPassword(password);
		this.setName(name);
		this.setCnic(cnic);
		this.setAge(Integer.parseInt(age));
		
		this.setCityName(city);
		this.area = area;
		this.houseNo = house;
		
		this.setContEmail(Email);
		this.setMobile(mobile);
		
		
		//this.user = user;
	}
}
