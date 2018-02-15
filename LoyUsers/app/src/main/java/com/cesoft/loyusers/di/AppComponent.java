package com.cesoft.loyusers.di;

import com.cesoft.loyusers.ui.main.MainActivity;
import com.cesoft.loyusers.ui.main.MainViewModel;

import javax.inject.Singleton;
import dagger.Component;


/**
 * Created by ccasanova on 07/02/2018
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
	void inject(MainActivity activity);
	void inject(MainViewModel model);
}
