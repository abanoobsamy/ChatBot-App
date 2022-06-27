package com.example.appmusic.chatbotapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appmusic.chatbotapp.R;
import com.example.appmusic.chatbotapp.adapter.ChatAdapter;
import com.example.appmusic.chatbotapp.api.RetrofitChatApi;
import com.example.appmusic.chatbotapp.model.ChatModel;
import com.example.appmusic.chatbotapp.model.MsgBotModel;
import com.example.appmusic.chatbotapp.ui.view_model.ChatViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    public static final String CHAT_USER = "user";
    public static final String CHAT_BOT = "bot";

    private FloatingActionButton fab;
    private EditText etChat;
    private RecyclerView rv;
    private ChatAdapter chatAdapter;

    private ChatViewModel chatViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fabSendMsg);
        etChat = findViewById(R.id.etEnterMsg);
        rv = findViewById(R.id.recyclerView);

        chatAdapter = new ChatAdapter(this);

        //If you have already records in the list before setting the adapter
        // then you have to Sort list as described below and after that set adapter to recycler view
//        Collections.reverse(chatAdapter.getChatModels());
        rv.setAdapter(chatAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        rv.setLayoutManager(layoutManager);

        chatViewModel = ViewModelProviders.of(this).get(ChatViewModel.class);

        chatViewModel.getChatModelMutableLiveData().observe(this, new Observer<ChatModel>() {
            @Override
            public void onChanged(ChatModel chatModel) {

                chatAdapter.addChat(chatModel);
                rv.smoothScrollToPosition(chatAdapter.getItemCount());
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                String chat = etChat.getText().toString();
                
                if (chat.isEmpty()) 
                {
                    Toast.makeText(getApplicationContext(), "Please Enter Your Message?", Toast.LENGTH_SHORT).show();
                    return;
                }

//                chatViewModel.getChatModel(chat);
                getResponse(chat);
                rv.smoothScrollToPosition(chatAdapter.getItemCount());
                etChat.setText("");
            }
        });
    }

    private void getResponse(String chat) {

        chatAdapter.addChat(new ChatModel(chat, CHAT_USER));
        rv.smoothScrollToPosition(chatAdapter.getItemCount());

        chatViewModel.getChatModel(chat);

//        String url = "http://api.brainshop.ai/get?bid=160101&key=bM93HalhDEBphI0v&uid=[uid]&msg=" + chat;
//        String baseUrl = "http://api.brainshop.ai";
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        RetrofitChatApi service = retrofit.create(RetrofitChatApi.class);
//
//        Call<MsgBotModel> call = service.getMessage(url);
//        call.enqueue(new Callback<MsgBotModel>() {
//            @Override
//            public void onResponse(Call<MsgBotModel> call, Response<MsgBotModel> response) {
//
//                if (response.isSuccessful())
//                {
//                    MsgBotModel msgBotModel = response.body();
//
//                    // use This
//                    chatAdapter.addChat(new ChatModel(msgBotModel.getCnt(), CHAT_BOT));
//                    // Or use This
////                    chatAdapter.addChat(new ChatModel(response.body().getCnt(), CHAT_BOT));
//
//                    //for making scroll to last message in chat.
//                    rv.smoothScrollToPosition(chatAdapter.getItemCount());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MsgBotModel> call, Throwable t) {
//
//                chatAdapter.addChat(new ChatModel("Please Revert Your Question?", CHAT_BOT));
//            }
//        });
    }
}