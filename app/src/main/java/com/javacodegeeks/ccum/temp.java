package com.javacodegeeks.ccum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class temp extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        EditText pcname = (EditText)findViewById(R.id.editpcName);
        EditText pcid = (EditText)findViewById(R.id.editpcid);
        Button addbrn = (Button)findViewById(R.id.addpcbtn);

        addbrn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pc_name = pcname.getText().toString();
                String pc_id = pcid.getText().toString();

                databaseReference.child("Pc_List").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if(snapshot.hasChild(pc_id)){
                            databaseReference.child("Pc_List").child(pc_id).child("status").setValue("occupied");
                            Toast.makeText(temp.this, "status inserted", Toast.LENGTH_SHORT).show();
                        }else {
                            databaseReference.child("Pc_List").child(pc_id).child("pc_name").setValue(pc_name);
                            Toast.makeText(temp.this, "Successfully added!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                });
            }
        });
    }
}