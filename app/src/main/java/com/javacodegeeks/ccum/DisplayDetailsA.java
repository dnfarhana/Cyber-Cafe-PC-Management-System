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

public class DisplayDetailsA extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displaydetailsa);

        //hide actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // get the key from previous activity
        Intent intent = getIntent();
        String pckey = intent.getStringExtra(Intent.EXTRA_TEXT);

        TextView pc_matrix = (TextView)findViewById(R.id.ddamatrix);
        TextView pc_no = (TextView)findViewById(R.id.ddapcno);
        TextView pc_amount = (TextView)findViewById(R.id.ddaamount);
        TextView pc_time = (TextView)findViewById(R.id.ddatime);
        TextView pc_date = (TextView)findViewById(R.id.ddadate);
        Button back = (Button)findViewById(R.id.backbtn3);

        databaseReference.child("pc_usage").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(pckey)){
                    String matrix = snapshot.child(pckey).child("matrix_no").getValue(String.class);
                    String id = snapshot.child(pckey).child("pc_id").getValue(String.class);
                    String no = snapshot.child(pckey).child("pc_name").getValue(String.class);
                    String amount = snapshot.child(pckey).child("total_amount").getValue(String.class);
                    String time = snapshot.child(pckey).child("total_time").getValue(String.class);
                    String date = snapshot.child(pckey).child("date").getValue(String.class);

                    pc_matrix.setText(matrix);
                    pc_no.setText(no);
                    pc_amount.setText("RM"+amount);
                    pc_time.setText(time+" seconds");
                    pc_date.setText(date);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DisplayDetailsA.this,DashboardA.class));
            }
        });
    }
}