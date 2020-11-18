package com.jkdx.example.homework05.annTest;

public class User {

    public void init(){
        System.out.println("-----User init--------");
    }

    private String userNameCn;

    public void setUserNameCn(String userNameCn) {
        this.userNameCn = userNameCn;
    }

    public String getUserNameCn() {
        return userNameCn;
    }
}
