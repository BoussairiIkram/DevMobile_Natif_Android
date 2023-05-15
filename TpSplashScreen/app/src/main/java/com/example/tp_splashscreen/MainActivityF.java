package com.example.tp_splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityF extends AppCompatActivity {
     TextView nomS,passS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainv2);
        nomS= findViewById(R.id.textnom2);
        passS=findViewById(R.id.textpas2);
        String nomtxt= getIntent().getStringExtra("nom");
        String passtxt= getIntent().getStringExtra("password");
        System.out.println(nomtxt);
        System.out.println(nomS);
        nomS.setText("Email :      "+nomtxt);
        passS.setText("Mot de passe :   "+passtxt);
    }
    public void retour(View view) {
        Intent F=new Intent(this,MainActivity.class);
        startActivity(F);

    }
}