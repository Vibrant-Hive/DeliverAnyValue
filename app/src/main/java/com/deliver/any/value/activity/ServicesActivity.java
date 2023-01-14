package com.deliver.any.value.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.deliver.any.value.databinding.ActivityServicesBinding;

public class ServicesActivity extends AppCompatActivity {

    private ActivityServicesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityServicesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}