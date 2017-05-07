package org.launchcode.models;

import java.util.ArrayList;

/**
 * Created by Jen on 5/6/2017.
 */
public class CheeseData {

    static ArrayList<Cheese> cheeses = new ArrayList<>();

    //get all
    public static ArrayList<Cheese> getAll(){
        return cheeses;
    }

    //add
    public static void add(Cheese newCheese){
        cheeses.add(newCheese);
    }

    //remove
    public static void remove(int id){
        Cheese cheeseToRemove = getById(id);
        cheeses.remove(cheeseToRemove);
    }

    //getById
    public static Cheese getById(int id){
        //loop through list and look for desired cheese
        Cheese theCheese = null;
        for (Cheese candidateCheese : cheeses){
            if (candidateCheese.getCheeseId() == id){
                theCheese = candidateCheese;
            }
        }

        return theCheese;
    }

}
