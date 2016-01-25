package com.example.arturo.supermarket;

import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class SupermarketActivity extends ActionBarActivity {

    ListView listView;
    ArrayList<product> products;
    product product;
    productsAdapter adapter;
    Button rButton, aButton;
    SwipeRefreshLayout mSwipeRefreshLayout = null;
    private Handler mHandler = new Handler();
    final Context context = this;
    HashMap<String,Integer> lifespanMap;
    ArrayList<String> categories;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supermarket_activity);


        products = new ArrayList<product>();
        rButton = (Button) findViewById(R.id.refreshButton);
        rButton.setVisibility(View.GONE);
        listView = (ListView) findViewById(R.id.entireList);
        aButton = (Button) findViewById(R.id.addButton);


        lifespanMap = new HashMap<String,Integer>();
        lifespanMap.put("Fruits",7);
        lifespanMap.put("Vegetables",7);
        lifespanMap.put("Baked Goods",7);
        lifespanMap.put("Yogurt",14);
        lifespanMap.put("Milk",14);
        lifespanMap.put("Cheese",14);
        lifespanMap.put("Ceareals and Grains",21);
        lifespanMap.put("Non-Alcoholic Beverages",21);
        lifespanMap.put("Eggs",21);
        lifespanMap.put("Deli Meats",21);
        lifespanMap.put("Frozen Produce",30);
        lifespanMap.put("Beer/Liquor",30);
        lifespanMap.put("Sauces and Condiments",30);
        lifespanMap.put("Frozen Premade Foods",30);
        lifespanMap.put("Meats",30);
        lifespanMap.put("Fish",30);

        categories = new ArrayList<String>();
        categories.addAll(lifespanMap.keySet());

        //Storing Variables for the Add function

        aButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Dialog dialog = new Dialog(context);

                dialog.setContentView(R.layout.dialog2);
                dialog.setTitle("Android gustaso");

                Button cancelButton = (Button) dialog.findViewById(R.id.dButton);
                Button submitButton = (Button) dialog.findViewById(R.id.sButton);
                ImageView cancelImage = (ImageView) dialog.findViewById(R.id.cancelImage);
                ImageView submitImage = (ImageView) dialog.findViewById(R.id.submitImage);

                final EditText nameText = (EditText) dialog.findViewById(R.id.nameTextField);
                final EditText priceText = (EditText) dialog.findViewById(R.id.priceTextField);
                final Spinner categoriesSpinner = (Spinner) dialog.findViewById(R.id.categorySpinner);

                final EditText nameText2 = (EditText) dialog.findViewById(R.id.productNameText);
                final EditText priceText2 = (EditText) dialog.findViewById(R.id.priceText);
                final Spinner categoriesSpinner2 = (Spinner) dialog.findViewById(R.id.spinner);

                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(context,
                        android.R.layout.simple_spinner_item,categories);


                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //categoriesSpinner.setAdapter(adapter1);
                categoriesSpinner2.setAdapter(adapter1);


                cancelImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
//                cancelButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.dismiss();
//                    }
//                });



                submitImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String addName="",addCategory="";
                        int addLifeSpan=0;
                        double addPrice=0.0;

                        product producto;

                        addName = nameText2.getText().toString();
                        addCategory = categoriesSpinner2.getSelectedItem().toString();
                        addLifeSpan = lifespanMap.get(categoriesSpinner2.getSelectedItem().toString());
                        addPrice = Double.parseDouble(priceText2.getText().toString());

                        producto = new product(addName,addCategory,addLifeSpan,addPrice);

                        products.add(producto);
                        listView.setAdapter(adapter);

                        dialog.dismiss();
                    }
                });


