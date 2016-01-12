package org.jerrycode.relaxwatch;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import org.jerrycode.relaxwatch.Adapters.MovieAdapter;
import org.jerrycode.relaxwatch.Events.PreferenceChangedEvent;
import org.jerrycode.relaxwatch.Models.Movie;
import org.jerrycode.relaxwatch.Models.MovieAPIResponse;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static final String LOG_TAG = MainActivityFragment.class.getSimpleName();
    private Context _context;
    private MovieAdapter _mAdapter;
    private GridView _mGrid;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _context = getActivity();
        RelaxAndWatchApplication.getInstance().getBus().register(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RelaxAndWatchApplication.getInstance().getBus().unregister(this);

    }

    @Subscribe
    public void presencesChanged(PreferenceChangedEvent event) {
        loadMovies(event.getSortPreference());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _mGrid = (GridView) getView().findViewById(R.id.images_grid);
        _mGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(_context, DetailsActivity.class);
                i.putExtra("MOVIE", _mAdapter.getItem(position));

                startActivity(i);
            }
        });
        _mAdapter = new MovieAdapter(_context, android.R.layout.simple_list_item_1);
        _mGrid.setAdapter(_mAdapter);


        loadMovies(null);
    }

    private void loadMovies(@Nullable String sortBy) {
        // Getting SortBy query
        if (sortBy == null)
            sortBy = PreferenceManager.getDefaultSharedPreferences(_context).getString(getString(R.string.pref_sort_key), getString(R.string.pref_sort_default));
        RelaxAndWatchApplication.getInstance().getMoviesAPIService().listMovies(sortBy).enqueue(new Callback<MovieAPIResponse>() {
            @Override
            public void onResponse(Response<MovieAPIResponse> response, Retrofit retrofit) {
                _mAdapter.clear();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    _mAdapter.addAll(response.body().getResults());
                } else {
                    for (Movie m : response.body().getResults()) {
                        _mAdapter.add(m);
                    }
                }
                _mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();

                Toast.makeText(_context, "Can't Connect to the webserver", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
