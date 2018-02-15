package com.cesoft.loyusers.repository.remote.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ccasanova on 05/02/2018
 */
public class RemoteUser {
	//{"results":[
	// {"name":{"title":"miss","first":"carla","last":"crespo"},
	// "location":{"street":"8822 calle de ángel garcía","city":"alcobendas","state":"ceuta","postcode":75324},
	// "phone":"930-175-824",
	// "picture":{
	// "large":"https://randomuser.me/api/portraits/women/65.jpg",
	// "medium":"https://randomuser.me/api/portraits/med/women/65.jpg",
	// "thumbnail":"https://randomuser.me/api/portraits/thumb/women/65.jpg"}}
	// ,
	// {"name":{"title":"mrs","first":"sandra","last":"lozano"},"location":{"street":"6019 calle de la almudena","city":"talavera de la reina","state":"cantabria","postcode":87781},"phone":"993-250-178","picture":{"large":"https://randomuser.me/api/portraits/women/72.jpg","medium":"https://randomuser.me/api/portraits/med/women/72.jpg","thumbnail":"https://randomuser.me/api/portraits/thumb/women/72.jpg"}}],"info":{"seed":"CESoft","results":2,"page":1,"version":"1.1"}}
	public class Name {
		public String title;
		public String first;
		public String last;
	}
	public class Location {
		public String street;
		public String city;
		public String state;
		public String postcode;
	}
	public class Picture {
		public String large;
		public String medium;
		public String thumbnail;
	}
	public class Result {
		public Name name;
		public Location location;
		public Picture picture;
	}

	@SerializedName("results")
	public List<Result> results;
}
