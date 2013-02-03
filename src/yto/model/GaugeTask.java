package yto.model;

import java.util.TimerTask;

import yto.view.WaitDialog;

public class GaugeTask extends TimerTask {

	private String[] strings = { "服务宗旨：客户要求　圆通使命", "经营理念：诚信服务　开拓创新",
			"企业方针：以人为本　创优质服务　安全快捷　树圆通品牌", "企业目标：超越自我　创民族品牌——建立一个国内、国际大圆通的品牌网络",
			"企业精神：与时俱进　挑战未来", "员工口号：圆通速递　时效第一 确保安全 诚信服务",
			"宣传口号：服务遍神州　诚信递万家 圆通速递--中国人的快递", "企业主题：时效、安全、和谐、创新--是圆通永恒的主题",
			"六个战略：安全时效战略 品牌形象战略 市场定位战略　规范服务战略 降低成本战略　创新和谐战略",
			"六 个 化：网络信息化　操作规范化 服务诚信化　中转合理化 管理程序化　发展国际化" };
	private int i = 0;

	public String getNext() {
		return strings[i++ % 9];
	}

	public void run() {
		WaitDialog.getInstance().SetWaitLabel(strings[i++ % 9]);
		WaitDialog.getInstance().repaint();
	}
}