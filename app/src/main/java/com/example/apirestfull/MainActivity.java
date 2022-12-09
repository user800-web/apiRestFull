package com.example.apirestfull;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.apirestfull.WebServices.Asynchtask;
import com.example.apirestfull.WebServices.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void processFinish(String result) throws JSONException {
        ArrayList<String> lstClientes = new ArrayList<String> ();
        JSONObject jsonObject =  new JSONObject(result);
        JSONArray JSONlista =  jsonObject.getJSONArray("data");
        String inf="";
        for(int i=0; i< JSONlista.length();i++){
            JSONObject cliente=  JSONlista.getJSONObject(i);
            //String nombre = "Nombre: " + cliente.getString("name") + "\n" + "email: "+ cliente.getString("email") + "\n\n";
            //inf += nombre;
            //lstClientes.add(cliente.getString("name").toString());
            lstClientes.add("ID: " + cliente.getString("id") + "\n" + "Nombre: "+ cliente.getString("name") + "\n\n");
        }
        TextView txtcontenido = findViewById(R.id.txtcontenido);
        txtcontenido.setText("Datos: "+lstClientes.toString());
    }
    public void btnconsumir (View view){
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://gorest.co.in/public/v1/users",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");

    }
}