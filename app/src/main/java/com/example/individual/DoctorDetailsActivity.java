package com.example.individual;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][] doctor_details1 =

    {
        {"Doctor Name : Ajith Silva", "Hospital Address : Lanka Hospital", "Exp : 5yrs", "Mobile No:+94221234543", "600"},
        {"Doctor Name : Susantha Dissanayake", "Hospital Address : Lanka Hospital", "Exp : 8yrs", "Mobile No:+94776545453", "800"},
        {"Doctor Name : Mineth Manilgamuwa", "Hospital Address : Co-operative Hospital", "Exp : 4yrs", "Mobile No:+94778787678", "900"},
        {"Doctor Name : Dilane De Silva", "Hospital Address : Lanka Hospital", "Exp : 4yrs", "Mobile No:+94778984563", "700"},
        {"Doctor Name : Wasantha Ekanayake", "Hospital Address : Co-operative Hospital", "Exp : 5yrs", "Mobile No:+94776545345", "600"}

    };

    private String[][] doctor_details2 =

    {
        {"Doctor Name : Ajith Silva", "Hospital Address : Lanka Hospital", "Exp : 5yrs", "Mobile No:+94221234543", "600"},
        {"Doctor Name : Susantha Dissanayake", "Hospital Address : Lanka Hospital", "Exp : 8yrs", "Mobile No:+94776545453", "800"},
        {"Doctor Name : Mineth Manilgamuwa", "Hospital Address : Co-operative Hospital", "Exp : 4yrs", "Mobile No:+94778787678", "900"},
        {"Doctor Name : Dilane De Silva", "Hospital Address : Lanka Hospital", "Exp : 4yrs", "Mobile No:+94778984563", "700"},
        {"Doctor Name : Wasantha Ekanayake", "Hospital Address : Co-operative Hospital", "Exp : 5yrs", "Mobile No:+94776545345", "600"}

    };

    private String[][] doctor_details3 =

    {
        {"Doctor Name : Ajith Silva", "Hospital Address : Lanka Hospital", "Exp : 5yrs", "Mobile No:+94221234543", "600"},
        {"Doctor Name : Susantha Dissanayake", "Hospital Address : Lanka Hospital", "Exp : 8yrs", "Mobile No:+94776545453", "800"},
        {"Doctor Name : Mineth Manilgamuwa", "Hospital Address : Co-operative Hospital", "Exp : 4yrs", "Mobile No:+94778787678", "900"},
        {"Doctor Name : Dilane De Silva", "Hospital Address : Lanka Hospital", "Exp : 4yrs", "Mobile No:+94778984563", "700"},
        {"Doctor Name : Wasantha Ekanayake", "Hospital Address : Co-operative Hospital", "Exp : 5yrs", "Mobile No:+94776545345", "600"}

    };

    private String[][] doctor_details4 =

    {
        {"Doctor Name : Ajith Silva", "Hospital Address : Lanka Hospital", "Exp : 5yrs", "Mobile No:+94221234543", "600"},
        {"Doctor Name : Susantha Dissanayake", "Hospital Address : Lanka Hospital", "Exp : 8yrs", "Mobile No:+94776545453", "800"},
        {"Doctor Name : Mineth Manilgamuwa", "Hospital Address : Co-operative Hospital", "Exp : 4yrs", "Mobile No:+94778787678", "900"},
        {"Doctor Name : Dilane De Silva", "Hospital Address : Lanka Hospital", "Exp : 4yrs", "Mobile No:+94778984563", "700"},
        {"Doctor Name : Wasantha Ekanayake", "Hospital Address : Co-operative Hospital", "Exp : 5yrs", "Mobile No:+94776545345", "600"}

    };

    private String[][] doctor_details5 =

    {
        {"Doctor Name : Ajith Silva", "Hospital Address : Lanka Hospital", "Exp : 5yrs", "Mobile No:+94221234543", "600"},
        {"Doctor Name : Susantha Dissanayake", "Hospital Address : Lanka Hospital", "Exp : 8yrs", "Mobile No:+94776545453", "800"},
        {"Doctor Name : Mineth Manilgamuwa", "Hospital Address : Co-operative Hospital", "Exp : 4yrs", "Mobile No:+94778787678", "900"},
        {"Doctor Name : Dilane De Silva", "Hospital Address : Lanka Hospital", "Exp : 4yrs", "Mobile No:+94778984563", "700"},
        {"Doctor Name : Wasantha Ekanayake", "Hospital Address : Co-operative Hospital", "Exp : 5yrs", "Mobile No:+94776545345", "600"}

    };


    TextView tv;
    Button btn;
    String[][] doctor_details = {};
    HashMap<String,String> item;
    ArrayList List;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.textViewCartTitle);
        btn =findViewById(R.id.buttonLTGoToCart);
        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Family Physicians")==0)
            doctor_details = doctor_details1;

        else
        if(title.compareTo("Dietician")==0)
            doctor_details = doctor_details2;

        else
        if(title.compareTo("Dentist")==0)
            doctor_details = doctor_details3;

        else
        if(title.compareTo("Surgeon")==0)
            doctor_details = doctor_details4;

        else
            doctor_details = doctor_details5;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));

            }
        });
        List = new ArrayList();
        for (int i = 0; i < doctor_details.length; i++) {
            HashMap<String, String> item = new HashMap<>();
            item.put("Line1", doctor_details[i][0]);
            item.put("Line2", doctor_details[i][1]);
            item.put("Line3", doctor_details[i][2]);
            item.put("Line4", doctor_details[i][3]);
            item.put("Line5", "Cons Fees: " + doctor_details[i][4]);
            List.add(item);
        }

        sa = new SimpleAdapter(this,List,
                R.layout.multi_lines,
                new String[]{"Line1", "Line2", "Line3", "Line4", "Line5"},

        new int[]{R.id.Line_a,R.id.Line_b,R.id.Line_c,R.id.Line_d,R.id.Line_e}
    );
        ListView listView = findViewById(R.id.textView1);
        listView.setAdapter(sa);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView,View view,int i,long l){
                Intent it = new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);
                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                startActivity(it);
                
            }
        });


    }
}