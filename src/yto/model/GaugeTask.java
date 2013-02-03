package yto.model;

import java.util.TimerTask;

import yto.view.WaitDialog;

public class GaugeTask extends TimerTask {

	private String[] strings = { "������ּ���ͻ�Ҫ��Բͨʹ��", "��Ӫ������ŷ��񡡿��ش���",
			"��ҵ���룺����Ϊ���������ʷ��񡡰�ȫ��ݡ���ԲͨƷ��", "��ҵĿ�꣺��Խ���ҡ�������Ʒ�ơ�������һ�����ڡ����ʴ�Բͨ��Ʒ������",
			"��ҵ������ʱ�������սδ��", "Ա���ںţ�Բͨ�ٵݡ�ʱЧ��һ ȷ����ȫ ���ŷ���",
			"�����ںţ���������ݡ����ŵ���� Բͨ�ٵ�--�й��˵Ŀ��", "��ҵ���⣺ʱЧ����ȫ����г������--��Բͨ���������",
			"����ս�ԣ���ȫʱЧս�� Ʒ������ս�� �г���λս�ԡ��淶����ս�� ���ͳɱ�ս�ԡ����º�гս��",
			"�� �� ����������Ϣ���������淶�� ������Ż�����ת���� ������򻯡���չ���ʻ�" };
	private int i = 0;

	public String getNext() {
		return strings[i++ % 9];
	}

	public void run() {
		WaitDialog.getInstance().SetWaitLabel(strings[i++ % 9]);
		WaitDialog.getInstance().repaint();
	}
}