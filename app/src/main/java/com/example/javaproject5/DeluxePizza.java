package com.example.javaproject5;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Stores Information about Deluxe Pizza option
 * add all toppings for Deluxe pizza
 * @author Ishani Mhatre
 * @author Keerthana Talla
 */
public class DeluxePizza extends Pizza {

    private static String pizzaType = "Deluxe"; //case of deluxe speciality pizza
    private static ArrayList<Topping> topping = new ArrayList<>(Arrays.asList(Topping.SAUSAGE, Topping.PEPPERONI, Topping.GREEN_PEPPER, Topping.ONION, Topping.MUSHROOM));

    /**
     * Constructor for Deluxe Pizza Object
     */
    public DeluxePizza() {
        super();
        this.addAllTopings();
        this.setSauce("tomato");
    }


    public static ArrayList<Topping> getStandardToppings(){
        return topping;
    }

    /**
     * Adds all the toppings to the pizza
     */
    private void addAllTopings() {
        addTopping(Topping.SAUSAGE);
        addTopping(Topping.PEPPERONI);
        addTopping(Topping.GREEN_PEPPER);
        addTopping(Topping.ONION);
        addTopping(Topping.MUSHROOM);
    }

    /**
     * Returns the price depending on the pizza size
     * @return 14.99 if Pizza is small, 16.99 if medium, 18.99 if large
     */
    @Override
    public double price() {
        double basePrice=0;

        switch (getSize()) {
            case SMALL:
                basePrice = 14.99;
                break;
            case MEDIUM:
                basePrice = 16.99;
                break;
            case LARGE:
                basePrice = 18.99;
                break;
        }
        return basePrice + hasExtraCheeseSauce();
    }

    /**
     * Gets pizza type of this object
     * @return pizza type as a string
     */
    public String getPizzaType() {
        return pizzaType;
    }

    /**
     * boolean function for abstract method located in Pizza class
     * @param topping
     * @return false
     */
    public boolean add(Topping topping){
        return false;
    }

    /**
     * boolean function for abstract method located in Pizza class
     * @param topping
     * @return false
     */
    public boolean remove(Topping topping) {
        return false;
    }


}
