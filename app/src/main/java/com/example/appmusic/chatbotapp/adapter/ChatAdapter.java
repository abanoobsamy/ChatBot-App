package com.example.appmusic.chatbotapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appmusic.chatbotapp.ui.MainActivity;
import com.example.appmusic.chatbotapp.R;
import com.example.appmusic.chatbotapp.model.ChatModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChatAdapter extends RecyclerView.Adapter {

    public ArrayList<ChatModel> chatModels = new ArrayList<>();

    private Context context;

    public ChatAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<ChatModel> getChatModels() {
        return chatModels;
    }

    public void setChatModels(ArrayList<ChatModel> chatModels) {
        this.chatModels = chatModels;
    }

    public void addChat(ChatModel chatModel) {
        chatModels.add(chatModel);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;

        switch (viewType) {

            case 0:
                view = LayoutInflater.from(context).inflate(R.layout.chatuser_item, parent, false);
                return new UserViewHolder(view);

            case 1:
                view = LayoutInflater.from(context).inflate(R.layout.chatbot_item, parent, false);
                return new BotViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ChatModel chatModel = chatModels.get(position);

        switch (chatModel.getSender()) {

            case MainActivity.CHAT_USER:

                ((UserViewHolder)holder).tvUser.setText(chatModel.getMessage());
                break;

            case MainActivity.CHAT_BOT:

                ((BotViewHolder)holder).tvBot.setText(chatModel.getMessage());
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {

        switch (chatModels.get(position).getSender()) {

            case MainActivity.CHAT_USER:
                return 0;

            case MainActivity.CHAT_BOT:
                return 1;

            default:
                return -1;
        }
    }

    @Override
    public int getItemCount() {

        return chatModels.size();
    }

    // For User Chat
    public class UserViewHolder extends RecyclerView.ViewHolder {

        TextView tvUser;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUser = itemView.findViewById(R.id.tvChatUser);
        }
    }

    // For Bot Chat
    public class BotViewHolder extends RecyclerView.ViewHolder {

        TextView tvBot;

        public BotViewHolder(@NonNull View itemView) {
            super(itemView);

            tvBot = itemView.findViewById(R.id.tvChatBot);
        }
    }
}
