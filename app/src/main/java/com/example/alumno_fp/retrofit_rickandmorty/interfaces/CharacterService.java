package com.example.alumno_fp.retrofit_rickandmorty.interfaces;

import com.example.alumno_fp.retrofit_rickandmorty.models.Character;
import com.example.alumno_fp.retrofit_rickandmorty.models.CharacterFeed;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CharacterService {

    @GET("character")
    Call<CharacterFeed> getData();

    @GET("character/{id}")
    Call<Character> getCharactetById(@Path("id") int id);
}
