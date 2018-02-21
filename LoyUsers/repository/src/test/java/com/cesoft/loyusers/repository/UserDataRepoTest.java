package com.cesoft.loyusers.repository;

import android.content.Context;

import com.cesoft.loyusers.domain.model.User;
import com.cesoft.loyusers.repository.remote.ConfigQuery;
import com.cesoft.loyusers.repository.remote.UserDataRepo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Created by ccasanova on 20/02/2018
 */
@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class UserDataRepoTest {

	private User user;
	private UserDataRepo userDataRepo;

	@Mock private UserDataRepo mockUserDataRepo;

	@Mock private User mockUser;
	@Mock private Context context;
	@Mock private ConfigQuery config;
	@Mock private Observable<List<User>> mockUsersList;

	@Before
	public void setUp() {
		userDataRepo = new UserDataRepo(context, config);
		//userDataRepo.getUserList();

		List<User> usersList = new ArrayList<>();
		user = new User(
				"title",
				"first",
				"last",
				"street",
				"city",
				"state",
				"postcode",
				"large",
				"medium",
				"thumbnail");
		usersList.add(user);
		given(mockUserDataRepo.getUserList()).willReturn(Observable.just(usersList));
	}

	@Test
	public void testGetUsers() {
		Observable<List<User>> usersList = mockUserDataRepo.getUserList();
		assertThat(usersList.count().blockingGet()).isEqualTo(1);
		List<User> list = usersList.blockingFirst();
		assertThat(list.get(0)).isEqualTo(user);
		verify(mockUserDataRepo).getUserList();
	}

	@Test(expected = Exception.class)
	public void testGetUsers2() {
		Observable<List<User>> usersList = userDataRepo.getUserList();
		List<User> list = usersList.blockingFirst();//NO_INTERNET_CONNECTION
		//assertThat(usersList.count().blockingGet()).isEqualTo(0);
	}

	/*@Tests
	public void testGetUsers3() {
		Observable<List<User>> usersList = mockUserDataRepo.getUserList();
		//Observable<List<User>> spyUserList = spy(usersList);
		//Mockito.when(spyUserList.blockingFirst().get(0)).thenReturn(user);
		//Mockito.doReturn(user).when(spyUserList).blockingFirst().get(0);
		//assertThat(spyUserList.blockingFirst().get(0)).isEqualTo(user);

		//Mockito.when(usersList.blockingFirst().get(0)).thenReturn(user);
		//assertThat(userDataRepo.getUserList().blockingFirst().get(0)).isEqualTo(user);

		Mockito.doReturn(user).when(mockUserDataRepo.getUserList().blockingFirst().get(0));
		assertThat(mockUserDataRepo.getUserList().blockingFirst().get(0)).isEqualTo(user);
	}*/
}
