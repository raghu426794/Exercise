package com.proficiency.excercise.util;

import android.util.Log;

/**
 * utility class for Logging
 */
public class LogUtil {
	private static final boolean DEBUG = true;
	
	/**
	 * Send an INFO log message.
	 * @param tag - Used to identify the source of a log message. It usually identifies the class or activity where the log call occurs. 
	 * @param message - The message you would like logged
	 */
	public static void i(String tag, String message) {
		if(DEBUG) {
			Log.i(tag, message);
		}
	}
	
	/**
	 * Send an VERBOSE log message.
	 * @param tag - Used to identify the source of a log message. It usually identifies the class or activity where the log call occurs. 
	 * @param message - The message you would like logged
	 */
	public static void v(String tag, String message) {
		if(DEBUG) {
			Log.v(tag, message);
		}
	}
	
	/**
	 * Send an ERROR log message.
	 * @param tag - Used to identify the source of a log message. It usually identifies the class or activity where the log call occurs. 
	 * @param message - The message you would like logged
	 */
	public static void e(String tag, String message) {
		if(DEBUG) {
			Log.e(tag, message);
		}
	}
	
	/**
	 * Send an DEBUG log message.
	 * @param tag - Used to identify the source of a log message. It usually identifies the class or activity where the log call occurs. 
	 * @param message - The message you would like logged
	 */
	public static void d(String tag, String message) {
		if(DEBUG) {
			Log.d(tag, message);
		}
	}
	
}
