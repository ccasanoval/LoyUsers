package com.cesoft.loyusers.repository.remote;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


import com.cesoft.loyusers.domain.model.User;
import com.cesoft.loyusers.domain.repo.UserRepo;
import com.cesoft.loyusers.repository.remote.cache.Cache;
import com.cesoft.loyusers.repository.remote.model.RemoteUser;
import com.cesoft.loyusers.repository.remote.model.RemoteUserParser;
import com.cesoft.loyusers.repository.remote.net.RemoteServiceFactory;
import com.nytimes.android.external.store.base.Store;
import com.nytimes.android.external.store.base.impl.BarCode;



import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;

/**
 * Created by ccasanova on 05/02/2018
 */
//TODO: Cache
// https://github.com/iainconnor/ObjectCache
//https://github.com/NYTimes/Store
@Singleton
public class UserDataRepo implements UserRepo {
	private static final String TAG = UserDataRepo.class.getSimpleName();

	private final Context context;
	private final ConfigQuery config;

	@Inject
	public UserDataRepo(Context context, ConfigQuery config) {
		this.context = context;
		this.config = config;
	}

	//______________________________________________________________________________________________
	@Override
	public io.reactivex.Observable<List<User>> getUserList() {
		return io.reactivex.Observable.create(emitter -> {
			if(isThereInternetConnection()) {
				try {
					Call<RemoteUser> call = RemoteServiceFactory.makeService().getUserList(config.getNumItems());
					RemoteUser remoteUser = call.execute().body();//SYNC
					List<User> userList = RemoteUserParser.parse(remoteUser);
					emitter.onNext(userList);
					emitter.onComplete();
				}
				catch(Exception e) {
					emitter.onError(e);
				}
			}
			else
				emitter.onError(new Exception("NO_INTERNET_CONNECTION"));
		});
	}

	//______________________________________________________________________________________________
	private boolean isThereInternetConnection() {
		boolean isConnected;

		ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if(cm != null) {
			NetworkInfo networkInfo = cm.getActiveNetworkInfo();
			isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());
		}
		else
			isConnected = false;

		return isConnected;
	}



	//______________________________________________________________________________________________
	/*Store store = null;
	@Override
	public rx.Observable<List<User>> getUserList2() {
		if(store == null)
			store = Cache.provideStore(context);

		// get bar code for unique
		final BarCode githubUserBarCode = Cache.generateBarCode("1");

		return store.get(githubUserBarCode)
				//.observeOn(AndroidSchedulers.mainThread())
				.subscribe(
					/*@Override
					public void onCompleted() {

						Log.e(TAG, "onCompleted: ");
					}

					@Override
					public void onError(Throwable e) {

						Log.e(TAG, "onError: ");
					}

					@Override
					public void onNext(GithubUser githubUser) {
						handleGithubUser(githubUser);
					}
				);
		/*return io.reactivex.Observable.create(emitter -> {
			if(isThereInternetConnection()) {
				try {
					Call<RemoteUser> call = RemoteServiceFactory.makeService().getUserList(config.getNumItems());
					RemoteUser remoteUser = call.execute().body();//SYNC
					List<User> userList = RemoteUserParser.parse(remoteUser);
					emitter.onNext(userList);
					emitter.onComplete();
				}
				catch(Exception e) {
					emitter.onError(e);
				}
			}
			else {
				emitter.onError(new Exception("NO_INTERNET_CONNECTION"));
			}
		});
	}*/


	//______________________________________________________________________________________________
	//Asyn Call
	/*private void loadUsers() {
		Call<RemoteUser> call = RemoteServiceFactory.makeService().getUsers();
		try {
			// ASYNC
			call.enqueue(new Callback<RemoteUser>() {
				@Override
				public void onResponse(Call<RemoteUser> call, Response<RemoteUser> response) {
					if(response.isSuccessful()) {
						RemoteUser ru = response.body();
						if(ru != null) {
							List<User> userList = RemoteUserParser.parse(ru);
							users.setValue(UiUserParser.parse(userList));

							for(RemoteUser.Result res : ru.results) {
								Log.e("Main", "onResponse:" + res.name.first + ", " + res.location.city);
							}
						}
					}
					else {
						try {
							if(response.errorBody() != null)
								Log.e("Main", "onStart:onResponse:fail:-------------------" + response.errorBody().string());
						}
						catch(Exception e) {
							Log.e("Main", "onStart:onResponse:fail:e:-------------------",e);
						}
					}
				}

				@Override
				public void onFailure(Call<RemoteUser> call, Throwable t) {
					Log.e("Main", "onStart:onFailure-------------------",t);
				}

			});
		}
		catch(Exception e) {
			Timber.e("Main", "onStart-------------------",e);
		}
	}*/
}
