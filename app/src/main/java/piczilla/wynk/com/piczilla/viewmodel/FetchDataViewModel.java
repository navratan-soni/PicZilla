package piczilla.wynk.com.piczilla.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import piczilla.wynk.com.piczilla.model.UiStateModel;
import piczilla.wynk.com.piczilla.utils.FetchImageAysncTask;
import piczilla.wynk.com.piczilla.utils.FetchResponseAsyncTask;
import piczilla.wynk.com.piczilla.utils.Utility;

/**
 * Created by navratan on 2019-09-14
 */
public class FetchDataViewModel extends ViewModel {

    private MutableLiveData<UiStateModel> bitmapLiveData;
    private List<String> allImages;
    private int currentImageIndex = -1;
    private int totalPages = Integer.MAX_VALUE;
    private int currentPageNo = 0;

    private FetchResponseAsyncTask reponseTask;
    private FetchImageAysncTask imaegTask;

    public FetchDataViewModel() {
        bitmapLiveData = new MutableLiveData<>();
        allImages = new ArrayList<>();
    }

    public void fetchNext() {
       if(currentImageIndex == allImages.size()-1) {
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
    public MutableLiveData<UiStateModel> getBitmapLiveData() {
        return bitmapLiveData;
    }

    private void fetchNextImage() {
        
        Utility.execute(new FetchImageAysncTask(allImages.get(currentImageIndex+1), response -> {
            currentImageIndex++;
            bitmapLiveData.setValue(new UiStateModel(response,
                    currentImageIndex > 0,
                       currentPageNo <= totalPages));
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
            currentImageIndex--;
            bitmapLiveData.setValue(new UiStateModel(response,
                    currentImageIndex > 0,
                    currentPageNo <= totalPages));

        }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
