package com.shopify.noah.shopifychallenge;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import okhttp3.OkHttpClient;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.lang.Integer;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import android.util.Log;

public class ListProducts extends AppCompatActivity {
    static ArrayList<Product> productList = new ArrayList<>();
    static ArrayList<Product> displayList = new ArrayList<>();
    private static HashMap<Product, List<ProductVariant>> cart = new HashMap<>();
    private static Product currentProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_products);
        Integer page = 1;
        new StoreData().execute(page);
        ListView productView = findViewById(R.id.ListView);
        Log.d("WAITING", "Waiting for click events");
        productView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                currentProduct = displayList.get(i);
                if(currentProduct.image.getBmp()==null){
                    Snackbar snackbar = Snackbar
                            .make(view.getRootView(), "Please wait for the item to fully load", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
                else{
                    Intent intent = new Intent(ListProducts.this, ViewProduct.class);
                    intent.putExtra("ProductImage", currentProduct.image.getBmp());
                    intent.putExtra("ProductDetails", currentProduct.detailsToString());
                    startActivity(intent);

                }
                Log.d("CLICK EVENT", "Clicked on product: " + currentProduct);
            }
        });
    }
    public void onCartClick(View view){
        Intent intent = new Intent(ListProducts.this, ViewCart.class);
        intent.putExtra("CartString", getCartDisplay());
        startActivity(intent);

    }
    public class StoreData extends AsyncTask<Integer, Integer, String> {
        @Override
        protected String doInBackground(Integer... page) {
            Integer p = page[0];
            OkHttpClient httpClient = new OkHttpClient();
            HttpUrl.Builder urlParameters = HttpUrl.parse("https://shopicruit.myshopify.com/admin/products.json").newBuilder();
            urlParameters.addQueryParameter("page", p.toString());
            urlParameters.addQueryParameter("access_token", "c32313df0d0ef512ca64d5b336a0d7c6");
            String url = urlParameters.build().toString();

            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response;
            String r = "";
            try {
                response = httpClient.newCall(request).execute();
                r = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String result) {
            final ListView storeList = findViewById(R.id.ListView);

            PL pL = new Gson().fromJson(result, PL.class);
            productList = pL.products;
            displayList = productList;

            final ProductAdapter productsAdapter = new ProductAdapter(getApplicationContext(), displayList);

            storeList.setAdapter(productsAdapter);

            EditText query = findViewById(R.id.search);
            query.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    displayList = productList;
                    displayList = filterProducts(displayList, charSequence);
                    final ProductAdapter productsAdapter = new ProductAdapter(getApplicationContext(), displayList);

                    storeList.setAdapter(productsAdapter);
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }

        private class PL{
            @SerializedName("products")
            private ArrayList<Product> products;
        }
    }
    public static ArrayList<Product> filterProducts(ArrayList<Product> products, CharSequence query) {
        ArrayList<Product> newList = new ArrayList<>();
        for (Product p : products) {
            if (p.detailsToString().toUpperCase().contains(query.toString().toUpperCase())) {
                newList.add(p);
            }
        }
        return newList;
    }

    public static boolean addToCart(Integer variant){
        variant--;
        try{
            if(cart.containsKey(currentProduct)){
                cart.get(currentProduct).add(currentProduct.variants.get(variant));
            }
            else {
                List<ProductVariant> tmp = new ArrayList<>();
                tmp.add(currentProduct.variants.get(variant));
                cart.put(currentProduct, tmp);
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }
    private static String getCartDisplay(){
        String c = "";
        Double total = 0.0;
        for(Product key: cart.keySet()){
            c+=key.toString();
            c+="\n";
            for(ProductVariant pv: cart.get(key)) {
                c += pv.simpleString();
                total += Double.parseDouble(pv.price);
                c+="\n";
            }
            c+="\n";
        }
        if(total==0.0){
            c = "You have no items in your cart!";
        }
        else{
            c+= "\nTotal: $" + String.format(Locale.CANADA, "%,.2f",total);
        }

        return c;
    }
}