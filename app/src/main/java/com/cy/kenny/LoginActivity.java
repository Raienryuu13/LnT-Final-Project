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

public class LoginActivity extends AppCompatActivity {

    private SharedPreference sharedPreference;
    private TextInputEditText id_email, id_password;
    private MaterialButton button_login;
    private MaterialTextView textView2;
    private ArrayList<User> userArrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreference = new SharedPreference(this);

        id_email = findViewById(R.id.id_email);
        id_password = findViewById(R.id.id_password);
        textView2 = findViewById(R.id.textView2);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });
        button_login = findViewById(R.id.button_login);
        button_login.setOnClickListener(view -> {
            String split[] = id_email.getText().toString().split("\\.");
            if(!id_email.getText().toString().contains("@")){
                Toast.makeText(LoginActivity.this, "Format email salah.", Toast.LENGTH_SHORT).show();
            }else if(!split[split.length - 1].equals("com")){
                Toast.makeText(LoginActivity.this, "Email harus menggunakan dot com.", Toast.LENGTH_SHORT).show();
            }else if(id_password.getText().toString().equals("")){
                Toast.makeText(LoginActivity.this, "Password wajib diisi.", Toast.LENGTH_SHORT).show();
            }else{
                loadData();
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
            Toast.makeText(LoginActivity.this, "User tidak terdaftar.", Toast.LENGTH_SHORT).show();
        }else{
            for (int i = 0; i < userArrayList.size(); i++) {
                if(userArrayList.get(i).getEmail().equals(id_email.getText().toString())){
                    if(userArrayList.get(i).getPassword().equals(id_password.getText().toString())){
                        Toast.makeText(LoginActivity.this, "Selamat datang, " + userArrayList.get(i).getName(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this, "Periksa kembali email / password Anda.", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
            }
        }
    }
}
