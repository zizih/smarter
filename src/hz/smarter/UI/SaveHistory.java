package hz.smarter.UI;

import hz.smarter.Model.History;
import hz.smarter.Model.HistorySingleton;
import hz.smarter.Util.DbHelper;
import hz.smarter.Util.MassageUtil;
import hz.smarter.Util.ViewUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.ManagerFactoryParameters;

import hz.smarter.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class SaveHistory extends Activity {

	Button save, reset, show;
	Button[] bus = { save, reset, show };
	int[] ids = { R.id.save, R.id.reset, R.id.show };
	TextView tv;
	ViewUtil viewUtil = new ViewUtil(SaveHistory.this);
	History history = HistorySingleton.getHistoryInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.savehistory);
		bus = viewUtil.getButtonViews(bus, ids);
		for (Button bu : bus) {
			bu.setOnClickListener(new MyOnClickListener());
		}
		tv = viewUtil.getTextView(tv, R.id.history_view);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	class MyOnClickListener implements OnClickListener {

		int ON_STATUS, OFF_STATUS, PHONE, TIME;
		DbHelper dbHelper;

		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			switch (arg0.getId()) {
			case R.id.save:
				doSave();
				break;
			case R.id.reset:
				doReset();
				break;
			case R.id.show:
				doShow();
				break;
			}
		}

		private void doSave() {
			dbHelper = new DbHelper(SaveHistory.this);
			dbHelper.insert(dbHelper.getWritableDatabase(), history);
			dbHelper.close();
		}

		private void doReset() {
			Builder builder = new AlertDialog.Builder(SaveHistory.this);
			builder.setTitle("do you want to save the last setting ?\n or directly reset !");
			builder.setPositiveButton("yes",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dbHelper = new DbHelper(SaveHistory.this);
							dbHelper.insert(dbHelper.getWritableDatabase(),
									history);
						}
					});
			HistorySingleton.initializeHistory();
			builder.setNegativeButton("no", null);
			builder.create();
			builder.show();
		}

		private void doShow() {
			tv.setText("...");
			tv.setLines(1);
			LayoutInflater inflater = getLayoutInflater();
			View item = inflater.inflate(R.layout.item, null);
			PHONE = item.findViewById(R.id.phone_item).getId();
			ON_STATUS = item.findViewById(R.id.on_status_item).getId();
			OFF_STATUS = item.findViewById(R.id.off_status_item).getId();
			TIME = item.findViewById(R.id.time_item).getId();
			ListView listView = (ListView) findViewById(R.id.listView);
			listView.setAdapter(getAdapter());
			listView.setOnItemClickListener(new OnItemClickListener() {

				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					Toast.makeText(SaveHistory.this, "你点击了该记录\n长按你不能将该设置设置为当前设置", Toast.LENGTH_LONG).show();
				}
			});
			listView.setOnItemLongClickListener(new OnItemLongClickListener() {

				public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					doReset();
					return false;
				}
			});
		}

		private SimpleAdapter getAdapter() {
			dbHelper = new DbHelper(SaveHistory.this);
			List<History> historyList = dbHelper.getAllHistory(dbHelper
					.getReadableDatabase());
			if (historyList != null) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				System.out.println("list size:" + list.size());
				Map<String, Object> values;
				int i = 1;
				for (History history : historyList) {
					System.out.println(i++);
					System.out.println(history.getHOUR() + history.getMINUTE());
					values = new HashMap<String, Object>();
					values.put("on_status", "ON状态\n" + history.isON() + " 时间:"+ history.getONTIME()+" 携带的信息:"+ history.getONCONTENT());
					values.put("off_status", "OFF状态\n" + history.isOFF() + " 时间:"
							+ history.getOFFTIME()+" 携带的信息:" +history.getOFFCONTENT());
					values.put("phone",
							"发送到对方的号码:"
									+ (history.getPHONE() == -1 ? "no seeted"
											: history.getPHONE()));
					values.put(
							"time",
							"预约时间:"
									+ (history.getHOUR() >= 10 ? history
											.getHOUR() : "0"
											+ history.getHOUR())
									+ ":"
									+ (history.getMINUTE() >= 10 ? history
											.getMINUTE() : "0"
											+ history.getMINUTE())+"  携带的信息:"+history.getTIMECONTENT());
					list.add(values);
				}
				dbHelper.close();
				SimpleAdapter adapter = new SimpleAdapter(SaveHistory.this,
						list, R.layout.item, new String[] { "phone",
								"on_status", "off_status", "time" }, new int[] {
								PHONE, ON_STATUS, OFF_STATUS, TIME });
				return adapter;
			} else {
				return null;
			}
		}
	}

}
