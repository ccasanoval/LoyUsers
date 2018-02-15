package com.cesoft.loyusers.domain.model;

/**
 * Created by ccasanova on 07/02/2018
 */
// Define el objeto entidad del negocio
public class User {
	private String title;
	private String first;
	private String last;

	private String street;
	private String city;
	private String state;
	private String postcode;

	private String large;
	private String medium;
	private String thumbnail;

	public User(
			String title,
			String first,
			String last,
			String street,
			String city,
			String state,
			String postcode,
			String large,
			String medium,
			String thumbnail) {
		this.title = title;
		this.first = first;
		this.last = last;
		this.street = street;
		this.city = city;
		this.state = state;
		this.postcode = postcode;
		this.large = large;
		this.medium = medium;
		this.thumbnail = thumbnail;
	}


	public String getTitle() {
		return title;
	}

	public String getFirst() {
		return first;
	}

	public String getLast() {
		return last;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getPostcode() {
		return postcode;
	}

	public String getLarge() {
		return large;
	}

	public String getMedium() {
		return medium;
	}

	public String getThumbnail() {
		return thumbnail;
	}
}
