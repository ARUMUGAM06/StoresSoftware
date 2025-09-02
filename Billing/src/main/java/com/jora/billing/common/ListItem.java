package com.jora.billing.common;

public class ListItem {

	private Object objText = "";

	private Object objValue = "";

	public ListItem(Object text) {
		this.objText = text;
		this.objValue = "";
	}

	public ListItem(Object text, Object value) {
		this.objText = text;
		this.objValue = value;
	}

	public Object getText() {
		return this.objText;
	}

	public Object getValue() {
		return this.objValue;
	}
}
