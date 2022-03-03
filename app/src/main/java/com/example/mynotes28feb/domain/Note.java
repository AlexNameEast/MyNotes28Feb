package com.example.mynotes28feb.domain;

import java.util.Date;

public class Note {

    private String id;

    private String title;

    private String content;

    private Date createdAt;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Note(String id, String title, String content, Date createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }


}
