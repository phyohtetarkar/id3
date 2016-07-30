package com.thRee.id3;

public class Tree {

	private String nodeName;
	private Tree yesNode;
	private Tree noNode;
	private boolean yesBranch;
	private boolean noBranch;
	private String yesLeaf;
	private String noLeaf;

	public Tree() {

	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public Tree getYesNode() {
		return yesNode;
	}

	public void setYesNode(Tree yesNode) {
		this.yesNode = yesNode;
	}

	public Tree getNoNode() {
		return noNode;
	}

	public void setNoNode(Tree noNode) {
		this.noNode = noNode;
	}

	public boolean isYesBranch() {
		return yesBranch;
	}

	public void setYesBranch(boolean yesBranch) {
		this.yesBranch = yesBranch;
	}

	public boolean isNoBranch() {
		return noBranch;
	}

	public void setNoBranch(boolean noBranch) {
		this.noBranch = noBranch;
	}

	public String getYesLeaf() {
		return yesLeaf;
	}

	public void setYesLeaf(String yesLeaf) {
		this.yesLeaf = yesLeaf;
	}

	public String getNoLeaf() {
		return noLeaf;
	}

	public void setNoLeaf(String noLeaf) {
		this.noLeaf = noLeaf;
	}

}
