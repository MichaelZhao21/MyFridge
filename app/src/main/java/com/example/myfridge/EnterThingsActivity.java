package com.example.myfridge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
                    changeBoldState((TextView) view);
                }
            });
        }

        Button next = findViewById(R.id.search);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = setIngredients(view);
                getRecipesAndStartIntent(s);
            }
        });

    }

    private void changeBoldState(TextView tv) {
        if (tv.getTypeface().getStyle()== Typeface.BOLD) {
            tv.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            list.remove(tv);
        }
        else {
            tv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            list.add(tv);
        }
    }

    private String setIngredients(View view) {
        StringBuilder sb = new StringBuilder();
        for (TextView tv : list) {
            sb.append(tv.getText());
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private void getRecipesAndStartIntent(String in) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "fridgeapp-268323.appspot.com/food";
        final String ina = in;
        StringRequest req = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Intent next = new Intent(EnterThingsActivity.this, RecipesActivity.class);
                        next.putExtra("recipes", response);
                        startActivity(next);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ERROR", "error => " + error.toString());
                    }
                }
                ) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("names", ina);
                        return params;
                    }
        };
        queue.add(req);
    }

}
