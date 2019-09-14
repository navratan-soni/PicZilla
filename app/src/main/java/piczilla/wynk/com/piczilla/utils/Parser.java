package piczilla.wynk.com.piczilla.utils;

import com.google.gson.Gson;

/**
 * Created by navratan on 2019-09-14
 */
public class Parser {
    public static <T> T parseResponse(String reponse, Class<T> typeKey) {
        Gson gson = new Gson();
        return gson.fromJson(reponse, typeKey);
    }
}
