package com.example.medical.network;

import com.example.medical.model.ApiResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.*;


public interface ApiService {

    @Multipart
    @POST("api/prescription/upload")
    Call<ApiResponse> upload(@Part MultipartBody.Part file);
}