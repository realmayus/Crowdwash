package com.mayus.crowdwash_android;

import java.io.IOException;

public class CrowdwashUser {
    String Name = "";
    String SessionID = "";
    boolean login = false;

    public CrowdwashUser(String name, String session) {
        System.out.println("CrowdwashUser");
        this.Name = name;
        this.SessionID = session;
        login =  true;
    }
    public CrowdwashUser() {
        login = false;
    }

    public String getUserName() {
            return Name;

    }

    public String getSessionID() {
        return SessionID;
    }

    public boolean isLogin() {

        return login;
    }
}