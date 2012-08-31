package hz.smarter.UI;

import hz.smarter.R;
import hz.smarter.Model.History;
import hz.smarter.Model.HistorySingleton;
import hz.smarter.Util.MassageUtil;
import hz.smarter.Util.ViewUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Switcher extends Activity {

//	about button
	Button on, off;
	Button[] bus = { on, off };
	int[] bu_ids = { R.id.on, R.id.off };
//	about textView
	TextView tv_status, tv_time,tv_content;
	TextView[] tvs ={tv_status, tv_time,tv_content};
	int[] tv_ids = { R.id.status_view,R.id.time_view,R.id.content_view };
	ViewUtil viewUtil = new ViewUtil(Switcher.this);
	History history = HistorySingleton.getHistoryInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.switcher);
		/*
		 * get buttons
		 */

		bus = viewUtil.getButtonViews(bus, bu_ids);
		for (Button bu : bus) {
			bu.setOnClickListener(new MyOnClickListener());
		}
		tv_status=viewUtil.getTextView(tv_status, R.id.status_view);
		tv_time=viewUtil.getTextView(tv_time, R.id.time_view);
		tv_content=viewUtil.getTextView(tv_content,R.id.content_view);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (history.isON() || history.isOFF()) {
			tv_status.setText(history.isON() ? "on" : "off");
			tv_time.setText(history.isON() ? history.getONTIME() : history
					.getOFFTIME());
			tv_content.setText(history.getONCONTENT());
		} else {
			tv_status.setText("");
			tv_time.setText("");
			tv_content.setText("");
		}
	}

	class MyOnClickListener implements OnClickListener {
		private Builder builder;
		private SimpleDateFormat sdf = new SimpleDateFormat(
				"yyyy-mm-dd hh:mm:ss");
		private String time = null;

		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			switch (arg0.getId()) {
			case R.id.on:
				doOn();
				break;
			case R.id.off:
				doOff();
				break;
			}
		}

		public void doOn() {
			time = sdf.format(new Date());
			history.setON(true);
			history.setONTIME(time);
			/*
			 * 设置发送内容的对话框
			*/
			setSendContent("设置on指令携带的信息:",1);
			tv_status.setText("on");
			tv_time.setText(time);
		}

		public void doOff() {
			time = sdf.format(new Date());
			history.setOFF(true);
			history.setOFFTIME(time);
			/*
			 * 设置发送内容的对话框
			*/
			setSendContent("设置off指令携带的信息:",-1);
			tv_status.setText("off");
			tv_time.setText(time);
		}
		
		
		public void setSendContent(String title, final int flag){
			final EditText et = new EditText(Switcher.this);
			et.setMaxWidth(20);
			et.setSelectAllOnFocus(true);
			if(flag ==1){
			et.setText(history.getONCONTENT());
			}
			else{
				et.setText(history.getOFFCONTENT());
			}
			et.setInputType(InputType.TYPE_CLASS_TEXT);
			builder = new AlertDialog.Builder(Switcher.this);
			builder.setTitle(title);
			builder.setView(et);
			builder.setPositiveButton("send",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							if(flag ==1){
							history.setONCONTENT(et.getText().toString());
							tv_content.setText(history.getONCONTENT());
							new MassageUtil(Switcher.this).send(history.getONCONTENT());
							}else{
								history.setOFFCONTENT(et.getText().toString());
								tv_content.setText(history.getOFFCONTENT());
								new MassageUtil(Switcher.this).send(history.getOFFCONTENT());
							}
						}
					});
			builder.setNegativeButton("cancel", null);
			builder.create().show();
		}
	}
}
