package platinum.panda.androidbox.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by sihrc on 8/28/14.
 */
public class BoxToast {
	public static void showShort(Context context, int message) {
		Toast.makeText(context, context.getString(message), Toast.LENGTH_SHORT).show();
	}
}
