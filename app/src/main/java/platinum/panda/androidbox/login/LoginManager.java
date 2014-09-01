package platinum.panda.androidbox.login;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import platinum.panda.androidbox.callback.JSONCallback;

/**
 * Created by sihrc on 8/31/14.
 */
public class LoginManager {
	/** Application Context **/
	Context context;

	/** Status **/
	boolean isLoggingIn;
	boolean isPandaLogged;

	public LoginManager(Context context) {
		this.context = context;
	}

	public void login(JSONCallback jsonCallback) {
		if (isLoggingIn || isLoggedIn()) {
			Log.i("Login", "Already logged in");
			return;
		}

		isLoggingIn = true;
		String deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
		//FIXME API Server Login Call
		try {
			jsonCallback.done(new JSONObject());
			isPandaLogged = true;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		isLoggingIn = false;
		Log.i("Login Device Id", deviceId);
	}

	public void logout() {
		if (!isLoggedIn()) {
			Log.i("Login", "Already logged out");
		}

		isPandaLogged = false;
	}

	public boolean isLoggedIn() {
		return isPandaLogged;
	}
}
