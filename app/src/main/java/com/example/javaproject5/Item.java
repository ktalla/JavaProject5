package com.example.javaproject5;

import java.util.List;

/**
 * Method to add specific pizza item to list of orders
 */
public class Item {

    String specialtyPizzaName;
    List<Topping> toppings;
    int image;
    Sauce sauce;

    /**
     * Getter method for sauce
     * @return sauce type
     */
    public Sauce getSauce() {
        return sauce;
    }

    /**
     * setter method for cauce
     * @param sauce tomato or alfredo
     */
    public void setSauce(Sauce sauce) {
        this.sauce = sauce;
    }

    /**
     * getter method for pizza type
     * @return speciality pizza name
     */
    public String getSpecialtyPizzaName() {
        return specialtyPizzaName;
    }

    /**
     * setter method for pizza name
     * @param specialtyPizzaName string
     */
    public void setSpecialtyPizzaName(String specialtyPizzaName) {
        this.specialtyPizzaName = specialtyPizzaName;
    }

    /**
     * list of toppings for pizza
     * @return array list of toppings
     */
    public List<Topping> getToppings() {
        return toppings;
    }

    /**
     * setter method for toppings
     * @param toppings for pizza
     */
    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    /**
     * getter method for image
     * @return image picture
     */
    public int getImage() {
        return image;
    }

    /**
     * setter method for image
     * @param image picture
     */
    public void setImage(int image) {
        this.image = image;
    }

    /**
     * create opect for item in order
     * @param specialtyPizzaName pizza type as string
     * @param toppings toppings as array list
     * @param image picture for pizza type
     * @param sauce tomato or alfredo string
     */
    public Item(String specialtyPizzaName, List<Topping> toppings, int image, Sauce sauce) {
        this.specialtyPizzaName = specialtyPizzaName;
        this.toppings = toppings;
        this.image = image;
        this.sauce = sauce;
    }


}
