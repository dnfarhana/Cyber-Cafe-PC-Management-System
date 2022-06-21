package com.javacodegeeks.ccum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class RegisterA extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registera);

        //hide actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        EditText staff_name = (EditText)findViewById(R.id.editSNameRegis);
        EditText staff_ic = (EditText)findViewById(R.id.editSIdRegis);
        EditText staff_phone = (EditText)findViewById(R.id.editSPhoneRegis);
        EditText staff_pass = (EditText)findViewById(R.id.editSPassRegis);
        EditText confirm = (EditText)findViewById(R.id.editSConfirmRegis);
        TextView signin = (TextView)findViewById(R.id.link4);
        Button reg = (Button)findViewById(R.id.btnRegisteradmin);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterA.this,LoginA.class));
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String sname = staff_name.getText().toString();
                final String sic = staff_ic.getText().toString();
                final String sphone = staff_phone.getText().toString();
                final String spass = staff_pass.getText().toString();
                final String scon = confirm.getText().toString();
                final String sid = "S"+ sic.substring(8,12);

                //check if all data has been inserted
                if(sname.isEmpty()||sic.isEmpty()||sphone.isEmpty()||spass.isEmpty()||scon.isEmpty()){
                    Toast.makeText(RegisterA.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
                else if(!spass.equals(scon)){
                    Toast.makeText(RegisterA.this, "Passwords are not matching!", Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseReference.child("staff").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            //check if staff has been registered
                            if(snapshot.hasChild(sid)){
                                Toast.makeText(RegisterA.this, "Staff has been registered!", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                databaseReference.child("staff").child(sid).child("staff_name").setValue(sname);
                                databaseReference.child("staff").child(sid).child("staff_phone").setValue(sphone);
                                databaseReference.child("staff").child(sid).child("staff_password").setValue(spass);


                                //Success message
                                Toast.makeText(RegisterA.this, "Successfully registered!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterA.this,LoginA.class));
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {

                        }
                    });
                }
            }
        });
    }
}