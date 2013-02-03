package yto.model;

public class BillPositionItem {
	private String time = null;
	private String position = null;

	public BillPositionItem(String time, String position) {
		this.time = time;
		this.position = position;
	}

	public String getTime() {
		return time;
	}

	public String getPosition() {
		return position;
	}

}