package com.cesoft.loyusers.repository.cache;
/*
import android.content.Context;
import android.support.annotation.NonNull;

import com.cesoft.loyusers.repository.remote.net.RemoteServiceFactory;
import com.cesoft.loyusers.repository.remote.model.RemoteUser;
import com.google.gson.Gson;
import com.nytimes.android.external.fs.SourcePersisterFactory;
import com.nytimes.android.external.store.base.Fetcher;
import com.nytimes.android.external.store.base.Persister;
import com.nytimes.android.external.store.base.Store;
import com.nytimes.android.external.store.base.impl.BarCode;
import com.nytimes.android.external.store.base.impl.ParsingStoreBuilder;
import com.nytimes.android.external.store.middleware.GsonParserFactory;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import rx.Observable;
import okio.BufferedSource;
import okio.Okio;
import rx.functions.Func1;
*/

import android.content.Context;

import com.cesoft.loyusers.domain.model.User;
import com.cesoft.loyusers.repository.remote.UserDataRepo;

import java.util.List;

import io.reactivex.Observable;
import io.rx_cache2.Encrypt;
import io.rx_cache2.EncryptKey;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;
import timber.log.Timber;

/**
 * Created by ccasanova on 21/02/2018
 */
@EncryptKey("CESoft2018")
public class Cache {

	private final Providers providers;
	private final UserDataRepo userRepo;

	//______________________________________________________________________________________________
	public Cache(Context app, UserDataRepo userRepo) {
		this.userRepo = userRepo;
		providers = new RxCache.Builder()
				.useExpiredDataIfLoaderNotAvailable(true)
				.persistence(app.getFilesDir(), new GsonSpeaker())
				.using(Providers.class);
	}

	//______________________________________________________________________________________________
	private Observable<List<User>> getExpensiveCall() {
		Timber.e("Cache:getExpensiveCall:---------------------------------------------------");
		return userRepo.callWS();
		//return RemoteServiceFactory.makeService().getUserList().map(RemoteUserParser::parse);
	}

	@Encrypt
	public Observable<List<User>> getUsers() {
		return providers.getUsers(getExpensiveCall());
	}
	@Encrypt
	public Observable<List<User>> getUsers5Min() {
		Timber.e("Cache:getUsers5Min:---------------------------------------------------");
		return providers.getUsers5Min(getExpensiveCall());
	}

//	public Observable<List<User>> getUsers(final boolean update) {
//		return providers.getUsers(getExpensiveMocks(), new EvictProvider(update));
//	}
//	public Observable<List<Mock>> getMocksPaginate(final int page, final boolean update) {
//		return providers.getMocksPaginateEvictingPerPage(getExpensiveMocks(), new DynamicKey(page), new EvictDynamicKey(update));
//	}
//
//	public Observable<List<User>> getMocksWithFiltersPaginate(final String filter, final int page, final boolean updateFilter) {
//		return providers.getMocksPaginateWithFiltersEvictingPerFilter(getExpensiveMocks(), new DynamicKeyGroup(filter, page), new EvictDynamicKey(updateFilter));
//	}




	/*public static Persister newPersister(Context context) {
		Persister persister = null;
		try {
			File cacheSubfolder = new File(context.getCacheDir(), "/users/");
			persister = SourcePersisterFactory.create(cacheSubfolder);
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return persister;
	}

	public static BarCode generateBarCode(String fecha) {
		return new BarCode(RemoteUser.class.getSimpleName(), fecha);
	}

	private static Observable<BufferedSource> buildFetcher(Observable<RemoteUser> sourceObservable) {
		return sourceObservable
				.map(new Func1<RemoteUser, BufferedSource>() {
						 @Override
						 public BufferedSource call(RemoteUser remoteUser) {
							 Gson gson = new Gson();
							 byte[] json = gson.toJson(remoteUser).getBytes(okhttp3.internal.Util.UTF_8);
							 return Okio.buffer(Okio.source(new ByteArrayInputStream(json)));
						 }
					 }
				);
	}

	private static Observable<RemoteUser> apiBuildSourceObservable() {
		return RemoteServiceFactory.makeService().getUserList();
	}

	public static Store provideStore(@NonNull Context context) {
		return ParsingStoreBuilder.<BufferedSource, RemoteUser>builder()
				.fetcher(new Fetcher<BufferedSource>() {
					@NonNull
					@Override
					public rx.Observable<BufferedSource> fetch(BarCode barCode) {
						return buildFetcher(apiBuildSourceObservable());
					}
					@Override
					public Observable<BufferedSource> fetch(BarCode barCode) {
						return buildFetcher(apiBuildSourceObservable());
					}
				})
				.persister(newPersister(context))
				.parser(GsonParserFactory.createSourceParser(new Gson(), RemoteUser.class))
				.open();
	}*/
}
