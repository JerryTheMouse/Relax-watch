package org.jerrycode.relaxwatch.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.jerrycode.relaxwatch.Models.Movie;
import org.jerrycode.relaxwatch.R;

/**
 * Created by jerry on 12/19/15.
 */
public class MovieAdapter extends ArrayAdapter<Movie> {
    private LayoutInflater inflater;

    public MovieAdapter(Context context, int resource) {
        super(context, resource);
        inflater = LayoutInflater.from(context);


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.grid_item_imageview, parent, false);
        }
        Movie m = getItem(position);
        String url = getContext().getString(R.string.movies_api_images_url) + m.getPoster_path();
        Picasso.with(getContext()).load(url).into((ImageView) convertView);
        return convertView;
    }
}
