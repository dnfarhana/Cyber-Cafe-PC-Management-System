package com.javacodegeeks.ccum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DashboardA extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    String sname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboarda);
        //hide actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        SharedPreferences prefs = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);
        String cStaff = prefs.getString("session",null);

        ImageView changestatus = (ImageView)findViewById(R.id.btnupdateA);
        changestatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardA.this,updateStatusA.class));
            }
        });

        Button lgout = (Button)findViewById(R.id.btnLogout2);
        lgout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("SHARED_PREF", MODE_PRIVATE).edit();
                editor.putString("session", "0");editor.apply();
                startActivity(new Intent(DashboardA.this,LoginC.class));
            }
        });

        ImageView gopcusage = (ImageView)findViewById(R.id.btnpc);
        gopcusage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardA.this,PcReportA.class));
            }
        });

        ImageView gosale = (ImageView)findViewById(R.id.btnsale);
        gosale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardA.this, SalesReportA.class));
            }
        });

        ImageView goreset = (ImageView)findViewById(R.id.btnresetdash);
        goreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardA.this, ResetPcA.class));
            }
        });

        //get staff name
        databaseReference.child("staff").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sname = snapshot.child(cStaff).child("staff_name").getValue(String.class);
                //check date
                TextView tgkdate = (TextView)findViewById(R.id.tengokdate);
                SharedPreferences sharedPreferences = getSharedPreferences("setDate", MODE_PRIVATE);
                String value = sharedPreferences.getString("valuedate","");
                tgkdate.setText(value+"\nWelcome "+sname);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}