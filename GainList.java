package com.thRee.id3;

public class GainList {

	private AttributeSet attributeSet;
	private double gain;

	public GainList(AttributeSet attributeSet, double gain) {
		this.attributeSet = attributeSet;
		this.gain = gain;
	}

	public AttributeSet getAttributeSet() {
		return attributeSet;
	}

	public void setAttributeSet(AttributeSet attributeSet) {
		this.attributeSet = attributeSet;
	}

	public double getGain() {
		return gain;
	}

	public void setGain(double gain) {
		this.gain = gain;
	}

}
