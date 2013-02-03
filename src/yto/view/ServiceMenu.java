package yto.view;

import javax.microedition.io.ConnectionNotFoundException;

import org.j4me.ui.Menu;
import org.j4me.ui.MenuItem;
import org.j4me.ui.UIManager;

import yto.model.CodeDefine;

public class ServiceMenu extends Menu {

	private static ServiceMenu instance;

	public ServiceMenu() {
		setTitle(CodeDefine.SERVICE_MENU_TITLE);

		this.appendMenuOption(new MenuItem() {
			public String getText() {
				return CodeDefine.ASK_MENU_TITLE;
			}

			public void onSelection() {
				acceptNotify();
			}
		});

		this.appendMenuOption(new MenuItem() {
			public String getText() {
				return CodeDefine.COMPLAIN_MENU_TITLE;
			}

			public void onSelection() {
				acceptNotify();
			}
		});

		this.appendMenuOption(new MenuItem() {
			public String getText() {
				return CodeDefine.WAP_MENU_TITLE;
			}

			public void onSelection() {
				acceptNotify();
			}
		});

		setMenuText(CodeDefine.DIAL_COMMAND_TITLE,
				CodeDefine.BACK_COMMAND_TITLE);
	}

	static {
		instance = new ServiceMenu();
	}

	public static ServiceMenu getInstance() {
		return instance;
	}

	protected void acceptNotify() {
		switch (getSelected()) {
		case 0:
			try {
				UIManager.getMIDlet().platformRequest(
						CodeDefine.ASK_MENU_NUMBER);
			} catch (ConnectionNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case 1:
			try {
				UIManager.getMIDlet().platformRequest(
						CodeDefine.COMPLAIN_MENU_NUMBER);
			} catch (ConnectionNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case 2:
			try {
				UIManager.getMIDlet().platformRequest(CodeDefine.WAP_MENU_URL);
			} catch (ConnectionNotFoundException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

	protected void declineNotify() {
		MainMenu.getInstance().show();
	}

	protected void keyPressed(int key) {
		super.keyPressed(key);
		if (getSelected() == 2) {
			setMenuText(CodeDefine.ENTER_COMMAND_TITLE,
					CodeDefine.BACK_COMMAND_TITLE);
		} else
			setMenuText(CodeDefine.DIAL_COMMAND_TITLE,
					CodeDefine.BACK_COMMAND_TITLE);
	}

}
