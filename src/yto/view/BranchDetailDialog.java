package yto.view;

import org.j4me.ui.Dialog;
import org.j4me.ui.components.Label;

import yto.controller.Yto;
import yto.model.CodeDefine;
import yto.model.Results;

public class BranchDetailDialog extends Dialog {

	private static BranchDetailDialog instance;
	private Results results;

	public BranchDetailDialog() {
		setTitle(CodeDefine.BRANCHDETAIL_DIALOG_TITLE);
		setMenuText(null, CodeDefine.BACK_COMMAND_TITLE);
	}

	static {
		instance = new BranchDetailDialog();
	}

	public static BranchDetailDialog getInstance() {
		return instance;
	}

	public void notifySuccess(int selected) {
		this.results = Yto.results;
		this.append(new Label(results.getItem(selected - 1)));
		this.show();
	}

	protected void declineNotify() {
		this.deleteAll();
		BranchesPreMenu.getInstance().show();
	}

}
