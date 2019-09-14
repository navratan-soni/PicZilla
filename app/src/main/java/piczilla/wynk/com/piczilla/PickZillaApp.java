package piczilla.wynk.com.piczilla;

import android.app.Application;
import android.content.Context;

/**
 * Created by navratan on 2019-09-14
 */
public class PickZillaApp extends Application {

    private static Application context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context.getApplicationContext();
    }
}
