package com.cy.kenny.ui.area;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.cy.kenny.R;
import com.cy.kenny.databinding.FragmentAreaBinding;

import java.util.ArrayList;
import java.util.List;

public class AreaFragment extends Fragment {

    private FragmentAreaBinding binding;
    private List<String> dataBangunDatar;
    private ArrayAdapter<String> adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentAreaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dataBangunDatar = new ArrayList<>();
        dataBangunDatar.add("-- Pilih Bangun Datar --");
        dataBangunDatar.add("Persegi");
        dataBangunDatar.add("Segitiga");
        dataBangunDatar.add("Lingkaran");

        adapter = new ArrayAdapter<>(getContext(), R.layout.templates_dropdown, dataBangunDatar);
        binding.spinnerBangunDatar.setAdapter(adapter);
        binding.spinnerBangunDatar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(), binding.spinnerBangunDatar.getText().toString(), Toast.LENGTH_SHORT).show();
                if(binding.spinnerBangunDatar.getText().toString().equals("Persegi")){
                    binding.persegi.setVisibility(View.VISIBLE);
                    binding.segitiga.setVisibility(View.GONE);
                    binding.lingkaran.setVisibility(View.GONE);
                }else if(binding.spinnerBangunDatar.getText().toString().equals("Segitiga")){
                    binding.persegi.setVisibility(View.GONE);
                    binding.segitiga.setVisibility(View.VISIBLE);
                    binding.lingkaran.setVisibility(View.GONE);
                }else if(binding.spinnerBangunDatar.getText().toString().equals("Lingkaran")){
                    binding.persegi.setVisibility(View.GONE);
                    binding.segitiga.setVisibility(View.GONE);
                    binding.lingkaran.setVisibility(View.VISIBLE);
                }else{
                    binding.persegi.setVisibility(View.GONE);
                    binding.segitiga.setVisibility(View.GONE);
                    binding.lingkaran.setVisibility(View.GONE);
                }
            }
        });

        binding.buttonHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.spinnerBangunDatar.getText().toString().equals("Persegi")){
                    try{
                        int sisi = Integer.parseInt(binding.editTextPersegiSisi.getText().toString());
                        int keliling = sisi * 4;
                        int luas = sisi * sisi;
                        binding.keliling.setText("Keliling : " + keliling + " cm");
                        binding.luas.setText("Luas : " + luas + " cm2");
                    }catch (NumberFormatException e){
                        Toast.makeText(getContext(), "Inputan salah.", Toast.LENGTH_SHORT).show();
                    }
                }else if(binding.spinnerBangunDatar.getText().toString().equals("Segitiga")){
                    try{
                        double alas = Double.parseDouble(binding.editTextSegitigaAlas.getText().toString());
                        double tinggi = Double.parseDouble(binding.editTextSegitigaTinggi.getText().toString());
                        double keliling = alas * 3;
                        double luas = 0.5 * alas * tinggi;
                        binding.keliling.setText("Keliling : " + keliling + " cm");
                        binding.luas.setText("Luas : " + luas + " cm2");
                    }catch (NumberFormatException e){
                        Toast.makeText(getContext(), "Inputan salah.", Toast.LENGTH_SHORT).show();
                    }
                }else if(binding.spinnerBangunDatar.getText().toString().equals("Lingkaran")){
                    try{
                        double diameter = Double.parseDouble(binding.editTextLingkaranDiameter.getText().toString());
                        double keliling = 3.14 * diameter;
                        double luas = 3.14 * (0.5 * diameter) * (0.5 * diameter);
                        binding.keliling.setText("Keliling : " + keliling + " cm");
                        binding.luas.setText("Luas : " + luas + " cm2");
                    }catch (NumberFormatException e){
                        Toast.makeText(getContext(), "Inputan salah.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(), "Pilih bidang terlebih dahulu.", Toast.LENGTH_SHORT).show();
                }
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