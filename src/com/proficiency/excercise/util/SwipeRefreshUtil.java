package com.proficiency.excercise.util;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * custom reusable class for swiperefresh functionality, overridden because to check the 
 * whether the view has been scrolled to top 
 */
public class SwipeRefreshUtil extends SwipeRefreshLayout {
	private View scrollView;
	
	/**
	 * Simple constructor to use when creating a SwipeRefreshLayout from code.
	 * @param context - app context
	 */
	public SwipeRefreshUtil(Context context) {
		super(context);
	}

	/**
	 * Constructor that is called when inflating SwipeRefreshLayout from XML.
	 * @param context - context
	 * @param attrs - A collection of attributes, as found associated with a tag in an XML document  
	 */
	public SwipeRefreshUtil(Context context, AttributeSet attrs) {
	    super(context, attrs);
	}
	
	/**
	 * set the scrollable view
	 * @param view - this can be listview or scrollview 
	 */
	public void setScrollableView(View view){
		scrollView = view;
	}
	
	@Override
	public boolean canChildScrollUp() {
		return ViewCompat.canScrollVertically(scrollView, -1);
	}
	
}
