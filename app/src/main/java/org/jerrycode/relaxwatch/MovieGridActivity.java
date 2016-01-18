package org.jerrycode.relaxwatch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import org.jerrycode.relaxwatch.Models.Movie;

public class MovieGridActivity extends AppCompatActivity implements MovieGridFragment.Callbacks {

    final private String LOG_TAG = MovieGridActivity.class.getSimpleName();
    private boolean mTwoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_grid);
        if (findViewById(R.id.movie_detail_container) != null) {
            mTwoPane = true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(Movie _movie) {
        if (mTwoPane) {
            MovieDetailFragment fragment = new MovieDetailFragment();
            Bundle arguments = new Bundle();
            arguments.putParcelable(MovieDetailFragment.MOVIE_ARG_ID, _movie);
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction().replace(R.id.movie_detail_container, fragment).commit();
        } else {
            Intent i = new Intent(this, MovieDetailsActivity.class);
            i.putExtra("MOVIE", _movie);
            startActivity(i);
        }
    }
}
