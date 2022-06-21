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

public class LoginC extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    SharedPreferences.Editor editor, testdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginc);
        //hide actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        SharedPreferences prefs = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);
        String userid = prefs.getString("session",null);

        final EditText cusMatrix = (EditText)findViewById(R.id.editMatrixLogin);
        final EditText cusPass = (EditText)findViewById(R.id.editPassLogin);
        final TextView regisNow = (TextView)findViewById(R.id.signup);
        final Button loginbtn = (Button)findViewById(R.id.loginbtn);
        TextView viewuser = (TextView)findViewById(R.id.checkId);

        regisNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginC.this,RegisterC.class));
            }
        });
        viewuser.setText(userid);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id = cusMatrix.getText().toString();
                final String pass = cusPass.getText().toString();

                //check if all data has been filled
                if(id.isEmpty() || pass.isEmpty()){
                    Toast.makeText(LoginC.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
                else if(id.charAt(0) == 'S'){
                    databaseReference.child("staff").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //check if user available
                            if(snapshot.hasChild(id)){
                                //user exist
                                final String getPassStaff = snapshot.child(id).child("staff_password").getValue(String.class);
                                if(getPassStaff.equals(pass)){
                                    Toast.makeText(LoginC.this, "Successfully Login!", Toast.LENGTH_SHORT).show();
                                    editor = getSharedPreferences("SHARED_PREF",MODE_PRIVATE).edit();
                                    editor.putString("session",id);editor.apply();
                                    startActivity(new Intent(LoginC.this,DashboardA.class));
                                }else{
                                    Toast.makeText(LoginC.this, "Wrong Password!", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(LoginC.this, "Account not exist!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                else{
                    databaseReference.child("customers").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            check if user exist in database
                                if (snapshot.hasChild(id)) {
                                    //user exist
                                    final String getPass = snapshot.child(id).child("password").getValue(String.class);
                                    if (getPass.equals(pass)) {
                                        Toast.makeText(LoginC.this, "Successfully Login", Toast.LENGTH_SHORT).show();
                                        editor = getSharedPreferences("SHARED_PREF", MODE_PRIVATE).edit();
                                        editor.putString("session", id);
                                        editor.apply();
                                        startActivity(new Intent(LoginC.this, DashboardC.class));
                                    } else {
                                        Toast.makeText(LoginC.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(LoginC.this, "Account not exist!", Toast.LENGTH_SHORT).show();
                                }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }
}