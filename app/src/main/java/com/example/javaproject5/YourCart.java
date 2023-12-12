package com.example.javaproject5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
/**
 * Class that holds all the pizzas of a current order
 * @author Ishani Mhatre
 * @author Keerthana Talla
 */
public class YourCart extends AppCompatActivity {

    TextView orderNumber;
    TextView subTotal;
    TextView salesTax;
    TextView orderTotal;

    private int orderNum; //number integer of current order
    private ArrayList<Pizza> pizzas; //array list for all pizzas in current order
    private static final double TAXRATE = 0.06625; //tax rate for every order

    private static final DecimalFormat df = new DecimalFormat( "#.00" ); //formatting code
    private Singleton singleton = Singleton.getInstance();
    private Order currOrder = singleton.getOrder();
    private ListView listView;
    private ArrayList<String> pizzaDetails = new ArrayList<String>();
    private ArrayAdapter<String> arrayAdapter;
    private Button placeOrder;
    private Button removePizza;
    private Button clearOrder;


    /**
     * Method to control all activity on page
     * @param savedInstanceState based on user input
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.your_cart);
        orderNumber = findViewById(R.id.orderNumberText);
        subTotal = findViewById(R.id.subTotalPriceText);
        salesTax = findViewById(R.id.salesTaxPriceText);
        orderTotal = findViewById(R.id.orderTotalPriceText);
        listView = findViewById(R.id.orderListView);
        placeOrder = findViewById(R.id.placeOrderButton);
        removePizza = findViewById(R.id.removeButton);
        clearOrder = findViewById(R.id.clearButton);
        if(currOrder!=null) {
            subTotal.setText(df.format(currOrder.getSubtotal()));
            salesTax.setText(df.format(currOrder.getTax()));
            orderTotal.setText(df.format(currOrder.getOrderTotal()));
            for(Pizza pizza: currOrder.getPizzas()){
                String details = this.getPizzaType(pizza) + " " + pizza.getSize() + ", ";
                details+= pizza.getSauce().toString() + ", Toppings: ";
                for(Topping tp: pizza.getToppings()){
                    details+= tp.toString() + ", ";
                }
                details+= "$" + pizza.price();
                pizzaDetails.add(details);
            }
            arrayAdapter = new ArrayAdapter<String>(YourCart.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, pizzaDetails);
            listView.setAdapter(arrayAdapter);
            listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            orderNumber.setText(singleton.getCurrentOrderNum()+"");

            setUpPlaceOrderListener();
            setUpRemoveOrderListener();
            setUpClearOrderListener();
        }
        else{
            subTotal.setText("0.00");
            salesTax.setText("0.00");
            orderTotal.setText("0.00");
        }

    }

    /**
     * returns correct instance of pizza type
     * @param p pizza object
     * @return pizza type
     */
    private String getPizzaType(Pizza p) {
        if (p instanceof DeluxePizza)
            return ((DeluxePizza) p).getPizzaType();
        if (p instanceof SupremePizza)
            return ((SupremePizza) p).getPizzaType();
        if (p instanceof PepperoniPizza)
            return ((PepperoniPizza) p).getPizzaType();
        if (p instanceof MeatzzaPizza)
            return ((MeatzzaPizza) p).getPizzaType();
        if (p instanceof SeafoodPizza)
            return ((SeafoodPizza) p).getPizzaType();
        if (p instanceof BuildYourOwnPizza)
            return ((BuildYourOwnPizza) p).getPizzaType();
        if(p instanceof LoverboyPizza)
            return ((LoverboyPizza) p).getPizzaType();
        if(p instanceof GrandmaPizza)
            return ((GrandmaPizza) p).getPizzaType();
        if(p instanceof BaddiePizza)
            return ((BaddiePizza) p).getPizzaType();
        if(p instanceof VeggieLoversPizza)
            return ((VeggieLoversPizza) p).getPizzaType();
        if(p instanceof CardiPPizza)
            return ((CardiPPizza) p).getPizzaType();
        return "";
    }

    /**
     * updates details of order with subtotal, tax, and order total
     */
    private void updateDetails() {
        orderNumber.setText(singleton.getCurrentOrderNum()+"");
        subTotal.setText(df.format(currOrder.getSubtotal()));
        salesTax.setText(df.format(currOrder.getTax()));
        orderTotal.setText(df.format(currOrder.getOrderTotal()));
    }

    /**
     * Method to clear pizza details and singleton instance based on user input
     */
    private void setUpClearOrderListener(){
        clearOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pizzaDetails.clear();
                arrayAdapter = new ArrayAdapter<String>(YourCart.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, pizzaDetails);
                listView.setAdapter(arrayAdapter);
                currOrder.clearOrder();
                singleton.setOrder(currOrder);
                subTotal.setText("0.00");
                salesTax.setText("0.00");
                orderTotal.setText("0.00");

            }
        });
    }

    /**
     * Method to remove pizza from order based on user input
     */
    private void setUpRemoveOrderListener(){
        removePizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = listView.getCheckedItemPosition();
                if (currOrder == null || currOrder.getPizzas().size() == 0)
                    return;
                if(index<0)
                    return;
                pizzaDetails.remove(index);
                arrayAdapter = new ArrayAdapter<String>(YourCart.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, pizzaDetails);
                listView.setAdapter(arrayAdapter);
                listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
                currOrder.removePizza(currOrder.getPizzas().get(index));
                Log.d("currOrder", ""+currOrder);
                singleton.setOrder(currOrder);
                updateDetails();
            }
        });
    }

    /**
     * Method to get and place store order based on user input
     */
    private void setUpPlaceOrderListener(){
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(singleton.getStoreOrders()!=null){
                    singleton.getStoreOrders().placeOrder(currOrder);
                }
                else{
                    singleton.initializeStoreOrders(new ArrayList<Order>());
                    singleton.getStoreOrders().placeOrder(currOrder);
                }
                singleton.setOrder(null);
                pizzaDetails.clear();
                arrayAdapter = new ArrayAdapter<String>(YourCart.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, pizzaDetails);
                listView.setAdapter(arrayAdapter);
                listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
                subTotal.setText("0.00");
                salesTax.setText("0.00");
                orderTotal.setText("0.00");
            }
        });
    }

}
