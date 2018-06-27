package com.erkprog.zensofthrcrm.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Comment {
    @SerializedName("id")
    private Integer id;

    @SerializedName("text")
    private String text;

    @SerializedName("createdBy")
    private User createdBy;

    @SerializedName("created")
    private Date created;
}