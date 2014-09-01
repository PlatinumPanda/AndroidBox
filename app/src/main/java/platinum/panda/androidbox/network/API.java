package platinum.panda.androidbox.network;

import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import platinum.panda.androidbox.PandaBox;
import platinum.panda.androidbox.callback.JSONCallback;
import platinum.panda.androidbox.views.activities.MainActivity;

/**
 * Created by sihrc on 8/28/14.
 */
public class API {
	//** Server Calls **//

	/**
	 * Gets query results from server
	 *
	 * @param query    - key words used for search
	 * @param callback - callback for results
	 */
	public static void getQueryResults(String query, JSONCallback callback) {
		Log.i("API", "getQueryResults");
		JSONRequest(Request.Method.GET, BoxURL.getQueryURL(query), null, "SearchQuery", callback);
	}


	/**
	 * Helper Methods for Sending Server Calls
	 */

	private static void JSONRequest(int method, String url, JSONObject json, final String tag, final JSONCallback callback) {
		execute(new JsonObjectRequest(method, url, json, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				Log.i("API", tag + " success");
				try {
					callback.done(response);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Log.i("API", tag + " error " + error.toString());

						try {
							callback.error(error);
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}));
	}

	protected static void execute(Request<?> request) {
		//Set Timeout
		RetryPolicy policy = request.getRetryPolicy();
		request.setRetryPolicy(new DefaultRetryPolicy(policy.getCurrentTimeout() * 4, policy.getCurrentRetryCount(), DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

		//Add Request
		PandaBox.app.getRequestQueue().add(request);
	}
}
