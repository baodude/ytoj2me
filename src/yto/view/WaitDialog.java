package yto.view;

import java.util.Timer;

import org.j4me.ui.Dialog;
import org.j4me.ui.components.Label;

import yto.controller.QueryThread;
import yto.model.CodeDefine;
import yto.model.GaugeTask;

public class WaitDialog extends Dialog {

	private static WaitDialog instance;
	private QueryThread qt;
	private GaugeTask gt;
	private Label qsLabel;
	private Label waitLabel;
	private Timer timer;
	private boolean error;

	public WaitDialog() {
		setTitle(CodeDefine.WAIT_DIALOG_TITLE);
		error = false;
		qsLabel = new Label("");
		this.append(qsLabel);
		waitLabel = new Label("");
		this.append(waitLabel);
		setMenuText("下一条", CodeDefine.CANCEL_COMMAND_TITLE);
	}

	static {
		instance = new WaitDialog();
	}

	public static WaitDialog getInstance() {
		return instance;
	}

	protected void acceptNotify() {
		if (!error) {
			CacelTimer();
			this.SetWaitLabel(gt.getNext());
		}
	}

	protected void declineNotify() {
		qt.CancelTry();
		MainMenu.getInstance().show();
	}

	public void BeginQuery(boolean BillorNot, String QueryString) {
		if (BillorNot)
			this.qsLabel.setLabel("运单号:" + QueryString);
		else
			this.qsLabel.setLabel("网点关键字:" + QueryString);
		setMenuText("下一条", CodeDefine.CANCEL_COMMAND_TITLE);
		this.repaint();
		error=false;
		timer = new Timer();
		gt = new GaugeTask();
		timer.schedule(gt, 0, 2500);
		qt = new QueryThread(BillorNot, QueryString);
		new Thread(qt).start();
	}

	public void SetWaitLabel(String label) {
		waitLabel.setLabel(label);
		this.repaint();
	}

	public void SetErrorLabel(String label) {
		CacelTimer();
		waitLabel.setLabel(label);
		error = true;
		setMenuText(null, CodeDefine.BACK_COMMAND_TITLE);
		this.repaint();
	}

	public void CacelTimer() {
		timer.cancel();
	}

}
