package com.example.myfridge;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class EnterThingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_things);

        ScrollView sv = findViewById(R.id.ingredients);
        LinearLayout l = (LinearLayout) sv.getChildAt(0);
        for (int i = 0; i < l.getChildCount(); i++) {
            l.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView tv = (TextView) view;
                    if (tv.getTypeface().getStyle()== Typeface.BOLD)
                        tv.setTypeface(null, Typeface.NORMAL);
                    else
                        tv.setTypeface(null, Typeface.BOLD);
                }
            });
        }
    }
}
