package platinum.panda.androidbox;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import platinum.panda.androidbox.network.BitmapLRUCache;

/**
 * Created by sihrc on 8/31/14.
 */
public class PandaBox extends Application {
	/**
	 * Network Handling
	 */
	private ImageLoader imageLoader;
	private RequestQueue requestQueue;

	/**
	 * Application Context
	 */
	public static PandaBox app;
	private Context context;


	@Override
	public void onCreate() {
		super.onCreate();
		/** Application Context **/
		app = this;
		context = getApplicationContext();

		/** Network Requests Setup **/
		requestQueue = Volley.newRequestQueue(this);
		imageLoader = new ImageLoader(requestQueue, new BitmapLRUCache(BitmapLRUCache.getScreenBasedCacheSize(this)));
	}

	public Context getContext() {
		return context;
	}

	public ImageLoader getImageLoader() {
		return imageLoader;
	}

	public RequestQueue getRequestQueue() {
		return requestQueue;
	}

}
