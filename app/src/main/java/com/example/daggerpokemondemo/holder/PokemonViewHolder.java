package com.example.daggerpokemondemo.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daggerpokemondemo.R;

public class PokemonViewHolder extends RecyclerView.ViewHolder {

    public ImageView pokemonImage;
    public TextView pokemonName;

    public PokemonViewHolder(View itemView) {
        super(itemView);
        pokemonImage = itemView.findViewById(R.id.pokemon_image);
        pokemonName = itemView.findViewById(R.id.pokemon_name);
    }
}
