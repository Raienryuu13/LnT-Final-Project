package com.cy.kenny;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    private SharedPreference sharedPreference;
    private TextInputEditText id_bimbel, id_name, id_email, id_password, id_password_confirm;
    private MaterialButton button_register;
    private MaterialTextView textView2;
    private ArrayList<User> userArrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sharedPreference = new SharedPreference(this);
        loadData();

        id_bimbel = findViewById(R.id.id_bimbel);
        id_name = findViewById(R.id.id_name);
        id_email = findViewById(R.id.id_email);
        id_password = findViewById(R.id.id_password);
        id_password_confirm = findViewById(R.id.id_password_confirm);
        textView2 = findViewById(R.id.textView2);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });
        button_register = findViewById(R.id.button_register);
        button_register.setOnClickListener(view -> {
            String split[] = id_email.getText().toString().split("\\.");
            if(id_bimbel.getText().toString().equals("")){
                Toast.makeText(RegisterActivity.this, "ID Bimbel wajib diisi.", Toast.LENGTH_SHORT).show();
            }else if(id_name.getText().toString().equals("") || id_name.getText().toString().length() < 5){
                Toast.makeText(RegisterActivity.this, "Nama wajib diisi dan minimal 5 huruf.", Toast.LENGTH_SHORT).show();
            }else if(!id_email.getText().toString().contains("@")){
                Toast.makeText(RegisterActivity.this, "Format email salah.", Toast.LENGTH_SHORT).show();
            }else if(!split[split.length - 1].equals("com")){
                Toast.makeText(RegisterActivity.this, "Email harus menggunakan dot com.", Toast.LENGTH_SHORT).show();
            }else if(id_password.getText().toString().equals("")){
                Toast.makeText(RegisterActivity.this, "Password wajib diisi.", Toast.LENGTH_SHORT).show();
            }else if(!id_password.getText().toString().equals(id_password_confirm.getText().toString())){
                Toast.makeText(RegisterActivity.this, "Password Confirm tidak sama dengan.", Toast.LENGTH_SHORT).show();
            }else{
                saveData();
            }
        });
    }

    public void loadData(){
        Gson gson = new Gson();
        String json = sharedPreference.getUser();
        Type type = new TypeToken<ArrayList<User>>() {}.getType();
        userArrayList = gson.fromJson(json, type);
        if(userArrayList == null){
            userArrayList = new ArrayList<>();
        }
    }

    public void saveData(){
        userArrayList.add(new User(id_bimbel.getText().toString(), id_email.getText().toString(), id_name.getText().toString(), id_password.getText().toString()));
        Gson gson = new Gson();
        String json = gson.toJson(userArrayList);
        sharedPreference.setUser(json);
        loadData();
        Toast.makeText(RegisterActivity.this, "Berhasil melakukan registrasi.", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();
    }
}
