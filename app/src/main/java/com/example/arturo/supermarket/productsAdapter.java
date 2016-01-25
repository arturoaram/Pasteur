package com.example.arturo.supermarket;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Arturo on 5/3/2015.
 */
public class productsAdapter extends ArrayAdapter<product> {
    public productsAdapter(Context context, ArrayList<product> placess){
        super(context,0,placess);
    }

    public View getView(int position,View ConvertView, ViewGroup parent){

        ArrayList<String> categories;
        String str = "";

        int h = R.drawable.fruits;
        int drawableValue=0;
        //Log.e("Drawable int", Integer.parseInt("fruits")+" Value");


        HashMap<String,Integer> hm = new HashMap<String,Integer>();
        hm.put("Fruits",R.drawable.fruits);
        hm.put("Vegetables",R.drawable.vegetable);
        hm.put("Baked Goods",R.drawable.bakedgoods);
        hm.put("Yogurt",R.drawable.yogurt);
        hm.put("Milk",R.drawable.milk);
        hm.put("Cheese",R.drawable.cheese);
        hm.put("Cereal and Grains",R.drawable.cerealsandgrains);
        hm.put("Non-Alcoholic Beverages",R.drawable.beverages);
        hm.put("Eggs",R.drawable.eggs);
        hm.put("Deli Meats",R.drawable.delimeats);
        hm.put("Frozen Produce",R.drawable.frozenproduce);
        hm.put("Beer/Liquor",R.drawable.alcohol);
        hm.put("Sauces and Condiments",R.drawable.saucesandcondiments);
        hm.put("Frozen Premade Foods",R.drawable.frozenpremadefood);
        hm.put("Meats",R.drawable.meat);
        hm.put("Fish",R.drawable.fish);


        categories = new ArrayList<String>();
        categories.addAll(hm.keySet());

        product product = getItem(position);

        if(ConvertView == null){
            ConvertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_user, parent, false);

        }
        if(product !=null) {
            TextView tvName = (TextView) ConvertView.findViewById(R.id.nameTextView);
            TextView tvLifeSpan = (TextView) ConvertView.findViewById(R.id.lifeSpanTextView);
            TextView tvPrice = (TextView) ConvertView.findViewById(R.id.priceTextView);
            ImageView ivPhoto = (ImageView) ConvertView.findViewById(R.id.itemImageView);

            NumberFormat format = new DecimalFormat("#.###");
            str = format.format(product.getPrice());
            tvName.setText(product.getName());
            tvLifeSpan.setText(product.getLifeSpan() + " Days");
            tvPrice.setText("$"+str);



            if(hm.get(product.getCategory()) != null) {

                drawableValue = hm.get(product.getCategory());
                Log.e("Valor de frutas:+ " + str, drawableValue + "  Value");

            }
            if((categories.contains(product.getCategory()) && drawableValue >0)){
                ivPhoto.setImageResource(hm.get(product.getCategory()));
            }

            else {ivPhoto.setImageResource(R.drawable.defaultt);
            }
        }

        return ConvertView;
    }
}
