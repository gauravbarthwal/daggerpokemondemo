package com.example.daggerpokemondemo.di.modules;

import android.content.Context;

import com.example.daggerpokemondemo.di.scope.PokemonApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * Context is the External dependency
 * to provide external dependency use constructor to pass the dependency
 */

@Module
public class ContextModule {

    private final Context fContext;

    public ContextModule(Context context) {
        fContext = context.getApplicationContext();
    }

    @Provides
    @PokemonApplicationScope
    public Context context() {
        return fContext;
    }
}
