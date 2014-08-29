package platinum.panda.androidbox.network;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by sihrc on 8/28/14.
 */

public class BitmapLRUCache extends LruCache<String, Bitmap> implements ImageLoader.ImageCache {

	public BitmapLRUCache() {
		this(getDefaultLruCacheSize());
	}

	public BitmapLRUCache(int maxSize) {
		super(maxSize);
	}

	public static int getDefaultLruCacheSize() {

		final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
		final int cacheSize = maxMemory / 8;


		return cacheSize;
	}

	public static int getScreenBasedCacheSize(Context context) {
		final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		final int screenWidth = displayMetrics.widthPixels;
		final int screenHeight = displayMetrics.heightPixels;
		final int screenBytes = screenWidth * screenHeight * 4; // 4 bytes per
		// pixel

		int cacheSize = screenBytes * 3 / 1024;

		return cacheSize;
	}

	public static int getMemoryClassBasedCacheSize(Context context) {
		ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		final int memClass = activityManager.getMemoryClass();
		// Use 1/16th of the available memory for this memory cache.
		final int cacheSize = 1024 * memClass / 8;

		return cacheSize;
	}

	@Override
	protected int sizeOf(String key, Bitmap value) {
		return value.getRowBytes() * value.getHeight() / 1024;
	}

	@Override
	public Bitmap getBitmap(String url) {
		return get(url);
	}

	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		put(url, bitmap);
	}
}