//                submitButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        String addName="",addCategory="";
//                        int addLifeSpan=0;
//                        double addPrice=0.0;
//
//                        product producto;
//
//                        addName = nameText.getText().toString();
//                        addCategory = categoriesSpinner.getSelectedItem().toString();
//                        addLifeSpan = lifespanMap.get(categoriesSpinner.getSelectedItem().toString());
//                        addPrice = Double.parseDouble(priceText.getText().toString());
//
//                        producto = new product(addName,addCategory,addLifeSpan,addPrice);
//
//                        products.add(producto);
//                        listView.setAdapter(adapter);
//
//                        dialog.dismiss();
//
//                    }
//                });

                dialog.show();


            }
        });


        //Setting the refresh swipe method.


        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new DownloadWebpageTask(new AsyncResult() {
                    @Override
                    public void onResult(JSONObject object) {
                        processJson(object);
                    }
                }).execute("https://spreadsheets.google.com/tq?key=" +
                        "1qk0mqdS5ea-seGvSGjOp7OtKM7fRwnyNZx4HemTN-24");
                //Waiting 2 seconds before making the swipeRefresh to hide.
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        stopRefreshing();
                    }
                }, 2000);

            }
        });


        //mSwipeRefreshLayout.setRefreshing(false);
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            rButton.setEnabled(true);
        } else {
            rButton.setEnabled(false);
        }


        //get the information from the Json file, from the expread sheet.
        new DownloadWebpageTask(new AsyncResult() {
            @Override
            public void onResult(JSONObject object) {
                processJson(object);
            }
        }).execute("https://spreadsheets.google.com/tq?key=" +
                "1qk0mqdS5ea-seGvSGjOp7OtKM7fRwnyNZx4HemTN-24");
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    //Refresh Button
    public void buttonClickHandler(View view) {

        new DownloadWebpageTask(new AsyncResult() {
            @Override
            public void onResult(JSONObject object) {
                processJson(object);
            }
        }).execute("https://spreadsheets.google.com/tq?key=" +
                "1qk0mqdS5ea-seGvSGjOp7OtKM7fRwnyNZx4HemTN-24");
    }

    //update the list view method.
    public void updateMethod() {
        new DownloadWebpageTask(new AsyncResult() {
            @Override
            public void onResult(JSONObject object) {
                processJson(object);
            }
        }).execute("https://spreadsheets.google.com/tq?key=" +
                "1qk0mqdS5ea-seGvSGjOp7OtKM7fRwnyNZx4HemTN-24");
    }

    //Convert the JsonObject into object.
    private void processJson(JSONObject object) {

        products.clear();
        //Log.e("hola", "Tratando de entrar");
        try {
            JSONArray rows = object.getJSONArray("rows");

            //Log.e("hola", "Estamos adentro");

            for (int r = 0; r < rows.length(); ++r) {
                JSONObject row = rows.getJSONObject(r);
                JSONArray columns = row.getJSONArray("c");

                String name = columns.getJSONObject(1).getString("v");
                String category = columns.getJSONObject(2).getString("v");
                int lifeSpan = columns.getJSONObject(3).getInt("v");
                Double price = columns.getJSONObject(4).getDouble("v");
                product = new product(name, category, lifeSpan, price);
                products.add(product);
            }
            //Log.e("hola", "Termino el loop");

            adapter = new productsAdapter(this, products);
            listView.setAdapter(adapter);

        } catch (JSONException e) {
            //Log.e("hola", "bye");
            e.printStackTrace();
        }

    }


    private void stopRefreshing() {
        mSwipeRefreshLayout.setRefreshing(false);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Supermarket Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.arturo.supermarket/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Supermarket Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.arturo.supermarket/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}






//    class UpdateExample extends TimerTask{
//
//        ArrayList<product> products;
//        products = new ArrayList<product>;
//
//        public void updateMethod(){
//            new DownloadWebpageTask(new AsyncResult() {
//                @Override
//                public void onResult(JSONObject object) {
//                    processJson(object);
//                }
//            }).execute("https://spreadsheets.google.com/tq?key=" +
//                    "1qk0mqdS5ea-seGvSGjOp7OtKM7fRwnyNZx4HemTN-24");
//        }
//
//        private void processJson(JSONObject object){
//
//            products.clear();
//            //Log.e("hola", "Tratando de entrar");
//            try{
//                JSONArray rows = object.getJSONArray("rows");
//
//                //Log.e("hola", "Estamos adentro");
//
//                for (int r=0; r <rows.length();++r){
//                    JSONObject row = rows.getJSONObject(r);
//                    JSONArray columns = row.getJSONArray("c");
//
//                    String name = columns.getJSONObject(1).getString("v");
//                    String category = columns.getJSONObject(2).getString("v");
//                    int lifeSpan = columns.getJSONObject(3).getInt("v");
//                    Double price = columns.getJSONObject(4).getDouble("v");
//                    product = new product(name,category,lifeSpan,price);
//                    products.add(product);
//                }
//                //Log.e("hola", "Termino el loop");
//
//                adapter = new productsAdapter(this,products);
//                listView.setAdapter(adapter);
//
//            }
//            catch (JSONException e) {
//                //Log.e("hola", "bye");
//                e.printStackTrace();
//            }
//
//        }
//
//        @Override
//        public void run() {
//            updateMethod();
//        }
//    }
