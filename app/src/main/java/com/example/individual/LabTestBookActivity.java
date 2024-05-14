package com.example.individual;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LabTestBookActivity extends AppCompatActivity {

    EditText edname, edaddress, edcontact, edpincode;
    Button btnBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);

        edname = findViewById(R.id.editTextLTPFullName);
        edaddress = findViewById(R.id.editTextLTPAddress);
        edcontact = findViewById(R.id.editTextLTPContact);
        edpincode = findViewById(R.id.editTextLTPPincode);
        btnBooking = findViewById(R.id.buttonLTPBooking);

        final float parsedPrice=0;
        Intent intent = getIntent();
        String priceString = intent.getStringExtra("price");

        // Correct initialization of 'parsedPrice'
         // Default initialization
        float computedPrice = parsedPrice;

        if (priceString != null) {
            // Ensure 'priceString' is not null to avoid NullPointerException
            String[] priceParts = priceString.split(java.util.regex.Pattern.quote(":"));
            if (priceParts.length > 1) {
                computedPrice = Float.parseFloat(priceParts[1]);  // Parse the second part as a float
            }
        }

        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");

        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "").toString();

                Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                db.addOrder(
                        username,
                        edname.getText().toString(),
                        edaddress.getText().toString(),
                        edcontact.getText().toString(),
                        Integer.parseInt(edpincode.getText().toString()),
                        date.toString(),
                        time.toString(),
                        parsedPrice, // Now resolved because 'parsedPrice' is defined
                        "lab"
                );
                db.removeCart(username, "lab");
                Toast.makeText(getApplicationContext(), "Your Booking is done successfully", Toast.LENGTH_SHORT).show(); // Corrected Toast method
                startActivity(new Intent(LabTestBookActivity.this, HomeActivity.class));
            }
        });
    }
}
