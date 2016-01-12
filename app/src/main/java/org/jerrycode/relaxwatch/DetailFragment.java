package org.jerrycode.relaxwatch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jerrycode.relaxwatch.Models.Movie;


/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {
    private Movie movie;
    private ImageView moviePosterIV;
    private TextView movieOriginalTitleTV, movieOverviewTV, movieRateTV;


    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        movie = getActivity().getIntent().getExtras().getParcelable("MOVIE");
        moviePosterIV = (ImageView) getView().findViewById(R.id.movie_poster_imageview);
        movieOriginalTitleTV = (TextView) getView().findViewById(R.id.movie_title_tv);
        movieOverviewTV = (TextView) getView().findViewById(R.id.movie_overview_tv);
        movieRateTV = (TextView) getView().findViewById(R.id.movie_rate_tv);

        String url = getString(R.string.movies_api_images_url) + movie.getPoster_path();
        Picasso.with(getActivity()).load(url).into(moviePosterIV);

        movieOriginalTitleTV.setText(movie.getOriginal_title());
        movieOverviewTV.setText("Overview : " + movie.getOverview());
        movieRateTV.setText("Rate :" + String.valueOf(movie.getVote_average()));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }


}
