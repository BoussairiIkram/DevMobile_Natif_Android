package com.example.calculatrice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView resultat, data;
    MaterialButton btnAC, btnPG, btnPD, btnDev, btn7, btn8, btn9, btnMult;
    MaterialButton btn4, btn5, btn6, btnSoustr, btn1, btn2, btn3, btnAdd;
    MaterialButton btn0, btnVergule,btnResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultat = findViewById(R.id.resultat);
        data = findViewById(R.id.data);

        assignID(btnAC, R.id.btnAC); assignID(btnPG, R.id.btnPG);
        assignID(btnPD, R.id.btnPD); assignID(btnDev, R.id.btnDev);

        assignID(btn7, R.id.btn7); assignID(btn8, R.id.btn8);
        assignID(btn9, R.id.btn9); assignID(btnMult, R.id.btnMult);

        assignID(btn4, R.id.btn4); assignID(btn5, R.id.btn5);
        assignID(btn6, R.id.btn6); assignID(btnSoustr, R.id.btnSoustr);

        assignID(btn1, R.id.btn1); assignID(btn2, R.id.btn2);
        assignID(btn3, R.id.btn3); assignID(btnAdd, R.id.btnAdd);

        assignID(btn0, R.id.btn0); assignID(btnVergule, R.id.btnVergule);

        assignID(btnResult, R.id.btnResult);

    }

    // set on Click Listner
    void assignID(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton btn = (MaterialButton) view;
        String btnText = btn.getText().toString();
        String dataCalculer = data.getText().toString();
        if(btnText.equals("AC")){
            data.setText("");
            resultat.setText("0");
            return;}
        if(!btnText.equals("=")){
            dataCalculer+= btnText;
            data.setText(dataCalculer);
        }else{
            resultat.setText(getResult(dataCalculer));
        } }
    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable= context.initStandardObjects();
            String resultatF = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            return resultatF;
        } catch (Exception e) {
            return "";
        }
    }
}