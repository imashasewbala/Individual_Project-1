package com.example.individual;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BuyBookMedicineActivity extends AppCompatActivity {
    EditText edname,edaddress,edcontact,edpincode;
    Button btnBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_book_medicine);

        edname = findViewById(R.id.editTextBMBFullName);
        edaddress = findViewById(R.id.editTextBMBAddress);
        edcontact = findViewById(R.id.editTextBMBContact);
        edpincode = findViewById(R.id.editTextBMBPincode);
        btnBooking = findViewById(R.id.buttonBMBBooking);

        Intent intent = getIntent();

        // Declare 'priceString' and 'price' as 'final' to use within inner class
        final String priceString = intent.getStringExtra("price"); // Original price string
        final String[] priceParts = priceString.split(java.util.regex.Pattern.quote(":")); // Split using ':'

        final String price; // Final variable
        if (priceParts.length > 1) {
            price = priceParts[1]; // Get the second element
        } else {
            price = "0"; // Default value if the split doesn't work as expected
        }

// Retrieve other intent data
        String date = intent.getStringExtra("date");


        btnBooking.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                SharedPreferences sharedPreferences = getSharedPreferences("shaped_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();

                Database db =new Database(getApplicationContext(),"healthcare",null,1);
                db.addOrder(
                        username,
                        edname.getText().toString(),
                        edaddress.getText().toString(),
                        edcontact.getText().toString(),
                        Integer.parseInt(edpincode.getText().toString())
                        ,date.toString(),
                        "",
                        Float.parseFloat(price),
                        "medicine");
                db.removeCart(username,"medicine");
                Toast.makeText(getApplicationContext(),"Your Booking is done successfully",Toast.LENGTH_SHORT);
                startActivity(new Intent(BuyBookMedicineActivity.this,HomeActivity.class));

            }
        });

    }
}