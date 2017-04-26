package com.beifeng.babymusic.http;

import com.beifeng.babymusic.App;
import com.beifeng.babymusic.http.cookie.CookieManager;
import com.beifeng.babymusic.http.interceptor.CacheInterceptor;
import com.beifeng.babymusic.http.interceptor.LoggingInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bei on 2017/4/26.
 * 初始化OkHttp和Retrofit
 */

public class HttpRetrofit {

    private static final long DEFAULT_TIME_OUT = 12;

    private final HttpApi httpApi;

    private final static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .serializeNulls()
            .create();

    HttpRetrofit() {

        File cacheDir = new File(App.getApplication().getCacheDir(), "http");
        Cache cache = new Cache(cacheDir, 10 * 1024 * 1024);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        builder.addNetworkInterceptor(new CacheInterceptor());
        builder.addInterceptor(new CacheInterceptor());
        builder.addInterceptor(new LoggingInterceptor());
        builder.cache(cache);
        builder.cookieJar(new CookieManager(App.getApplication()));
        OkHttpClient client = builder.build();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        Retrofit retrofit = retrofitBuilder.baseUrl(HttpApi.BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        httpApi = retrofit.create(HttpApi.class);
    }

    public HttpApi getHttpApi() {
        return httpApi;
    }
}
