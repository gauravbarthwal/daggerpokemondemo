package com.example.daggerpokemondemo.classes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.daggerpokemondemo.R;
import com.example.daggerpokemondemo.adapter.PokemonAdapter;
import com.example.daggerpokemondemo.api.PokemonService;
import com.example.daggerpokemondemo.di.DaggerMainActivityComponent;
import com.example.daggerpokemondemo.di.MainActivityComponent;
import com.example.daggerpokemondemo.di.modules.MainActivityModule;
import com.example.daggerpokemondemo.model.PokemonResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    // Dagger can't inject private fields
    @Inject
    PokemonAdapter pokemonAdapter;
    private List<PokemonResponse.Pokemon> pokemonList = new ArrayList<>();
    private ProgressBar mProgressBar;
    @Inject
    PokemonService pokemonService;
    private Call<PokemonResponse> call;
    private PokemonApplication pokemonApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.app_name));
        }
        RecyclerView mRecyclerView = findViewById(R.id.main_activity_recyclerview);
        mProgressBar = findViewById(R.id.main_activity_progressbar);
        pokemonApplication = PokemonApplication.get(this);

        MainActivityComponent mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(this))
                .pokemonApplicationComponent(PokemonApplication.get(this).getPokemonApplicationComponent())
                .build();

        mainActivityComponent.inject(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(pokemonAdapter);
        loadPokemon();
    }

    private void loadPokemon() {
        call = pokemonService.getPokemon(949);
        mProgressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(@NonNull Call<PokemonResponse> call, @NonNull Response<PokemonResponse> response) {
                mProgressBar.setVisibility(View.GONE);
                pokemonList.clear();
                pokemonList.addAll(response.body().getResults());
                pokemonAdapter.setData(pokemonList);
                pokemonAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(@NonNull Call<PokemonResponse> call, @NonNull Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (call != null) {
            call.cancel();
        }
    }
}
