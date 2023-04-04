package com.aca.myprojectaca.dto;



public class UserResponseAuthorDto {


    private String title;
    private String lyrics;

    public UserResponseAuthorDto(String title, String lyrics) {
        this.title = title;
        this.lyrics = lyrics;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }
//    public List<String> getTitle() {
//        return title;
//    }
//
//    public void setTitle(List<String> title) {
//        this.title = title;
//    }
//
//    public List<String> getLyrics() {
//        return lyrics;
//    }
//
//    public void setLyrics(List<String> lyrics) {
//        this.lyrics = lyrics;
//    }


}
