package com.example.tp_splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    TextInputLayout textInputLayout;

    TextInputLayout textInputLayoutpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textInputLayout = findViewById(R.id.textNom);
        textInputLayoutpass = findViewById(R.id.textPass);
    }

    public void passage(View view) {
        String text = String.valueOf(textInputLayout.getEditText().getText());
        String passtxt = String.valueOf(textInputLayoutpass.getEditText().getText());
        Intent F=new Intent(this,MainActivityF.class);
        F.putExtra("nom",text);
        F.putExtra("password",passtxt);
        startActivity(F);

    }


}