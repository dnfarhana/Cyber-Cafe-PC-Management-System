package com.javacodegeeks.ccum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PcAvailableC extends AppCompatActivity {
    //tanya orang whether buat array or not
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    String statuspc1,statuspc2, statuspc3,statuspc4,statuspc5,statuspc6;
//    String name1,name2,name3,name4,name5,name6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pcavailablec);

        //hide actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //session
        SharedPreferences prefs = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);
        String cMatrix = prefs.getString("session",null);

        //display status for each pc
        TextView status1 = (TextView)findViewById(R.id.statusPc1);
        TextView status2 = (TextView)findViewById(R.id.statusPc2);
        TextView status3 = (TextView)findViewById(R.id.statusPc3);
        TextView status4 = (TextView)findViewById(R.id.statusPc4);
        TextView status5 = (TextView)findViewById(R.id.statusPc5);
        TextView status6 = (TextView)findViewById(R.id.statusPc6);

        //start use
        ImageView start1 = (ImageView)findViewById(R.id.pc1);
        ImageView start2 = (ImageView)findViewById(R.id.pc2);
        ImageView start3 = (ImageView)findViewById(R.id.pc3);
        ImageView start4 = (ImageView)findViewById(R.id.pc4);
        ImageView start5 = (ImageView)findViewById(R.id.pc5);
        ImageView start6 = (ImageView)findViewById(R.id.pc6);

        Button back = (Button)findViewById(R.id.backbtn6);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PcAvailableC.this,DashboardC.class));
            }
        });

        databaseReference.child("Pc_List").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //get Status for each pc
                statuspc1 = snapshot.child("CCPC0001").child("status").getValue(String.class);
                statuspc2 = snapshot.child("CCPC0002").child("status").getValue(String.class);
                statuspc3 = snapshot.child("CCPC0003").child("status").getValue(String.class);
                statuspc4 = snapshot.child("CCPC0004").child("status").getValue(String.class);
                statuspc5 = snapshot.child("CCPC0005").child("status").getValue(String.class);
                statuspc6 = snapshot.child("CCPC0006").child("status").getValue(String.class);

//                //get name for each pc
//                name1 = snapshot.child("CCPC0001").child("pc_name").getValue(String.class);
//                name2 = snapshot.child("CCPC0002").child("pc_name").getValue(String.class);
//                name3 = snapshot.child("CCPC0003").child("pc_name").getValue(String.class);
//                name4 = snapshot.child("CCPC0004").child("pc_name").getValue(String.class);
//                name5 = snapshot.child("CCPC0005").child("pc_name").getValue(String.class);
//                name6 = snapshot.child("CCPC0006").child("pc_name").getValue(String.class);

                status1.setText(statuspc1);
                status2.setText(statuspc2);
                status3.setText(statuspc3);
                status4.setText(statuspc4);
                status5.setText(statuspc5);
                status6.setText(statuspc6);

                //1
                start1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(statuspc1.equalsIgnoreCase("available")){
                            //bring pc id to next page
                            String pcId = "CCPC0001";
                            Intent intent = new Intent(PcAvailableC.this, StartC.class);
                            intent.putExtra(Intent.EXTRA_TEXT, pcId);
                            startActivity(intent);
                        }else{
                            Toast.makeText(PcAvailableC.this, "You need to wait until the pc status become available", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                //2
                start2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(statuspc2.equalsIgnoreCase("available")){
                            //bring pc id to next page
                            String pcId = "CCPC0002";
                            Intent intent = new Intent(PcAvailableC.this, StartC.class);
                            intent.putExtra(Intent.EXTRA_TEXT, pcId);
                            startActivity(intent);
                        }else{
                            Toast.makeText(PcAvailableC.this, "You need to wait until the pc status become available", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                //3
                start3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(statuspc3.equalsIgnoreCase("available")){
                            //bring pc id to next page
                            String pcId = "CCPC0003";
                            Intent intent = new Intent(PcAvailableC.this, StartC.class);
                            intent.putExtra(Intent.EXTRA_TEXT, pcId);
                            startActivity(intent);
                        }else{
                            Toast.makeText(PcAvailableC.this, "You need to wait until the pc status become available", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                //4
                start4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(statuspc4.equalsIgnoreCase("available")){
                            //bring pc id to next page
                            String pcId = "CCPC0004";
                            Intent intent = new Intent(PcAvailableC.this, StartC.class);
                            intent.putExtra(Intent.EXTRA_TEXT, pcId);
                            startActivity(intent);
                        }else{
                            Toast.makeText(PcAvailableC.this, "You need to wait until the pc status become available", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                //5
                start5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(statuspc5.equalsIgnoreCase("available")){
                            //bring pc id to next page
                            String pcId = "CCPC0005";
                            Intent intent = new Intent(PcAvailableC.this, StartC.class);
                            intent.putExtra(Intent.EXTRA_TEXT, pcId);
                            startActivity(intent);
                        }else{
                            Toast.makeText(PcAvailableC.this, "You need to wait until the pc status become available", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                //6
                start6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(statuspc6.equalsIgnoreCase("available")){
                            //bring pc id to next page
                            String pcId = "CCPC0006";
                            Intent intent = new Intent(PcAvailableC.this, StartC.class);
                            intent.putExtra(Intent.EXTRA_TEXT, pcId);
                            startActivity(intent);
                        }else{
                            Toast.makeText(PcAvailableC.this, "You need to wait until the pc status become available", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

    }
}