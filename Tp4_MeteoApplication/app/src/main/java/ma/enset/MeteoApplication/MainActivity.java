package ma.enset.MeteoApplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    EditText villeEdit;
    TextView dateView, tempView, tempMaxView, tempMinView, pressionView, humiditeView, descView;
    TextView degreView;


    ImageButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        villeEdit = findViewById(R.id.ville);

        tempView = findViewById(R.id.temp);
        tempMaxView = findViewById(R.id.tempMax);
        tempMinView = findViewById(R.id.tempMin);
        pressionView = findViewById(R.id.pression);
        humiditeView = findViewById(R.id.humidite);
        descView = findViewById(R.id.desc);

        degreView = findViewById(R.id.degre);
        dateView = findViewById(R.id.date);

        btn = findViewById(R.id.btnSearch);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tempURL;
                String villeNom= villeEdit.getText().toString().trim();

                if(villeNom.equals("")){
                    dateView.setText("La Ville ne peut pas etre vide");
                }else{
                    tempURL = "https://api.openweathermap.org/data/2.5/weather?q="+villeNom+"&appid=d6426d42fd853462b140109758ca51d1&units=metric";

                    StringRequest stringRequest = new StringRequest(
                            Request.Method.GET, tempURL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //Log.d("response",response);
                            try{
                                //Recuperer la reponse format JSON
                                JSONObject jsonResponse = new JSONObject(response);
                                //Avoir la partie json main : temp, tempMax, tempMin..
                                JSONObject mainInfo = jsonResponse.getJSONObject("main");

                                tempView.setText(String.valueOf(mainInfo.getDouble("temp"))   );
                                degreView.setText("°C");
                                tempMaxView.setText(  String.valueOf(mainInfo.getDouble("temp_max")) );
                                tempMinView.setText( String.valueOf(mainInfo.getDouble("temp_min")) );
                                pressionView.setText( String.valueOf(mainInfo.getInt("pressure")));
                                humiditeView.setText( String.valueOf(mainInfo.getInt("humidity")));

                                JSONArray weatherInfo =jsonResponse.getJSONArray("weather");
                                JSONObject weatherDesc = weatherInfo.getJSONObject(0);
                                descView.setText(weatherDesc.getString("main"));
                                setImageMeteo(weatherDesc.getString("main"));

                                Date date=new Date(jsonResponse.getLong("dt")*1000);
                                SimpleDateFormat simpleDateFormat=
                                        new SimpleDateFormat("dd MMM yyyy");
                                dateView.setText(simpleDateFormat.format(date));


                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this,"Ville not Exist!",Toast.LENGTH_SHORT).show();
                        }
                    });

                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                }
            }
        });




    }

    private void setImageMeteo(String desc) {
        ImageView imgview=findViewById(R.id.imgMeteo);
        if(desc.equals("Rain")) {
            imgview.setImageResource(R.drawable.rain);
        }
        else if(desc.equals("Clear")){
            imgview.setImageResource(R.drawable.clear);}
        else if(desc.equals("Thunderstorm")) {
            imgview.setImageResource(R.drawable.thunderstorm);}
        else if(desc.equals("Clouds"))
        {
            imgview.setImageResource(R.drawable.cloudy);
        }
    }
}