package yto.view;

import org.j4me.ui.Dialog;
import org.j4me.ui.Theme;
import org.j4me.ui.UIManager;
import org.j4me.ui.components.Label;
import org.j4me.ui.components.TextBox;
import org.j4me.ui.components.Whitespace;

import yto.model.CodeDefine;

public class BillDialog extends Dialog {

	private static BillDialog instance;

	private Label label;
	private TextBox textBox;

	public BillDialog() {
		setTitle(CodeDefine.BILL_DIALOG_TITLE);

		textBox = new TextBox();
		textBox.setLabel(CodeDefine.BILL_TEXTBOX_TITLE);
		textBox.setForNumericOnly();
		textBox.setMaxSize(10);
		textBox.setString("1225046842");
		this.append(textBox);

		Whitespace ws = new Whitespace(5);
		append(ws);

		label = new Label(CodeDefine.BILL_RULE_TITLE);
		this.append(label);

		setMenuText(CodeDefine.QUERY_COMMAND_TITLE,
				CodeDefine.BACK_COMMAND_TITLE);
	}

	static {
		instance = new BillDialog();
	}

	public static BillDialog getInstance() {
		return instance;
	}

	protected void acceptNotify() {
		if (textBox.size() != 10) {
			label.setFontColor(Theme.BLUE);
			label.setLabel(CodeDefine.BILL_INVALID_TITLE);
			label.repaint();
		} else {
			WaitDialog.getInstance().show();
			WaitDialog.getInstance().BeginQuery(true, textBox.getString());
		}
	}

	protected void declineNotify() {
		label.setFontColor(UIManager.getTheme().getFontColor());
		label.setLabel(CodeDefine.BILL_RULE_TITLE);
		label.repaint();
		setSelected(0);
		MainMenu.getInstance().show();
	}
}
