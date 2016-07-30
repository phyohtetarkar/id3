package com.thRee.id3;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class TreePaint extends JPanel{
	private static final long serialVersionUID = 7151343648440463209L;
	Tree tree;
	public TreePaint(Tree tree) {
		this.tree = tree;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.WHITE);
		
		
		int ox = (getWidth()/ 2) - 50;
		int oy = 10;
		int sx = (getWidth()/ 2) - 25;
		int sy = oy + 30;
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setStroke(new BasicStroke(2));
		g2.setColor(Color.BLUE.darker());
		g2.drawOval(ox, oy, 100, 50);		
		g2.drawString(tree.getNodeName(), sx, sy);
		
		if (tree.isYesBranch()) {
			ox = ox + 25;
			oy = oy + 48;
			g2.drawLine(ox-20, oy-10, ox - 350, oy + 40);
			g2.drawString("Yes", ox - 200, oy + 10);
			makeTreeYes(g2, tree.getYesNode(), ox - 350, oy + 40);
		} else {
			ox = ox + 25;
			oy = oy + 48;
			g2.drawLine(ox-20, oy-10, ox - 350, oy + 40);
			g2.drawString("Yes", ox - 200, oy + 10);
			g2.drawString(tree.getYesLeaf(), ox - 300, oy + 50);
		}
		if (tree.isNoBranch()) {
			ox = ox + 45;
			g2.drawLine(ox+25, oy-10, ox + 350, oy + 40);
			g2.drawString("No", ox + 200, oy + 10);
			makeTreeNo(g2, tree.getNoNode(), ox + 350, oy + 40);
		} else {
			ox = ox + 45;
			g2.drawLine(ox+25, oy-10, ox + 350, oy + 40);
			g2.drawString("No", ox + 200, oy + 10);
			g2.drawString(tree.getNoLeaf(), ox + 300, oy + 50);
		}
	}
	
	private void makeTreeYes(Graphics2D g, Tree tree, int ox, int oy) {
		ox = ox - 50;
		g.drawOval(ox, oy, 100, 50);
		g.drawString(tree.getNodeName(), ox + 30, oy + 30);
		
		if (!tree.isYesBranch()) {
			oy = oy + 48;
			g.drawLine(ox + 25, oy, ox - 25, oy + 50);
			g.drawString("Yes", ox - 20 , oy + 20);
			g.drawString(tree.getYesLeaf(), ox - 40, oy + 60);
		} else {
			oy = oy + 48;
			g.drawLine(ox + 25, oy, ox - 25, oy + 50);
			g.drawString("Yes", ox - 20 , oy + 20);
			makeTreeYesLoop(g, tree.getYesNode(), ox - 80, oy + 50);
		}
		
		if (!tree.isNoBranch()) {
			g.drawLine(ox + 70, oy, ox + 120, oy + 50);
			g.drawString("No", ox + 100, oy + 25);
			g.drawString(tree.getNoLeaf(), ox + 90, oy + 60);
		} else {
			g.drawLine(ox + 70, oy, ox + 120, oy + 60);
			g.drawString("No", ox + 100, oy + 25);
			makeTreeNoLoop(g, tree.getNoNode(), ox + 80, oy + 60);
		}
	}
	
	private void makeTreeNo(Graphics2D g, Tree tree, int ox, int oy) {
		ox = ox - 50;
		g.drawOval(ox, oy, 100, 50);
		g.drawString(tree.getNodeName(), ox + 30, oy + 30);
		
		if (!tree.isYesBranch()) {
			oy = oy + 48;
			g.drawLine(ox + 25, oy, ox - 25, oy + 50);
			g.drawString("Yes", ox - 20 , oy + 20);
			g.drawString(tree.getYesLeaf(), ox - 60, oy + 60);
		} else {
			oy = oy + 48;
			g.drawLine(ox + 25, oy, ox - 25, oy + 50);
			g.drawString("Yes", ox - 20 , oy + 20);
			makeTreeYesLoop(g, tree.getYesNode(), ox - 80, oy + 50);
		}
		
		if (!tree.isNoBranch()) {
			g.drawLine(ox + 70, oy, ox + 120, oy + 50);
			g.drawString("No", ox + 100, oy + 25);
			g.drawString(tree.getNoLeaf(), ox + 120, oy + 60);
		} else {
			g.drawLine(ox + 70, oy, ox + 120, oy + 60);
			g.drawString("No", ox + 100, oy + 25);
			makeTreeNoLoop(g, tree.getNoNode(), ox + 100, oy + 60);
		}
	}
	
	
	private void makeTreeYesLoop(Graphics2D g, Tree tree, int ox, int oy) {
		
		g.drawOval(ox, oy, 100, 50);
		g.drawString(tree.getNodeName(), ox + 30, oy + 30);
		
		if (!tree.isYesBranch()) {
			oy = oy + 48;
			g.drawLine(ox + 25, oy, ox - 25, oy + 50);
			g.drawString("Yes", ox - 20 , oy + 20);
			g.drawString(tree.getYesLeaf(), ox - 40, oy + 60);
		} else {
			oy = oy + 48;
			g.drawLine(ox + 25, oy, ox - 25, oy + 50);
			g.drawString("Yes", ox - 20 , oy + 20);
			makeTreeYesLoop(g, tree.getYesNode(), ox - 80, oy + 50);
		}
		
		if (!tree.isNoBranch()) {
			g.drawLine(ox + 70, oy, ox + 120, oy + 50);
			g.drawString("No", ox + 100, oy + 25);
			g.drawString(tree.getNoLeaf(), ox + 120, oy + 60);
		} else {
			g.drawLine(ox + 70, oy, ox + 120, oy + 60);
			g.drawString("No", ox + 100, oy + 25);
			makeTreeNoLoop(g, tree.getNoNode(), ox + 80, oy + 60);
		}
	}
	
	private void makeTreeNoLoop(Graphics2D g, Tree tree, int ox, int oy) {
		
		g.drawOval(ox, oy, 100, 50);
		g.drawString(tree.getNodeName(), ox + 30, oy + 30);
		
		if (!tree.isYesBranch()) {
			oy = oy + 48;
			g.drawLine(ox + 25, oy, ox - 25, oy + 50);
			g.drawString("Yes", ox - 20 , oy + 20);
			g.drawString(tree.getYesLeaf(), ox - 40, oy + 60);
		} else {
			oy = oy + 48;
			g.drawLine(ox + 25, oy, ox - 25, oy + 50);
			g.drawString("Yes", ox - 20 , oy + 20);
			makeTreeYesLoop(g, tree.getYesNode(), ox - 80, oy + 50);
		}
		
		if (!tree.isNoBranch()) {
			g.drawLine(ox + 70, oy, ox + 120, oy + 50);
			g.drawString("No", ox + 100, oy + 25);
			g.drawString(tree.getNoLeaf(), ox + 120, oy + 60);
		} else {
			g.drawLine(ox + 70, oy, ox + 120, oy + 50);
			g.drawString("No", ox + 100, oy + 25);
			makeTreeNoLoop(g, tree.getNoNode(), ox + 80, oy + 50);
		}
	}
}
