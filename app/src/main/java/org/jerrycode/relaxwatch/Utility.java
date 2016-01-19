package org.jerrycode.relaxwatch;

import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import org.jerrycode.relaxwatch.Models.Review;
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

    public static ArrayList<Review> buildReviewsFromJsonArray(JsonArray arr) {

        ArrayList<Review> reviews = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            JsonElement item = arr.get(i);
            Review review = new Gson().fromJson(item, Review.class);
            reviews.add(review);

        }
        return reviews;
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

}
