package piczilla.wynk.com.piczilla.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import piczilla.wynk.com.piczilla.interfaces.ResponseListener;
import piczilla.wynk.com.piczilla.model.SearchResultModel;
import piczilla.wynk.com.piczilla.utils.FetchImageAysncTask;
import piczilla.wynk.com.piczilla.utils.FetchResponseAsyncTask;
import piczilla.wynk.com.piczilla.utils.Utility;

/**
 * Created by navratan on 2019-09-14
 */
public class FetchDataViewModel extends ViewModel {

    private MutableLiveData<Bitmap> bitmapLiveData;
    private List<String> allImages;
    private int currentImageIndex = -1;
    private int totalPages = Integer.MAX_VALUE;
    private int currentPageNo = 0;


    public FetchDataViewModel() {
        bitmapLiveData = new MutableLiveData<>();
        allImages = new ArrayList<>();
    }

    public void fetchNext() {
       if(currentImageIndex == allImages.size()-1) {
           if(currentPageNo == totalPages) {
               bitmapLiveData.setValue(null);
               return;
           }
           fetchResponse();
       } else {
           fetchNextImage();
       }
    }

    public void fetchPreviuos() {
        if(currentImageIndex == 0) {
            bitmapLiveData.setValue(null);
            return;
        }
        fetchPreviousImage();
    }
    public MutableLiveData<Bitmap> getBitmapLiveData() {
        return bitmapLiveData;
    }

    private void fetchNextImage() {
        Utility.execute(new FetchImageAysncTask(allImages.get(currentImageIndex+1), response -> {
            bitmapLiveData.setValue(response);
            currentImageIndex++;
        }));
    }

    private void fetchResponse() {

        Utility.execute(new FetchResponseAsyncTask(currentPageNo + 1, response -> {
            currentPageNo = response.getCurrentPage();
            totalPages = response.getPages();
            allImages.addAll(response.getImageUrls());
            fetchNextImage();
        }));
    }

    private void fetchPreviousImage() {
        Utility.execute(new FetchImageAysncTask(allImages.get(currentImageIndex-1), response -> {
            bitmapLiveData.setValue(response);
            currentImageIndex--;
        }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
