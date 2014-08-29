package platinum.panda.androidbox.network;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by sihrc on 8/28/14.
 */
public class BoxURL {
	public static String getQueryURL(final String query) {
		return Base.STAGING.getUrl("query", new HashMap<String, String>() {{
			try {
				put("keyword", URLEncoder.encode(query, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}});
	}
}
