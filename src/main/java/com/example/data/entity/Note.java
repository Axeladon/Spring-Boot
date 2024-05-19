package com.example.data.entity;


import jakarta.validation.constraints.NotBlank;

public class Note {
    private Long id;
    @NotBlank(message = "title cannot be empty")
    private String title;
    @NotBlank(message = "content cannot be empty")
    private String content;

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
