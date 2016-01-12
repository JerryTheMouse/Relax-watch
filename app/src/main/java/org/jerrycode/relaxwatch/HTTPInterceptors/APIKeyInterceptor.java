package org.jerrycode.relaxwatch.HTTPInterceptors;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.jerrycode.relaxwatch.BuildConfig;

import java.io.IOException;

/**
 * Created by jerry on 12/18/15.
 */
public class APIKeyInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        HttpUrl newUrl = originalRequest.httpUrl().newBuilder().addQueryParameter("api_key", BuildConfig.MOVIES_API_KEY).build();
        Request newRequest = originalRequest.newBuilder().url(newUrl).build();
        return chain.proceed(newRequest);
    }
}
