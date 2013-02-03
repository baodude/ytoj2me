package yto.view;

import java.util.Timer;
import java.util.TimerTask;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class FirstCanvas extends Canvas {

	private Image image;

	public FirstCanvas() {
		this.setFullScreenMode(true);
		try {
			image = Image.createImage("/yto.png");
		} catch (Exception e) {
		}
	}

	protected void paint(Graphics g) {
		if (image != null) {
			g.drawImage(image, (getWidth() - image.getWidth()) / 2,
					(getHeight() - image.getHeight()) / 2, Graphics.TOP
							| Graphics.LEFT);
			Timer timer = new Timer();
			TimerTask timerTask = new TimerTask() {
				public void run() {
					GuideMenu.getInstance().show();
				}
			};
			timer.schedule(timerTask, 1800L);
		} else {
			GuideMenu.getInstance().show();
		}
	}
}
