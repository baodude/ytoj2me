package yto.model;

import java.util.Vector;

public class Results {
	private String QueryString;
	private Vector stories = new Vector();
	private boolean billOrBranch;

	public Results(boolean billOrBranch, String QueryString) {
		this.billOrBranch = billOrBranch;
		this.QueryString = QueryString;
	}

	public boolean isBill() {
		return billOrBranch;
	}

	public String getQueryString() {
		return QueryString;
	}

	public int getItemsAmount() {
		return stories.size();
	}

	public void addItem(String time, String position) {
		stories.addElement(new BillPositionItem(time, position));
	}

	public void addItem(String s0, String s1, String s2, String s3, String s4,
			String s5, String s6, String s7, String s8, String s9, String s10,
			String s11) {
		stories.addElement(new BranchNodeItem(s0, s1, s2, s3, s4, s5, s6, s7,
				s8, s9, s10, s11));
	}

	public String getItemTitles(int i) {
		if (billOrBranch) {
			return ((BillPositionItem) stories.elementAt(i)).getTime();
		} else {
			return ((BranchNodeItem) stories.elementAt(i)).getNode(0);
		}
	}

	public String getItem(int i) {
		if (billOrBranch) {
			return (i + 1) + ".\r  "
					+ ((BillPositionItem) stories.elementAt(i)).getTime()
					+ "\r  "
					+ ((BillPositionItem) stories.elementAt(i)).getPosition()
					+ "\r";
		} else {
			return "1.���ڵ�����\r  "
					+ ((BranchNodeItem) stories.elementAt(i)).getNode(0)
					+ "\r2.��������\r  "
					+ ((BranchNodeItem) stories.elementAt(i)).getNode(1)
					+ "\r3.������룺\r  "
					+ ((BranchNodeItem) stories.elementAt(i)).getNode(2)
					+ "\r4.�������䣺\r  "
					+ ((BranchNodeItem) stories.elementAt(i)).getNode(3)
					+ "\r5.MSN �˺ţ�\r  "
					+ ((BranchNodeItem) stories.elementAt(i)).getNode(4)
					+ "\r6.��ѯ�绰��\r  "
					+ ((BranchNodeItem) stories.elementAt(i)).getNode(5)
					+ "\r7.���ͷ�Χ��\r  "
					+ ((BranchNodeItem) stories.elementAt(i)).getNode(6)
					+ "\r8.�����ͷ�Χ��\r  "
					+ ((BranchNodeItem) stories.elementAt(i)).getNode(7)
					+ "\r9.����ʱ�ޣ�\r  "
					+ ((BranchNodeItem) stories.elementAt(i)).getNode(8)
					+ "\r10.24Сʱ����\r  "
					+ ((BranchNodeItem) stories.elementAt(i)).getNode(9)
					+ "\r11.�������\r  "
					+ ((BranchNodeItem) stories.elementAt(i)).getNode(10)
					+ "\r12.������ע��\r  "
					+ ((BranchNodeItem) stories.elementAt(i)).getNode(11);
		}
	}

}