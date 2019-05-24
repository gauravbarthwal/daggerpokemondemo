package com.example.daggerpokemondemo.classes;

import android.app.Activity;
import android.app.Application;

import com.example.daggerpokemondemo.di.DaggerPokemonApplicationComponent;
import com.example.daggerpokemondemo.di.PokemonApplicationComponent;
import com.example.daggerpokemondemo.di.modules.ContextModule;

import timber.log.Timber;

public class PokemonApplication extends Application {

    public static PokemonApplication get(Activity activity) {
        return (PokemonApplication) activity.getApplication();
    }

    //private PokemonService pokemonService;
    //private Picasso picasso;
    private PokemonApplicationComponent pokemonApplicationComponent;

    // ***********Dependency graph**********
    //
    //            Activity
    //            /       \
    //  PokemonService    Picasso
    //        |                |
    //    retrofit       OkHttpDownloader
    //     /     \       /
    //  Gson       OKHttp
    //            /      \
    //       logger      cache
    //         |          |
    //       Timber      File


    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());

        // need to include modules that have constructor arguments
        pokemonApplicationComponent = DaggerPokemonApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();

        //pokemonService = pokemonApplicationComponent.getPokemonService();
        //picasso = pokemonApplicationComponent.getPicasso();

    }

    public PokemonApplicationComponent getPokemonApplicationComponent() {
        return pokemonApplicationComponent;
    }

}
