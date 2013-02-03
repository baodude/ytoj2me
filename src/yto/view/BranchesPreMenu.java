package yto.view;

import org.j4me.ui.Menu;
import org.j4me.ui.components.Label;

import yto.controller.Yto;
import yto.model.CodeDefine;
import yto.model.Results;

public class BranchesPreMenu extends Menu {

	private static BranchesPreMenu instance;
	private Results results;

	public BranchesPreMenu() {
		setTitle(CodeDefine.BRCHDET_MENU_TITLE);

		this.setMenuText(CodeDefine.OK_COMMAND_TITLE,
				CodeDefine.BACK_COMMAND_TITLE);
	}

	static {
		instance = new BranchesPreMenu();
	}

	public static BranchesPreMenu getInstance() {
		return instance;
	}

	public void notifySuccess() {
		this.results = Yto.results;
		this.append(new Label("Íøµã¹Ø¼ü×Ö£º" + results.getQueryString()));
		for (int i = 0, j = results.getItemsAmount() - 1; i <= j; i++) {
			this.appendMenuOption(results.getItemTitles(i), BranchDetailDialog
					.getInstance());
		}
		this.setSelected(1);
	}

	protected void acceptNotify() {
		BranchDetailDialog.getInstance().notifySuccess(getSelected());
	}

	protected void declineNotify() {
		this.deleteAll();
		BranchDialog.getInstance().show();
	}

	protected void keyPressed(int key) {
		super.keyPressed(key);
		if (key == FIRE) {
			BranchDetailDialog.getInstance().notifySuccess(getSelected());
		}
	}

}
