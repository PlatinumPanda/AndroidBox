package platinum.panda.androidbox.network;

import java.util.Map;

/**
 * Created by sihrc on 8/28/14.
 */
public enum Base {
	STAGING("http://panda-box.appspot.com");

	String url;

	private Base(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return url;
	}

	public String getUrl(String path, Map<String, String> params) {
		StringBuilder sb = new StringBuilder(url + '/' + path);
		sb.append('?');
		for (Map.Entry<String, String> entry : params.entrySet()) {
			sb.append(entry.getKey()).append('=').append(entry.getValue()).append('&');
		}
		return sb.substring(0, sb.length() - 1);
	}
}
