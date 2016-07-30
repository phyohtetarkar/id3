package com.thRee.id3;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {

	public static void main(String[] args) {
		try {
			File f = new File(Main.class.getResource("data.txt").toURI());
			String[][] data = new String[14][100];
			Scanner in = new Scanner(f);
			int count = 0;
			while (in.hasNext()) {
				
				for (int i = 0; i < 14; i++) {
					String ss = in.next();
					if (ss.equalsIgnoreCase("male") || ss.equalsIgnoreCase("female")) {
						ss = ss.equalsIgnoreCase("male") ? "Yes" : "No";
					} else if (ss.equalsIgnoreCase(">50") || ss.equalsIgnoreCase("<50")) {
						ss = ss.equalsIgnoreCase(">50") ? "Yes" : "No";
					}
					data[i][count] = ss;
				}
				
				count++;
				
				
			}
			
			List<AttributeSet> set = CreateAttributeSet.create(data);
			List<String> classList = new ArrayList<String>();
			for (Attribute as : set.get(0).getAttributes()) {
				classList.add(as.getClassName());
			}
			
			Tree tree = ID3.compute(set, classList);
			
			/*Map<String, String> input = new HashMap<String, String>();
			input.put("gender", "yes");
			input.put("jp_some", "yes");
			input.put("gen", "no");
			input.put("tophi", "yes");
			input.put("red", "no");
			
			new CheckTree().check(tree, input);*/
			
			JFrame frame = new JFrame("ID3 Decision Tree Result");
			Container c = frame.getContentPane();
			c.setLayout(new BorderLayout());			
			JPanel p = new TreePaint(tree);
			c.add(p);
			frame.setSize(1200, 750);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
