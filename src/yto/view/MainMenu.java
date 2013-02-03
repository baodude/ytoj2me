package yto.view;

import org.j4me.ui.Menu;
import org.j4me.ui.UIManager;

import yto.model.CodeDefine;

public class MainMenu extends Menu {
	private static MainMenu instance;

	public MainMenu() {
		setTitle(CodeDefine.MAIN_MENU_TITLE);

		this.appendMenuOption(BillDialog.getInstance());

		this.appendMenuOption(BranchDialog.getInstance());

		this.appendMenuOption(ServiceMenu.getInstance());

		this.appendMenuOption(AboutDialog.getInstance());

		this.setMenuText(CodeDefine.OK_COMMAND_TITLE,
				CodeDefine.EXIT_COMMAND_TITLE);

	}

	static {
		instance = new MainMenu();
	}

	public static MainMenu getInstance() {
		return instance;
	}

	protected void declineNotify() {
		UIManager.getMIDlet().notifyDestroyed();
	}

}
