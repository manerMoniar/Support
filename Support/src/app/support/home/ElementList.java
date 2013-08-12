package app.support.home;

import android.graphics.drawable.Drawable;

public class ElementList {
	
	Drawable icon;
	String name;
	String address;
	int stars;
	int id;
	String countStars;
	
	public ElementList() {
		super();
	}

	public ElementList(Drawable icon, String name, String address, int stars, String countStars, int id) {
		super();
		this.icon = icon;
		this.name = name;
		this.address = address;
		this.stars = stars;
		this.countStars = countStars;
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
		
}
