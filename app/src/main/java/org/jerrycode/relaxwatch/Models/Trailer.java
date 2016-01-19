package org.jerrycode.relaxwatch.Models;

import android.net.Uri;

/**
 * Created by Shady Atef (shadyoatef@gmail.com) on 1/19/16.
 */
public class Trailer {

    private Uri uri;
    private String name;


    public Trailer(String name, Uri uri) {
        this.uri = uri;
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public Uri getUri() {
        return uri;
    }

}
