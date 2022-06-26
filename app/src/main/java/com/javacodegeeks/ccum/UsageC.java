package com.javacodegeeks.ccum;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UsageC extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    ListView usgDisplay;
    ArrayList<usage> ul = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usagec);

        //hide actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //receive matrix no
        SharedPreferences prefs = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);
        String cMatrix = prefs.getString("session",null);

        databaseReference.child("pc_usage").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    usage obj = new usage();
                    String matrix = snapshot.child("matrix_no").getValue(String.class);
                    if(cMatrix.equals(matrix)) {
                        String pckey = snapshot.getKey();
                        String pcid = snapshot.child("pc_id").getValue(String.class);
                        String pcname = snapshot.child("pc_name").getValue(String.class);
                        Double amount = Double.parseDouble(snapshot.child("total_amount").getValue(String.class));
                        Double time = Double.parseDouble(snapshot.child("total_time").getValue(String.class));
                        String dataset = snapshot.child("date").getValue(String.class);
                        obj.setKey(pckey);
                        obj.setMatrix(matrix);
                        obj.setPcid(pcid);
                        obj.setPcname(pcname);
                        obj.setTotamount(amount);
                        obj.setTottime(time);
                        obj.setDateset(dataset);
                        ul.add(obj);
                    }
                    CustomAdapterC customAdapter = new CustomAdapterC(getApplicationContext(),ul);
                    usgDisplay = (ListView)findViewById(R.id.displayusage);
                    usgDisplay.setAdapter(customAdapter);

                    usgDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            for(int i=0;i<=ul.size();i++){
                                if(position==i){
                                    usage pc = (usage)ul.get(i);
                                    String pc_key = pc.getKey();
                                    Intent intent = new Intent(UsageC.this, DisplayUsageC.class);
                                    intent.putExtra(Intent.EXTRA_TEXT, pc_key);
                                    startActivity(intent);
                                }
                            }
                        }
                    });
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Button bck = (Button)findViewById(R.id.backbtn5);
        bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UsageC.this,DashboardC.class));
            }
        });
    }
}