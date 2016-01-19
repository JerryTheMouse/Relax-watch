package org.jerrycode.relaxwatch;

import android.net.Uri;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import org.jerrycode.relaxwatch.Models.Trailer;

import java.util.ArrayList;

/**
 * Created by jerry on 1/18/16.
 */
public class Utility {

    public static ArrayList<Trailer> buildTrailersUriFromJsonArray(JsonArray arr) {

        ArrayList<Trailer> trailers = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            JsonElement item = arr.get(i);

            Uri uri = new Uri.Builder()
                    .scheme("https")
                    .authority("youtube.com").appendPath("watch")
                    .appendQueryParameter("v", item.getAsJsonObject().get("key").getAsString())
                    .build();
            String name = "Trailer " + String.valueOf(i + 1);
            Trailer trailer = new Trailer(name, uri);
            trailers.add(trailer);

        }
        return trailers;
    }

}
