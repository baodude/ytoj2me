package yto.controller;

import javax.microedition.midlet.MIDlet;

import org.j4me.ui.UIManager;

import yto.model.Results;
import yto.model.YtoTheme;
import yto.view.FirstCanvas;

public class Yto extends MIDlet {

	private static boolean FirstConnect = true;
	public static Results results;

	public Yto() {
		UIManager.init(this);

		YtoTheme theme = new YtoTheme();
		UIManager.setTheme(theme);

		FirstCanvas firstCanvas = new FirstCanvas();
		UIManager.getDisplay().setCurrent(firstCanvas);
	}

	public static boolean getFirstConnect() {
		return FirstConnect;
	}

	public static void setFirstConnect(boolean first) {
		FirstConnect = first;
	}

	protected void destroyApp(boolean arg0) {
	}

	protected void pauseApp() {
	}

	protected void startApp() {
	}

}
