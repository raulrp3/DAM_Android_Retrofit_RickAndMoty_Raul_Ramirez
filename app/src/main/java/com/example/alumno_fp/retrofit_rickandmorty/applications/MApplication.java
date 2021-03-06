package com.example.alumno_fp.retrofit_rickandmorty.applications;

import android.app.Application;

import com.example.alumno_fp.retrofit_rickandmorty.utils.Utils;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MApplication extends Application {

    private static Retrofit retrofit = null;

    @Override
    public void onCreate() {
        super.onCreate();

        if (retrofit == null){
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(Utils.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
    }

    public static Retrofit getDefaultInstance(){
        return retrofit;
    }
}
