package com.example.javaproject5;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Build Your Own Activity allows for users to customize pizzas and add to their order
 * @author : Ishani Mhatre
 * @author : Keerthana Talla
 */
public class BuildYourOwn extends AppCompatActivity {
    private RadioGroup sizeGroup; 
    private Pizza selectedPizza;
    private RadioGroup sauceGroup;
    private CheckBox extraSauce;
    private CheckBox extraCheese;
    private Button placeOrder;
    private TextView price;
    private CheckBox sausage;
    private CheckBox pepperoni;
    private CheckBox greenpepper;
    private CheckBox onion;
    private CheckBox mushroom;
    private CheckBox ham;
    private CheckBox olives;
    private CheckBox beef;
    private CheckBox shrimp;
    private CheckBox crabmeat;
    private CheckBox jalapeneos;
    private CheckBox squid;
    private CheckBox bacon;
    private Singleton singleton = Singleton.getInstance();

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})

    /**
     * Creates new pizza object of type build your own. Sets initial price to 0
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.build_your_own);
        PizzaMaker pizzaMaker = new PizzaMaker();
        selectedPizza = pizzaMaker.createPizza("buildyourown");
        price = findViewById(R.id.price_byo);
        price.setText("0.00");

        sizeGroup = findViewById(R.id.sizeGroup);
        sizeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            /**
             * Sets the size based on user input 
             * @param group radiogroup for size
             * @param checkedId checkbox selected
             */
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Find the selected radio button
                RadioButton selectedSize = group.findViewById(checkedId);
                String selectedText = selectedSize.getText().toString();
                selectedPizza.setSize(selectedText);
                price.setText(selectedPizza.price()+"");
            }
        });
        sauceGroup = findViewById(R.id.sauceGroup);
        sauceGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            /**
             * Sets the size based on user input 
             * @param group  radiogroup for sauce
             * @param checkedId checkbox selected
             */
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Find the selected radio button
                RadioButton selectedSauce = group.findViewById(checkedId);
                String selectedText = selectedSauce.getText().toString();
                selectedPizza.setSauce(selectedText);
                //Log.d("Sauce set: ", selectedPizza.getSauce().toString());
                price.setText(selectedPizza.price()+"");
            }
        });

        
        extraSauce = findViewById(R.id.extraSauce);
        extraSauce.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /**
             * Checks whether or not extra sauce option is selected from user input
             * @param compoundButton button group for extra sauce 
             * @param b true or false 
             */
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    selectedPizza.setExtraSauce(true);
                    price.setText(selectedPizza.price()+"");
                }
                else{
                    selectedPizza.setExtraSauce(false);
                    price.setText(selectedPizza.price()+"");
                }
            }
        });

        extraCheese = findViewById(R.id.extraCheese);
        extraCheese.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /**
             * Checks whether or not extra cheese option is selected from user input
             * @param compoundButton button group for extra cheese 
             * @param b true or false 
             */
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    selectedPizza.setExtraCheese(true);
                    price.setText(selectedPizza.price()+"");
                }
                else{
                    selectedPizza.setExtraCheese(false);
                    price.setText(selectedPizza.price()+"");
                }
            }
        });

        //Pizza toppings
        sausage = findViewById(R.id.sausage);
        sausage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /**
             * Checks whether or not sausage option is selected and adds to Topping array 
             * @param compoundButton button group for toppings checkbox 
             * @param b true if selected, false if not 
             */
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(selectedPizza.getSize()!=null) {
                    if (compoundButton.isChecked()) {
                        selectedPizza.add(Topping.SAUSAGE);
                        price.setText(selectedPizza.price() + "");
                    } else {
                        selectedPizza.remove(Topping.SAUSAGE);
                        price.setText(selectedPizza.price() + "");
                    }
                }
                else{
                    alertMessage("Please select a size");
                }
            }
        });

        pepperoni = findViewById(R.id.pepperoni);
        pepperoni.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /**
             * Checks whether or not pepperoni option is selected and adds to Topping array 
             * @param compoundButton button group for toppings checkbox 
             * @param b true if selected, false if not 
             */
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(selectedPizza.getSize()!=null) {
                    if (compoundButton.isChecked()) {
                        selectedPizza.add(Topping.PEPPERONI);
                        price.setText(selectedPizza.price() + "");
                    } else {
                        selectedPizza.remove(Topping.PEPPERONI);
                        price.setText(selectedPizza.price() + "");
                    }
                }
                else{
                    alertMessage("Please select a size");
                }
            }
        });

        greenpepper = findViewById(R.id.greenpepper);
        greenpepper.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /**
             * Checks whether or not green pepper option is selected and adds to Topping array
             * @param compoundButton button group for toppings checkbox
             * @param b true if selected, false if no
             */
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(selectedPizza.getSize()!=null) {
                    if (compoundButton.isChecked()) {
                        selectedPizza.add(Topping.GREEN_PEPPER);
                        price.setText(selectedPizza.price() + "");
                    } else {
                        selectedPizza.remove(Topping.GREEN_PEPPER);
                        price.setText(selectedPizza.price() + "");
                    }
                }
                else{
                    alertMessage("Please select a size");
                }
            }
        });

        onion = findViewById(R.id.onion);
        onion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /**
             * Checks whether or not onion option is selected and adds to Topping array
             * @param compoundButton button group for toppings checkbox
             * @param b true if selected, false if no
             */
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(selectedPizza.getSize()!=null) {
                    if (compoundButton.isChecked()) {
                        selectedPizza.add(Topping.ONION);
                        price.setText(selectedPizza.price() + "");
                    } else {
                        selectedPizza.remove(Topping.ONION);
                        price.setText(selectedPizza.price() + "");
                    }
                }
                else{
                    alertMessage("Please select a size");
                }
            }
        });

        mushroom = findViewById(R.id.mushroom);
        mushroom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /**
             * Checks whether or not mushroom option is selected and adds to Topping array
             * @param compoundButton button group for toppings checkbox
             * @param b true if selected, false if no
             */
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(selectedPizza.getSize()!=null) {
                    if (compoundButton.isChecked()) {
                        selectedPizza.add(Topping.MUSHROOM);
                        price.setText(selectedPizza.price() + "");
                    } else {
                        selectedPizza.remove(Topping.MUSHROOM);
                        price.setText(selectedPizza.price() + "");
                    }
                }
                else{
                    alertMessage("Please select a size");
                }
            }
        });

        ham = findViewById(R.id.ham);
        ham.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /**
             * Checks whether or not ham option is selected and adds to Topping array
             * @param compoundButton button group for toppings checkbox
             * @param b true if selected, false if no
             */
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(selectedPizza.getSize()!=null) {
                    if (compoundButton.isChecked()) {
                        selectedPizza.add(Topping.HAM);
                        price.setText(selectedPizza.price() + "");
                    } else {
                        selectedPizza.remove(Topping.HAM);
                        price.setText(selectedPizza.price() + "");
                    }
                }
                else{
                    alertMessage("Please select a size");
                }
            }
        });

        olives = findViewById(R.id.olives);
        olives.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /**
             * Checks whether or not black olives option is selected and adds to Topping array
             * @param compoundButton button group for toppings checkbox
             * @param b true if selected, false if no
             */
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(selectedPizza.getSize()!=null) {
                    if (compoundButton.isChecked()) {
                        selectedPizza.add(Topping.BLACK_OLIVE);
                        price.setText(selectedPizza.price() + "");
                    } else {
                        selectedPizza.remove(Topping.BLACK_OLIVE);
                        price.setText(selectedPizza.price() + "");
                    }
                }
                else{
                    alertMessage("Please select a size");
                }
            }
        });

        beef = findViewById(R.id.beef);
        beef.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /**
             * Checks whether or not beef option is selected and adds to Topping array
             * @param compoundButton button group for toppings checkbox
             * @param b true if selected, false if no
             */
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(selectedPizza.getSize()!=null) {
                    if (compoundButton.isChecked()) {
                        selectedPizza.add(Topping.BEEF);
                        price.setText(selectedPizza.price() + "");
                    } else {
                        selectedPizza.remove(Topping.BEEF);
                        price.setText(selectedPizza.price() + "");
                    }
                }
                else{
                    alertMessage("Please select a size");
                }
            }
        });

        shrimp = findViewById(R.id.shrimp);
        shrimp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /**
             * Checks whether or not shrimp option is selected and adds to Topping array
             * @param compoundButton button group for toppings checkbox
             * @param b true if selected, false if no
             */
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(selectedPizza.getSize()!=null) {
                    if (compoundButton.isChecked()) {
                        selectedPizza.add(Topping.SHRIMP);
                        price.setText(selectedPizza.price() + "");
                    } else {
                        selectedPizza.remove(Topping.SHRIMP);
                        price.setText(selectedPizza.price() + "");
                    }
                }
                else{
                    alertMessage("Please select a size");
                }
            }
        });

        crabmeat = findViewById(R.id.crabmeat);
        crabmeat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /**
             * Checks whether or not crabmeat option is selected and adds to Topping array
             * @param compoundButton button group for toppings checkbox
             * @param b true if selected, false if no
             */
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(selectedPizza.getSize()!=null) {
                    if (compoundButton.isChecked()) {
                        selectedPizza.add(Topping.CRAB_MEAT);
                        price.setText(selectedPizza.price() + "");
                    } else {
                        selectedPizza.remove(Topping.CRAB_MEAT);
                        price.setText(selectedPizza.price() + "");
                    }
                }
                else{
                    alertMessage("Please select a size");
                }
            }
        });

        jalapeneos = findViewById(R.id.jalapenos);
        jalapeneos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /**
             * Checks whether or not jalapenos option is selected and adds to Topping array
             * @param compoundButton button group for toppings checkbox
             * @param b true if selected, false if no
             */
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(selectedPizza.getSize()!=null) {
                    if (compoundButton.isChecked()) {
                        selectedPizza.add(Topping.JALAPENOS);
                        price.setText(selectedPizza.price() + "");
                    } else {
                        selectedPizza.remove(Topping.JALAPENOS);
                        price.setText(selectedPizza.price() + "");
                    }
                }
                else{
                    alertMessage("Please select a size");
                }
            }
        });

        squid = findViewById(R.id.squid);
        squid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /**
             * Checks whether or not squid option is selected and adds to Topping array
             * @param compoundButton button group for toppings checkbox
             * @param b true if selected, false if no
             */
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(selectedPizza.getSize()!=null) {
                    if (compoundButton.isChecked()) {
                        selectedPizza.add(Topping.SQUID);
                        price.setText(selectedPizza.price() + "");
                    } else {
                        selectedPizza.remove(Topping.SQUID);
                        price.setText(selectedPizza.price() + "");
                    }
                }
                else{
                    alertMessage("Please select a size");
                }
            }
        });

        bacon = findViewById(R.id.bacon);
        bacon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /**
             * Checks whether or not bacon option is selected and adds to Topping array
             * @param compoundButton button group for toppings checkbox
             * @param b true if selected, false if no
             */
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(selectedPizza.getSize()!=null) {
                    if (compoundButton.isChecked()) {
                        selectedPizza.add(Topping.BACON);
                        price.setText(selectedPizza.price() + "");
                    } else {
                        selectedPizza.remove(Topping.BACON);
                        price.setText(selectedPizza.price() + "");
                    }
                }
                else{
                    alertMessage("Please select a size");
                }
            }
        });

        //Place Order
        placeOrder = findViewById(R.id.buildYourOwn_placeOrder);
        placeOrder.setOnClickListener(new View.OnClickListener() {
            /**
             * Places order based on user input and includes error handling based on incorrect input  
             * @param view total user input 
             */
            @Override
            public void onClick(View view) {
                Log.d("Topping array", selectedPizza.toString()+"");
                if (selectedPizza.getSize()==null || selectedPizza.getSauce()==null || selectedPizza.getToppings().size() < 3 || selectedPizza.getToppings().size() > 7) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(BuildYourOwn.this);
                    builder.setMessage("Please select a size, sauce, and 3-7 toppings.");
                    builder.setTitle("Alert!");
                    builder.setNeutralButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else { Order order = singleton.getOrder();
                    if (order == null) {order = new Order(new ArrayList<Pizza>());
                        singleton.setOrder(order);
                    }
                    order.addPizza(selectedPizza);
                    singleton.setOrder(order);
                    AlertDialog.Builder builder = new AlertDialog.Builder(BuildYourOwn.this);
                    builder.setMessage("Pizza Ordered!");
                    builder.setTitle("Success! Order Number: " + singleton.getCurrentOrderNum());
                    builder.setNeutralButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });
    }

    /**
     * Method for error handling alerts 
     * @param s string for alert message 
     */
    protected void alertMessage(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(BuildYourOwn.this);
        builder.setMessage(s);
        builder.setTitle("Alert!");
        builder.setNeutralButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
