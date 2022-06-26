package com.javacodegeeks.ccum;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PcReportA extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    ListView pcDisplay;
    ArrayList<usage> pcl = new ArrayList<>();
    Double totaltime = 0.00;
    Double avg = 0.00;
    int count = 0;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pcreporta);

        //hide actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        databaseReference.child("pc_usage").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                usage obj = new usage();
                String pckey = snapshot.getKey();
                String matrix = snapshot.child("matrix_no").getValue(String.class);
                String pcid = snapshot.child("pc_id").getValue(String.class);
                String pcname = snapshot.child("pc_name").getValue(String.class);
                Double amount = Double.parseDouble(snapshot.child("total_amount").getValue(String.class));
                Double time = Double.parseDouble(snapshot.child("total_time").getValue(String.class));
                String dateset = snapshot.child("date").getValue(String.class);
                    obj.setKey(pckey);
                    obj.setMatrix(matrix);
                    obj.setPcid(pcid);
                    obj.setPcname(pcname);
                    obj.setTotamount(amount);
                    obj.setTottime(time);
                    obj.setDateset(dateset);
                    pcl.add(obj);

                CustomAdapterPcA customAdapterPcA = new CustomAdapterPcA(getApplicationContext(),pcl);
                pcDisplay = (ListView)findViewById(R.id.displaypcusage);
                pcDisplay.setAdapter(customAdapterPcA);

                pcDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        for(int i=0;i<=pcl.size();i++){
                            if(position==i){
                                usage pc = (usage)pcl.get(i);
                                String pc_key = pc.getKey();
                                Intent intent = new Intent(PcReportA.this, DisplayDetailsA.class);
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

        TextView tottime = (TextView)findViewById(R.id.pcatottime);
        TextView avgtime = (TextView)findViewById(R.id.pcaavgtime);

        //count total time and average time usage
        databaseReference.child("pc_usage").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    Double t = Double.parseDouble(ds.child("total_time").getValue(String.class));
                    totaltime += t;
                    count++;

                }
                String stotaltime = String.valueOf(df.format(totaltime));
                tottime.setText(stotaltime+" seconds");
                //count average
                avg = totaltime / count;
                String savg = String.valueOf(df.format(avg));
                avgtime.setText(savg+" seconds");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}