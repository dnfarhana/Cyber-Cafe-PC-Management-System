package com.javacodegeeks.ccum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateProfileC extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updateprofilec);

        //hide actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        SharedPreferences prefs = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);
        String cMatrix = prefs.getString("session",null);

        EditText upname = (EditText)findViewById(R.id.updateNameC);
        EditText upmatrix = (EditText)findViewById(R.id.updateMatrixC);
        EditText upphone = (EditText)findViewById(R.id.updatePhoneC);
        Button update = (Button)findViewById(R.id.updatebtn);

        //get data
        databaseReference.child("customers").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if(snapshot.hasChild(cMatrix)){
                    String cName = snapshot.child(cMatrix).child("name").getValue(String.class);
                    String cPhone = snapshot.child(cMatrix).child("phone").getValue(String.class);
                    upname.setText(cName);
                    upmatrix.setText(cMatrix);
                    upphone.setText(cPhone);

                    //make matrix no to be readonly
                    upmatrix.setEnabled(false);
                    upmatrix.setTextColor(Color.parseColor("#918C8C"));

                    update.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //update data
                            String newName = upname.getText().toString();
                            String newPhone = upphone.getText().toString();
                            databaseReference.child("customers").child(cMatrix).child("name").setValue(newName);
                            databaseReference.child("customers").child(cMatrix).child("phone").setValue(newPhone);

                            //success message
                            Toast.makeText(UpdateProfileC.this, "Successfully update", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(UpdateProfileC.this,ProfileC.class));
                        }
                    });

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }
}