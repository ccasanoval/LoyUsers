package com.cesoft.loyusers.ui.model;

/**
 * Created by ccasanova on 07/02/2018
 */
public class UiUser {
	private String firstName;
	private String lastName;
	private String city;
	private String picture;

	public UiUser(String firstName, String lastName, String city, String picture) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.picture = picture;
	}

	public String getFirstName() { return firstName; }
	public String getLastName() { return lastName; }
	public String getCity() { return city; }
	public String getPicture() { return picture; }
}
