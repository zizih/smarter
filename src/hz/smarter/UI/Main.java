package hz.smarter.UI;

import hz.smarter.R;
import hz.smarter.Model.History;
import hz.smarter.Model.HistorySingleton;
import hz.smarter.Util.DbHelper;
import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * @author hezi
 */
public class Main extends ActivityGroup {
	private final int EXIT_ITEM = 001;
	private final int HELP_ITEM = 002;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
		// LayoutInflater.from(this).inflate(layout.main,
		// tabHost.getTabContentView(), true);
		tabHost.setup(this.getLocalActivityManager());
		tabHost.addTab(tabHost.newTabSpec("switch").setIndicator("控制")
				.setContent(new Intent(this, Switcher.class)));
		tabHost.addTab(tabHost.newTabSpec("timing").setIndicator("定時")
				.setContent(new Intent(this, Timing.class)));
		tabHost.addTab(tabHost.newTabSpec("phone").setIndicator("号码")
				.setContent(new Intent(this, Phone.class)));
		tabHost.addTab(tabHost.newTabSpec("saveAndHistory")
				.setIndicator("历史记录")
				.setContent(new Intent(this, SaveHistory.class)));
		tabHost.setCurrentTab(0);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		DbHelper dbHelper = new DbHelper(Main.this);
		History history = HistorySingleton.getHistoryInstance();
		history = dbHelper.getLastHistory(dbHelper.getWritableDatabase());
		dbHelper.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(0, EXIT_ITEM, 0, "exit");
		menu.add(0, HELP_ITEM, 0, "help");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case EXIT_ITEM:
			this.finish();
			break;
		case HELP_ITEM:
			TextView tView = new TextView(Main.this);
			tView.setPadding(25, 25, 25, 25);
			tView.setTextSize(18);
			tView.setTextColor(Color.GREEN);
			tView.setText(getResources().getString(R.string.help));
			new AlertDialog.Builder(Main.this).setTitle("help").setView(tView)
					.setNegativeButton("cancle", null).create().show();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
