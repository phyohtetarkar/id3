package com.thRee.id3;

public class Attribute {

	private String value;
	private String className;

	public Attribute(String value, String className) {
		this.value = value;
		this.className = className;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}
