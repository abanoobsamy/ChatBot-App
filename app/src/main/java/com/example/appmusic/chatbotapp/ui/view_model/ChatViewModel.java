package com.example.appmusic.chatbotapp.ui.view_model;

import com.example.appmusic.chatbotapp.api.RetrofitChatApi;
import com.example.appmusic.chatbotapp.model.ChatModel;
import com.example.appmusic.chatbotapp.model.MsgBotModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.appmusic.chatbotapp.ui.MainActivity.CHAT_BOT;

public class ChatViewModel extends ViewModel {

    MutableLiveData<ChatModel> chatModelMutableLiveData = new MutableLiveData<>();

    public void getChatModel(String chat) {

        String url = "http://api.brainshop.ai/get?bid=160101&key=bM93HalhDEBphI0v&uid=[uid]&msg=" + chat;
        String baseUrl = "http://api.brainshop.ai";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitChatApi service = retrofit.create(RetrofitChatApi.class);

        Call<MsgBotModel> call = service.getMessage(url);
        call.enqueue(new Callback<MsgBotModel>() {
            @Override
            public void onResponse(Call<MsgBotModel> call, Response<MsgBotModel> response) {

                if (response.isSuccessful())
                {
                    MsgBotModel msgBotModel = response.body();
                    chatModelMutableLiveData.setValue(new ChatModel(msgBotModel.getCnt(), CHAT_BOT));

                    // use This
//                    chatAdapter.addChat(new ChatModel(msgBotModel.getCnt(), CHAT_BOT));
                    // Or use This
//                    chatAdapter.addChat(new ChatModel(response.body().getCnt(), CHAT_BOT));

                    //for making scroll to last message in chat.
//                    rv.smoothScrollToPosition(chatAdapter.getItemCount());
                }
            }

            @Override
            public void onFailure(Call<MsgBotModel> call, Throwable t) {

//                chatAdapter.addChat(new ChatModel("Please Revert Your Question?", CHAT_BOT));

                chatModelMutableLiveData.setValue(new ChatModel("Please Revert Your Question?", CHAT_BOT));
            }
        });
    }

    public MutableLiveData<ChatModel> getChatModelMutableLiveData() {
        return chatModelMutableLiveData;
    }
}
