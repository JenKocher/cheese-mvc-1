package org.launchcode.models;

/**
 * Created by Jen on 4/22/2017.
 */
public class Cheese {

    private String name;
    private String description;

    public Cheese() {
        this.name = null;
        this.description=null;
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


}
