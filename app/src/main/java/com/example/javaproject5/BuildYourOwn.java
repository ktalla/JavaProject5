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
 * Class that allows user to create a buildyourown pizza
 * @author Ishani Mhatre
 * @author Keerthana Talla
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

    /**
     * Method to handle activity for this page
     * @param savedInstanceState based on user input
     */
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.build_your_own);
        PizzaMaker pizzaMaker = new PizzaMaker();
        selectedPizza = pizzaMaker.createPizza("buildyourown");
        price = findViewById(R.id.price_byo);
        price.setText("0.00");
        sizeGroup = findViewById(R.id.sizeGroup);
        sauceGroup = findViewById(R.id.sauceGroup);
        extraSauce = findViewById(R.id.extraSauce);
        extraCheese = findViewById(R.id.extraCheese);
        handleSizeGroupAction();
        handleSauceGroupAction();
        handleExtraCheeseSauceAction();
        sausage = findViewById(R.id.sausage);
        handleSausageSelection();
        pepperoni = findViewById(R.id.pepperoni);
        handlePepperoniSelection();
        greenpepper = findViewById(R.id.greenpepper);
        handleGreenPepperSelection();
        onion = findViewById(R.id.onion);
        handleOnionSelection();
        mushroom = findViewById(R.id.mushroom);
        handleMushroomSelection();
        ham = findViewById(R.id.ham);
        handleHamSelection();
        olives = findViewById(R.id.olives);
        handleOlivesSelection();
        beef = findViewById(R.id.beef);
        handleBeefSelection();
        shrimp = findViewById(R.id.shrimp);
        handleShrimpSelection();
        crabmeat = findViewById(R.id.crabmeat);
        handleCrabMeatSelection();
        jalapeneos = findViewById(R.id.jalapenos);
        handleJalapenosSelection();
        squid = findViewById(R.id.squid);
        handleSquidSelection();
        bacon = findViewById(R.id.bacon);
        handleBaconSelection();
        placeOrder = findViewById(R.id.buildYourOwn_placeOrder);
        handlePlaceOrderAction();
    }

    /**
     * Method to change size based on user input and update pizza price
     */
    private void handleSizeGroupAction(){
        sizeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selectedSize = group.findViewById(checkedId);
                String selectedText = selectedSize.getText().toString();
                selectedPizza.setSize(selectedText);
                price.setText(selectedPizza.price()+"");
            }
        });
    }

    /**
     * Method to change sauce based on user input and update pizza price
     */
    private void handleSauceGroupAction(){
        sauceGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selectedSauce = group.findViewById(checkedId);
                String selectedText = selectedSauce.getText().toString();
                selectedPizza.setSauce(selectedText);
                //Log.d("Sauce set: ", selectedPizza.getSauce().toString());
               // price.setText(selectedPizza.price() + "");
            }
        });
    }

    /**
     * Method to change option for extra cheese and sauce based on user input
     * Updates the price accordingly 
     */
    private void handleExtraCheeseSauceAction(){
        extraSauce.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(selectedPizza.getSize()!=null) {
                    if (compoundButton.isChecked()) {
                        selectedPizza.setExtraSauce(true);
                        price.setText(selectedPizza.price() + "");
                    } else {
                        selectedPizza.setExtraSauce(false);
                        price.setText(selectedPizza.price() + "");
                    }
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(BuildYourOwn.this);
                    builder.setMessage("Please select a size");
                    builder.setTitle("Alert!");
                    builder.setNeutralButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    extraSauce.setChecked(false);
                }
            }
        });
        extraCheese.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            /**
             * Method to set extra cheese as true or false and change price 
             * @param compoundButton radio group
             * @param b true or false 
             */
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(selectedPizza.getSize()!=null) {
                    if (compoundButton.isChecked()) {
                        selectedPizza.setExtraCheese(true);
                        price.setText(selectedPizza.price() + "");
                    } else {
                        selectedPizza.setExtraCheese(false);
                        price.setText(selectedPizza.price() + "");
                    }
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(BuildYourOwn.this);
                    builder.setMessage("Please select a size");
                    builder.setTitle("Alert!");
                    builder.setNeutralButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    extraCheese.setChecked(false);
                }
            }
        });
    }

    /**
     * Method to add sausage to topping array for pizza based on user input
     * Creates alert if size is not selected before
     */
    private void handleSausageSelection(){
        sausage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(BuildYourOwn.this);
                    builder.setMessage("Please select a size");
                    builder.setTitle("Alert!");
                    builder.setNeutralButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    sausage.setChecked(false);
                }
            }
        });
    }

    /**
     * Method to add pepperoni to topping array for pizza based on user input
     * Creates alert if size is not selected before
     */
    private void handlePepperoniSelection(){
        pepperoni.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(BuildYourOwn.this);
                    builder.setMessage("Please select a size");
                    builder.setTitle("Alert!");
                    builder.setNeutralButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    pepperoni.setChecked(false);
                }
            }
        });
    }

    /**
     * Method to add green pepper to topping array for pizza based on user input
     * Creates alert if size is not selected before
     */
    private void handleGreenPepperSelection(){
        greenpepper.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(BuildYourOwn.this);
                    builder.setMessage("Please select a size");
                    builder.setTitle("Alert!");
                    builder.setNeutralButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    greenpepper.setChecked(false);
                }
            }
        });
    }

    /**
     * Method to add onion to topping array for pizza based on user input
     * Creates alert if size is not selected before
     */
    private void handleOnionSelection(){
        onion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(BuildYourOwn.this);
                    builder.setMessage("Please select a size");
                    builder.setTitle("Alert!");
                    builder.setNeutralButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    onion.setChecked(false);
                }
            }
        });
    }

    /**
     * Method to add mushroom to topping array for pizza based on user input
     * Creates alert if size is not selected before
     */
    private void handleMushroomSelection(){
        mushroom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(BuildYourOwn.this);
                    builder.setMessage("Please select a size");
                    builder.setTitle("Alert!");
                    builder.setNeutralButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    mushroom.setChecked(false);
                }
            }
        });
    }

    /**
     * Method to add ham to topping array for pizza based on user input
     * Creates alert if size is not selected before
     */
    private void handleHamSelection(){
        ham.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(BuildYourOwn.this);
                    builder.setMessage("Please select a size");
                    builder.setTitle("Alert!");
                    builder.setNeutralButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    ham.setChecked(false);
                }
            }
        });
    }

    /**
     * Method to add black olives to topping array for pizza based on user input
     * Creates alert if size is not selected before
     */
    private void handleOlivesSelection(){
        olives.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(BuildYourOwn.this);
                    builder.setMessage("Please select a size");
                    builder.setTitle("Alert!");
                    builder.setNeutralButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    olives.setChecked(false);
                }
            }
        });
    }

    /**
     * Method to add beef to topping array for pizza based on user input
     * Creates alert if size is not selected before
     */
    private void handleBeefSelection(){
        beef.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(BuildYourOwn.this);
                    builder.setMessage("Please select a size");
                    builder.setTitle("Alert!");
                    builder.setNeutralButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    beef.setChecked(false);
                }
            }
        });
    }

    /**
     * Method to add shrimp to topping array for pizza based on user input
     * Creates alert if size is not selected before
     */
    private void handleShrimpSelection(){
        shrimp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(BuildYourOwn.this);
                    builder.setMessage("Please select a size");
                    builder.setTitle("Alert!");
                    builder.setNeutralButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    shrimp.setChecked(false);
                }
            }
        });
    }

    /**
     * Method to add crab meat to topping array for pizza based on user input
     * Creates alert if size is not selected before
     */
    private void handleCrabMeatSelection(){
        crabmeat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(BuildYourOwn.this);
                    builder.setMessage("Please select a size");
                    builder.setTitle("Alert!");
                    builder.setNeutralButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    crabmeat.setChecked(false);
                }
            }
        });
    }

    /**
     * Method to add jalapeno to topping array for pizza based on user input
     * Creates alert if size is not selected before
     */
    private void handleJalapenosSelection(){
        jalapeneos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(BuildYourOwn.this);
                    builder.setMessage("Please select a size");
                    builder.setTitle("Alert!");
                    builder.setNeutralButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    jalapeneos.setChecked(false);
                }
            }
        });
    }

    /**
     * Method to add squid to topping array for pizza based on user input
     * Creates alert if size is not selected before
     */
    private void handleSquidSelection(){
        squid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(BuildYourOwn.this);
                    builder.setMessage("Please select a size");
                    builder.setTitle("Alert!");
                    builder.setNeutralButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    squid.setChecked(false);
                }
            }
        });
    }

    /**
     * Method to add bacon to topping array for pizza based on user input
     * Creates alert if size is not selected before
     */
    private void handleBaconSelection(){
        bacon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(BuildYourOwn.this);
                    builder.setMessage("Please select a size");
                    builder.setTitle("Alert!");
                    builder.setNeutralButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    bacon.setChecked(false);
                }
            }
        });
    }

    /**
     * Method to place order based on user input
     * Alerts will appear if there are any errors such as insufficient information
     */
    private void handlePlaceOrderAction(){
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedPizza.getSize()==null || selectedPizza.getSauce()==null || selectedPizza.getToppings().size() < 3 || selectedPizza.getToppings().size() > 7) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(BuildYourOwn.this);
                    builder.setMessage("Please select a size, sauce, and 3-7 toppings.");
                    builder.setTitle("Alert!");
                    builder.setNeutralButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else {
                    Order order = singleton.getOrder();
                    if (order == null) {
                        order = new Order(new ArrayList<Pizza>());
                        singleton.setOrder(order);
                    }
                    order.addPizza(selectedPizza);
                    singleton.setOrder(order);
                    //Log.d("Success", singleton.getOrder().toString());

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
}
