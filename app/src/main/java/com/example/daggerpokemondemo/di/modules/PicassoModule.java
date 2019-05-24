package com.example.daggerpokemondemo.di.modules;

import android.content.Context;

import com.example.daggerpokemondemo.di.scope.PokemonApplicationScope;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module(includes = {ContextModule.class, NetworkModule.class})
public class PicassoModule {

    @Provides
    @PokemonApplicationScope
    public Picasso picasso(Context context, OkHttp3Downloader okHttp3Downloader ) {
        return new Picasso.Builder(context)
                .downloader(okHttp3Downloader)
                .build();
    }

    @Provides
    @PokemonApplicationScope
    public OkHttp3Downloader okHttp3Downloader(OkHttpClient okHttpClient) {
        return new OkHttp3Downloader(okHttpClient);
    }
}
