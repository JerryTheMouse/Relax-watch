package org.jerrycode.relaxwatch;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jerrycode.relaxwatch.Adapters.TrailerAdapter;
import org.jerrycode.relaxwatch.Models.Movie;
import org.jerrycode.relaxwatch.Models.MovieAPIResponse;
import org.jerrycode.relaxwatch.Models.Review;
import org.jerrycode.relaxwatch.Models.Trailer;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class MovieDetailFragment extends Fragment {
    public static final String MOVIE_ARG_ID = "MOVIE_ARGUMENT";
    private Movie mMovie;
    private ImageView mMoviePosterIV;
    private TextView mMovieOriginalTitleTV, mMovieOverviewTV, mMovieRateTV;


    private TrailerAdapter mTrailerAdapter;
    private ListView mTrailerListView;


    private ListView mReviewListView;
    private ArrayAdapter<Review> mReviewAdapter;

    private Button mToggleFavouriteButton;

    public MovieDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initailizeReviewAdapter();

        initalizeTrailerAdapter();


        // If there are arguments and MOVIE_ARG_ID, this is two pane mode
        if (getArguments() != null && getArguments().containsKey(MOVIE_ARG_ID))
            mMovie = getArguments().getParcelable(MOVIE_ARG_ID);
        else
            mMovie = getActivity().getIntent().getExtras().getParcelable("MOVIE");


        mMoviePosterIV = (ImageView) getView().findViewById(R.id.movie_poster_imageview);
        mMovieOriginalTitleTV = (TextView) getView().findViewById(R.id.movie_title_tv);
        mMovieOverviewTV = (TextView) getView().findViewById(R.id.movie_overview_tv);
        mMovieRateTV = (TextView) getView().findViewById(R.id.movie_rate_tv);

        String url = getString(R.string.movies_api_images_url) + mMovie.getPoster_path();
        Picasso.with(getActivity()).load(url).into(mMoviePosterIV);

        mMovieOriginalTitleTV.setText(mMovie.getOriginal_title());
        mMovieOverviewTV.setText("Overview : " + mMovie.getOverview());
        mMovieRateTV.setText("Rate :" + String.valueOf(mMovie.getVote_average()));
        initializeToggleButton();
        loadTrailers();// I have to load Trailers after getting mMovie object
        loadReviews();

    }

    private void initalizeTrailerAdapter() {
        mTrailerAdapter = new TrailerAdapter(getActivity(), android.R.layout.simple_list_item_1);
        mTrailerListView = (ListView) getView().findViewById(R.id.trailers_lv);
        mTrailerListView.setAdapter(mTrailerAdapter);
        mTrailerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(Intent.ACTION_VIEW, mTrailerAdapter.getItem(position).getUri());
                startActivity(i);
            }
        });
    }

    private void initailizeReviewAdapter() {
        mReviewAdapter = new ArrayAdapter<Review>(getActivity(), android.R.layout.simple_list_item_1);
        mReviewListView = (ListView) getView().findViewById(R.id.reviews_lv);
        mReviewListView.setAdapter(mReviewAdapter);
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

    private void loadTrailers() {

        RelaxAndWatchApplication.getInstance().getMoviesAPIService().getTrailers(mMovie.getId()).enqueue(new Callback<MovieAPIResponse<Trailer>>() {
            @Override
            public void onResponse(Response<MovieAPIResponse<Trailer>> response, Retrofit retrofit) {
                mTrailerAdapter.clear();
                ArrayList<Trailer> trailers = response.body().getResults();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    mTrailerAdapter.addAll(trailers);
                } else {
                    for (Trailer t : trailers) {
                        mTrailerAdapter.add(t);
                    }
                }
                mTrailerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    private void loadReviews() {

        RelaxAndWatchApplication.getInstance().getMoviesAPIService().getReviews(mMovie.getId()).enqueue(new Callback<MovieAPIResponse<Review>>() {
            @Override
            public void onResponse(Response<MovieAPIResponse<Review>> response, Retrofit retrofit) {
                mReviewAdapter.clear();
                ArrayList<Review> reviews = response.body().getResults();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    mReviewAdapter.addAll(reviews);
                } else {
                    for (Review t : reviews) {
                        mReviewAdapter.add(t);
                    }
                }
                mReviewAdapter.notifyDataSetChanged();

                Utility.setListViewHeightBasedOnChildren(mReviewListView);

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    private void initializeToggleButton() {
        final FavouriteMoviesManager FMM = FavouriteMoviesManager.getInstance(getActivity());

        mToggleFavouriteButton = (Button) getView().findViewById(R.id.toggle_fav_button);
        mToggleFavouriteButton.setText(getString(FMM.hasMovie(mMovie) ? R.string.fav_button_state_marked : R.string.fav_button_state_unmarked));

        mToggleFavouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (FMM.hasMovie(mMovie))
                    FMM.removeMovie(mMovie);
                else
                    FMM.addMovie(mMovie);

                mToggleFavouriteButton.setText(getString(FMM.hasMovie(mMovie) ? R.string.fav_button_state_marked : R.string.fav_button_state_unmarked));

            }
        });
    }

}
