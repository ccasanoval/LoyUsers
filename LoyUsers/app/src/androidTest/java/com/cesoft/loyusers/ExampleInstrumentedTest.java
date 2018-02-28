package com.cesoft.loyusers;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

	@Before
	public void init() {
		if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
			Context appContext = InstrumentationRegistry.getTargetContext();
			InstrumentationRegistry
				.getInstrumentation()
				.getUiAutomation()
				.executeShellCommand(
					String.format("pm grant %s android.permission.READ_EXTERNAL_STORAGE",
						appContext.getPackageName()
				));
		}
	}
	@Test
	public void useAppContext() throws Exception {
		// Context of the app under test.
		Context appContext = InstrumentationRegistry.getTargetContext();
		assertEquals("com.cesoft.loyusers", appContext.getPackageName());
	}
}
