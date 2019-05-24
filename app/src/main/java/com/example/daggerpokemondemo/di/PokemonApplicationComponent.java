package com.example.daggerpokemondemo.di;

import com.example.daggerpokemondemo.api.PokemonService;
import com.example.daggerpokemondemo.di.modules.PicassoModule;
import com.example.daggerpokemondemo.di.modules.PokemonServiceModule;
import com.example.daggerpokemondemo.di.scope.PokemonApplicationScope;
import com.squareup.picasso.Picasso;

import dagger.Component;

/**
 * interface to bridge the gap between dependent and dependency
 *
 * Public interface to your dependency graph
 *
 */

@PokemonApplicationScope
//we are telling component to generate Picasso and PokemonService it needs these modules
@Component(modules = {PokemonServiceModule.class, PicassoModule.class})
public interface PokemonApplicationComponent {

    Picasso getPicasso();
    PokemonService getPokemonService();
}
