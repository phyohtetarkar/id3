package com.thRee.id3;

import java.util.Map;

public class CheckResult {
	
	private OnResultListener OnResultListener;
	
	public void addOnResultListener(OnResultListener OnResultListener) {
		this.OnResultListener = OnResultListener;
	}
	
	public void check(Tree tree, Map<String, String> input) {
		
		String choice = input.get(tree.getNodeName());
		
		if (choice.equalsIgnoreCase("Yes")) {
			if (tree.isYesBranch()) {
				check(tree.getYesNode(), input);
			} else {
				OnResultListener.onResult(tree.getYesLeaf());
				//System.out.println("Result: " + tree.getYesLeaf());
			}
		} else {
			if (tree.isNoBranch()) {
				check(tree.getNoNode(), input);
			} else {
				OnResultListener.onResult(tree.getNoLeaf());
				//System.out.println("Result: " + tree.getNoLeaf());
			}
		}
		
	}
	
	public interface OnResultListener {
		public void onResult(String result);
	}
}
