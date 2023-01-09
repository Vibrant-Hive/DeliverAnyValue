package com.deliver.any.value.fragment;

import static com.deliver.any.value.constant.Constants.PHONE;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.deliver.any.value.R;
import com.deliver.any.value.activity.AreaMapActivity;
import com.deliver.any.value.databinding.FragmentEnterOtpBinding;

public class EnterOTPFragment extends Fragment {

    private FragmentEnterOtpBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentEnterOtpBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSecond.setOnClickListener(view1 -> startActivity(new Intent(this.getContext(), AreaMapActivity.class)));

        ((TextView) view.findViewById(R.id.mobileNumber)).setText(PHONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}