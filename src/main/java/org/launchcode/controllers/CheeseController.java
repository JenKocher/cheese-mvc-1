package org.launchcode.controllers;

import org.launchcode.models.Cheese;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//Chris Bay: "It's a good thing to keep your code clean,
//so when these import statement aren't being used, they really should be deleted."
//I'm keeping it here as a reminder of what we learned.

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jen on 4/20/2017.
 */

@Controller
//This specifies a base path for every method within the controller.
//Note that no "value=" is necessary.
//Also note that within the templates directory, we made a directory called "cheese" to hold
//all the templates that correspond to the "cheese" controller.
@RequestMapping("cheese")
public class CheeseController {

    //This ArrayList was changed to a HashMap so that a description field could be added.
    //static ArrayList<String> cheeses = new ArrayList<>();
    //Declare a HashMap, Key, Value
    //private static HashMap<String, String> cheeses = new HashMap<>();
    //private String cheese;
    //private String description;

    //refactor CheeseController to use Cheese objects instead of strings
    static ArrayList<Cheese> cheeses = new ArrayList<>();


    //Request path is /cheese/
    @RequestMapping(value = "")
    //@ResponseBody would be used if the return portion of the method
    //held a string that was writing directly to the web page
    //@ResponseBody
    //Note that this is also called a "handler" method
    /*
    public String index() {

        //This is an example of the return portion of the method
        //writing directly to the web page
        //return "My Cheese";

        //When using a template for a view, return the template name (not the file name) in quotes
        //return "index";
        //When using the @RequestMapping to specificy a global path, you need to include the path here
        return "cheese/index";
    }
    The following version of the method will pass data to the template
    */
    public String index(Model model) {
        //create an empty ArrayList
        //Move this ArrayList up above, outside of this method, so it's available to
        //numerous methods.
        // ArrayList<String> cheeses = new ArrayList<>();

        //add 3 cheeses to the ArrayList
        //I commented this out because we're testing a modified template that conditionally displays
        //and error message that appears if an empty ArrayMap is passed.
        //And remove the hard-coded list because we're going to add them via the form.
        //cheeses.add("cheddar");
        //cheeses.add("parmesan");
        //cheeses.add("muenster");

        //pass the title to the view
        model.addAttribute("title", "My Cheeses");

        //pass the ArrayList to the view
        //Uh oh, but what if you don't do that? How can you conditionally display a message in the template?
        model.addAttribute("cheeses", cheeses);

        //Let's pass an ArrayList that's empty
        //Note that this syntax works if cheeses is either an ArrayList or a HashMap
        //model.addAttribute("cheeses", cheeses);

        return "cheese/index";
    }

    //It's a common pattern to have both handlers refer to the same URL,
    //with the GET method being the display and the POST method being the processing.
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    //For the processing of the form, we need to get data out of the request.
    //We can do it using the HttpServletRequest object.
    //public String processAddCheeseForm(HttpServletRequest request) {
    //The string inside the getParameter needs to match the name given to the
    //text box in the form.
    //String cheeseName = request.getParameter("cheeseName");
    //Alternatively, use the @RequestParam annotation.
    //This will hand us a string that /corresponds to the data that was posted.
    //
    //The cheeseDescription parameter was added here later
    public String processAddCheeseForm(@RequestParam String cheeseName, @RequestParam String cheeseDescription) {
        //add a cheese that you received from the form.
        //Change ArrayList to HashMap
        //This was for ArrayList of strings: cheeses.add(cheeseName);
        //This was for HashMap: cheeses.put(cheeseName, cheeseDescription);
        //Now we need to do it for ArrayList of cheese objects
        Cheese cheese = new Cheese();
        cheese.setName(cheeseName);
        cheese.setDescription(cheeseDescription);
        cheeses.add(cheese);

        //return "cheese/add";
        //Typically, you would do "redirect:view name
        //But this syntax will redirect to the path mentioned above in the
        //global @RequestMapping("cheese")
        return "redirect:";
    }

    @RequestMapping(value="remove", method=RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
    //public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("title", "Remove Cheese");
        model.addAttribute("cheeses", cheeses);
        return "cheese/remove";
    }

    @RequestMapping(value="remove", method=RequestMethod.POST)
    //This is the old way, using a String
    //public String processRemoveCheeseForm(Model model, @RequestParam String cheeseName) {
    // This is the new way, using a Cheese object.
     public String processRemoveCheeseForm(Model model, @RequestParam Cheese cheeseObject){
        //This was done using the HashMap and a string:  cheese.remove(cheeseName);
        //Now you need to iterate over the ArrayList of objects, test if each one is equal
        //to the cheese object you got back from the form, and delete it.
        //To delete an object from an ArrayList, the syntax is:


        List<Cheese> found = new ArrayList<Cheese>();
        for(Cheese cheese:cheeses) {
            if(cheese.isEqual(cheeseObject)){
                found.add(cheese);
            }
        }
        cheeses.removeAll(found);
        model.addAttribute("cheeses", cheeses);
        return "redirect:";
    }
}