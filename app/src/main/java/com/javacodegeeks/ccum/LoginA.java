package com.javacodegeeks.ccum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginA extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logina);

        //hide actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //receive staff id and staff password
        Intent intent = getIntent();
        String sid = intent.getStringExtra("STAFFID");
        String spass = intent.getStringExtra("STAFFPASS");

        EditText staff_id = (EditText)findViewById(R.id.editSNameLogin);
        EditText staff_pass = (EditText)findViewById(R.id.editSPassLogin);
        TextView goreg = (TextView)findViewById(R.id.link3);
        Button login = (Button)findViewById(R.id.btnLogin2);

        goreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginA.this,RegisterA.class));
            }
        });

//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final String sid = staff_id.getText().toString();
//                final String spass = staff_pass.getText().toString();
//
//                //check all data has been filled
//                if(sid.isEmpty()||spass.isEmpty()){
//                    Toast.makeText(LoginA.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
//                }
//                else{
                    databaseReference.child("staff").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            //check if user available
                            if(snapshot.hasChild(sid)){
                                //user exist
                                final String getPassStaff = snapshot.child(sid).child("staff_password").getValue(String.class);
                                if(getPassStaff.equals(spass)){
                                    Toast.makeText(LoginA.this, "Successfully Login!", Toast.LENGTH_SHORT).show();
                                    editor = getSharedPreferences("SHARED_PREF",MODE_PRIVATE).edit();
                                    editor.putString("session",sid);editor.apply();
                                    startActivity(new Intent(LoginA.this,DashboardA.class));
                                }else{
                                    Toast.makeText(LoginA.this, "Wrong Password!", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(LoginA.this, "Account not exist!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {

                        }
                    });
                }
            }
//        });

