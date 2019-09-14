package piczilla.wynk.com.piczilla.model;

import java.util.List;

/**
 * Created by navratan on 2019-09-14
 */
public class CollectionModel {
    int pages;
    int currentPage;
    List<String> imageUrls;

    public CollectionModel(int pages, int currentPage, List<String> imageUrls) {
        this.pages = pages;
        this.currentPage = currentPage;
        this.imageUrls = imageUrls;
    }

    public int getPages() {
        return pages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }
}
