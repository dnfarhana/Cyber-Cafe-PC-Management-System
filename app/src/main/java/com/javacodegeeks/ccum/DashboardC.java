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

public class DashboardC extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboardc);
        //hide actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //receive session
        SharedPreferences prefs = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);
        String cMatrix = prefs.getString("session",null);

        //display user
        TextView disCustomer = (TextView)findViewById(R.id.cId);
        databaseReference.child("customers").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(cMatrix)){
                    String cusName = snapshot.child(cMatrix).child("name").getValue(String.class);
                    disCustomer.setText("Welcome, "+cusName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ImageView goProfile = (ImageView) findViewById(R.id.btnprofile);
        goProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardC.this,ProfileC.class));
            }
        });

        ImageView logout = (ImageView)findViewById(R.id.btnLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("SHARED_PREF", MODE_PRIVATE).edit();
                editor.putString("session", "0");editor.apply();
                startActivity(new Intent(DashboardC.this,LoginC.class));
            }
        });

        ImageView gopcAvailable = (ImageView)findViewById(R.id.btnpcmenu);
        gopcAvailable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardC.this,PcAvailableC.class));
            }
        });

        ImageView goUsage = (ImageView)findViewById(R.id.btnpcusage);
        goUsage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardC.this,UsageC.class));
            }
        });
    }
}