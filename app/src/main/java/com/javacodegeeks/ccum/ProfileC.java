package com.javacodegeeks.ccum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileC extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilec);

        //hide actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //get Session
        SharedPreferences prefs = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);
        String cMatrix = prefs.getString("session",null);

        //display user detail
        TextView cusname = (TextView)findViewById(R.id.retcName);
        TextView cusmatrix = (TextView)findViewById(R.id.retcMatrix);
        TextView cusphone = (TextView)findViewById(R.id.retcPhone);
        Button goUpdate = (Button)findViewById(R.id.editDetailbtn);
        Button back1 = (Button)findViewById(R.id.backbtn1);

        goUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileC.this, UpdateProfileC.class));
            }
        });
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileC.this,DashboardC.class));
            }
        });

        databaseReference.child("customers").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.hasChild(cMatrix)){
                    String cusName = snapshot.child(cMatrix).child("name").getValue(String.class);
                    String cusPhone = snapshot.child(cMatrix).child("phone").getValue(String.class);
                    cusname.setText(cusName);
                    cusmatrix.setText(cMatrix);
                    cusphone.setText(cusPhone);
                }
                else{
                    Toast.makeText(ProfileC.this, "user not exist!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

    }
}