package com.warrantix.main.activities.social;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.adapter.WalletBrandSocialChatMessageList;
import com.warrantix.main.adapter.WalletBrandSocialSelectContactList;
import com.warrantix.main.common.rest.model.ChatMessage;

import java.util.ArrayList;

public class WalletBrandSocialChatMessage extends BaseActivity {

    ListView list;
    ArrayList<ChatMessage> loadedMessages = null;
    WalletBrandSocialChatMessageList listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_chat_message);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (isInitialized == false) {
            Initialize();
            isInitialized = true;
        }
    }

    public void Initialize() {
        list = (ListView) findViewById(R.id.list);

        String title = getIntent().getStringExtra("title");
        if (title != null) {
            TextView titleView = (TextView) findViewById(R.id.title);
            titleView.setText(title);
        }

        Boolean bLoadMessage = getIntent().getBooleanExtra("loadMessage", false);
        if (bLoadMessage == true) {
            loadedMessages = loadMessages();
        }

        TextView name = (TextView) findViewById(R.id.name);
        name.setText("Srinivasan M.K");

        listAdapter = new WalletBrandSocialChatMessageList(this, loadedMessages);
        list.setAdapter(listAdapter);

        final EditText txtSendMessage = (EditText) findViewById(R.id.txtSendMessage);

        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });

        ImageButton btnSend = (ImageButton) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sendText = txtSendMessage.getText().toString();
                sendText = sendText.trim();
                if (sendText.equalsIgnoreCase("")==true)
                    return;

                ChatMessage newMessage = new ChatMessage();
                newMessage.id = 0;
                newMessage.isMe = true;
                newMessage.username = "Srinivasan M.K";
                newMessage.message = txtSendMessage.getText().toString();
                newMessage.dateTime = "now";

                if (loadedMessages == null)
                    loadedMessages = new ArrayList<ChatMessage>();

                loadedMessages.add(newMessage);
                listAdapter.add(newMessage);

                txtSendMessage.setText("");
                list.smoothScrollToPosition(listAdapter.getCount());
            }
        });

    }

    public ArrayList<ChatMessage> loadMessages() {
        ArrayList<ChatMessage> messages = new ArrayList<>();
        ChatMessage newMessage = new ChatMessage();
        newMessage.id = 0;
        newMessage.isMe = false;
        newMessage.username = "Srinivasan M.K";
        newMessage.message = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's dummy text ever since the 1500s";
        newMessage.dateTime = "32 minutes ago";
        messages.add(newMessage);

        ChatMessage newMessage1 = new ChatMessage();
        newMessage1.id = 1;
        newMessage1.isMe = true;
        newMessage1.username = "Srinivasan M.K";
        newMessage1.message = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's dummy text ever since the 1500s";
        newMessage1.dateTime = "22 minutes ago";
        messages.add(newMessage1);

        ChatMessage newMessage2 = new ChatMessage();
        newMessage2.id = 2;
        newMessage2.isMe = false;
        newMessage2.username = "Srinivasan M.K";
        newMessage2.message = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's dummy text ever since the 1500s";
        newMessage2.dateTime = "12 minutes ago";
        messages.add(newMessage2);
        return messages;
    }

}