package com.javacodegeeks.ccum;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;

public class TotalC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.totalc);

        //hide actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Button back2 = (Button)findViewById(R.id.btnmenu);
        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TotalC.this,DashboardC.class));
            }
        });

        // get the text from MainActivity
        Intent intent = getIntent();
        String totTime = intent.getStringExtra("TOTALTIME");
        String totAmount = intent.getStringExtra("TOTALAMOUNT");
//        int secs = Integer.parseInt(totTime);
//        Double totAmount = secs * 0.03;
//        String totalAmount = String.valueOf(totAmount);

        TextView resTime = (TextView)findViewById(R.id.totTime);
        TextView resAmount = (TextView)findViewById(R.id.totAmount);
        resTime.setText(totTime+" seconds");
        resAmount.setText("RM "+totAmount);

    }
}