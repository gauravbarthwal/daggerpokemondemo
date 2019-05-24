package com.example.daggerpokemondemo.adapter;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.daggerpokemondemo.R;
import com.example.daggerpokemondemo.classes.MainActivity;
import com.example.daggerpokemondemo.holder.PokemonViewHolder;
import com.example.daggerpokemondemo.model.PokemonResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class PokemonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PokemonResponse.Pokemon> pokemonList = new ArrayList<>();
    private Picasso picasso;
    private Resources resources;

    @Inject
    public PokemonAdapter(Picasso picasso, MainActivity context) {
        this.picasso = picasso;
        this.resources = context.getResources();
    }

    public void setData(List<PokemonResponse.Pokemon> pokemonList) {
        this.pokemonList.clear();
        this.pokemonList.addAll(pokemonList);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_single_item, parent, false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PokemonResponse.Pokemon pokemon = pokemonList.get(position);
        ((PokemonViewHolder) holder).pokemonName.setText(pokemon.getName());
        picasso.load(resources.getString(R.string.image_link_new, pokemon.getNumber()))
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(((PokemonViewHolder) holder).pokemonImage);
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }
}
