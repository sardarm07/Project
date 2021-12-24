package logic;

public class Address {
	private String cityName;
	private String area;
	private String street;
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

	public String houseNo;
	
	public Address( String city,String area, String house) {
		this.setCityName(city);
		this.setArea(area);
		this.houseNo = house;
	}
}
