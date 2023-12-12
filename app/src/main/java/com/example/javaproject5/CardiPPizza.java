package com.example.javaproject5;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Stores Information about a Cardi Pizza
 * addAllToppings for this option
 * @author Keerthana Talla
 * @author Ishani Mhatre
 */
public class CardiPPizza extends Pizza{
    private static String pizzaType = "CardiP"; //case of supreme speciality pizza
    private static ArrayList<Topping> topping = new ArrayList<>(Arrays.asList(Topping.BACON, Topping.PEPPERONI, Topping.BLACK_OLIVE, Topping.MUSHROOM));

    /**
     * public constructor method
     */
    public CardiPPizza(){
        super();
        this.addAllTopings();
        this.setSauce("alfredo");
    }

    /**
     * method to add all toppings for this speciality pizza
     */
    private void addAllTopings() {
        addTopping(Topping.BACON);
        addTopping(Topping.PEPPERONI);
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
                basePrice = 10.99;
                break;
            case MEDIUM:
                basePrice = 12.99;
                break;
            case LARGE:
                basePrice = 14.99;
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
