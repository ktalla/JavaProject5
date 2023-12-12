package com.example.javaproject5;


import java.util.ArrayList;

/**
 * Singleton class to create one instance of pizza object, error handling
 * @author Keerthana Talla
 */
    public class Singleton {
    /**
     * public constructor
     */
    private static final Singleton instance = new Singleton();

        private Order order;
        private StoreOrders storeOrders;

    /**
     * getter method for pizza object
     * @return pizza object
     */

    public Pizza getPizza() {
            return pizza;
        }

    /**
     * setter method for pizza object
     * @return pizza object
     */
        public void setPizza(Pizza pizza) {
            this.pizza = pizza;
        }

        private Pizza pizza;

        private boolean orderAdded = false;

    /**
     * getter instance method
     * @return current instance of pizza
     */

        public static Singleton getInstance(){
            return instance;
        }


    /**
     * method to arrange items in order
     * @param pizzaList array list of pizzas ordered
     */
        public void initializeOrder(ArrayList<Pizza> pizzaList){
            order = new Order(pizzaList);
        }

    /**
     * getter method for current order
     * @return order
     */
        public Order getOrder() {
            return order;
        }


    /**
     * getter method for order added
     * @return order added
     */
        public boolean getOrderAdded(){
            return orderAdded;
        }


    /**
     * setter method for order added
     * @param bool true or false
     */
        public void setOrderAdded(boolean bool){
            orderAdded = bool;
        }

    /**
     * setter method for order
     * @param newOrder  order
     */
        public void setOrder(Order newOrder){
            order = newOrder;
        }

    /**
     * initialize array list with store orders
     * @param ordersList of pizza items
     */
        public void initializeStoreOrders(ArrayList <Order> ordersList){
            storeOrders = new StoreOrders(ordersList);
        }

    /**
     * getter method for store orders
     * @return store orders
     */
    public StoreOrders getStoreOrders(){
            return storeOrders;
        }

    /**
     * setter method for store orders
     * @param newStoreOrder store orders
     */
    public void setStoreOrders(StoreOrders newStoreOrder){
            storeOrders = newStoreOrder;
        }

    /**
     * getter method for current order number, prevents duplicate instances/orders
     * @return order number
     */
    public int getCurrentOrderNum(){
            if(storeOrders!=null){
                if(storeOrders.getOrders()!=null)
                    return StoreOrders.getNextOrderNumber();
                else
                    return 1;
            }
            else{
                return 1;
            }
        }

    }

