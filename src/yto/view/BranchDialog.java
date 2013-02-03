package yto.view;

import org.j4me.ui.Dialog;
import org.j4me.ui.Theme;
import org.j4me.ui.UIManager;
import org.j4me.ui.components.Label;
import org.j4me.ui.components.TextBox;
import org.j4me.ui.components.Whitespace;

import yto.model.CodeDefine;

public class BranchDialog extends Dialog {

	private static BranchDialog instance;

	private Label label;
	private TextBox textBox;

	public BranchDialog() {
		setTitle(CodeDefine.BRANCH_DIALOG_TITLE);

		textBox = new TextBox();
		textBox.setLabel(CodeDefine.BRANCH_TEXTBOX_TITLE);
		textBox.setMaxSize(10);
		textBox.setString("…œ∫£");
		this.append(textBox);

		Whitespace ws = new Whitespace(5);
		append(ws);

		label = new Label(CodeDefine.BRANCH_RULE_TITLE);
		this.append(label);

		setMenuText(CodeDefine.QUERY_COMMAND_TITLE,
				CodeDefine.BACK_COMMAND_TITLE);
	}

	static {
		instance = new BranchDialog();
	}

	public static BranchDialog getInstance() {
		return instance;
	}

	protected void acceptNotify() {
		if (textBox.size() == 0) {
			label.setFontColor(Theme.BLUE);
			label.setLabel(CodeDefine.BRANCH_INVALID_TITLE);
			label.repaint();
		} else {
			WaitDialog.getInstance().show();
			WaitDialog.getInstance().BeginQuery(false, textBox.getString());
		}
	}

	protected void declineNotify() {
		label.setFontColor(UIManager.getTheme().getFontColor());
		label.setLabel(CodeDefine.BRANCH_RULE_TITLE);
		label.repaint();
		setSelected(0);
		MainMenu.getInstance().show();
	}

}
