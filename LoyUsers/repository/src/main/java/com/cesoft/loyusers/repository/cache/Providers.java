package com.cesoft.loyusers.repository.cache;

import com.cesoft.loyusers.domain.model.User;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.LifeCache;
import io.rx_cache2.ProviderKey;

/**
 * Created by ccasanova on 28/02/2018
 */
interface Providers {

	@ProviderKey("mocks")
	Observable<List<User>> getUsers(Observable<List<User>> users);

	@ProviderKey("mocks-5-minute-ttl")
	@LifeCache(duration = 5, timeUnit = TimeUnit.MINUTES)
	Observable<List<User>> getUsers5Min(Observable<List<User>> users);

//	@ProviderKey("mocks-evict-provider")
//	Observable<List<Mock>> getMocksEvictProvider(Observable<List<Mock>> oMocks, EvictProvider evictProvider);
//
//	@ProviderKey("mocks-paginate")
//	Observable<List<Mock>> getMocksPaginate(Observable<List<Mock>> oMocks, DynamicKey page);
//
//	@ProviderKey("mocks-paginate-evict-per-page")
//	Observable<List<Mock>> getMocksPaginateEvictingPerPage(Observable<List<Mock>> oMocks, DynamicKey page, EvictDynamicKey evictPage);
//
//	@ProviderKey("mocks-paginate-evict-per-filter")
//	Observable<List<Mock>> getMocksPaginateWithFiltersEvictingPerFilter(Observable<List<Mock>> oMocks, DynamicKeyGroup filterPage, EvictDynamicKey evictFilter);
}

