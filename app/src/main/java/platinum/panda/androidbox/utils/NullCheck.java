package platinum.panda.androidbox.utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sihrc on 8/28/14.
 */
public class NullCheck {
	public static String stringChecker(String check, String def) {
		if (check == null || check.isEmpty()) {
			return def;
		}
		return check;
	}

	public static String getJSONString(JSONObject json, String field, String def) throws JSONException {
		if (json.has(field)) {
			return json.getString(field);
		} else {
			return def;
		}
	}
}
