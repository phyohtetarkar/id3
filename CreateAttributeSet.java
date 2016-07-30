package com.thRee.id3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateAttributeSet {

	private static final List<String> attributeName = Arrays.asList("gender", "age", "jp", "pb", "gen", "alco", "food",
			"midp", "ovrwei", "red", "tophi", "disease", "jp_some");

	public static List<AttributeSet> create(String[][] data) {
		List<AttributeSet> set = new ArrayList<AttributeSet>();
		for (int i = 0; i < attributeName.size(); i++) {
			List<Attribute> list = new ArrayList<Attribute>();
			for (int j = 0; j < data[i].length; j++) {
				list.add(new Attribute(data[i][j], data[13][j]));
				//System.out.println(data[8][j]);
			}
			set.add(i, new AttributeSet(list, attributeName.get(i), false));
		}

		return set;
	}

	public static List<AttributeSet> getSet(List<AttributeSet> set, String type, AttributeSet rootSet) {
		List<AttributeSet> newSet = new ArrayList<AttributeSet>();

		for (int i = 0; i < set.size(); i++) {
			
			List<Attribute> list = new ArrayList<Attribute>();
			for (int j = 0; j < set.get(i).getAttributes().size(); j++) {
				if (rootSet.getAttributes().get(j).getValue().equalsIgnoreCase(type)) {
					list.add(set.get(i).getAttributes().get(j));
				}
			}
			newSet.add(new AttributeSet(list, set.get(i).getAttributeName(), 
					set.get(i).isRoot()));
		}

		return newSet;
	}
}
