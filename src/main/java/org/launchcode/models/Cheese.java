package org.launchcode.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Jen on 4/22/2017.
 */
public class Cheese {

    //Model validation
    @NotNull
    @Size(min=3, max=15)
    private String name;

    //Model validation
    @NotNull
    @Size(min=1, message="Description must not be empty.")
    private String description;


    //first foray into enum
    //not a great approach; need to limit values
    //private String type;

    //replace w/enum
    //private CheeseType type = CheeseType.HARD;
    //looks similar to working with a static
    //replace - set cheese type through view and controller
    private CheeseType type;

    private int cheeseId;
    private static int nextId = 1;

    /*
    public Cheese() {
        this.name = null;
        this.description=null;
    }*/

    //match video
    public Cheese(String aName, String aDescription, CheeseType aType){
        this();
        name = aName;
        description = aDescription;
        type = aType;
    }
    //match video
    public Cheese(){
        cheeseId = nextId;
        nextId++;
    }

    //set and get name
    public void setName(String aName){
        name = aName;
    }
    public String getName(){
        return name;
    }

    //set and get description
    public void setDescription(String aDescription){
        description = aDescription;
    }
    public String getDescription(){
        return description;
    }

    //set and get id
    public void setCheeseId(int cheeseId){
        this.cheeseId = cheeseId;
    }
    public int getCheeseId(){return cheeseId;}

    //test equality
    //@Override use this if the name of the method were equals
    public boolean isEqual(Object obj) {
        if (obj instanceof Cheese) {
            Cheese cheeseObj = (Cheese) obj;
            if(cheeseObj.getName().equals(this.getName()) &&
                    cheeseObj.getDescription().equals(this.getDescription())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public CheeseType getType() {
        return type;
    }

    public void setType(CheeseType type) {
        this.type = type;
    }
}