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
        characterAdapter = new CharacterAdapter(data, MainActivity.this);
        rv.setAdapter(characterAdapter);

        buttonGet = findViewById(R.id.button_get);
    }

    private void loadJson(){

        CharacterService characterService = retrofit.create(CharacterService.class);
        Call<CharacterFeed> call = characterService.getData();

        call.enqueue(new Callback<CharacterFeed>() {
            @Override
            public void onResponse(Call<CharacterFeed> call, Response<CharacterFeed> response) {
                if (response.isSuccessful()){
                    CharacterFeed feed = response.body();
                    Log.i("Resultado", feed.getResults().toString());
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
