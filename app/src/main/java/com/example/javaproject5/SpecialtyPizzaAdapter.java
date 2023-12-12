package com.example.javaproject5;

import android.content.Context;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
/**
 * Adapter needed for RecyclerView
 * @author Keerthana Talla
 */

public class SpecialtyPizzaAdapter extends RecyclerView.Adapter<ViewHolder> {

    Context context;
    List<Item> items;
    /**
     * Uses singleton class to get instance
     */
    private Singleton singleton = Singleton.getInstance();

    /**
     * public constructor to avoid errors
     * @param context context of pizza object
     * @param items list of items/info for pizza
     */
    public SpecialtyPizzaAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    /**
     * view holder for activity page
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return new view holder based on user input
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_specialty_pizza,parent,false));
    }

    /**
     * binding view holder for information displayed on activity page, changes image
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull  ViewHolder holder, int position) {
        holder.pizzaTitle.setText(items.get(position).getSpecialtyPizzaName());
        List<Topping> toppings = items.get(position).getToppings();
        holder.setToppings(toppings, items.get(position).getSauce());
        //holder.toppingsList.setText(items.get(position).getToppings());
        holder.pizzaImage.setImageResource(items.get(position).getImage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String pizzaName = (String) holder.pizzaTitle.getText();
               PizzaMaker pizzaMaker = new PizzaMaker();
               Pizza selectedPizza = pizzaMaker.createPizza(pizzaName);
               singleton.setPizza(selectedPizza);
               Toast toast = Toast.makeText(context.getApplicationContext(), pizzaName + " selected!", Toast.LENGTH_SHORT);
               toast.show();
            }
        });
    }

    /**
     * item count of pizzas
     * @return integer for item count
     */
    @Override
    public int getItemCount() {
        return items.size();
    }
}

