package com.aca.myprojectaca.dto;

import java.util.List;

public class JwtResponce {

    private String accessToken;

    private MessageResponce messageResponce;

   public MessageResponce getMessageResponce() {return messageResponce;}

    public void setMessageResponce(MessageResponce messageResponce) {this.messageResponce = messageResponce;}

    public String getAccessToken() {return accessToken;}

    public void setAccessToken(String accessToken) {this.accessToken = accessToken;}

}
