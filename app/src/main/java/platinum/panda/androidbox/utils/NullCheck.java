package platinum.panda.androidbox.utils;

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
}
