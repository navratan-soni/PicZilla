package piczilla.wynk.com.piczilla.utils;

import android.graphics.Bitmap;
import android.util.LruCache;


/**
 * Created by navratan on 2019-09-14
 */
public class BitmapCache extends LruCache<String, Bitmap> {

    private static volatile BitmapCache mInstance;

    private BitmapCache(int maxSize) {
        super(maxSize);
    }

    public static BitmapCache getInstance() {
        if (mInstance == null) {
            synchronized (BitmapCache.class) {
                if (mInstance == null) {
                    mInstance = new BitmapCache(getCacheSize());
                }
            }
        }
        return mInstance;
    }

    private static int getCacheSize() {
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        return maxMemory / 8;
    }
}
