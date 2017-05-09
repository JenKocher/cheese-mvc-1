package org.launchcode.models;

/**
 * Created by Jen on 5/7/2017.
 */
public class User {

    private String username;
    private String email;
    private String password;

    public User (String aUsername, String anEmail, String aPassword) {
        this();
        username = aUsername;
        email = anEmail;
        password = aPassword;
    }

    public User(){};

    public String getUsername() {
        //if (username.length()==0){
        //    return null;
        //} else return username;
        return username;
    }

    public void setUsername(String aUsername) {
        username = aUsername;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String anEmail) {
        email = anEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String aPassword) {
        password = aPassword;
    }
}
