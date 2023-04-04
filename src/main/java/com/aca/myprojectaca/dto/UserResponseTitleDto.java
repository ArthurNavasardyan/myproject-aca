package com.aca.myprojectaca.dto;

public class UserResponseTitleDto {


    private String lyrics;

    private String authorName;

    public UserResponseTitleDto(String lyrics, String authorName) {
        this.lyrics = lyrics;
        this.authorName = authorName;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
