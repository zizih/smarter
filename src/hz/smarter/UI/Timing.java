package hz.smarter.UI;

import hz.smarter.R;
import hz.smarter.Model.History;
import hz.smarter.Model.HistorySingleton;
import hz.smarter.Util.MassageUtil;
import hz.smarter.Util.ViewUtil;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class Timing extends Activity {

	Button set, edit, send;
	Button[] bus = { set, edit, send };
	int[] ids = { R.id.set, R.id.modify, R.id.send };
	TextView tv_time, tv_content;
	ViewUtil viewUtil = new ViewUtil(Timing.this);
	History history = HistorySingleton.getHistoryInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timing);
		/*
		 * get buttons
		 */
		bus = viewUtil.getButtonViews(bus, ids);
		for (Button bu : bus) {
			bu.setOnClickListener(new MyOnClickListener());
		}
		tv_time = viewUtil.getTextView(tv_time, R.id.time_view);
		tv_content = viewUtil.getTextView(tv_content, R.id.content_view);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (history.HOUR != -1 && history.MINUTE != -1) {
			tv_time.setText((history.getHOUR() >= 10 ? history.getHOUR() : "0"
					+ history.getHOUR())
					+ ":"
					+ (history.getMINUTE() >= 10 ? history.getMINUTE() : "0"
							+ history.getMINUTE()));
			tv_content.setText(history.getONCONTENT());
		} else {
			tv_time.setText("");
			tv_content.setText("");
		}
	}

	class MyOnClickListener implements OnClickListener {

		private Builder builder;

		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			switch (arg0.getId()) {
			case R.id.set:
				doSet();
				break;
			case R.id.modify:
				doEdit();
				break;

			case R.id.send:
				doSend();
				break;
			}
		}

		public void doSet() {
			Calendar defaultTime = Calendar.getInstance();
			getTimePickerDialog(defaultTime.get(Calendar.HOUR),
					defaultTime.get(Calendar.MINUTE));
		}

		public void doEdit() {
				getTimePickerDialog(history.HOUR, history.MINUTE);
		}

		public void doSend() {
			if (history.HOUR != -1 && history.TIMECONTENT != null) {
				setSendContent("设置timing指令携带的信息:");
			} else {
				Toast.makeText(Timing.this, "you haven't setted any time",
						Toast.LENGTH_SHORT).show();
			}
		}

		public void getTimePickerDialog(int hour, int minute) {
			new TimePickerDialog(Timing.this, 0,
					new TimePickerDialog.OnTimeSetListener() {

						public void onTimeSet(TimePicker arg0, int arg1,
								int arg2) {
							// TODO Auto-generated method stub
							history.setHOUR(arg1);
							history.setMINUTE(arg2);
							String timing =(history.getHOUR() >= 10 ? history
									.getHOUR() : "0" + history.getHOUR())
									+ ":"
									+ (history.getMINUTE() >= 10 ? history
											.getMINUTE() : "0"
											+ history.getMINUTE());
							history.setTIMECONTENT(timing);
							tv_time.setText(timing);
						}
					}, hour, minute, false).show();
			return;
		}

		public void setSendContent(String title) {
			final EditText et = new EditText(Timing.this);
			et.setText(history.getTIMECONTENT());
			et.setMaxWidth(20);
			et.setSelectAllOnFocus(true);
			et.setInputType(InputType.TYPE_CLASS_TEXT);
			builder = new AlertDialog.Builder(Timing.this);
			builder.setTitle(title);
			builder.setView(et);
			builder.setPositiveButton("send",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							history.setTIMECONTENT(et.getText().toString());
							tv_content.setText(history.getTIMECONTENT());
							new MassageUtil(Timing.this).send(history.getTIMECONTENT());
						}
					});
			builder.setNegativeButton("cancel", null);
			builder.create().show();
		}
	}
}
