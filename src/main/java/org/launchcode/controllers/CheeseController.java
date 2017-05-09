package org.launchcode.controllers;

import org.launchcode.models.Cheese;
import org.launchcode.models.CheeseData;
import org.launchcode.models.CheeseType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

//Chris Bay: "It's a good thing to keep your code clean,
//so when these import statement aren't being used, they really should be deleted."
//I'm keeping it here as a reminder of what we learned.

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
    //refactor again to move this to CheeseData class in models package
    //static ArrayList<Cheese> cheeses = new ArrayList<>();


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
        //model.addAttribute("cheeses", cheeses);
        //When we removed the data from the Controller, we switched to the following:
        model.addAttribute("cheeses", CheeseData.getAll());
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
        //add an empty Cheese object
        //We can use the properties of this skeleton object to properly render the form.
        //Note that no key-value syntax is used here.
        //If no "key" is given, then a "key" is implied that is the name of the Class but
        //in lower-case.
        model.addAttribute(new Cheese());
        //CheeseType.values() gets all the values of the enum, returns them as an array
        model.addAttribute("cheeseTypes", CheeseType.values());
        return "cheese/add";
    }
/**
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
        //Cheese cheese = new Cheese();
        //cheese.setName(cheeseName);
        //cheese.setDescription(cheeseDescription);
        Cheese cheese = new Cheese(cheeseName, cheeseDescription);
        //cheeses.add(cheese);
        //Use separate model
        CheeseData.add(cheese);

        //return "cheese/add";
        //Typically, you would do "redirect:view name
        //But this syntax will redirect to the path mentioned above in the
        //global @RequestMapping("cheese")
        return "redirect:";
    }

**/

//Do the processAddCheeseForm once the data management and the data model are separate
    @RequestMapping(value="add", method=RequestMethod.POST)
    //@Valid says that bound model will be checked for validity
    //based on annotations that we provided in the model class.
    //It checks and stores messages alongside the object.
    //Errors parameter is made available to us when we validate the object.
    public String processAddCheeseForm(@ModelAttribute @Valid Cheese newCheese,
                                       Errors errors, Model model){
        /**
         *
         * You are binding your model class to the RequestHandler
         *
         * Implied code.
         * SpringBoot will create a new object using the default constructor.
         * The default constructor initializes our cheeseId for us.
         * The new object doesn't have a name or a description yet because those are
         * not initialized within our default constructor.
         *
         * Note that SpringBoot doesn't know what other constructors we might have for our class.
         * You need a default constructor for all of the model classes because there are going
         * to be these situations where objects are automatically created by the
         * framework and the only way that can happen is if we provide a default
         * constructor.
         *
         * Cheese newCheese = new Cheese();
         * The code on the next line is likely not exactly correct.
         * The framework will go to the request that is coming in.
         * It will look for a parameter that corresponds to the same name as the field we are
         * trying to set, and it will call that setter.
         *
         * newCheese.setName(Request.getParameter("name"));
         *
         * It will do the same thing for the description:
         * newCheese.setDescription(Request.getParameter("description"));
         *
         * This is why it is important for the form fields to match up with the names
         * of the fields in the class.
         * If they don't match, SpringBoot will not be able to correctly initialize our objects for us.
         */
        //Check if there are any errors to validating the model during model binding
        if (errors.hasErrors()){
            //If there are errors, render the form again, just like what was done in
            //display form controller
            model.addAttribute("title", "Add Cheese");
            return "cheese/add";
        } else {
            //Framework checked to see if the data was valid.
            //Since it had no errors, the framework created the object and added it to the data store.
            CheeseData.add(newCheese);
            return "redirect:";
        }
    }


    @RequestMapping(value="remove", method=RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
    //public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("title", "Remove Cheese");
        //model.addAttribute("cheeses", cheeses);
        //use this once you've moved object into separate model package
        model.addAttribute("cheeses", CheeseData.getAll());
        return "cheese/remove";
    }
    /**  Old way, before separating out model into its own class
    @RequestMapping(value="remove", method=RequestMethod.POST)
    //This is the old way, using a String
    //public String processRemoveCheeseForm(Model model, @RequestParam String cheeseName) {
    // This is the new way, using a Cheese object.
     public String processRemoveCheeseForm(Model model, @RequestParam String selectedCheese){
        //This was done using the HashMap and a string:  cheese.remove(cheeseName);
        //Now you need to iterate over the ArrayList of objects, test if each one is equal
        //to the cheese object you got back from the form, and delete it.
        //To delete an object from an ArrayList, the syntax is:


        List<Cheese> found = new ArrayList<Cheese>();
        for(Cheese cheese:cheeses) {
            if((cheese.getName() + cheese.getDescription()).equals(selectedCheese)){
                found.add(cheese);
            }
        }
        cheeses.removeAll(found);
        model.addAttribute("cheeses", cheeses);
        return "redirect:";
     **/

    /**
     * This was how class leader did it before model was removed to its own class.
     *
     * @RequestMapping(value="remove", method=RequestMethod.POST)
     * public String processRemoveCheeseForm(@RequestParam ArrayList<String> cheese){
     *     for (String acheese : cheese){
     *         cheeses.remove(acheese);
     *     }
     *     return("redirect:");
     * }
     *
     */

    //Now we change it to how it needs to be with data removed to model
    @RequestMapping(value="remove", method=RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds){
        for (int cheeseId : cheeseIds) {
            CheeseData.remove(cheeseId);
        }
        return("redirect:");
    }

    @RequestMapping(value="edit/{cheeseId}", method=RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int cheeseId){
        model.addAttribute("cheese", CheeseData.getById(cheeseId));
        return("cheese/edit");
    }

    @RequestMapping(value="edit/{cheeseId}", method=RequestMethod.POST)
    //public String processEditForm(@RequestParam int cheeseId, @RequestParam String name, @RequestParam String description){
    public String processEditForm(Model model, @RequestParam int cheeseId, @RequestParam String name, @RequestParam String description){
        //query CheeseData for the cheese with the given Id
        //update its name and description
        CheeseData.getById(cheeseId).setName(name);
        CheeseData.getById(cheeseId).setDescription(description);
        //redirect the user to the home page
        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "My Cheeses");
        return("cheese/index");
        //return("redirect:");
    }
}