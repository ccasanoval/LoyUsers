package com.cesoft.loyusers;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.cesoft.loyusers.ui.main.MainActivity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by ccasanova on 27/02/2018
 */
@RunWith(AndroidJUnit4.class)
public class Tests {

	private RecyclerView uiList = null;
	@Rule
	public final ActivityTestRule<MainActivity> main = new ActivityTestRule(MainActivity.class, true);
	@Before
	public void init() {
		uiList = main.getActivity().findViewById(R.id.user_list);
		//uiList = main.getActivity().findViewById(android.R.id.list);
	}
	@Test
	public void listCount() {
		Assert.assertEquals(0, uiList.getAdapter().getItemCount());
	}
}
