package com.deliver.any.value.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.deliver.any.value.R;
import com.deliver.any.value.adapter.ServicesAdapter;
import com.deliver.any.value.model.ServiceData;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class ServicesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ServicesAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        List<ServiceData> list = getData();

        recyclerView
                = (RecyclerView) findViewById(
                R.id.recyclerView);
        adapter = new ServicesAdapter(list, getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(ServicesActivity.this));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    // Sample data for RecyclerView
    private List<ServiceData> getData() {
        List<ServiceData> list = new ArrayList<>();
        list.add(new ServiceData("Ironing", "Rs. 100"));
        list.add(new ServiceData("Normal Wash", "Rs. 200"));
        list.add(new ServiceData("Wash + Ironing", "Rs. 300"));
        list.add(new ServiceData("Dry Wash", "Rs. 400"));
        return list;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}