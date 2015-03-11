
package com.proficiency.excercise.util;


import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Html;
/**
 * utility class for all dialog related functionality
 */
public class DialogUtil {
	private static DialogUtil mSingleton;
	private AlertDialog mAlertDialog;
	private ProgressDialog mProgressDialog;
	private final String TAG = "DialogUtil";

	private DialogUtil() {}

	/**
	 * 
	 * @return singleton instance
	 */
	public static DialogUtil getInstance() {
		if (mSingleton == null) {
			mSingleton = new DialogUtil();
		}
		return mSingleton;
	}

	/**
	 * dismiss the dialog if showing on the screen
	 */
	public void dismissAlertDialog() {
		if(mAlertDialog != null && mAlertDialog.isShowing()) {
			mAlertDialog.dismiss();
		}
	}
	
	
	/**
	 * show alert dialog
	 * @param context - context
	 * @param title - title on the alert dialog
	 * @param message - Message to be displayed in the alert dialog
	 */
	public void showAlertDialog(Context context, String title, String message) {
		dismissAlertDialog();
		Builder mAlertDialogBuilder = new AlertDialog.Builder(context);
		mAlertDialogBuilder.setTitle(title);
		mAlertDialogBuilder.setMessage(message);
		mAlertDialog = mAlertDialogBuilder.create();
		mAlertDialog.setButton(DialogInterface.BUTTON_POSITIVE,
				Html.fromHtml("<big> OK </big>"),
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		mAlertDialog.show();
	}

	/**
	 * show progress dialog
	 * @param context - context
	 * @param message - Message to be displayed in the progress dialog
	 */
	public void showProgress(Context context, String message) {
		dismissAlertDialog();
		dismissProgressDialog();
		mProgressDialog = new ProgressDialog(context);
		mProgressDialog.setMessage(Html.fromHtml("<big>" + message + "</big>"));
		mProgressDialog.setCancelable(false);
		mProgressDialog.show();
	}

	/**
	 * dismiss the progress dialog if its already showing on the screen
	 */
	public void dismissProgressDialog() {
		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
			mProgressDialog = null;
		}
	}
	
	/**
	 * 
	 * @param context - context
	 * @param message -  Message to be displayed in the progress dialog
	 * @return - progress dialog reference
	 */
	public ProgressDialog getProgress(Context context, String message) {
		ProgressDialog progressDialog = new ProgressDialog(context);
		progressDialog.setMessage(Html.fromHtml("<big>" + message + "</big>"));
		progressDialog.setCancelable(false);
		return progressDialog;
	}

}
