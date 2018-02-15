package com.cesoft.loyusers.ui.model;

import com.cesoft.loyusers.domain.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ccasanova on 07/02/2018
 */
public class UiUserParser {
	private UiUserParser(){}
	public static List<UiUser> parse(List<User> userList) {
		List<UiUser> uiUserList = new ArrayList<>();
		for(User user : userList) {
			uiUserList.add(new UiUser(
				user.getFirst(),
				user.getLast(),
				user.getCity(),
				user.getThumbnail()
			));
		}
		return uiUserList;
	}
}
