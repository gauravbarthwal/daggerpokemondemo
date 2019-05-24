package com.example.daggerpokemondemo.di.modules;

import com.example.daggerpokemondemo.api.PokemonService;
import com.example.daggerpokemondemo.di.scope.PokemonApplicationScope;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * provides PokemonService dependency
 */
@Module(includes = NetworkModule.class) // includes tells PokemonServiceModule requires NetworkModule
public class PokemonServiceModule {
    @Provides
    @PokemonApplicationScope
    public PokemonService pokemonService(Retrofit retrofit) {
        return retrofit.create(PokemonService.class);
    }

    @Provides
    @PokemonApplicationScope
    public Gson gson() {
        return new GsonBuilder().create();
    }

    @Provides
    @PokemonApplicationScope
    public Retrofit retrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .baseUrl("https://pokeapi.co/")
                .build();
    }
}
