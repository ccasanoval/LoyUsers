package com.cesoft.loyusers.repository.remote;


/**
 * Created by ccasanova on 15/02/2018
 */
public abstract class ConfigQuery {
	private int numItems;
	public int getNumItems() { return numItems; }
	public void setNumItems(int v) { numItems = v; }
	public ConfigQuery(int numItems) {
		this.numItems = numItems;
	}
}
