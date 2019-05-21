package com.example.alumno_fp.retrofit_rickandmorty.interfaces;

import com.example.alumno_fp.retrofit_rickandmorty.models.Character;
import com.example.alumno_fp.retrofit_rickandmorty.models.CharacterFeed;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CharacterService {

    @GET("character")
    Call<CharacterFeed> getData();
}
