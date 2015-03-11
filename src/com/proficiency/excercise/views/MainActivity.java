package com.proficiency.excercise.views;

import org.jdeferred.DoneCallback;
import org.jdeferred.FailCallback;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.widget.ListView;

import com.google.gson.Gson;
import com.proficiency.excercise.R;
import com.proficiency.excercise.data.AppData;
import com.proficiency.excercise.model.ListData;
import com.proficiency.excercise.network.BackgroundService;
import com.proficiency.excercise.util.DialogUtil;
import com.proficiency.excercise.util.LogUtil;
import com.proficiency.excercise.util.SwipeRefreshUtil;

/**
 * launcher activity, Application start point
 */
public class MainActivity extends Activity {
	private final String TAG = "MainActivity";
	private final String APP_DATA = "APP_DATA";
	private final String URL = "https://dl.dropboxusercontent.com/u/746330/facts.json";
	private ListView listView;
	private SwipeRefreshUtil swipeRefreshLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		listView = (ListView) findViewById(R.id.pull_refresh_list);
		swipeRefreshLayout = (SwipeRefreshUtil) findViewById(R.id.swiperefresh_layout);
		swipeRefreshLayout.setScrollableView(listView);
		
		swipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh() {
				LogUtil.i(TAG, "Refreshing");
				getDataFromServer(false);
			}
		});
		checkForAppData(savedInstanceState);
	}

	/**
	 * checks for the previous saved app data, if does not exists 
	 * it will load from the server
	 * @param savedInstanceState
	 */
	private void checkForAppData(Bundle savedInstanceState) {
		if(savedInstanceState == null) {
			if (AppData.getIntance().getListData() == null) {
				LogUtil.i(TAG, "[checkForAppData] no app data");
				getDataFromServer(true);
			} else {
				LogUtil.i(TAG, "[checkForAppData] app data exists");
				loadView();
			}
		} else {
			LogUtil.i(TAG, "[checkForAppData] saved daa exists");
			String jsonData = savedInstanceState.getString(APP_DATA);
			parseData(jsonData);
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		LogUtil.i(TAG, "onSaveInstanceState");
		ListData listData = AppData.getIntance().getListData();
		outState.putString(APP_DATA, new Gson().toJson(listData));
	}
	
	
	/**
	 * loads the view(actionbar and listview) with the app data
	 */
	private void loadView() {
		ListData listData = AppData.getIntance().getListData();
		if(listData != null) {
			String actionBarTitle = listData.getTitle();
			if(actionBarTitle != null) {
				getActionBar().setTitle(actionBarTitle);
			}
			RowAdapter adapter = new RowAdapter(MainActivity.this, listData.getRows());
			listView.setAdapter(adapter);
		}
	}

	/**
	 * Retrieve the data from the server making a service call.
	 * @param showProgress - true if progress bar has to be shown, false otherwise
	 */
	private void getDataFromServer(boolean showProgress) {
		BackgroundService.getInstance().get(MainActivity.this, URL,showProgress).then(onSuccess, onFail);
	}

	
	/**
	 * success call back called after successful response from server
	 */
	private DoneCallback<JSONObject> onSuccess = new DoneCallback<JSONObject>() {

		@Override
		public void onDone(JSONObject jsonObject) {
			onServerCallDone();
			if (jsonObject != null) {
				parseData(jsonObject.toString());
			}
		}
	};

	/**
	 * Fail callback when service call fails due to network issue or some other issue
	 */
	private FailCallback<Object> onFail = new FailCallback<Object>() {

		@Override
		public void onFail(Object arg0) {
			onServerCallDone();
			if (arg0 != null){
				LogUtil.i(TAG, "onFail : "+arg0.toString());
				DialogUtil.getInstance().showAlertDialog(MainActivity.this, "Error", (String)arg0);
			}
		}
	};
	
	/**
	 * process things here once the service call has been returned
	 */
	private void onServerCallDone() {
		swipeRefreshLayout.setRefreshing(false);
	}
	
	/**
	 * parse the json response returned from the server 
	 * @param json
	 */
	private void parseData(String json){
		Gson gson = new Gson();
		ListData listData = gson.fromJson(json.toString(), ListData.class);
		AppData.getIntance().setListData(listData);
		loadView();
	}
}
