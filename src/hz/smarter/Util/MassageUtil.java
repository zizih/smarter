package hz.smarter.Util;

import hz.smarter.Model.HistorySingleton;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

public class MassageUtil {

	public Activity activity;

	public MassageUtil(Activity activity) {
		this.activity = activity;
	}

	public void send(String content) {
		if (HistorySingleton.getHistoryInstance().getPHONE() != "") {
			try {
				SmsManager sManager = SmsManager.getDefault();
				PendingIntent pi = PendingIntent.getActivity(activity, 0,
						new Intent(), 0);
				sManager.sendTextMessage(HistorySingleton.getHistoryInstance()
						.getPHONE() + "", null, content, pi, null);
				Toast.makeText(activity, "成功发送", Toast.LENGTH_SHORT).show();
			} catch (Exception e) {
				// TODO: handle exception
				Toast.makeText(activity, "发送失敗", Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(activity, "发送失敗,there is no any phone num setted",
					Toast.LENGTH_SHORT).show();
		}
	}
}
