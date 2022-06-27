package com.example.appmusic.chatbotapp.api;

import com.example.appmusic.chatbotapp.model.MsgBotModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RetrofitChatApi {

    @GET
    Call<MsgBotModel> getMessage(@Url String url);
}
