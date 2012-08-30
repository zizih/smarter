package hz.smarter.Util;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;

public class ViewUtil {

	public Activity activity;

	public ViewUtil(Activity activity) {
		this.activity = activity;
	}

	public Button[] getButtonViews(Button[] bus, int[] ids) {
		for (int i = 0; i < bus.length; i++) {
			bus[i] = (Button) activity.findViewById(ids[i]);
		}
		return bus;
	}

	public TextView getTextView(TextView tv, int id) {
		tv = (TextView) activity.findViewById(id);
		return tv;
	}

	public TextView[] getTextViews(TextView[] tvs, int[] ids) {
		for (int i = 0; i < tvs.length; i++) {
			tvs[i] = (TextView) activity.findViewById(ids[i]);
		}
		return tvs;
	}

}
