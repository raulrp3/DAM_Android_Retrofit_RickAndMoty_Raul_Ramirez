package com.example.alumno_fp.retrofit_rickandmorty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.alumno_fp.retrofit_rickandmorty.adapters.CharacterAdapter;
import com.example.alumno_fp.retrofit_rickandmorty.applications.MApplication;
import com.example.alumno_fp.retrofit_rickandmorty.interfaces.CharacterService;
import com.example.alumno_fp.retrofit_rickandmorty.interfaces.CustomClick;
import com.example.alumno_fp.retrofit_rickandmorty.models.Character;
import com.example.alumno_fp.retrofit_rickandmorty.models.CharacterFeed;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private List<Character> data;
    private CharacterAdapter characterAdapter;
    private RecyclerView rv;
    private Button buttonGet;
    private Retrofit retrofit;
    private CharacterService characterService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = MApplication.getDefaultInstance();

        initUI();

        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadJson();
            }
        });
    }

    private void initUI(){
        rv = findViewById(R.id.recyclerview_characters);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        data = new ArrayList<>();
        characterAdapter = new CharacterAdapter(data, MainActivity.this, new CustomClick() {
            @Override
            public void onClick(View view, int index) {
                int id = data.get(index).getId();
                Call<Character> call = characterService.getCharactetById(id);

                call.enqueue(new Callback<Character>() {
                    @Override
                    public void onResponse(Call<Character> call, Response<Character> response) {
                        if (response.isSuccessful()){
                            Character character = response.body();
                            String characterJson = character.toJson();
                            Log.i("Resultado", characterJson);
                        }
                    }

                    @Override
                    public void onFailure(Call<Character> call, Throwable t) {

                    }
                });
            }
        });
        rv.setAdapter(characterAdapter);
        characterService = retrofit.create(CharacterService.class);

        buttonGet = findViewById(R.id.button_get);
    }

    private void loadJson(){

        Call<CharacterFeed> call = characterService.getData();

        call.enqueue(new Callback<CharacterFeed>() {
            @Override
            public void onResponse(Call<CharacterFeed> call, Response<CharacterFeed> response) {
                if (response.isSuccessful()){
                    CharacterFeed feed = response.body();
                    data.addAll(feed.getResults());
                    characterAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<CharacterFeed> call, Throwable t) {
                Toast.makeText(MainActivity.this, "¡ERROR! " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
