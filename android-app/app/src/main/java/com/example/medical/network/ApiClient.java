package com.example.medical.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static Retrofit getClient() {
        return new Retrofit.Builder()
                .baseUrl("http://localhost:8080/") // 🔴 CHANGE THIS
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}