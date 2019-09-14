package piczilla.wynk.com.piczilla.utils;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import piczilla.wynk.com.piczilla.Constants;
import piczilla.wynk.com.piczilla.PickZillaApp;
import piczilla.wynk.com.piczilla.interfaces.ResponseListener;
import piczilla.wynk.com.piczilla.model.CollectionModel;
import piczilla.wynk.com.piczilla.model.PhotoModel;
import piczilla.wynk.com.piczilla.model.SearchResultModel;

/**
 * Created by navratan on 2019-09-14
 */
public class FetchResponseAsyncTask extends AsyncTask<Void, Void, CollectionModel> {

    int page = 1;
    WeakReference<ResponseListener<CollectionModel>> modelResponseListener;

    public FetchResponseAsyncTask(int page, ResponseListener<CollectionModel> listener) {
        this.page = page;
        this.modelResponseListener = new WeakReference<>(listener);
    }

    @Override
    protected CollectionModel doInBackground(Void... voids) {
        if(Utility.isNetworkAvailable(PickZillaApp.getContext())) {
            if (!isCancelled()) {
                String response = Utility.getResponse(Constants.BASE_URL + "&page=" + page);
                SearchResultModel resultModel = Parser.parseResponse(response, SearchResultModel.class);
                return new CollectionModel(resultModel.getPhotos().getPages(),
                        resultModel.getPhotos().getPage(),
                        getMappedList(resultModel));
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(CollectionModel model) {
        super.onPostExecute(model);
        if(modelResponseListener.get() != null) {
            modelResponseListener.get().onSuccess(model);
        }
    }

    private List<String> getMappedList(@Nullable SearchResultModel resultModel) {
        if(resultModel == null) {
            return null;
        }
        List<String> allImagesUrl = new ArrayList<>();
        for(PhotoModel photo : resultModel.getPhotos().getPhoto()) {
            allImagesUrl.add(photo.getImageUrl());
        }
        return allImagesUrl;
    }
}
