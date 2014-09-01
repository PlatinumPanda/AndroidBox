package platinum.panda.androidbox.views.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import platinum.panda.androidbox.PandaBox;
import platinum.panda.androidbox.R;
import platinum.panda.androidbox.callback.JSONCallback;
import platinum.panda.androidbox.views.activities.MainActivity;

/**
 * Created by sihrc on 8/31/14.
 */
public class LoginFragment extends DialogFragment {
	/** Parent Activity **/
	MainActivity activity;

	public LoginFragment() {}
	public static void display(MainActivity activity) {
		LoginFragment loginFragment = new LoginFragment();
		loginFragment.activity = activity;
		loginFragment.show(activity.getFragmentManager(), LoginFragment.class.getSimpleName());
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_NoActionBar_Fullscreen);
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return super.onCreateDialog(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.login_fragment, container, false);

		View panda_login = view.findViewById(R.id.panda_login);
		panda_login.setOnClickListener(getPandaLoginClickListener());

		return view;
	}

	private View.OnClickListener getPandaLoginClickListener() {
		return new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				PandaBox.app.getLoginManager().login(new JSONCallback() {
					@Override
					public void done(JSONObject response) throws JSONException {
						activity.getmNavigationDrawerFragment().update();
						activity.showFragment(CardFeedFragment.newInstance(), true);
						dismiss();
					}

					@Override
					public void error(VolleyError error) throws JSONException {

					}
				});
			}
		};
	}
}
