package com.proficiency.excercise.util;


import android.app.Activity;

/**
 * utility class for all common functionalities accessed through out the application 
 */
public class CommonUtil {

	/**
	 * returns the string resource provided id.
	 * @param activity - Current Activity reference
	 * @param id - string resource id
	 * @return
	 */
	public static String getString(Activity activity, int id) {
		return activity.getResources().getString(id);
	}
	
}
