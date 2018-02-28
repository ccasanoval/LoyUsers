package com.cesoft.loyusers;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.cesoft.loyusers.ui.main.MainActivity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by ccasanova on 26/02/2018
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants=BuildConfig.class, sdk=26)
public class Tests {

	private RecyclerView uiList = null;
	private ProgressBar progress = null;

	@Before
	public void setUp() throws Exception {
		MainActivity activity =	Robolectric.setupActivity(MainActivity.class);
		uiList = activity.findViewById(R.id.user_list);
		progress = activity.findViewById(R.id.progress);
		//MockitoAnnotations.initMocks(this);
	}

	@Test
	public void listCount() {
		Assert.assertEquals(0, uiList.getAdapter().getItemCount());
	}
	@Test
	public void progressStat() {
		Assert.assertEquals(View.VISIBLE, progress.getVisibility());
	}
}
