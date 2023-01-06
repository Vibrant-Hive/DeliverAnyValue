package com.deliver.any.value.fragment;

import static com.deliver.any.value.constant.Constants.MOBILE_NUMBER;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.deliver.any.value.R;
import com.deliver.any.value.databinding.FragmentGetOtpBinding;
import com.deliver.any.value.util.CommonUtilities;

public class SendOTPFragment extends Fragment {

    private FragmentGetOtpBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentGetOtpBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.button.setOnClickListener(view1 -> {
            NavHostFragment.findNavController(SendOTPFragment.this)
                    .navigate(R.id.action_First2Fragment_to_Second2Fragment);
            CommonUtilities.putPrefString(getContext(), MOBILE_NUMBER, ((EditText) view.findViewById(R.id.mobileNo)).getText().toString());
        });

        ((EditText) view.findViewById(R.id.mobileNo)).setText(CommonUtilities.getPrefString(MOBILE_NUMBER));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}