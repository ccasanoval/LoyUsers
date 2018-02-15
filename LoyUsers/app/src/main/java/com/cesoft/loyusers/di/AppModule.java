package com.cesoft.loyusers.di;

import android.app.Application;
import android.content.Context;

import com.cesoft.loyusers.ConfigQueryImp;
import com.cesoft.loyusers.UIThread;
import com.cesoft.loyusers.domain.executor.PostExecutionThread;
import com.cesoft.loyusers.domain.executor.ThreadExecutor;
import com.cesoft.loyusers.domain.repo.UserRepo;
import com.cesoft.loyusers.repository.remote.ConfigQuery;
import com.cesoft.loyusers.repository.remote.UserDataRepo;
import com.cesoft.loyusers.repository.remote.executor.JobExecutor;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

/**
 * Created by ccasanova on 07/02/2018
 */
@Module
public class AppModule {

	private Application app;

	public AppModule(Application application) {
		app = application;
	}

	/*@Provides
	@Singleton
	Application providesApp() {
		return app;
	}*/

	@Provides
	@Singleton
	Context provideAppContext() {
		return app;
	}

	@Provides
	@Singleton
	ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
		return jobExecutor;
	}

	@Provides
	@Singleton
	PostExecutionThread providePostExecutionThread(UIThread uiThread) {
		return uiThread;
	}

	@Provides
	@Singleton
	UserRepo provideUserRepo(UserDataRepo userDataRepository) {
		return userDataRepository;
	}

	@Provides
	@Singleton
	ConfigQuery provideConfigQuery(ConfigQueryImp config) {
		return config;
	}
}
