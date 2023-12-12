package com.example.javaproject5;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Stores Information about a Veggie Lovers Pizza
 * addAllToppings for this option
 * @author Keerthana Talla
 * @author Ishani Mhatre
 */
public class VeggieLoversPizza extends Pizza {
    private static String pizzaType = "VeggieLovers"; //case of supreme speciality pizza
    private static ArrayList<Topping> topping = new ArrayList<>(Arrays.asList(Topping.JALAPENOS, Topping.GREEN_PEPPER, Topping.ONION, Topping.BLACK_OLIVE, Topping.MUSHROOM));

    /**
     * public constructor to avoid errors
     */
    public VeggieLoversPizza(){
        super();
        this.addAllTopings();
        this.setSauce("tomato");
    }

    /**
     * method to add relevant toppings for pizza type
     */
    private void addAllTopings() {
        addTopping(Topping.GREEN_PEPPER);
        addTopping(Topping.JALAPENOS);
        addTopping(Topping.MUSHROOM);
        addTopping(Topping.ONION);
        addTopping(Topping.BLACK_OLIVE);
    }

    /**
     * method to get toppings
     * @return topping for pizza
     */
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
                basePrice = 13.99;
                break;
            case MEDIUM:
                basePrice = 15.99;
                break;
            case LARGE:
                basePrice = 17.99;
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
