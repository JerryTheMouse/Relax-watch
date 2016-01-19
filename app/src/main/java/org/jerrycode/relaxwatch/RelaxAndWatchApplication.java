package org.jerrycode.relaxwatch;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.otto.Bus;

import org.jerrycode.relaxwatch.HTTPInterceptors.APIKeyInterceptor;
import org.jerrycode.relaxwatch.Services.MovieAPIService;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by jerry on 12/18/15.
 */
public class RelaxAndWatchApplication extends android.app.Application {
    private static RelaxAndWatchApplication _app;
    private Retrofit _retrofit;
    private MovieAPIService _apiService;
    private OkHttpClient _client;
    private Bus bus = new Bus();

    public static RelaxAndWatchApplication getInstance() {
        return _app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        _app = this;
    }




    public MovieAPIService getMoviesAPIService() {
        if (_client == null) {
            _client = new OkHttpClient();
            _client.interceptors().add(new APIKeyInterceptor());
            _retrofit = new Retrofit.Builder().client(_client).addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(getString(R.string.movies_api_base_url)).build();


            _apiService = _retrofit.create(MovieAPIService.class);


        }
        return _apiService;
    }

    public Bus getBus() {
        return bus;
    }
}
