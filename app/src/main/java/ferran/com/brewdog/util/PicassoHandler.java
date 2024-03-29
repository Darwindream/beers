package ferran.com.brewdog.util;

import android.content.Context;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class PicassoHandler {
    private Picasso picasso;
    private static PicassoHandler instance;
    private OkHttpClient client;


    private PicassoHandler(Context context) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();
        picasso = new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(client))
                .build();
    }

    public static PicassoHandler getInstance(Context context) {
        if (instance == null)
            instance = new PicassoHandler(context);
        return instance;
    }

    public Picasso getPicasso() {
        return picasso;
    }
}
