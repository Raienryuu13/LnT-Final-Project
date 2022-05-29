package com.cy.kenny.ui.counter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.cy.kenny.SharedPreference;
import com.cy.kenny.databinding.FragmentCounterBinding;

public class CounterFragment extends Fragment {

    private FragmentCounterBinding binding;
    private SharedPreference sharedPreference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCounterBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sharedPreference = new SharedPreference(getContext());
        binding.editTextCounter.setText(String.valueOf(sharedPreference.getCounter()));

        binding.buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nilai = Integer.parseInt(binding.editTextCounter.getText().toString());
                if(nilai > 0){
                    nilai--;
                    binding.editTextCounter.setText(String.valueOf(nilai));
                    sharedPreference.setCounter(nilai);
                }
            }
        });

        binding.buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nilai = Integer.parseInt(binding.editTextCounter.getText().toString());
                nilai++;
                binding.editTextCounter.setText(String.valueOf(nilai));
                sharedPreference.setCounter(nilai);
            }
        });

        binding.buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editTextCounter.setText("0");
                sharedPreference.setCounter(0);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}