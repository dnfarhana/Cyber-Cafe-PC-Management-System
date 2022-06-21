package com.javacodegeeks.ccum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class InprogressC extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    String pcid,getpcname,cMatrix,adminsetdate;
    TextView tvTimer;
    long startTime, timeInMilliseconds = 0;
    Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inprogressc);
        //hide actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //receive date from admin
        //receive date
        SharedPreferences sharedPreferences = getSharedPreferences("setDate", MODE_PRIVATE);
        adminsetdate = sharedPreferences.getString("valuedate","");

        //receive matrix_no
        SharedPreferences prefs = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);
        cMatrix = prefs.getString("session",null);

        // get the pcid from MainActivity
        Intent intent = getIntent();
        pcid = intent.getStringExtra(Intent.EXTRA_TEXT);

        //display pc name
        TextView pcno = (TextView)findViewById(R.id.pcNo);
        databaseReference.child("Pc_List").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(pcid)){
                    String pcname = snapshot.child(pcid).child("pc_name").getValue(String.class);
                    pcno.setText(pcname);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        tvTimer = (TextView) findViewById(R.id.tvTimer);
        //start timer
        startTime = SystemClock.uptimeMillis();
        customHandler.postDelayed(updateTimerThread, 0);
    }
    public static String getDateFromMillis(long d) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        return df.format(d);
    }
    public void stop(View v) {

        customHandler.removeCallbacks(updateTimerThread);
        String time = tvTimer.getText().toString();
        String[] units = time.split(":");
        int hour = Integer.parseInt(units[0]);
        int min = Integer.parseInt(units[1]);
        int seconds = Integer.parseInt(units[2]);
        int totSecs = (3600*hour)+(60*min)+seconds;
        String total = String.valueOf(totSecs);
        int secs = Integer.parseInt(total);
        Double totAmount = secs * 0.03;
        String totalAmount = String.valueOf(totAmount);

        //get pc no
        databaseReference.child("Pc_List").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if(snapshot.hasChild(pcid)){
                    getpcname = snapshot.child(pcid).child("pc_name").getValue(String.class);
                    //change status back to "available"
                    databaseReference.child("Pc_List").child(pcid).child("status").setValue("Available");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        //insert data into pc_usage table
        databaseReference.child("pc_usage").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String usageid = databaseReference.push().getKey();
                databaseReference.child("pc_usage").child(usageid).child("matrix_no").setValue(cMatrix);
                databaseReference.child("pc_usage").child(usageid).child("pc_id").setValue(pcid);
                databaseReference.child("pc_usage").child(usageid).child("pc_name").setValue(getpcname);
                databaseReference.child("pc_usage").child(usageid).child("total_time").setValue(total);
                databaseReference.child("pc_usage").child(usageid).child("total_amount").setValue(totalAmount);
                databaseReference.child("pc_usage").child(usageid).child("date").setValue(adminsetdate);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        bring total seconds to next page
        Intent intent = new Intent(InprogressC.this, TotalC.class);
        intent.putExtra("TOTALTIME", total);
        intent.putExtra("TOTALAMOUNT", totalAmount);
        startActivity(intent);
    }

    private Runnable updateTimerThread = new Runnable() {
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            tvTimer.setText(getDateFromMillis(timeInMilliseconds));
            customHandler.postDelayed(this, 1000);
        }
    };

}