package hz.smarter.Util;

import hz.smarter.Model.History;
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
		try {
			System.out.println(History.PHONE);
			SmsManager sManager = SmsManager.getDefault();
			PendingIntent pi = PendingIntent.getActivity(activity, 0,
					new Intent(), 0);
			sManager.sendTextMessage(History.PHONE + "", null, content, pi,
					null);
			Toast.makeText(activity, "成功发送", Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(activity, "发送失敗", Toast.LENGTH_SHORT).show();
		}
	}
}
