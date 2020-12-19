package com.example.gunawan_api_local;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ProgressBar pb;
    static MyDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(
                getApplicationContext(),MyDatabase.class,"database")
                .allowMainThreadQueries().build();

        //Ambil seluru data dari dao
        List<Meal> meals = db.dataDao().getAll();
        if(meals.size() > 0){
            Intent i = new Intent(getApplicationContext(),MainAppActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            finish();
            return;
        }
        // Ambi data berupa json dari themealdb
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        String url = "https://www.themealdb.com/api/json/v1/1/filter.php?c=Seafood";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(
            Request.Method.GET, url, null,

            new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("cek json", response.toString());
                String id, nama, image;
                try {
                    JSONArray jsonArray = response.getJSONArray("meals");
                    if (jsonArray.length() != 0) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject data = jsonArray.getJSONObject(i);
                            nama = data.getString("strMeal").toString().trim();
                            image = data.getString("strMealThumb").toString().trim();

                            //masukan melalui dao
                            db.dataDao().insertAll(new Meal(nama, image));
                        }
                        // Jika data sudah masuk smua, buka mainactivity
                        Intent i = new Intent(getApplicationContext(), MainAppActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getApplicationContext().startActivity(i);
                        finish(); //tutup activity
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error :", error.toString());
            }

    });
        queue.add(jsObjRequest);
    }
}


















