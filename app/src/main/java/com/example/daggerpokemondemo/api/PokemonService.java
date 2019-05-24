package com.example.daggerpokemondemo.api;

import com.example.daggerpokemondemo.model.PokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokemonService {

    //API Method to get Pokemon
    @GET("/api/v2/pokemon/")
    Call<PokemonResponse> getPokemon(@Query("limit") int limit);
}