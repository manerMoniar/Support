package app.support.settings;

import android.graphics.drawable.Drawable;

public class ListSettings {
	String cont;
	Drawable icon;
	int id;
	
	public ListSettings() {
		super();
	}

	public ListSettings(String cont, Drawable icon, int id) {
		super();
		this.cont = cont;
		this.icon = icon;
		this.id = id;
	}

	public String getCont() {
		return cont;
	}

	public void setCont(String cont) {
		this.cont = cont;
	}

	public Drawable getIcon() {
		return icon;
	}

	public void setIcon(Drawable icon) {
		this.icon = icon;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
