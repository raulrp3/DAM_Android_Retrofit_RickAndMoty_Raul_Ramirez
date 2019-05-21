package com.example.alumno_fp.retrofit_rickandmorty.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.alumno_fp.retrofit_rickandmorty.R;
import com.example.alumno_fp.retrofit_rickandmorty.models.Character;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    public static class CharacterViewHolder extends RecyclerView.ViewHolder{

        ImageView imgCharacter;
        TextView tvName, tvStatus, tvSpecie, tvGender;

        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCharacter = itemView.findViewById(R.id.image_character);
            tvName = itemView.findViewById(R.id.textview_name_character);
            tvStatus = itemView.findViewById(R.id.textview_status_character);
            tvSpecie = itemView.findViewById(R.id.textview_specie_character);
            tvGender = itemView.findViewById(R.id.textview_gender_character);
        }
    }

    List<Character> characters;
    Context context;

    public CharacterAdapter(List<Character> characters, Context context){
        this.characters = characters;
        this.context = context;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_character, viewGroup, false);
        final CharacterViewHolder chv = new CharacterViewHolder(v);

        return chv;
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder characterViewHolder, int i) {
        Glide.with(context)
                .load(characters.get(i).getImage())
                .into(characterViewHolder.imgCharacter);

        characterViewHolder.tvName.setText(characters.get(i).getName());
        characterViewHolder.tvStatus.setText(characters.get(i).getStatus());
        characterViewHolder.tvSpecie.setText(characters.get(i).getSpecies());
        characterViewHolder.tvGender.setText(characters.get(i).getGender());
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }
}
