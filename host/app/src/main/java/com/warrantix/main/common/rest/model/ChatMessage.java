package com.warrantix.main.common.rest.model;

import com.google.gson.annotations.SerializedName;

public class ChatMessage
{
    @SerializedName("id")
    public long id;     // auto-increment id

    @SerializedName("isme")
    public boolean isMe;

    @SerializedName("username")
    public String username;

    @SerializedName("message")
    public String message;

    @SerializedName("datetime")
    public String dateTime;
}
