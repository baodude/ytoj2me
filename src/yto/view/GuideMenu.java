package yto.view;

import org.j4me.ui.Menu;
import org.j4me.ui.UIManager;
import org.j4me.ui.components.Label;

import yto.model.CodeDefine;

public class GuideMenu extends Menu {

	private static GuideMenu instance;

	public GuideMenu() {
		setTitle(CodeDefine.GUIDE_MENU_TITLE);
		this.append(new Label(CodeDefine.TITLE_LABEL_TITLE));
		this.appendMenuOption(CodeDefine.GUIDE1_LABEL_TITLE, BranchDialog
				.getInstance());
		this.append(new Label(CodeDefine.GUIDE2_LABEL_TITLE));
		this.appendMenuOption(CodeDefine.GUIDE3_LABEL_TITLE, BillDialog
				.getInstance());
		setMenuText(CodeDefine.LIST_COMMAND_TITLE,
				CodeDefine.EXIT_COMMAND_TITLE);
		this.setSelected(1);
	}

	static {
		instance = new GuideMenu();
	}

	public static GuideMenu getInstance() {
		return instance;
	}

	protected void acceptNotify() {
		MainMenu.getInstance().show();
	}

	protected void declineNotify() {
		UIManager.getMIDlet().notifyDestroyed();
	}
}
