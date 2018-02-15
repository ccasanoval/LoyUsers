package com.cesoft.loyusers.repository.remote.model;

import com.cesoft.loyusers.domain.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ccasanova on 07/02/2018
 */
public class RemoteUserParser {

	private RemoteUserParser(){}
	public static List<User> parse(RemoteUser remote) {
		List<User> userList = new ArrayList<>();
		for(RemoteUser.Result res : remote.results) {
			User user = new User(
					res.name.title,
					res.name.first,
					res.name.last,
					res.location.street,
					res.location.city,
					res.location.state,
					res.location.postcode,
					res.picture.large,
					res.picture.medium,
					res.picture.thumbnail
					);
			userList.add(user);
		}
		return userList;
	}
}
