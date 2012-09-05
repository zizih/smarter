package hz.smarter.UI;

import hz.smarter.Model.History;
import hz.smarter.Model.HistorySingleton;
import hz.smarter.Util.MassageUtil;
import hz.smarter.Util.ViewUtil;
import hz.smarter.R;
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
import android.widget.Toast;

public class Phone extends Activity {

	Button set, edit;
	Button[] bus = { set, edit };
	int[] ids = { R.id.set, R.id.modify };
	TextView tv;
	ViewUtil viewUtil = new ViewUtil(Phone.this);
	History history = HistorySingleton.getHistoryInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone);
		/*
		 * get buttons
		 */
		bus = viewUtil.getButtonViews(bus, ids);
		for (Button bu : bus) {
			bu.setOnClickListener(new MyOnClickListener());
		}
		tv = viewUtil.getTextView(tv, R.id.phoneView);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		tv.setText(history.PHONE + "");
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
				doModify();
				break;
			}
		}

		public void doSet() {
			getBuilder("请输入对方的号码", null);
		}

		public void doModify() {
			if (history.getPHONE() != "") {
				getBuilder("修改号码", tv.getText().toString());
			} else {
				Toast.makeText(Phone.this, "你还没有进行任何的设置,请先设置",
						Toast.LENGTH_SHORT).show();
			}
		}

		public void getBuilder(String title, String etString) {
			final EditText et = new EditText(Phone.this);
			et.setText(etString);
			et.setMaxWidth(20);
			et.setSelectAllOnFocus(true);
			et.setInputType(InputType.TYPE_CLASS_NUMBER);
			builder = new AlertDialog.Builder(Phone.this);
			builder.setTitle(title);
			builder.setView(et);
			builder.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							System.out.println("phone test...");
							String phone = et.getText().toString();
							history.PHONE = phone;
							tv.setText(phone + "");
						}
					});
			builder.setNegativeButton("取消", null);
			builder.create().show();
		}
	}
}
