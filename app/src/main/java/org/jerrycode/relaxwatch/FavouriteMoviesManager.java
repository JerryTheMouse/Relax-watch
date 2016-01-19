package org.jerrycode.relaxwatch;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import org.jerrycode.relaxwatch.Models.Movie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by Shady Atef (shadyoatef@gmail.com) on 1/19/16.
 */

// This class manages Favourite Movie
public class FavouriteMoviesManager {
    private static FavouriteMoviesManager mInstance;
    private final String mPreferenceFile = "FAVOURITE_MOVIES";
    private Context mContext;
    private HashSet<Movie> mFavMovies;

    private FavouriteMoviesManager(Context context) {
        mContext = context;
        mFavMovies = new HashSet<>();
        mFavMovies.toArray();
        initializeFavouriteMovies();
    }

    public static FavouriteMoviesManager getInstance(Context context) {
        if (context == null)
            throw new NullPointerException();
        if (mInstance == null) {
            mInstance = new FavouriteMoviesManager(context);

        }

        return mInstance;
    }

    private void initializeFavouriteMovies() {
        Map<String, String> movies_gson_input = (Map<String, String>) mContext.getSharedPreferences(mPreferenceFile, Context.MODE_PRIVATE).getAll();
        for (Map.Entry<String, String> entry : movies_gson_input.entrySet()) {
            Movie m = new Gson().fromJson(entry.getValue(), Movie.class);
            mFavMovies.add(m);
        }

    }

    public boolean addMovie(Movie m) {
        if (mFavMovies.add(m)) {
            SharedPreferences.Editor editor = mContext.getSharedPreferences(mPreferenceFile, Context.MODE_PRIVATE).edit();
            editor.putString(String.valueOf(m.getId()), new Gson().toJson(m));
            editor.commit();
            return true;
        } else return false;
    }

    public boolean removeMovie(Movie m) {
        if (mFavMovies.remove(m)) {
            SharedPreferences.Editor editor = mContext.getSharedPreferences(mPreferenceFile, Context.MODE_PRIVATE).edit();
            editor.remove(String.valueOf(m.getId()));
            editor.commit();
            return true;
        } else return false;
    }

    public boolean hasMovie(Movie m) {
        return mFavMovies.contains(m);
    }


    public ArrayList<Movie> getMovies() {
        return new ArrayList<Movie>(mFavMovies);
    }
}