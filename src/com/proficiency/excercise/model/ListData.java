package com.proficiency.excercise.model;
import java.util.ArrayList;

/**
 * model class to store the list data from the server
 */
public class ListData {

	private String title;
	private ArrayList<Row> rows = new ArrayList<Row>();

	/**
	 * 
	 * @return The title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 
	 * @param title
	 *            The title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 
	 * @return The rows
	 */
	public ArrayList<Row> getRows() {
		return rows;
	}

	/**
	 * 
	 * @param rows
	 *            The rows
	 */
	public void setRows(ArrayList<Row> rows) {
		this.rows = rows;
	}

}