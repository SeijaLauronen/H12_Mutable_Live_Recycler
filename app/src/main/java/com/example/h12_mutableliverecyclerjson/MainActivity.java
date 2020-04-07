package com.example.h12_mutableliverecyclerjson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RequestQueue que;
    private Button button;
    private static final String ENDPOINT = "https://jsonplaceholder.typicode.com/users";

    //private List<luokka> luokka;
    private List<String> hakutulos; //TODO: tämä pitäis hajottaa luokaksi

    private RecyclerView recyclerView;
    private RecyclerAdapteri recyclerAdapteri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerAdapteri = new RecyclerAdapteri(getApplicationContext());

        this.button = findViewById(R.id.buttonHae);
        this.que = Volley.newRequestQueue(this);
        this.recyclerView = findViewById(R.id.recyclerview);

        hakutulos = new ArrayList<String>(); //TODO arrayn sisälle luokka
        recyclerView.setAdapter(recyclerAdapteri);

        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JsonArrayRequest request = new JsonArrayRequest(ENDPOINT, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //TODO luokaksi
                        ArrayList<String> tuloslista = new ArrayList<String>();
                        Type listantyyppi = new TypeToken<ArrayList<String>>(){}.getType();
                        Gson gson = new Gson();
                        //TODO:tähän kaatuu:
                        //tuloslista = gson.fromJson(response.toString(),listantyyppi);

                        //Kokeillaan ensin tämmösillä
                        tuloslista.add(("Test1"));
                        tuloslista.add(("Test2"));

                        //TODO No niin... ja mites sitten saatkaan ne arvot tänne
                        //RecyclerAdapteri.
                        recyclerAdapteri.setItems(tuloslista);//Mahtaakohan olla tännepäinkään
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //TODO: tulee tähän: Missing internet permission. Piti laittaa manifestiin permissiot
                        error.toString();
                    }
                });

                que.add(request);
                //button.setVisibility(View.GONE);
            }
        });








        // 0:25:47 videolla
        ArrayList<String> lista =new ArrayList<>();
        recyclerAdapteri.setItems(lista);

        //Ja tämä pitäisi sitten laittaakin ViewModeliin
        //30:20
        MutableLiveData<List<String >> listMutableLiveData = new MutableLiveData<>();
        //pitää asettaa koko lista, ei voi asettaa itemeitä
        //listMutableLiveData.setValue();//asettaa heti
        //listMutableLiveData.postValue();//asettaa pääsäikeen jonoon

        listMutableLiveData.observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                recyclerAdapteri.setItems((ArrayList<String>) strings);
            }
        });
    }
}
