package platinum.panda.androidbox.callback;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sihrc on 8/28/14.
 */
public interface JSONCallback {
	public void done(JSONObject response) throws JSONException;

	public void error(VolleyError error) throws JSONException;
}
