package com.example.daggerpokemondemo.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Scope lets the dagger to pass same object rather than creating new one every time
 *
 * This scope needs to be assigned to all the modules and the Components
 */
@Scope
@Retention(RetentionPolicy.CLASS)
public @interface PokemonApplicationScope {
}
