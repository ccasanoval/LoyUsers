package com.cesoft.loyusers;

import android.app.Application;

import com.cesoft.loyusers.di.AppComponent;
import com.cesoft.loyusers.di.AppModule;
import com.cesoft.loyusers.di.DaggerAppComponent;

import timber.log.Timber;

/**
 * Created by ccasanova on 07/02/2018
 */
public class App extends Application {

	private static AppComponent appComponent;
	public static AppComponent getAppComponent() {
		return appComponent;
	}


	//______________________________________________________________________________________________
	@Override
	public void onCreate() {
		super.onCreate();
		setupDI(this);
		setupLog();
	}

	//______________________________________________________________________________________________
	private void setupLog() {
		if(BuildConfig.DEBUG)
			Timber.plant(new Timber.DebugTree());
	}
	//______________________________________________________________________________________________
	private static void setupDI(Application app) {
		appComponent = DaggerAppComponent.builder()
			.appModule(new AppModule(app))
			.build();

	}

}
