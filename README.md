# Piczilla

The app is meant to fetch a set of images from an API. The utility methods for the same have been provided. You have to show these images on UI with two buttons Next and Previous. They should fetch random images and set them in an ImageView. Please also make an LRU for effective image loading and caching.
The scope of the app should not require the usage of any libraries. Please try to <b>write clean and well-organized code</b> with proper architecture.


Please fetch the images from the API below


    https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=3e7cc266ae2b0e0d78e279ce8e361736&format=json&nojsoncallback=1&safe_search=1&tags=kitten&per_page=10&page=1

Build image url from response object (farm, server, id, secret are present in response object)

    https://farm${this.farm}.staticflickr.com/${this.server}/${this.id}_${this.secret}_q.jpg


Please register at https://www.flickr.com/services/api/ in case the given api key does not work.
