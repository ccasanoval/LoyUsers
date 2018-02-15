package com.cesoft.loyusers.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.cesoft.loyusers.App;
import com.cesoft.loyusers.domain.interactor.GetUserList;
import com.cesoft.loyusers.domain.model.User;

import com.cesoft.loyusers.repository.remote.ConfigQuery;
import com.cesoft.loyusers.ui.ViewModelInterface;
import com.cesoft.loyusers.ui.model.UiUser;
import com.cesoft.loyusers.ui.model.UiUserParser;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
import timber.log.Timber;


/**
 * Created by ccasanova on 07/02/2018
 */
public class MainViewModel extends ViewModel implements ViewModelInterface {

	private static final String TAG = MainViewModel.class.getSimpleName();

	@Inject
	GetUserList getUserListUseCase;
	@Inject
	ConfigQuery configQuery;

	private MutableLiveData<List<UiUser>> users = new MutableLiveData<>();
	private MutableLiveData<String> messages  = new MutableLiveData<>();
	private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

	public LiveData<List<UiUser>> getUsers() {
		App.getAppComponent().inject(this);
		Timber.e(TAG, "getUsers----------------"+getUserListUseCase);
		loadUserList();
		return users;
	}
	public LiveData<String> getMessages() {
		messages.setValue("");
		return messages;
	}
	public LiveData<Boolean> getIsLoading() {
		isLoading.setValue(true);
		return isLoading;
	}


	////---- implements ViewModelInterface
	@Override public void start() {
		Timber.e(TAG, "start----------------"+getUserListUseCase);
		loadUserList();
	}
	@Override public void destroy() {
		getUserListUseCase.dispose();
	}
	@Override public void resume() {}
	@Override public void pause() {}

	private void loadUserList() {
		configQuery.setNumItems(150);
		if(getUserListUseCase != null)
			getUserListUseCase.execute(new UserListObserver());
		else
			Timber.e(TAG, "loadUserList:e:----------------------NULL");
	}

	////////////////////////////////////////////////////////////////////////////////////////////////
	private final class UserListObserver extends DisposableObserver<List<User>> {

		@Override public void onComplete() {
			setLoading(false);
		}
		@Override public void onError(Throwable e) {
			setLoading(false);
			setMessage(e.toString());
		}
		@Override public void onNext(List<User> users) {
			setUsers(users);
		}

		private void setLoading(boolean state) {
			isLoading.setValue(state);
		}
		private void setMessage(String msg) {
			messages.setValue(msg);
		}
		private void setUsers(List<User> userList) {
			users.setValue(UiUserParser.parse(userList));
		}
	}



	//______________________________________________________________________________________________
	/*public LiveData<List<UiUser>> getUsers() {
		if(users == null) {
			users = new MutableLiveData<>();
			loadUsers();
		}
		return users;
	}

	//______________________________________________________________________________________________
	private void loadUsers() {
		Call<RemoteUser> call = RemoteServiceFactory.makeService().getUsers();
		try {
			//List<RemoteUser> result = call.execute().body(); NOT ON MAIN THREAD
			call.enqueue(new Callback<RemoteUser>() {
				@Override
				public void onResponse(Call<RemoteUser> call, Response<RemoteUser> response) {
					if(response.isSuccessful()) {
						RemoteUser ru = response.body();
						if(ru != null) {
							List<User> userList = RemoteUserParser.parse(ru);
							users.setValue(UiUserParser.parse(userList));

//TODO: Del
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
