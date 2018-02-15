package com.cesoft.loyusers.domain.interactor;

import com.cesoft.loyusers.domain.executor.PostExecutionThread;
import com.cesoft.loyusers.domain.executor.ThreadExecutor;
import com.cesoft.loyusers.domain.model.User;
import com.cesoft.loyusers.domain.repo.UserRepo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by ccasanova on 07/02/2018
 */
//@Singleton
public class GetUserList extends UseCase<List<User>> {

	private final UserRepo userRepo;

	@Inject
	GetUserList(UserRepo userRepo,
				ThreadExecutor threadExecutor,
				PostExecutionThread postExecutionThread) {
		super(threadExecutor, postExecutionThread);
		this.userRepo = userRepo;
	}

	@Override
	Observable<List<User>> buildUseCaseObservable() {
		return userRepo.getUserList();
	}
}
