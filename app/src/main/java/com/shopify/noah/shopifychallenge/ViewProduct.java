package com.shopify.noah.shopifychallenge;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.view_product);
        TextView description = findViewById(R.id.textView);
        ImageView image = findViewById(R.id.imageView);

        description.setText((String) intent.getSerializableExtra("ProductDetails"));
        description.setMovementMethod(new ScrollingMovementMethod());

        image.setImageBitmap((Bitmap) intent.getParcelableExtra("ProductImage"));

        Button addToCart = findViewById(R.id.button);
        final String[] variant = {""};
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder getVariant = new AlertDialog.Builder(ViewProduct.this);
                getVariant.setTitle("Title");

                final EditText inputVariant = new EditText(ViewProduct.this);
                inputVariant.setInputType(InputType.TYPE_CLASS_TEXT);
                getVariant.setView(inputVariant);
                getVariant.setTitle("Enter Variant Number (1 or higher)");
                getVariant.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        variant[0] = inputVariant.getText().toString();
                        boolean failed;
                        try {
                            Integer variantNo = Integer.parseInt(variant[0]);
                            failed = !ListProducts.addToCart(variantNo);
                        }catch (NumberFormatException e){
                            failed = true;
                        }
                        if(failed){
                            AlertDialog.Builder failedAlert;
                            failedAlert = new AlertDialog.Builder(ViewProduct.this);
                            failedAlert.setTitle("Add to Cart Failed")
                                    .setMessage("Please Enter the Number (Starting at 1) of the variant you wish to add");
                            failedAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            failedAlert.show();
                        }
                    }
                });
                getVariant.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                getVariant.show();


            }
        });
    }
}
