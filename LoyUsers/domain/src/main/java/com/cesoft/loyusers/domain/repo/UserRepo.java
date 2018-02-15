package com.cesoft.loyusers.domain.repo;

import com.cesoft.loyusers.domain.model.User;

import java.util.List;
import io.reactivex.Observable;

/**
 * Created by ccasanova on 07/02/2018
 */
// Define como debe ser la forma del repositorio para acceder a las entidades
public interface UserRepo {
	Observable<List<User>> getUserList();
}
