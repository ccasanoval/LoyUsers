package com.cesoft.loyusers.repository.remote.net;

import com.cesoft.loyusers.repository.remote.model.RemoteUser;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ccasanova on 05/02/2018
 */
// Definición de la llamada al web service
//https://randomuser.me/documentation
public interface RemoteService {

	//@GET("api/?inc=picture,name,location,phone&nat=es&seed=CESoft")
	//io.reactivex.Observable<RemoteUser> getUserListObservable(@Query("results") int results);

	//@GET("api/?inc=picture,name,location,phone&nat=es&seed=CESoft&results=100")
	@GET("api/?inc=picture,name,location,phone&nat=es&seed=CESoft")
	Call<RemoteUser> getUserList(@Query("results") int results);

	@GET("api/?inc=picture,name,location,phone&nat=es&seed=CESoft&results=100")
	Observable<RemoteUser> getUserList();
}
