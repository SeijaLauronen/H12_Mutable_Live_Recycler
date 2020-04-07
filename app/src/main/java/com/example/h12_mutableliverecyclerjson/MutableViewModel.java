package com.example.h12_mutableliverecyclerjson;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MutableViewModel extends AndroidViewModel {

    private static final String ENDPOINT = "https://jsonplaceholder.typicode.com/users";
    private RecyclerAdapteri recyclerAdapteri;
    private RequestQueue que;


    private MutableLiveData<List<String>> listMutableLiveData;

    public MutableViewModel(@NonNull Application application) {
        super(application);
        //No mistä ne nyt tähän saa...täshänkö se Json oliskin pitänyt laittaa


        //this.listMutableLiveData =

        JsonArrayRequest request = new JsonArrayRequest(ENDPOINT, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //TODO luokaksi
                ArrayList<String> tuloslista = new ArrayList<String>();
                Type listantyyppi = new TypeToken<ArrayList<String>>(){}.getType();
                Gson gson = new Gson();
                //TODO:tähän kaatuu:
                tuloslista = gson.fromJson(response.toString(),listantyyppi);

                //Kokeillaan ensin tämmösillä
                /*
                tuloslista.add(("Test1"));
                tuloslista.add(("Test2"));
                */
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
        //No ei...
        //this.listMutableLiveData =recyclerAdapteri.setItems({"Diipa","Daapa"});
    }
}
