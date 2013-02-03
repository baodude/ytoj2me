package yto.view;

import org.j4me.ui.Dialog;
import org.j4me.ui.components.Label;

import yto.controller.Yto;
import yto.model.CodeDefine;
import yto.model.Results;

public class BillDetailDialog extends Dialog {

	private static BillDetailDialog instance;
	private Results results;

	public BillDetailDialog() {
		setTitle(CodeDefine.BILLDETAIL_DIALOG_TITLE);

		setMenuText(null, CodeDefine.BACK_COMMAND_TITLE);
	}

	static {
		instance = new BillDetailDialog();
	}

	public static BillDetailDialog getInstance() {
		return instance;
	}

	public void notifySuccess() {
		this.results = Yto.results;
		this.append(new Label("‘Àµ•∫≈£∫" + results.getQueryString()));
		StringBuffer sb = new StringBuffer();
		for (int i = 0, j = results.getItemsAmount() - 1; i <= j; i++) {
			sb.append(results.getItem(i));
		}
		this.append(new Label(sb.toString()));
	}

	protected void declineNotify() {
		this.deleteAll();
		BillDialog.getInstance().show();
	}

}
