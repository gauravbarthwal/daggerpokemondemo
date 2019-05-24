package com.example.daggerpokemondemo.di;

import com.example.daggerpokemondemo.classes.MainActivity;
import com.example.daggerpokemondemo.di.modules.MainActivityModule;
import com.example.daggerpokemondemo.di.scope.MainActivityScope;

import dagger.Component;

/**
 * MainActivityComponent is interacting with PokemonApplicationComponent to get all the dependencies it needs
 * this is called cross scope
 */
@MainActivityScope
@Component(modules = MainActivityModule.class, dependencies = PokemonApplicationComponent.class)
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);
}
