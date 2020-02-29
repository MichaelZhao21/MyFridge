package com.example.myfridge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class RecipesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        Intent intent = getIntent();
        String rec = intent.getStringExtra("recipes");

        try {
            JSONObject recipes = new JSONObject(rec);
        } catch (JSONException e) {
            Log.e("Error: ", e.toString());
        }
    }
}
