package com.danmattern.soundboardbase;

/**
 * Created by dan on 2/25/2017.
 */

public class ClipEntry {
	private String id;
	private String tag;
	private String textLabel;

	public String getId () {
		return id;
	}

	public void setId (String id) {
		this.id = id;
	}

	public String getTag () {
		return tag;
	}

	public void setTag (String tag) {
		this.tag = tag;
	}

	public String getTextLabel () {
		return textLabel;
	}

	public void setTextLabel (String textLabel) {
		this.textLabel = textLabel;
	}

	public ClipEntry (String id) {
		this.id = id;
		this.tag = id;
		this.textLabel = id.replace('_', ' ');
	}
}
