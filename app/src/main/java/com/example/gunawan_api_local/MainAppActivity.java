package com.example.gunawan_api_local;

import android.os.Bundle;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainAppActivity extends AppCompatActivity {
    DataAdapter adapter;
    List<Meal> meals;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_app_activity);
        showRecycleView();
    }

    private void showRecycleView() {
        RecyclerView view = (RecyclerView) findViewById(R.id.rv_meal);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this,2);
        view.setLayoutManager(mLayoutManager);

        meals = MainActivity.db.dataDao().getAll(); //Ambil semua data
        adapter = new DataAdapter(meals, this);
        view.setAdapter(adapter);
    }
}











