package org.jerrycode.relaxwatch.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jerry on 12/18/15.
 */
public class Movie implements Parcelable {
    int id;
    String poster_path, original_title, overview;
    float vote_average;


    public Movie(Parcel in) {
        id = in.readInt();
        poster_path = in.readString();
        original_title = in.readString();
        overview = in.readString();
        vote_average = in.readFloat();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public float getVote_average() {
        return vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public int getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(poster_path);
        dest.writeString(original_title);
        dest.writeString(overview);
        dest.writeFloat(vote_average);
    }
}
