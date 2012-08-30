package hz.smarter.UI;

import hz.smarter.Model.History;
import hz.smarter.Model.HistorySingleton;
import hz.smarter.Util.MassageUtil;
import hz.smarter.Util.ViewUtil;

import java.util.Calendar;

import hz.smarter.R;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class Timing extends Activity {

	Button set, edit, send;
	Button[] bus = { set, edit, send };
	int[] ids = { R.id.set, R.id.edit, R.id.send };
	TextView tv;
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
		tv = viewUtil.getTextView(tv, R.id.timedView);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (history.HOUR != -1 && history.MINUTE != -1) {
			tv.setText((history.getHOUR() >= 10 ? history.getHOUR() : "0"
					+ history.getHOUR())
					+ ":"
					+ (history.getMINUTE() >= 10 ? history.getMINUTE() : "0"
							+ history.getMINUTE()));
		} else {
			tv.setText("");
		}
	}

	class MyOnClickListener implements OnClickListener {

		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			switch (arg0.getId()) {
			case R.id.set:
				doSet();
				break;
			case R.id.edit:
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
			if (history.HOUR != -1) {
				getTimePickerDialog(history.HOUR, history.MINUTE);
			} else {
				Toast.makeText(Timing.this, "you haven't setted any time",
						Toast.LENGTH_SHORT).show();
			}
		}

		public void doSend() {
			if (history.HOUR != -1) {
				new MassageUtil(Timing.this).send(history.HOUR + ":"
						+ history.MINUTE);
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
							history.HOUR = arg1;
							history.MINUTE = arg2;
							tv.setText((history.getHOUR() >= 10 ? history
									.getHOUR() : "0" + history.getHOUR())
									+ ":"
									+ (history.getMINUTE() >= 10 ? history
											.getMINUTE() : "0"
											+ history.getMINUTE()));
						}
					}, hour, minute, false).show();
			return;
		}
	}
}
