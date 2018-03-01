package com.cesoft.loyusers.repository.remote;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


import com.cesoft.loyusers.domain.model.User;
import com.cesoft.loyusers.domain.repo.UserRepo;
import com.cesoft.loyusers.repository.cache.Cache;
import com.cesoft.loyusers.repository.remote.model.RemoteUser;
import com.cesoft.loyusers.repository.remote.model.RemoteUserParser;
import com.cesoft.loyusers.repository.remote.net.RemoteServiceFactory;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import retrofit2.Call;

/**
 * Created by ccasanova on 05/02/2018
 */
@Singleton
public class UserDataRepo implements UserRepo {
	private static final String TAG = UserDataRepo.class.getSimpleName();

	private final Context context;
	private final ConfigQuery config;
	private final Cache cache;

	//______________________________________________________________________________________________
	@Inject
	public UserDataRepo(Context context, ConfigQuery config) {
		this.context = context;
		this.config = config;
		this.cache = new Cache(context, this);
	}

	//______________________________________________________________________________________________
	@Override
	public Observable<List<User>> getUserList() {
		return cache.getUsers5Min();
		//return cache.getUsers();
		//return callWS();
	}

	//______________________________________________________________________________________________
	public Observable<List<User>> callWS() {
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
}
