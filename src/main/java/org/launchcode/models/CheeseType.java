package org.launchcode.models;

/**
 * Created by Jen on 5/8/2017.
 */
public enum CheeseType {

    HARD ("Hard"), //you are calling constructor here
    SOFT ("Soft"),
    FAKE ("Fake");

    //final because once we initialize a CheeseType object,
    //we son;t want the value to be changed.
    private final String name;


    //Constructor should not be a public constructor;
    //it should have default access level.
    //This will allow enum to be created with string (display) name.
    CheeseType(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
