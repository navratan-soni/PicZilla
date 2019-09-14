package piczilla.wynk.com.piczilla.model;

import android.graphics.Bitmap;

import piczilla.wynk.com.piczilla.utils.BitmapCache;

/**
 * Created by navratan on 2019-09-14
 */
public class UiStateModel {
    private Bitmap bitmap;
    private boolean enabledPreviuos;
    private boolean enabledNext;

    private UiStateModel() {}

    public UiStateModel(Bitmap bitmap, boolean enabledPreviuos, boolean enabledNext) {
        this.bitmap = bitmap;
        this.enabledPreviuos = enabledPreviuos;
        this.enabledNext = enabledNext;
    }
}
