package com.example.lab3.ui.notifications;

import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lab3.R;
import com.example.lab3.ui.Adapter.MyAdapter;
import com.example.lab3.ui.Country.Country;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    RecyclerView myRecyclerView;
    private String URL = "https://restcountries.eu/rest/v1/all";
    MyAdapter Adapter1;
    public static ArrayList<Country> sCountryList = new ArrayList<Country>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        //final TextView textView = root.findViewById(R.id.text_notifications);
        //ArrayList<Country> Countries = new ArrayList<Country>();


        myRecyclerView = root.findViewById(R.id.myRecycleView);


        loadCountries();


















        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        return root;
    }

    private void loadCountries() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject countryObject = response.getJSONObject(i);


                        String name = countryObject.getString("name").toString();
                        String Capital = countryObject.getString("capital").toString();
                        String Region = countryObject.getString("region").toString();
                        String Population = countryObject.getString("alpha2Code").toString();
                        String numericCode = countryObject.getString("subregion").toString();

                        Country country = new Country(name,Capital,Region);

                        sCountryList.add(country);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                Adapter1 = new MyAdapter(R.layout.country_card, sCountryList);
                myRecyclerView.setAdapter(Adapter1);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: ");
            }
        });
        queue.add(jsonArrayRequest);

    }



    }
