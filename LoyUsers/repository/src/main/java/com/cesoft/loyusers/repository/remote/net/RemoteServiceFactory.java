package com.cesoft.loyusers.repository.remote.net;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ccasanova on 05/02/2018
 */
public class RemoteServiceFactory {
	private static final String TAG = RemoteServiceFactory.class.getSimpleName();
	private static final String URL = "https://randomuser.me/";

	private RemoteServiceFactory() {}

	public static RemoteService makeService() {
		OkHttpClient okHttpClient = makeOkHttpClient();
		return makeService(okHttpClient, makeGson());
	}

	private static RemoteService makeService(OkHttpClient okHttpClient, Gson gson) {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(URL)
				.client(okHttpClient)
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.addConverterFactory(GsonConverterFactory.create(gson))
				.build();
		return retrofit.create(RemoteService.class);
	}

	private static OkHttpClient makeOkHttpClient() {
		return new OkHttpClient.Builder()
				//.addInterceptor(httpLoggingInterceptor)
				.connectTimeout(120, TimeUnit.SECONDS)
				.readTimeout(120, TimeUnit.SECONDS)
				.build();
	}

	private static Gson makeGson() {
		return new GsonBuilder()
				.setLenient()
				.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
				.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
				.create();
	}

}
