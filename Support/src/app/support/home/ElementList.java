package app.support.home;

import android.graphics.drawable.Drawable;

public class ElementList {
	
	Drawable icon;
	String name;
	String address;
	int stars;
	int id;
	String countStars;
	String email;
	String telephone;
	
	public ElementList() {
		super();
	}

	public ElementList(Drawable icon, String name, String address, int stars, String countStars, String email, String telephone, int id) {
		super();
		this.icon = icon;
		this.name = name;
		this.address = address;
		this.stars = stars;
		this.countStars = countStars;
		this.email = email;
		this.telephone = telephone;
		this.id = id;
	}

	public Drawable getIcon() {
		return icon;
	}

	public void setIcon(Drawable icon) {
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountStars() {
		return countStars;
	}

	public void setCountStars(String countStars) {
		this.countStars = countStars;
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
}
