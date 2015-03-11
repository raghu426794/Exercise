package com.proficiency.excercise.model;

/**
 * model class to store the row data of each list item.
 * @author 426794
 *
 */

public class Row {

	private String title;
	private String description;
	private String imageHref;

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
	 * @return The description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description
	 *            The description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return The imageHref
	 */
	public String getImageHref() {
		return imageHref;
	}

	/**
	 * 
	 * @param imageHref
	 *            The imageHref
	 */
	public void setImageHref(String imageHref) {
		this.imageHref = imageHref;
	}

}