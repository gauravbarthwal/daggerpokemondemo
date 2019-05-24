package com.example.daggerpokemondemo.di.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.daggerpokemondemo.di.scope.PokemonApplicationScope;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

@Module(includes = ContextModule.class) // includes tells NetworkModule requires ContextModule
public class NetworkModule {

    @Provides
    @PokemonApplicationScope
    public HttpLoggingInterceptor loggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor =  new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NonNull String message) {
                Timber.i(message);
            }
        });

        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        return httpLoggingInterceptor;
    }

    @Provides
    @PokemonApplicationScope
    public Cache cache(File cacheFile) {
        return new Cache(cacheFile, 10 * 1024 * 1024);
    }

    @Provides
    @PokemonApplicationScope
    public File cacheFile(Context context) {
        return new File(context.getCacheDir(), "cache");
    }

    @Provides
    @PokemonApplicationScope
    public OkHttpClient okHttpClient(HttpLoggingInterceptor loggingInterceptor, Cache cache) {
        return new OkHttpClient()
                .newBuilder()
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .build();
    }
}
