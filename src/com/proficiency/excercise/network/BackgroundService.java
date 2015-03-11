package com.proficiency.excercise.network;

import org.jdeferred.Deferred;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.proficiency.excercise.R;
import com.proficiency.excercise.util.CommonUtil;
import com.proficiency.excercise.util.DialogUtil;
import com.proficiency.excercise.util.LogUtil;

/**
 * singleton class used to communicate with the web services asynchronously
 */
public class BackgroundService {
	private static final String TAG = "BackgroundService";
	private static BackgroundService mSingleton;
		
	private BackgroundService() {}
	
	/**
	 * 
	 * @return singleton reference
	 */
	public static BackgroundService getInstance() {
		if(mSingleton == null) {
			mSingleton = new BackgroundService();
		}
		return mSingleton;
	}
	
	/**
	 * connects with the server asynchronously using get method and you get back a 
	 * promise object.On this promise you can attach callbacks that will get fired 
	 * in case of success/failure notifications. Whenever the piece of code that was 
	 * executing asynchronously finishes as expected the Promise is called to be resolved. 
	 * If some error occurred that promise is called to be rejected.
	 *  
	 * @param activity - current activity
	 * @param url - url to be called
	 * @param showProgress - true if progress bar has to be shown, false otherwise
	 * 
	 * @return - promise object
	 */
	@SuppressWarnings("unchecked")
	public Promise get(final Activity activity, String url, boolean showProgress) {
		final Deferred deferred = new DeferredObject();
		AQuery aQuery = new AQuery(activity);
		ProgressDialog progress = DialogUtil.getInstance().getProgress(activity, 
				CommonUtil.getString(activity, R.string.msg_pleasewait));
		if (progress != null && showProgress) {
			DialogUtil.getInstance().dismissAlertDialog();
			aQuery.progress(progress);
		}
		
		AjaxCallback<JSONObject> ajaxCallBack = new AjaxCallback<JSONObject>() {
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				try {
					if (json != null) {
						if (isValidResponse(status)) {
							deferred.resolve(json);
						} else {
							LogUtil.i(TAG, "[get] "+status.getError());
							deferred.reject(CommonUtil.getString(activity, R.string.network_error));
						}
					} else {
						deferred.reject(CommonUtil.getString(activity, R.string.network_error));
					}
				} catch (Exception exception) {
					deferred.reject(status.getMessage());
				}
			}
		};
		
		if(!isInternetOn(activity)) {
			deferred.reject(CommonUtil.getString(activity, R.string.no_network));
		} else {
			aQuery.ajax(url, JSONObject.class, ajaxCallBack);
		}
		return deferred.promise();
	}
	
	/**
	 * check if the HTTP response code is success
	 * @param status - ajaxstatus of the service call, holds HTTP respsonse code and other data
	 * @return - true if success, false otherwise
	 */
	private static boolean isValidResponse(AjaxStatus status) {
		if(status == null || status.getCode() != 200) {
			return false;
		}
		return true;
	}
	
	/**
	 * check if there is active internet connection 
	 * @param activity - current activity
	 * @return - true if connected to internet, false otherwise
	 */
	private final boolean isInternetOn(Activity activity) {

		ConnectivityManager cm = (ConnectivityManager) activity
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		boolean isConnected = activeNetwork != null
				&& activeNetwork.isConnectedOrConnecting();
		return isConnected;
	}
	

}
