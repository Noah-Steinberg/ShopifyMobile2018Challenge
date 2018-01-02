package com.shopify.noah.shopifychallenge;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import static com.shopify.noah.shopifychallenge.R.layout.list_item_layout;

/**
 * Created by cadav on 1/1/2018.
 */

public class ProductAdapter extends ArrayAdapter {
    private final Context context;
    private final ArrayList<Product> products;

    public ProductAdapter(Context context, ArrayList<Product> products) {
        super(context, R.layout.activity_list_products, products);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.products=products;
        for(int i=0;i<products.size();i++){
            new GetProductImage(products.get(i).image).execute(products.get(i).image.src);
        }
    }

    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater= LayoutInflater.from(this.context);
        @SuppressLint("ViewHolder") View rowView=inflater.inflate(list_item_layout, null,true);

        TextView txtTitle = rowView.findViewById(R.id.item);
        ImageView imageView = rowView.findViewById(R.id.icon);

        txtTitle.setText(products.get(position).toString());
        imageView.setImageBitmap(products.get(position).image.getBmp());
        return rowView;

    }

    private class GetProductImage extends AsyncTask<String, Void, Bitmap> {
        Image image;

        GetProductImage(Image image) {
            this.image = image;
        }

        protected Bitmap doInBackground(String... urls) {
            Log.d("GETTING IMAGE", "");
            String url = urls[0];
            Bitmap bmp = null;
            try {
                URL image_url = new URL(url);
                bmp = BitmapFactory.decodeStream(image_url.openConnection().getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.image.setBmp(bmp);
            return bmp;
        }

        public void onPostExecute(Bitmap result){
            notifyDataSetChanged();
        }
    }
}
