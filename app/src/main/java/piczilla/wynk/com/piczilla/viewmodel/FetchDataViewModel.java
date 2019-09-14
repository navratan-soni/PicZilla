package piczilla.wynk.com.piczilla.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by navratan on 2019-09-14
 */
public class FetchDataViewModel extends ViewModel {

    public MutableLiveData<Bitmap> bitmapLiveData;
    public List<String> allImages;
    public int currentImageIndex = 0;

    public FetchDataViewModel() {
        bitmapLiveData = new MutableLiveData<>();
        allImages = new ArrayList<>();
    }

    public void fetchNext() {
       if(currentImageIndex == allImages.size()) {
           fetchResponse();
       } else {
           fetchNextImage();
           currentImageIndex++;
       }
    }

    public void fetchPreviuos() {
        if(currentImageIndex == 0) {
            return;
        }
        fetchPreviousImage();
        currentImageIndex--;
    }
    public MutableLiveData<Bitmap> getBitmapLiveData() {
        return bitmapLiveData;
    }

    private void fetchNextImage() {
      //TODO Navratan
    }

    private void fetchResponse() {
        //TODO Navratan
    }

    public void fetchPreviousImage() {
        //TODO Navratan
    }
}
