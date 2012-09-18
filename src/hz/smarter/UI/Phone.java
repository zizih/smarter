package hz.smarter.UI;

import hz.smarter.R;
import hz.smarter.Model.History;
import hz.smarter.Util.MassageUtil;
import hz.smarter.Util.ViewUtil;
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

public class Phone extends Activity {

	Button reset;
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone);
		/*
		 * get buttons
		 */
		reset = (Button) findViewById(R.id.reset);
		reset.setOnClickListener(new MyOnClickListener());
		tv = (TextView) findViewById(R.id.phoneView);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		tv.setText(History.PHONE);
	}

	class MyOnClickListener implements OnClickListener {

		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			final EditText et = new EditText(Phone.this);
			et.setText(History.PHONE);
			et.setMaxWidth(20);
			et.setSelectAllOnFocus(true);
			et.setInputType(InputType.TYPE_CLASS_NUMBER);
			Builder builder = new AlertDialog.Builder(Phone.this);
			builder.setTitle("输入号码");
			builder.setView(et);
			builder.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							History.PHONE = et.getText().toString();
							tv.setText(History.PHONE);
						}
					});
			builder.setNegativeButton("取消", null);
			builder.create().show();
		}
	}
}
