package piczilla.wynk.com.piczilla.utils;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;

import piczilla.wynk.com.piczilla.interfaces.ResponseListener;

/**
 * Created by navratan on 2019-09-14
 */
public class FetchImageAysncTask extends AsyncTask<Void, Void, Bitmap> {

    private BitmapCache cache;
    private WeakReference<ResponseListener<Bitmap>> bitmapListener;
    private String url;

    public FetchImageAysncTask(String url, ResponseListener<Bitmap> listener) {
        this.url = url;
        this.bitmapListener = new WeakReference<>(listener);
        this.cache = BitmapCache.getInstance();
    }

    @Override
    protected Bitmap doInBackground(Void... voids) {
        if(!isCancelled()) {
           if(cache.get(url) == null){
               cache.put(url, Utility.getBitmapFromURL(url));
           }
           return cache.get(url);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if(bitmapListener.get() != null) {
            bitmapListener.get().onSuccess(bitmap);
        }
    }
}
