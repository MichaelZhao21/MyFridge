package com.example.myfridge;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class EnterThingsActivity extends AppCompatActivity {

    ArrayList<TextView> list = new ArrayList<>();

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
                    if (tv.getTypeface().getStyle()== Typeface.BOLD) {
                        tv.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                        list.remove(tv);
                    }
                    else {
                        tv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                        list.add(tv);
                    }
                }
            });
        }

        Button next = findViewById(R.id.search);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder sb = new StringBuilder();
                for (TextView tv : list) {
                    sb.append(tv.getText());
                    sb.append(",");
                }
                sb.deleteCharAt(sb.length() - 1);
                TextView title = findViewById(R.id.ingredientsTitle);
                title.setText(sb.toString());
            }
        });

    }
}
