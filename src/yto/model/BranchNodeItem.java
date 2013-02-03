package yto.model;

import java.util.Vector;

public class BranchNodeItem {
	private Vector s;

	public BranchNodeItem(String s0, String s1, String s2, String s3,
			String s4, String s5, String s6, String s7, String s8, String s9,
			String s10, String s11) {
		s = new Vector();
		s.addElement(s0);
		s.addElement(s1);
		s.addElement(s2);
		s.addElement(s3);
		s.addElement(s4);
		s.addElement(s5);
		s.addElement(s6);
		s.addElement(s7);
		s.addElement(s8);
		s.addElement(s9);
		s.addElement(s10);
		s.addElement(s11);
	}

	public String getNode(int i) {
		return (String) s.elementAt(i);
	}
}