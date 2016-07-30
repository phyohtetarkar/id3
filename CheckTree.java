package com.thRee.id3;

import java.util.Map;

import com.id3.one.CheckResult.OnResultListener;

public class CheckTree implements OnResultListener {
	
	public void check(Tree tree, Map<String, String> input) {
		CheckResult check = new CheckResult();
		//check.addOnResultListener(this);
		check.check(tree, input);		
	}

	@Override
	public void onResult(String result) {
		System.out.println(result);
	}
}
