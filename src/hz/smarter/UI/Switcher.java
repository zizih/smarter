package hz.smarter.UI;

import hz.smarter.Model.History;
import hz.smarter.Model.HistorySingleton;
import hz.smarter.Util.MassageUtil;
import hz.smarter.Util.ViewUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import hz.smarter.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Switcher extends Activity {

	Button on, off;
	Button[] bus = { on, off };
	int[] ids = { R.id.on, R.id.off };
	TextView tv_status, tv_time;
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

		bus = viewUtil.getButtonViews(bus, ids);
		for (Button bu : bus) {
			bu.setOnClickListener(new MyOnClickListener());
		}
		tv_status = viewUtil.getTextView(tv_status, R.id.status_view);
		tv_time = viewUtil.getTextView(tv_time, R.id.time_view);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (history.isON() || history.isOFF()) {
			tv_status.setText(history.isON() ? "on" : "off");
			tv_time.setText(history.isON() ? history.getONTIME() : history
					.getOFFTIME());
		} else {
			tv_status.setText("");
			tv_time.setText("");
		}
	}

	class MyOnClickListener implements OnClickListener {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		String time = null;

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
			new MassageUtil(Switcher.this).send("on");
			tv_status.setText("on");
			tv_time.setText(time);
		}

		public void doOff() {
			time = sdf.format(new Date());
			history.setOFF(true);
			history.setOFFTIME(time);
			new MassageUtil(Switcher.this).send("off");
			tv_status.setText("off");
			tv_time.setText(time);
		}
	}
}
