package yto.view;

import java.io.IOException;

import org.j4me.ui.Dialog;
import org.j4me.ui.components.Label;
import org.j4me.ui.components.Picture;

import yto.model.CodeDefine;

public class AboutDialog extends Dialog {

	private static AboutDialog instance;

	public AboutDialog() {
		setTitle(CodeDefine.ABOUT_DIALOG_TITLE);
		try {
			Picture ytoPicture = new Picture();
			ytoPicture.setImage("/ytoall.png");
			this.append(ytoPicture);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.append(new Label(CodeDefine.ABOUT_LABEL_TITLE));
		this.append(new Label(CodeDefine.AUTH0R_LABEL_TITLE));
		setMenuText(null, CodeDefine.BACK_COMMAND_TITLE);
	}

	static {
		instance = new AboutDialog();
	}

	public static AboutDialog getInstance() {
		return instance;
	}

	protected void declineNotify() {
		MainMenu.getInstance().show();
	}

}
