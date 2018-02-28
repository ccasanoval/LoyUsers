package com.cesoft.loyusers.repository.net;

import com.cesoft.loyusers.repository.remote.model.RemoteUser;
import com.cesoft.loyusers.repository.remote.net.RemoteServiceFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ccasanova on 22/02/2018
 */
public class Tests {

	private CountDownLatch responseLatch;

	@Before
	public void setUp() {
		responseLatch = new CountDownLatch(1);
	}

	@Test(timeout=30000)
	public void fetchQuestions() throws InterruptedException {
		final int nReg = 1;
		RemoteServiceFactory.makeService().getUserList(nReg).enqueue(new Callback<RemoteUser>() {
			@Override
			public void onResponse(Call<RemoteUser> call, Response<RemoteUser> response) {
				Assert.assertNotNull(response);
				Assert.assertNotNull(response.body());
				List<RemoteUser.Result> res = response.body().results;
				Assert.assertEquals(res.size(), nReg);
				System.err.println("------"+res.get(0).name.first);

				/*User user = new User(
						"miss",
						"carla",
						"crespo",
						"8822 calle de ángel garcía",
						"alcobendas",
						"ceuta",
						"75324",
						"https://randomuser.me/api/portraits/women/65.jpg",
						"https://randomuser.me/api/portraits/med/women/65.jpg",
						"https://randomuser.me/api/portraits/thumb/women/65.jpg");*/

				responseLatch.countDown();
			}

			@Override
			public void onFailure(Call<RemoteUser> call, Throwable t) {
				responseLatch.countDown();
				Assert.fail("onFailure: "+t);
			}
		});

		responseLatch.await();
	}
}
