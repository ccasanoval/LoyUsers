package com.cesoft.loyusers.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cesoft.loyusers.App;
import com.cesoft.loyusers.R;
import com.cesoft.loyusers.ui.main.widget.EmptyView;
import com.cesoft.loyusers.ui.main.widget.ErrorView;

import javax.inject.Inject;

import timber.log.Timber;

import static android.view.View.GONE;


/**
 * Created by ccasanova on 05/02/2018
 */
public class MainActivity extends AppCompatActivity {

	@Inject
	UserAdapter userAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// DI
		App.getAppComponent().inject(this);

		// UI
		setContentView(R.layout.activity_main);
		RecyclerView uiList = findViewById(R.id.user_list);
		uiList.setLayoutManager(new LinearLayoutManager(this));
		uiList.setAdapter(userAdapter);
		setupViewListeners();
		ProgressBar progress = findViewById(R.id.progress);

		// ViewModel
		MainViewModel model = ViewModelProviders.of(this).get(MainViewModel.class);

		model.getUsers().observe(this, users -> {
			userAdapter.setUsers(users);
			userAdapter.notifyDataSetChanged();
			progress.setVisibility(GONE);
		});
		model.getMessages().observe(this, message ->
			Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show()
		);
		model.getIsLoading().observe(this, isLoading -> {
			if(isLoading !=null && isLoading)
				progress.setVisibility(View.VISIBLE);
			else
				progress.setVisibility(View.GONE);
		});
	}

	@Override
	public void onStart(){
		super.onStart();
	}




	private EmptyView.EmptyListener emptyListener = () -> {

	};

	private ErrorView.ErrorListener errorListener = () -> {

	};
	private void setupViewListeners() {
		EmptyView viewEmpty = findViewById(R.id.view_empty);
		//Timber.e("---1----------------------"+viewEmpty);
		//Timber.e("---2---------------------"+emptyListener);

		viewEmpty.emptyListener = emptyListener;
		ErrorView viewError = findViewById(R.id.view_error);
		viewError.errorListener = errorListener;
	}

}
