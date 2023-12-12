package com.example.javaproject5;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
/**
 * ViewHolder Class that is required for the RecyclerView
 * @author Keerthana Talla
 */
public class ViewHolder extends RecyclerView.ViewHolder {

//    private static final String[] specialPizzas = {"Deluxe", "Supreme", "Meatzza", "Seafood", "Pepperoni"};
     ImageView pizzaImage;
     TextView pizzaTitle;
     TextView toppingsList;
//     Pizza selectedPizza;
//     ArrayList<Topping> toppings;
     ArrayAdapter<Topping> arrayAdapter;
//     Singleton singleton = Singleton.getInstance();


    /**
     * UI to display pizza title, image, and toppings
     * @param itemView to display pizza
     */
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        pizzaImage = itemView.findViewById(R.id.imageview);
        pizzaTitle = itemView.findViewById(R.id.pizzaName);
        toppingsList = itemView.findViewById(R.id.toppingsList);

    }

    /**
     * method to set toppings and append using String Builder
     * @param toppings of array list type
     * @param sauce tomato or alfredo
     */
    public void setToppings(List<Topping> toppings, Sauce sauce) {
        StringBuilder toppingsText = new StringBuilder();
        toppingsText.append("Toppings: ");
        for (Topping topping : toppings) {
            toppingsText.append(topping.getName(topping)).append(", ");
        }
        toppingsText.append("Sauce: "+ sauce);

        if (toppingsText.length() > 0) {
            toppingsText.delete(toppingsText.length() - 2, toppingsText.length());
        }

        toppingsList.setText(toppingsText.toString());
    }

}
