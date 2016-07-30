package com.thRee.id3;

import java.util.List;

public class AttributeSet {

	private List<Attribute> attributes;

	private String attributeName;
	private boolean root;

	public AttributeSet(List<Attribute> attributes, String attributeName, boolean root) {
		this.attributes = attributes;
		this.attributeName = attributeName;
		this.root = root;
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public boolean isRoot() {
		return root;
	}

	public void setRoot(boolean root) {
		this.root = root;
	}

	@Override
	public String toString() {
		return attributeName;
	}
}
