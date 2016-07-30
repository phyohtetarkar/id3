package com.thRee.id3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ID3 {

	private static Tree tree;
	
	// choose first Root
	public static Tree compute(List<AttributeSet> set, List<String> classList) {
		List<Double> gains = new ArrayList<Double>();
		Map<Double, AttributeSet> map = new HashMap<Double, AttributeSet>();
		List<AttributeSet> newSet = new ArrayList<AttributeSet>();
		int[] sizes = { Collections.frequency(classList, "d4"), Collections.frequency(classList, "d3"),
				Collections.frequency(classList, "d2"), Collections.frequency(classList, "d1") };
		for (AttributeSet as : set) {
			int[] yes_sizes = { 0, 0, 0, 0 };
			int[] no_sizes = { 0, 0, 0, 0 };
			for (Attribute a : as.getAttributes()) {

				if (a.getClassName().equals("d4") && a.getValue().equalsIgnoreCase("Yes")) {
					yes_sizes[0] += 1;
				} else if (a.getClassName().equalsIgnoreCase("d3") && a.getValue().equalsIgnoreCase("Yes")) {
					yes_sizes[1] += 1;
				} else if (a.getClassName().equalsIgnoreCase("d2") && a.getValue().equalsIgnoreCase("Yes")) {
					yes_sizes[2] += 1;
				} else if (a.getClassName().equalsIgnoreCase("d1") && a.getValue().equalsIgnoreCase("Yes")) {
					yes_sizes[3] += 1;
				}

				if (a.getClassName().equals("d4") && a.getValue().equalsIgnoreCase("No")) {
					no_sizes[0] += 1;
				} else if (a.getClassName().equalsIgnoreCase("d3") && a.getValue().equalsIgnoreCase("No")) {
					no_sizes[1] += 1;
				} else if (a.getClassName().equalsIgnoreCase("d2") && a.getValue().equalsIgnoreCase("No")) {
					no_sizes[2] += 1;
				} else if (a.getClassName().equalsIgnoreCase("d1") && a.getValue().equalsIgnoreCase("No")) {
					no_sizes[3] += 1;
				}

			}
			int size = getSum(sizes);
			int yesSize = getSum(yes_sizes);
			int noSize = getSum(no_sizes);
			double G_D = computeGain(computeEntropy(sizes, size), computeEntropy(yes_sizes, yesSize),
					computeEntropy(no_sizes, noSize), yesSize, noSize, size);
			gains.add(G_D);
			map.put(G_D, as);

		}

		double max = Collections.max(gains);
		String root = map.get(max).getAttributeName();
		AttributeSet rootSet = map.get(max);

		System.out.println(gains);
		System.out.println(max);
		System.out.println(root);

		List<String> yesList = new ArrayList<String>();
		List<String> noList = new ArrayList<String>();
		for (AttributeSet as : set) {
			if (as.equals(rootSet)) {
				as.setRoot(true);
				for (Attribute a : as.getAttributes()) {
					if (a.getValue().equalsIgnoreCase("Yes")) {
						yesList.add(a.getClassName());
					} else {
						noList.add(a.getClassName());
					}
				}
			}
			newSet.add(as);
		}
		
		tree = new Tree();
		tree.setNodeName(root);

		classify(CreateAttributeSet.getSet(newSet, "Yes", rootSet), "Yes", tree, yesList);
		classify(CreateAttributeSet.getSet(newSet, "No", rootSet), "No", tree, noList);

		return tree;

	}

	// recursive until leaf
	public static void classify(List<AttributeSet> set, String type, Tree tree, List<String> rootList) {
		int[] sizes = { Collections.frequency(rootList, "d4"), Collections.frequency(rootList, "d3"),
				Collections.frequency(rootList, "d2"), Collections.frequency(rootList, "d1") };

		if (Collections.frequency(Arrays.asList(sizes[0], sizes[1], sizes[2], sizes[3]), 0) > 2) {
			if (type.equalsIgnoreCase("Yes")) {
				tree.setYesBranch(false);
				tree.setYesLeaf(getLeafName(sizes));
				System.out.println("Yes Leaf: " + getLeafName(sizes));
			} else {
				tree.setNoBranch(false);
				tree.setNoLeaf(getLeafName(sizes));
				System.out.println("No Leaf: " + getLeafName(sizes));
			}
		} else {
			List<Double> gains = new ArrayList<Double>();
			Map<Double, AttributeSet> map = new HashMap<Double, AttributeSet>();
			List<AttributeSet> newSet = new ArrayList<AttributeSet>();
			Tree subNode = new Tree();
			List<GainList> gainList = new ArrayList<GainList>();
			for (AttributeSet as : set) {
				int[] yes_sizes = { 0, 0, 0, 0 };
				int[] no_sizes = { 0, 0, 0, 0 };
				if (!as.isRoot()) {
					for (Attribute a : as.getAttributes()) {

						if (a.getClassName().equals("d4") && a.getValue().equalsIgnoreCase("Yes")) {
							yes_sizes[0] += 1;
						} else if (a.getClassName().equalsIgnoreCase("d3") && a.getValue().equalsIgnoreCase("Yes")) {
							yes_sizes[1] += 1;
						} else if (a.getClassName().equalsIgnoreCase("d2") && a.getValue().equalsIgnoreCase("Yes")) {
							yes_sizes[2] += 1;
						} else if (a.getClassName().equalsIgnoreCase("d1") && a.getValue().equalsIgnoreCase("Yes")) {
							yes_sizes[3] += 1;
						}

						if (a.getClassName().equals("d4") && a.getValue().equalsIgnoreCase("No")) {
							no_sizes[0] += 1;
						} else if (a.getClassName().equalsIgnoreCase("d3") && a.getValue().equalsIgnoreCase("No")) {
							no_sizes[1] += 1;
						} else if (a.getClassName().equalsIgnoreCase("d2") && a.getValue().equalsIgnoreCase("No")) {
							no_sizes[2] += 1;
						} else if (a.getClassName().equalsIgnoreCase("d1") && a.getValue().equalsIgnoreCase("No")) {
							no_sizes[3] += 1;
						}

					}

					int size = getSum(sizes);
					int yesSize = getSum(yes_sizes);
					int noSize = getSum(no_sizes);

					double G_D = computeGain(computeEntropy(sizes, size), computeEntropy(yes_sizes, yesSize),
							computeEntropy(no_sizes, noSize), yesSize, noSize, size);

					gains.add(G_D);
					map.put(G_D, as);
					gainList.add(new GainList(as, G_D));
				}
			}

			double max = Collections.max(gains);

			for (GainList gl : gainList) {
				if (gl.getGain() == max) {
					String root = gl.getAttributeSet().getAttributeName();
					AttributeSet rootSet = gl.getAttributeSet();

					System.out.println(gains);
					System.out.println(max);
					System.out.println(root);

					List<String> subYesList = new ArrayList<String>();
					List<String> subNoList = new ArrayList<String>();

					for (AttributeSet as : set) {
						if (as.equals(rootSet)) {
							as.setRoot(true);
							for (Attribute a : as.getAttributes()) {
								if (a.getValue().equalsIgnoreCase("Yes")) {
									subYesList.add(a.getClassName());
								} else {
									subNoList.add(a.getClassName());
								}
							}
						}

						newSet.add(as);
					}
					subNode.setNodeName(root);
					if (type.equalsIgnoreCase("Yes")) {
						tree.setYesNode(subNode);
						tree.setYesBranch(true);
					} else {
						tree.setNoNode(subNode);
						tree.setNoBranch(true);
					}

					classify(CreateAttributeSet.getSet(newSet, "Yes", rootSet), "Yes", subNode, subYesList);
					classify(CreateAttributeSet.getSet(newSet, "No", rootSet), "No", subNode, subNoList);
					break;
				}
			}
		}
	}

	private static String getLeafName(int[] sizes) {
		String leafname = "";
		for (int i = 0; i < sizes.length; i++) {
			if (sizes[i] != 0) {
				switch (i) {
				case 0:
					leafname = "d4";
					break;
				case 1:
					leafname = "d3";
					break;
				case 2:
					leafname = "d2";
					break;
				case 3:
					leafname = "d1";
					break;
				}
			}
		}

		return leafname;
	}

	// Compute Entropy
	private static double computeEntropy(int[] sizes, int size) {
		double result = 0;
		for (int d : sizes) {
			result += (size == 0.0 ? 0.0 : (((double) d / size) * log2((double) d / size)));
		}
		result = -(result);
		return result;
	}

	// Compute Gain
	private static double computeGain(double H_D, double H_Dyes, double H_Dno, int yesSize, int noSize, int size) {

		double G_D = H_D - ((((double) yesSize / size) * H_Dyes) + (((double) noSize / size) * H_Dno));

		return G_D;
	}

	private static int getSum(int[] sizes) {
		int sum = 0;
		for (int i : sizes) {
			sum += i;
		}

		return sum;
	}

	// Compute log base 2
	private static double log2(double val) {
		return val == 0 ? 0 : Math.log(val) / Math.log(2);
	}
}
