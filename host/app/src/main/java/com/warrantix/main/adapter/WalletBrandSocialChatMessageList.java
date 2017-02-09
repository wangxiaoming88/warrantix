package com.warrantix.main.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.common.rest.model.ChatMessage;

import java.util.ArrayList;
import java.util.List;

public class WalletBrandSocialChatMessageList extends BaseAdapter {

    private ArrayList<ChatMessage> chatMessages = null;
    private Activity context = null;

    public WalletBrandSocialChatMessageList(Activity context, ArrayList<ChatMessage> chatMessages) {
        this.context = context;
        this.chatMessages = chatMessages;
    }

    @Override
    public int getCount() {
        if (chatMessages != null) {
            return chatMessages.size();
        }
        else {
            return 0;
        }
    }

    @Override
    public ChatMessage getItem(int position) {
        if (chatMessages != null) {
            return chatMessages.get(position);
        }
        else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        ChatMessage chatMessage = getItem(position);
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = vi.inflate(R.layout.listview_social_chat_message, null);
            holder = createViewHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        boolean myMsg = chatMessage.isMe ; //Just a dummy check to simulate whether it me or other sender
        setAlignment(holder, myMsg);
        if (chatMessage.isMe == true) {
            holder.txtMyMessage.setText(chatMessage.message);
            holder.txtMyTimeInfo.setText(chatMessage.dateTime);
        }
        else {
            holder.txtMessage.setText(chatMessage.message);
            holder.txtTimeInfo.setText(chatMessage.dateTime);
        }

        return convertView;
    }

    public void add(ChatMessage message) {
        if (chatMessages == null)
            chatMessages = new ArrayList<ChatMessage>();
        chatMessages.add(message);
        notifyDataSetChanged();
    }

    public void add(List<ChatMessage> messages) {
        chatMessages.addAll(messages);
        notifyDataSetChanged();
    }

    private void setAlignment(ViewHolder holder, boolean isMe) {
        if (isMe == true) {
            holder.contentWithBG.setVisibility(View.GONE);
            holder.txtTimeInfo.setVisibility(View.GONE);
        }
        else {
            holder.myContentWithBG.setVisibility(View.GONE);
            holder.txtMyTimeInfo.setVisibility(View.GONE);
        }
    }

    private ViewHolder createViewHolder(View v) {
        ViewHolder holder = new ViewHolder();
        holder.txtMyMessage = (TextView) v.findViewById(R.id.mytxtMessage);
        holder.txtMessage = (TextView) v.findViewById(R.id.txtMessage);
        holder.txtTimeInfo = (TextView) v.findViewById(R.id.timeInfo);
        holder.txtMyTimeInfo = (TextView) v.findViewById(R.id.myTimeInfo);
        holder.myContentWithBG = (LinearLayout) v.findViewById(R.id.myContentWithBackground);
        holder.contentWithBG = (LinearLayout) v.findViewById(R.id.contentWithBackground);
        return holder;
    }


    private static class ViewHolder {
        public TextView txtMyMessage;
        public TextView txtMessage;
        public TextView txtTimeInfo;
        public TextView txtMyTimeInfo;
        public LinearLayout contentWithBG;
        public LinearLayout myContentWithBG;
    }
}
