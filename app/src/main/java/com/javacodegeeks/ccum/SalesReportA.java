package com.javacodegeeks.ccum;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SalesReportA extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    ListView salesDisplay;
    ArrayList<usage> sl = new ArrayList<>();
    Double totalsale = 0.00;
    Double avg = 0.00;
    int count = 0;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salesreporta);

        //hide actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        TextView totsale = (TextView)findViewById(R.id.totalSale);
        TextView avgsale  =(TextView)findViewById(R.id.avgSale);

        databaseReference.child("pc_usage").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                usage obj = new usage();
                String matrix = snapshot.child("matrix_no").getValue(String.class);
                String pcid = snapshot.child("pc_id").getValue(String.class);
                String pcname = snapshot.child("pc_name").getValue(String.class);
                Double amount = Double.parseDouble(snapshot.child("total_amount").getValue(String.class));
                Double time = Double.parseDouble(snapshot.child("total_time").getValue(String.class));
                String dateset = snapshot.child("date").getValue(String.class);
                obj.setMatrix(matrix);
                obj.setPcid(pcid);
                obj.setPcname(pcname);
                obj.setTotamount(amount);
                obj.setTottime(time);
                obj.setDateset(dateset);
                sl.add(obj);

                CustomAdapterSaleA customAdapterSaleA = new CustomAdapterSaleA(getApplicationContext(),sl);
                salesDisplay = (ListView)findViewById(R.id.displaysales);
                salesDisplay.setAdapter(customAdapterSaleA);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //count total sale and average
        databaseReference.child("pc_usage").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    Double a = Double.parseDouble(ds.child("total_amount").getValue(String.class));
                    totalsale += a;
                    count++;

                }
                String stotalsale = String.valueOf(df.format(totalsale));
                totsale.setText("RM"+stotalsale);
                //count average
                avg = totalsale / count;
                String savg = String.valueOf(df.format(avg));
                avgsale.setText("RM"+savg);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //test masuk date
        String ttt = "testdatedate";
        SharedPreferences sharedPref = getSharedPreferences("myKey", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("value", ttt);
        editor.apply();


    }
}