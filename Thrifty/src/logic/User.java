package logic;

public class User {
	//make all private
	private String name;
	private String cnic;
	private String gender;
	private int age;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Address getAdr() {
		return adr;
	}

	public void setAdr(Address adr) {
		this.adr = adr;
	}

	public ContactInfo getCont() {
		return cont;
	}

	public void setCont(ContactInfo cont) {
		this.cont = cont;
	}

	public Address adr;
	public ContactInfo cont;
	
	public User(String name, String cnic, String age, Address ad, ContactInfo cn) {
		this.setName(name);
		this.setCnic(cnic);
		this.setAge(Integer.parseInt(age));
		this.adr = ad;
		this.cont = cn;
	}
	
	
}
