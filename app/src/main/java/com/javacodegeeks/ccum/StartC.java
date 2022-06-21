package com.javacodegeeks.ccum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StartC extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startc);

        //hide actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // get the text from MainActivity
        Intent intent = getIntent();
        String pcid = intent.getStringExtra(Intent.EXTRA_TEXT);

        // use the text in a TextView
        TextView viewid = (TextView) findViewById(R.id.getpcNo);
        databaseReference.child("Pc_List").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(pcid)){
                    String pcname = snapshot.child(pcid).child("pc_name").getValue(String.class);
                    viewid.setText(pcname);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Button scanqr = (Button)findViewById(R.id.btnscanqr);
        scanqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bring pc id to next page
                Intent intent = new Intent(StartC.this, ScanqrC.class);
                intent.putExtra(Intent.EXTRA_TEXT, pcid);
                startActivity(intent);
            }
        });
    }
}