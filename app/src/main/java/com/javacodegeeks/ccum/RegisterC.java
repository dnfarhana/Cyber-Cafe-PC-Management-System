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

public class RegisterC extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerc);
        //hide actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        final EditText cusName = (EditText)findViewById(R.id.editNameRegis);
        final EditText cusMatrix = (EditText)findViewById(R.id.editMatrixRegis);
        final EditText cusPhone = (EditText)findViewById(R.id.editPhoneRegis);
        final EditText cusPass = (EditText)findViewById(R.id.editPasswordRegis);
        final EditText cusCon = (EditText)findViewById(R.id.editConfirmRegis);
        final TextView loginNow = (TextView)findViewById(R.id.signin);
        final Button reg = (Button)findViewById(R.id.registerbtn);

        loginNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterC.this,LoginC.class));
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String cName = cusName.getText().toString();
                final String cMatrix = cusMatrix.getText().toString();
                final String cPhone = cusPhone.getText().toString();
                final String cPass = cusPass.getText().toString();
                final String cCon = cusCon.getText().toString();

                //check if user has enter all data
                if(cName.isEmpty() || cMatrix.isEmpty() || cPhone.isEmpty() || cPass.isEmpty() || cCon.isEmpty()){
                    Toast.makeText(RegisterC.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
                //check if passwords are matching
                else if(!cPass.equals(cCon)){
                    Toast.makeText(RegisterC.this, "Passwords are not matching", Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseReference.child("customers").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            //check if user has been registered
                            if(snapshot.hasChild(cMatrix)){
                                Toast.makeText(RegisterC.this, "User already registered!", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                databaseReference.child("customers").child(cMatrix).child("name").setValue(cName);
                                databaseReference.child("customers").child(cMatrix).child("phone").setValue(cPhone);
                                databaseReference.child("customers").child(cMatrix).child("password").setValue(cPass);

                                //success message
                                Toast.makeText(RegisterC.this, "User successfully registered!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterC.this, LoginC.class));
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