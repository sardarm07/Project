package logic;

public class ContactInfo {
	private String email;
	private String mobile;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public ContactInfo(String mobile, String Email) {
		this.setEmail(Email);
		this.setMobile(mobile);
	}
}
