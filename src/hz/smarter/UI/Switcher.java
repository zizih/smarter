package hz.smarter.UI;

import hz.smarter.R;
import hz.smarter.Model.History;
import hz.smarter.Util.MassageUtil;
import hz.smarter.Util.ViewUtil;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Switcher extends Activity {

	// about button
	Button opendevice, open1, open2, open3, open4;
	Button closedevice, close1, close2, close3, close4;
	Button status, reset_prompt;
	Button[] bus = { opendevice, open1, open2, open3, open4, closedevice,
			close1, close2, close3, close4, status, reset_prompt };
	int[] bu_ids = { R.id.opendevice, R.id.open1, R.id.open2, R.id.open3,
			R.id.open4, R.id.closedevice, R.id.close1, R.id.close2,
			R.id.close3, R.id.close4, R.id.status, R.id.reset_prompt };
	TextView prompt;
	private ViewUtil viewUtil = new ViewUtil(Switcher.this);
	private MassageUtil massageUtil = new MassageUtil(Switcher.this);
	StringBuilder builder = new StringBuilder();

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
		prompt = (TextView) findViewById(R.id.prompt_view);
		// open1.setBackgroundDrawable(Switcher.this.getResources()
		// .getDrawable(R.drawable.bu_bg_black));
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
			case R.id.opendevice:
				doOpenDevice();
				break;
			case R.id.open1:
				doOpenLed1();
				break;
			case R.id.open2:
				doOpenLed2();
				break;
			case R.id.open3:
				doOpenLed3();
				break;
			case R.id.open4:
				doOpenLed4();
				break;
			case R.id.closedevice:
				doCloseDevice();
				break;
			case R.id.close1:
				doCloseLed1();
				break;
			case R.id.close2:
				doCloseLed2();
				break;
			case R.id.close3:
				doCloseLed3();
				break;
			case R.id.close4:
				doCloseLed4();
				break;
			case R.id.status:
				doStatus();
				break;
			case R.id.reset_prompt:
				doResetPrompt();
				break;
			}
		}

		public void doOpenDevice() {
			massageUtil.send("opendevice");
			setPromptText("opendevice");
		}

		public void doOpenLed1() {
			massageUtil.send("opendled1");
			setPromptText("opendled1");
		}

		public void doOpenLed2() {
			History.OPEN2 = true;
			massageUtil.send("opendled2");
			setPromptText("opendled2");
		}

		public void doOpenLed3() {
			History.OPEN3 = true;
			massageUtil.send("opendled3");
			setPromptText("opendled3");
		}

		public void doOpenLed4() {
			History.OPEN4 = true;
			massageUtil.send("opendled4");
			setPromptText("opendled4");
		}

		public void doCloseDevice() {
			History.CLOSEDEVICE = true;
			massageUtil.send("closedevice");
			setPromptText("closedevice");
		}

		public void doCloseLed1() {
			History.CLOSE1 = true;
			massageUtil.send("closeled1");
			setPromptText("closeled1");
		}

		public void doCloseLed2() {
			History.CLOSE2 = true;
			massageUtil.send("closeled2");
			setPromptText("closeled2");
		}

		public void doCloseLed3() {
			History.CLOSE3 = true;
			massageUtil.send("closeled3");
			setPromptText("closeled3");
		}

		public void doCloseLed4() {
			History.CLOSE4 = true;
			massageUtil.send("closeled4");
			setPromptText("closeled4");
		}

		public void doStatus() {
			History.STATUS = true;
			massageUtil.send("status");
			setPromptText("status");
		}

		public void doResetPrompt() {
			builder = new StringBuilder();
			prompt.setText("");
		}

		public void setPromptText(String text) {
			builder.append(text + "\n");
			prompt.setText(builder.toString());
		}
	}
}
