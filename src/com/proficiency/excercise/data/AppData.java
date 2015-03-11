package com.proficiency.excercise.data;

import com.proficiency.excercise.model.ListData;

/**
 * singleton class to store the app data which can be used through out the application
 */
public class AppData {

	private static AppData singleton;
	private ListData listData;
	
	private AppData() {}
	
	/**
	 * 
	 * @return singleton reference
	 */
	public static AppData getIntance() {
		if(singleton == null) {
			singleton = new AppData();
		}
		return singleton;
	}

	/**
	 * 
	 * @return data retrieved from server
	 */
	public ListData getListData() {
		return listData;
	}
	
	/**
	 * 
	 * @param listData - data from the server 
	 */
	public void setListData(ListData listData) {
		this.listData = listData;
	}

}
