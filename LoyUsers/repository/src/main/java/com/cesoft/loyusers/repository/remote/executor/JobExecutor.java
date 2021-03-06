package com.cesoft.loyusers.repository.remote.executor;

//import android.support.annotation.NonNull;

import com.cesoft.loyusers.domain.executor.ThreadExecutor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ccasanova on 07/02/2018
 */
@Singleton
public class JobExecutor implements ThreadExecutor {
	private final ThreadPoolExecutor threadPoolExecutor;

	@Inject
	JobExecutor() {
		threadPoolExecutor = new ThreadPoolExecutor(
				3, 5, 10, TimeUnit.SECONDS,
				new LinkedBlockingQueue<>(), new JobThreadFactory());
	}

	@Override public void execute(Runnable runnable) {
		threadPoolExecutor.execute(runnable);
	}

	private static class JobThreadFactory implements ThreadFactory {
		private int counter = 0;
		@Override
		public Thread newThread(Runnable runnable) {
			return new Thread(runnable, "android_" + counter++);
		}
	}
}
