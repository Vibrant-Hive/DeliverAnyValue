package com.deliver.any.value;

import static com.deliver.any.value.constants.Constants.MOBILE_NUMBER;
import static com.deliver.any.value.constants.Constants.PREFERENCES;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.deliver.any.value.databinding.FragmentEnterOtpBinding;
import com.deliver.any.value.util.CommonUtilities;

public class Second2Fragment extends Fragment {

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

        binding.buttonSecond.setOnClickListener(view1 -> NavHostFragment.findNavController(Second2Fragment.this)
                .navigate(R.id.action_Second2Fragment_to_First2Fragment));

        ((TextView) view.findViewById(R.id.mobileNumber)).setText(CommonUtilities.getPrefString(MOBILE_NUMBER));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}