package com.cesoft.loyusers.domain.interactor;


import com.cesoft.loyusers.domain.executor.PostExecutionThread;
import com.cesoft.loyusers.domain.executor.ThreadExecutor;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ccasanova on 07/02/2018
 */
public abstract class UseCase<T> {

	private final ThreadExecutor threadExecutor;
	private final PostExecutionThread postExecutionThread;
	private final CompositeDisposable disposables;

	UseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
		this.threadExecutor = threadExecutor;
		this.postExecutionThread = postExecutionThread;
		this.disposables = new CompositeDisposable();
	}

	abstract Observable<T> buildUseCaseObservable();

	public void execute(DisposableObserver<T> observer) {
		final Observable<T> observable = buildUseCaseObservable()
			.subscribeOn(Schedulers.from(threadExecutor))
			.observeOn(postExecutionThread.getScheduler());
		disposables.add(observable.subscribeWith(observer));
	}

	public void dispose() {
		if( ! disposables.isDisposed())
		  disposables.dispose();
	}
}
