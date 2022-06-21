package com.javacodegeeks.ccum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class updateStatusA extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    String pc1,pc2,pc3,pc4,pc5,pc6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatestatusa);

        //hide actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        SharedPreferences prefs = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);
        String cMatrix = prefs.getString("session",null);

        //display status for each pc
        TextView spc1 = (TextView)findViewById(R.id.ustatusPc1);
        TextView spc2 = (TextView)findViewById(R.id.ustatusPc2);
        TextView spc3 = (TextView)findViewById(R.id.ustatusPc3);
        TextView spc4 = (TextView)findViewById(R.id.ustatusPc4);
        TextView spc5 = (TextView)findViewById(R.id.ustatusPc5);
        TextView spc6 = (TextView)findViewById(R.id.ustatusPc6);

        //change status
        ImageView uspc1 = (ImageView)findViewById(R.id.uspc1);
        ImageView uspc2 = (ImageView)findViewById(R.id.uspc2);
        ImageView uspc3 = (ImageView)findViewById(R.id.uspc3);
        ImageView uspc4 = (ImageView)findViewById(R.id.uspc4);
        ImageView uspc5 = (ImageView)findViewById(R.id.uspc5);
        ImageView uspc6 = (ImageView)findViewById(R.id.uspc6);

        databaseReference.child("Pc_List").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //get status for each pc
                pc1 = snapshot.child("CCPC0001").child("status").getValue(String.class);
                pc2 = snapshot.child("CCPC0002").child("status").getValue(String.class);
                pc3 = snapshot.child("CCPC0003").child("status").getValue(String.class);
                pc4 = snapshot.child("CCPC0004").child("status").getValue(String.class);
                pc5 = snapshot.child("CCPC0005").child("status").getValue(String.class);
                pc6 = snapshot.child("CCPC0006").child("status").getValue(String.class);

                spc1.setText(pc1);
                spc2.setText(pc2);
                spc3.setText(pc3);
                spc4.setText(pc4);
                spc5.setText(pc5);
                spc6.setText(pc6);

                //change status

                //1
                uspc1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(updateStatusA.this);
                        builder.setCancelable(true);
                        builder.setTitle("Change Status");
                        builder.setMessage("Are you sure you want to change status?");
                        builder.setPositiveButton("Confirm",
                                new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(pc1.equalsIgnoreCase("available")){
                                    databaseReference.child("Pc_List").child("CCPC0001").child("status").setValue("Occupied");
                                }else{
                                    databaseReference.child("Pc_List").child("CCPC0001").child("status").setValue("Available");
                                }
                                Toast.makeText(updateStatusA.this, "Successfully update status", Toast.LENGTH_SHORT).show();
                                finish();
                                overridePendingTransition(0, 0);
                                startActivity(getIntent());
                                overridePendingTransition(0, 0);
                            }
                        });
                        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });

                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                });
                //2
                uspc2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(updateStatusA.this);
                        builder.setCancelable(true);
                        builder.setTitle("Change Status");
                        builder.setMessage("Are you sure you want to change status?");
                        builder.setPositiveButton("Confirm",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if(pc2.equalsIgnoreCase("available")){
                                            databaseReference.child("Pc_List").child("CCPC0002").child("status").setValue("Occupied");
                                        }else{
                                            databaseReference.child("Pc_List").child("CCPC0002").child("status").setValue("Available");
                                        }
                                        Toast.makeText(updateStatusA.this, "Successfully update status", Toast.LENGTH_SHORT).show();
                                        finish();
                                        overridePendingTransition(0, 0);
                                        startActivity(getIntent());
                                        overridePendingTransition(0, 0);
                                    }
                                });
                        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });

                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                });
                //3
                uspc3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(updateStatusA.this);
                        builder.setCancelable(true);
                        builder.setTitle("Change Status");
                        builder.setMessage("Are you sure you want to change status?");
                        builder.setPositiveButton("Confirm",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if(pc3.equalsIgnoreCase("available")){
                                            databaseReference.child("Pc_List").child("CCPC0003").child("status").setValue("Occupied");
                                        }else{
                                            databaseReference.child("Pc_List").child("CCPC0003").child("status").setValue("Available");
                                        }
                                        Toast.makeText(updateStatusA.this, "Successfully update status", Toast.LENGTH_SHORT).show();
                                        finish();
                                        overridePendingTransition(0, 0);
                                        startActivity(getIntent());
                                        overridePendingTransition(0, 0);
                                    }
                                });
                        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });

                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                });
                //4
                uspc4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(updateStatusA.this);
                        builder.setCancelable(true);
                        builder.setTitle("Change Status");
                        builder.setMessage("Are you sure you want to change status?");
                        builder.setPositiveButton("Confirm",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if(pc4.equalsIgnoreCase("available")){
                                            databaseReference.child("Pc_List").child("CCPC0004").child("status").setValue("Occupied");
                                        }else{
                                            databaseReference.child("Pc_List").child("CCPC0004").child("status").setValue("Available");
                                        }
                                        Toast.makeText(updateStatusA.this, "Successfully update status", Toast.LENGTH_SHORT).show();
                                        finish();
                                        overridePendingTransition(0, 0);
                                        startActivity(getIntent());
                                        overridePendingTransition(0, 0);
                                    }
                                });
                        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });

                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                });
                //5
                uspc5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(updateStatusA.this);
                        builder.setCancelable(true);
                        builder.setTitle("Change Status");
                        builder.setMessage("Are you sure you want to change status?");
                        builder.setPositiveButton("Confirm",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if(pc5.equalsIgnoreCase("available")){
                                            databaseReference.child("Pc_List").child("CCPC0005").child("status").setValue("Occupied");
                                        }else{
                                            databaseReference.child("Pc_List").child("CCPC0005").child("status").setValue("Available");
                                        }
                                        Toast.makeText(updateStatusA.this, "Successfully update status", Toast.LENGTH_SHORT).show();
                                        finish();
                                        overridePendingTransition(0, 0);
                                        startActivity(getIntent());
                                        overridePendingTransition(0, 0);
                                    }
                                });
                        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });

                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                });
                //6
                uspc6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(updateStatusA.this);
                        builder.setCancelable(true);
                        builder.setTitle("Change Status");
                        builder.setMessage("Are you sure you want to change status?");
                        builder.setPositiveButton("Confirm",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if(pc6.equalsIgnoreCase("available")){
                                            databaseReference.child("Pc_List").child("CCPC0006").child("status").setValue("Occupied");
                                        }else{
                                            databaseReference.child("Pc_List").child("CCPC0006").child("status").setValue("Available");
                                        }
                                        Toast.makeText(updateStatusA.this, "Successfully update status", Toast.LENGTH_SHORT).show();
                                        finish();
                                        overridePendingTransition(0, 0);
                                        startActivity(getIntent());
                                        overridePendingTransition(0, 0);
                                    }
                                });
                        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });

                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}