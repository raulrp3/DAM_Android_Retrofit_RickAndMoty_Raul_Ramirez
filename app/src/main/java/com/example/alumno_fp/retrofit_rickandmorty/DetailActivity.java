package com.example.alumno_fp.retrofit_rickandmorty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.alumno_fp.retrofit_rickandmorty.models.Character;

public class DetailActivity extends AppCompatActivity {

    private ImageView imageDetail;
    private TextView tvName, tvStatus, tvSpecie, tvGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initUI();

        initData();
    }

    private void initUI(){
        imageDetail = findViewById(R.id.img_detail);
        tvName = findViewById(R.id.name_detail);
        tvStatus = findViewById(R.id.status_detail);
        tvSpecie = findViewById(R.id.specie_detail);
        tvGender = findViewById(R.id.gender_detail);
    }

    private void initData(){
        Intent intent = getIntent();
        Character character = new Character();
        character = character.fromJson(intent.getStringExtra("Character"));

        tvName.setText(character.getName());
        tvStatus.setText(character.getStatus());
        tvSpecie.setText(character.getSpecies());
        tvGender.setText(character.getGender());

        Glide.with(DetailActivity.this)
                .load(character.getImage())
                .into(imageDetail);
    }
}
