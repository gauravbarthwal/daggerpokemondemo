package com.example.daggerpokemondemo.di.modules;

import com.example.daggerpokemondemo.classes.MainActivity;
import com.example.daggerpokemondemo.di.scope.MainActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    private final MainActivity mainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @MainActivityScope
    public MainActivity mainActivity() {
        return mainActivity;
    }
}
