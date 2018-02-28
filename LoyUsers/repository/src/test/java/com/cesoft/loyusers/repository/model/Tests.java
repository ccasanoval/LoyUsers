package com.cesoft.loyusers.repository.model;

import com.cesoft.loyusers.domain.model.User;
import com.cesoft.loyusers.repository.remote.net.RemoteServiceFactory;
import com.cesoft.loyusers.repository.remote.model.RemoteUser;
import com.cesoft.loyusers.repository.remote.model.RemoteUserParser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.Before;


import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ccasanova on 21/02/2018
 */
public class Tests {
	private User user;
	private RemoteUser remoteUser;

	@Before
	public void setUp() {
		user = new User(
				"miss",
				"carla",
				"crespo",
				"8822 calle de ángel garcía",
				"alcobendas",
				"ceuta",
				"75324",
				"https://randomuser.me/api/portraits/women/65.jpg",
				"https://randomuser.me/api/portraits/med/women/65.jpg",
				"https://randomuser.me/api/portraits/thumb/women/65.jpg");

		//String json = "{\"results\":[	{\"name\":{\"title\":\"miss\",\"first\":\"carla\",\"last\":\"crespo\"},	\"location\":{\"street\":\"8822 calle de ángel garcía\",\"city\":\"alcobendas\",\"state\":\"ceuta\",\"postcode\":75324},	\"phone\":\"930-175-824\",	\"picture\":{	\"large\":\"https://randomuser.me/api/portraits/women/65.jpg\",	\"medium\":\"https://randomuser.me/api/portraits/med/women/65.jpg\",	\"thumbnail\":\"https://randomuser.me/api/portraits/thumb/women/65.jpg\"}}	, {	\"name\":{\"title\":\"mrs\",\"first\":\"sandra\",\"last\":\"lozano\"},\"location\":{\"street\":\"6019 calle de la almudena\",\"city\":\"talavera de la reina\",\"state\":\"cantabria\",\"postcode\":87781},\"phone\":\"993-250-178\",\"picture\":{\"large\":\"https://randomuser.me/api/portraits/women/72.jpg\",\"medium\":\"https://randomuser.me/api/portraits/med/women/72.jpg\",\"thumbnail\":\"https://randomuser.me/api/portraits/thumb/women/72.jpg\"}}],\"info\":{\"seed\":\"CESoft\",\"results\":2,\"page\":1,\"version\":\"1.1\"}}";
		String json = "[{\"name\":{\"title\":\"miss\",\"first\":\"carla\",\"last\":\"crespo\"},	\"location\":{\"street\":\"8822 calle de ángel garcía\",\"city\":\"alcobendas\",\"state\":\"ceuta\",\"postcode\":75324},	\"phone\":\"930-175-824\",	\"picture\":{	\"large\":\"https://randomuser.me/api/portraits/women/65.jpg\",	\"medium\":\"https://randomuser.me/api/portraits/med/women/65.jpg\",	\"thumbnail\":\"https://randomuser.me/api/portraits/thumb/women/65.jpg\"}}	, {	\"name\":{\"title\":\"mrs\",\"first\":\"sandra\",\"last\":\"lozano\"},\"location\":{\"street\":\"6019 calle de la almudena\",\"city\":\"talavera de la reina\",\"state\":\"cantabria\",\"postcode\":87781},\"phone\":\"993-250-178\",\"picture\":{\"large\":\"https://randomuser.me/api/portraits/women/72.jpg\",\"medium\":\"https://randomuser.me/api/portraits/med/women/72.jpg\",\"thumbnail\":\"https://randomuser.me/api/portraits/thumb/women/72.jpg\"}}]";

		try {
			//Para no hacer makeGson public...
			Method method = RemoteServiceFactory.class.getDeclaredMethod("makeGson", (Class<?>[]) null);
			method.setAccessible(true);
			Gson gson = (Gson)method.invoke(null);

			Type listType = new TypeToken<ArrayList<RemoteUser.Result>>(){}.getType();
			remoteUser = new RemoteUser();
			remoteUser.results = gson.fromJson(json, listType);

			System.err.println("-------------"+remoteUser.results.size());
		}catch(Exception e){System.err.println("Exception:-------------"+e);}
	}

	@org.junit.Test
	public void testGetUsers() {
		List<User> userList = RemoteUserParser.parse(remoteUser);
		assertThat(userList.get(0).getCity()).isEqualTo(user.getCity());
		assertThat(userList.get(0).getFirst()).isEqualTo(user.getFirst());
		assertThat(userList.get(0).getLarge()).isEqualTo(user.getLarge());
		assertThat(userList.get(0).getLast()).isEqualTo(user.getLast());
		assertThat(userList.get(0).getMedium()).isEqualTo(user.getMedium());
		assertThat(userList.get(0).getPostcode()).isEqualTo(user.getPostcode());
		assertThat(userList.get(0).getState()).isEqualTo(user.getState());
		assertThat(userList.get(0).getStreet()).isEqualTo(user.getStreet());
		assertThat(userList.get(0).getTitle()).isEqualTo(user.getTitle());
	}
}
