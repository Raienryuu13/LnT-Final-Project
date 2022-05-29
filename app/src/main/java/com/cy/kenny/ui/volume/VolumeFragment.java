package com.cy.kenny.ui.volume;

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
import com.cy.kenny.databinding.FragmentVolumeBinding;

import java.util.ArrayList;
import java.util.List;

public class VolumeFragment extends Fragment {

    private FragmentVolumeBinding binding;
    private List<String> dataBangunDatar;
    private ArrayAdapter<String> adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentVolumeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dataBangunDatar = new ArrayList<>();
        dataBangunDatar.add("-- Pilih Bangun Datar --");
        dataBangunDatar.add("Balok");
        dataBangunDatar.add("Piramid");
        dataBangunDatar.add("Tabung");

        adapter = new ArrayAdapter<>(getContext(), R.layout.templates_dropdown, dataBangunDatar);
        binding.spinnerBangunDatar.setAdapter(adapter);
        binding.spinnerBangunDatar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(binding.spinnerBangunDatar.getText().toString().equals("Balok")){
                    binding.persegi.setVisibility(View.VISIBLE);
                    binding.segitiga.setVisibility(View.GONE);
                    binding.lingkaran.setVisibility(View.GONE);
                }else if(binding.spinnerBangunDatar.getText().toString().equals("Piramid")){
                    binding.persegi.setVisibility(View.GONE);
                    binding.segitiga.setVisibility(View.VISIBLE);
                    binding.lingkaran.setVisibility(View.GONE);
                }else if(binding.spinnerBangunDatar.getText().toString().equals("Tabung")){
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
                if(binding.spinnerBangunDatar.getText().toString().equals("Balok")){
                    try{
                        int tinggi = Integer.parseInt(binding.editTextPersegiTinggi.getText().toString());
                        int panjang = Integer.parseInt(binding.editTextPersegiPanjang.getText().toString());
                        int lebar = Integer.parseInt(binding.editTextPersegiLebar.getText().toString());
                        int volume = tinggi * panjang * lebar;
                        binding.keliling.setText("Volume : " + volume + " cm3");
                    }catch (NumberFormatException e){
                        Toast.makeText(getContext(), "Inputan salah.", Toast.LENGTH_SHORT).show();
                    }
                }else if(binding.spinnerBangunDatar.getText().toString().equals("Piramid")){
                    try{
                        double panjang = Double.parseDouble(binding.editTextSegitigaPanjang.getText().toString());
                        double lebar = Double.parseDouble(binding.editTextSegitigaLebar.getText().toString());
                        double tinggi = Double.parseDouble(binding.editTextSegitigaTinggi.getText().toString());
                        double volume = (panjang * lebar * tinggi) / 3;
                        binding.keliling.setText("Volume : " + volume + " cm3");
                    }catch (NumberFormatException e){
                        Toast.makeText(getContext(), "Inputan salah.", Toast.LENGTH_SHORT).show();
                    }
                }else if(binding.spinnerBangunDatar.getText().toString().equals("Tabung")){
                    try{
                        double diameter = Double.parseDouble(binding.editTextLingkaranDiameter.getText().toString());
                        double tinggi = Double.parseDouble(binding.editTextLingkaranTinggi.getText().toString());
                        double volume = 3.14 * (0.5 * diameter) * (0.5 * diameter) * tinggi;
                        binding.keliling.setText("Volume : " + volume + " cm3");
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