package com.cesoft.loyusers;


import com.cesoft.loyusers.repository.remote.ConfigQuery;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ccasanova on 15/02/2018
 */
@Singleton
public class ConfigQueryImp extends ConfigQuery {
	@Inject
	public ConfigQueryImp() {
		super(100);
	}
}
