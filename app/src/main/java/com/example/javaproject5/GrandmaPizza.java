package com.example.javaproject5;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Stores Information about a Grandma Pizza
 * addAllToppings for this option
 * @author Keerthana Talla
 * @author Ishani Mhatre
 */
public class GrandmaPizza extends Pizza{
    private static String pizzaType = "Grandma"; //case of supreme speciality pizza
    private static ArrayList<Topping> topping = new ArrayList<>(Arrays.asList(Topping.CRAB_MEAT, Topping.PEPPERONI, Topping.JALAPENOS, Topping.GREEN_PEPPER, Topping.SHRIMP, Topping.BLACK_OLIVE, Topping.MUSHROOM));

    /**
     * public constructor to avoid errors
     */
    public GrandmaPizza(){
        super();
        this.addAllTopings();
        this.setSauce("alfredo");

    }

    /**
     * method to add all relevant toppings for this pizza type
     */
    private void addAllTopings() {
        addTopping(Topping.CRAB_MEAT);
        addTopping(Topping.PEPPERONI);
        addTopping(Topping.JALAPENOS);
        addTopping(Topping.GREEN_PEPPER);
        addTopping(Topping.SHRIMP);
        addTopping(Topping.BLACK_OLIVE);
        addTopping(Topping.MUSHROOM);
    }
    public static ArrayList<Topping> getStandardToppings(){
        return topping;
    }

    /**
     * Returns the price depending on the pizza size
     * @return 15.99 if Pizza is small, 17.99 if medium, 19.99 if large
     */
    @Override
    public double price() {
        double basePrice=0;

        switch (getSize()) {
            case SMALL:
                basePrice = 15.99;
                break;
            case MEDIUM:
                basePrice = 17.99;
                break;
            case LARGE:
                basePrice = 19.99;
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


