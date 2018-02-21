package tests;

import com.cesoft.loyusers.domain.executor.PostExecutionThread;
import com.cesoft.loyusers.domain.executor.ThreadExecutor;
import com.cesoft.loyusers.domain.interactor.GetUserList;
import com.cesoft.loyusers.domain.model.User;
import com.cesoft.loyusers.domain.repo.UserRepo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Created by ccasanova on 19/02/2018
 */
@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class Tests {

	private GetUserList getUserList;

	@Mock private ThreadExecutor mockThreadExecutor;
	@Mock private PostExecutionThread mockPostExecutionThread;
	@Mock private UserRepo mockUserRepo;

	//______________________________________________________________________________________________
	@Before
	public void setUp() {
		getUserList = new GetUserList(mockUserRepo, mockThreadExecutor,
				mockPostExecutionThread);
	}

	//______________________________________________________________________________________________
	@Test
	public void testUserModel() {
		User user = new User(
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
		assertThat(user.getTitle()).isEqualTo("title");
		assertThat(user.getFirst()).isEqualTo("first");
		assertThat(user.getLast()).isEqualTo("last");
		assertThat(user.getStreet()).isEqualTo("street");
		assertThat(user.getCity()).isEqualTo("city");
		assertThat(user.getState()).isEqualTo("state");
		assertThat(user.getPostcode()).isEqualTo("postcode");
		assertThat(user.getLarge()).isEqualTo("large");
		assertThat(user.getMedium()).isEqualTo("medium");
		assertThat(user.getThumbnail()).isEqualTo("thumbnail");
	}

	//______________________________________________________________________________________________
	@Test
	public void testGetUserList() {
		getUserList.buildUseCaseObservable();

		verify(mockUserRepo).getUserList();
		verifyNoMoreInteractions(mockUserRepo);
		verifyZeroInteractions(mockThreadExecutor);
		verifyZeroInteractions(mockPostExecutionThread);
	}
}
