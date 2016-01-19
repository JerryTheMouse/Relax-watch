package org.jerrycode.relaxwatch.Models;

import android.net.Uri;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Shady Atef (shadyoatef@gmail.com) on 1/19/16.
 */
public class Trailer {

    private Uri uri;
    private String name;

    @SerializedName("key")
    private String video_key;

    public Trailer(String name, Uri uri) {
        this.uri = uri;
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public Uri getUri() {

        if (uri == null)
            buildUri();

        return uri;
    }

    private void buildUri() {
        uri = new Uri.Builder()
                .scheme("https")
                .authority("youtube.com").appendPath("watch")
                .appendQueryParameter("v", video_key)
                .build();

    }
}
