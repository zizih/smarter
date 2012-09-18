package hz.smarter.UI;

import hz.smarter.R;
import hz.smarter.Model.History;
import hz.smarter.Util.MassageUtil;
import hz.smarter.Util.ViewUtil;

import java.util.Calendar;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class Timing extends Activity {

	Button set, edit, send;
	Button[] bus = { set, edit, send };
	int[] ids = { R.id.set, R.id.modify, R.id.send };
	TextView tv_time;
	private ViewUtil viewUtil;
	private MassageUtil massageUtil;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timing);

		viewUtil = new ViewUtil(Timing.this);
		massageUtil = new MassageUtil(Timing.this);
		/*
		 * get buttons
		 */
		bus = viewUtil.getButtonViews(bus, ids);
		for (Button bu : bus) {
			bu.setOnClickListener(new MyOnClickListener());
		}
		tv_time = viewUtil.getTextView(tv_time, R.id.time_view);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	class MyOnClickListener implements OnClickListener {

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
			getTimePickerDialog(History.HOUR, History.MINUTE);
		}

		public void doSend() {
			massageUtil.send("seth" + History.HOUR + "hm" + History.MINUTE + "m");
		}

		public void getTimePickerDialog(int hour, int minute) {
			new TimePickerDialog(Timing.this, 0,
					new TimePickerDialog.OnTimeSetListener() {

						public void onTimeSet(TimePicker arg0, int arg1,
								int arg2) {
							// TODO Auto-generated method stub
							History.HOUR = arg1;
							History.MINUTE = arg2;
							tv_time.setText(History.HOUR + ":" + History.MINUTE);
						}
					}, hour, minute, false).show();
			return;
		}
	}
}
