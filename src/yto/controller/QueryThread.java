package yto.controller;

import java.io.IOException;
import java.io.UTFDataFormatException;

import javax.microedition.io.ConnectionNotFoundException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

import org.kxml2.io.KXmlParser;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import yto.model.Results;
import yto.view.BillDetailDialog;
import yto.view.BranchesPreMenu;
import yto.view.WaitDialog;

public class QueryThread implements Runnable {

	private HttpConnection httpConnection = null;
	private Results results;

	private boolean trying = true;

	public QueryThread(boolean BillorNot, String QueryString) {
		results = new Results(BillorNot, QueryString);
		Yto.results = this.results;
	}

	private void tryConnect(boolean isProxy) throws Throwable {
		if (isProxy) {
			if (Yto.getFirstConnect()) {
				if (results.isBill())
					httpConnection = (HttpConnection) Connector
							.open("http://10.0.0.172/m1.xml");
				else
					httpConnection = (HttpConnection) Connector
							.open("http://10.0.0.172/m2.xml");
				httpConnection.setRequestProperty("X-Online-Host",
						"www.kangyuanpf.com");
				httpConnection.getHeaderField("Content-Type");
				httpConnection.close();
				Yto.setFirstConnect(false);
			}

			if (results.isBill())
				httpConnection = (HttpConnection) Connector
						.open("http://10.0.0.172/m1.xml");
			else
				httpConnection = (HttpConnection) Connector
						.open("http://10.0.0.172/m2.xml");
			httpConnection.setRequestProperty("X-Online-Host",
					"www.kangyuanpf.com");
		} else {
			if (results.isBill())
				httpConnection = (HttpConnection) Connector
						.open("http://127.0.0.1/m1.xml");
			else
				httpConnection = (HttpConnection) Connector
						.open("http://127.0.0.1/m2.xml");
		}
		if (httpConnection != null) {
			Parse();
		} else {
			throw new Exception("HttpConnection不能为null");
		}
	}

	private void Parse() throws Throwable {

		KXmlParser parser = new KXmlParser();

		parser.setInput(httpConnection.openInputStream(), "UTF-8");

		parser.nextTag();

		parser.require(XmlPullParser.START_TAG, null, "a");

		if (results.isBill()) {
			String time, position;
			while (parser.nextTag() != XmlPullParser.END_TAG) {
				parser.require(XmlPullParser.START_TAG, null, "b");
				parser.nextTag();

				parser.require(XmlPullParser.START_TAG, null, "c");
				time = parser.nextText();
				parser.require(XmlPullParser.END_TAG, null, "c");
				parser.nextTag();

				parser.require(XmlPullParser.START_TAG, null, "d");
				position = parser.nextText();
				parser.require(XmlPullParser.END_TAG, null, "d");
				parser.nextTag();

				parser.require(XmlPullParser.END_TAG, null, "b");

				results.addItem(time, position);
			}
		} else {
			String s0, s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11;
			while (parser.nextTag() != XmlPullParser.END_TAG) {
				parser.require(XmlPullParser.START_TAG, null, "b");
				parser.nextTag();

				parser.require(XmlPullParser.START_TAG, null, "c");
				s0 = parser.nextText();
				parser.require(XmlPullParser.END_TAG, null, "c");
				parser.nextTag();

				parser.require(XmlPullParser.START_TAG, null, "d");
				s1 = parser.nextText();
				parser.require(XmlPullParser.END_TAG, null, "d");
				parser.nextTag();

				parser.require(XmlPullParser.START_TAG, null, "e");
				s2 = parser.nextText();
				parser.require(XmlPullParser.END_TAG, null, "e");
				parser.nextTag();

				parser.require(XmlPullParser.START_TAG, null, "f");
				s3 = parser.nextText();
				parser.require(XmlPullParser.END_TAG, null, "f");
				parser.nextTag();

				parser.require(XmlPullParser.START_TAG, null, "g");
				s4 = parser.nextText();
				parser.require(XmlPullParser.END_TAG, null, "g");
				parser.nextTag();

				parser.require(XmlPullParser.START_TAG, null, "h");
				s5 = parser.nextText();
				parser.require(XmlPullParser.END_TAG, null, "h");
				parser.nextTag();

				parser.require(XmlPullParser.START_TAG, null, "i");
				s6 = parser.nextText();
				parser.require(XmlPullParser.END_TAG, null, "i");
				parser.nextTag();

				parser.require(XmlPullParser.START_TAG, null, "j");
				s7 = parser.nextText();
				parser.require(XmlPullParser.END_TAG, null, "j");
				parser.nextTag();

				parser.require(XmlPullParser.START_TAG, null, "k");
				s8 = parser.nextText();
				parser.require(XmlPullParser.END_TAG, null, "k");
				parser.nextTag();

				parser.require(XmlPullParser.START_TAG, null, "l");
				s9 = parser.nextText();
				parser.require(XmlPullParser.END_TAG, null, "l");
				parser.nextTag();

				parser.require(XmlPullParser.START_TAG, null, "m");
				s10 = parser.nextText();
				parser.require(XmlPullParser.END_TAG, null, "m");
				parser.nextTag();

				parser.require(XmlPullParser.START_TAG, null, "n");
				s11 = parser.nextText();
				parser.require(XmlPullParser.END_TAG, null, "n");
				parser.nextTag();

				parser.require(XmlPullParser.END_TAG, null, "b");

				results.addItem(s0, s1, s2, s3, s4, s5, s6, s7, s8, s9, s10,
						s11);
			}
		}
		parser.require(XmlPullParser.END_TAG, null, "a");
		parser.next();
		parser.require(XmlPullParser.END_DOCUMENT, null, null);
		if (trying) {
			if (results.isBill()) {
				BillDetailDialog.getInstance().notifySuccess();
				BillDetailDialog.getInstance().show();
			} else {
				BranchesPreMenu.getInstance().notifySuccess();
				BranchesPreMenu.getInstance().show();
			}
		}
	}

	public void run() {
		try {
			tryConnect(true);
		} catch (Throwable e) {
			if (e instanceof SecurityException) {
				WaitDialog.getInstance().SetErrorLabel("请允许联网，请重新启动程序");
			} else if (e instanceof ConnectionNotFoundException) {
				WaitDialog.getInstance().SetErrorLabel("连接服务器出错，请联系公司解决");
			} else if (e instanceof UTFDataFormatException) {
				WaitDialog.getInstance().SetErrorLabel("UTF格式解析错误，请重新查询");
			} else if (e instanceof XmlPullParserException) {
				WaitDialog.getInstance().SetErrorLabel("解析错误，请重新查询");
			} else if (e instanceof IOException) {
				WaitDialog.getInstance().SetErrorLabel("IO错误，请重新查询");
			} else if (e instanceof Exception) {
				WaitDialog.getInstance().SetErrorLabel("未知错误，请重新查询");
			}
		} finally {
			closeConnection();
			WaitDialog.getInstance().CacelTimer();
		}
	}

	private void closeConnection() {
		if (httpConnection != null) {
			try {
				httpConnection.close();
				httpConnection = null;
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

	public void CancelTry() {
		trying = false;
		closeConnection();
	}

}