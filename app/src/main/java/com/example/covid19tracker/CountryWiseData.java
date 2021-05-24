package com.example.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CountryWiseData extends AppCompatActivity {

    CountryAdapter adapter;
    RecyclerView recyclerView;
    Toolbar toolbar;
    EditText editText;
    ArrayList<Country> countryArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_wise_data);

        editText = findViewById(R.id.et_Search);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        recyclerView = findViewById(R.id.country_data_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        fetchCountryWiseData();
        adapter = new CountryAdapter();
        recyclerView.setAdapter(adapter);


    }

    private void filter(String text) {
        ArrayList<Country> filteredList = new ArrayList<>();

        for (Country item : countryArrayList) {
            if (item.getCountry().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
            adapter.filterList(filteredList);
        }

    }

    private void fetchCountryWiseData() {

        String url = "https://corona.lmao.ninja/v2/countries/";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);

                    countryArrayList = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);


                        String countryName = jsonObject.getString("country");
                        String total_cases = jsonObject.getString("cases");
                        String active_cases = jsonObject.getString("active");
                        String recovered_cases = jsonObject.getString("recovered");
                        String deaths = jsonObject.getString("deaths");

                        JSONObject object = jsonObject.getJSONObject("countryInfo");
                        String flagUrl = object.getString("flag");

                        Country country = new Country(flagUrl, countryName, total_cases, active_cases, recovered_cases, deaths);
                        countryArrayList.add(country);

                        adapter.updateCountry(countryArrayList);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CountryWiseData.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        );

        MySingleton.getInstance(this).addToRequestQueue(request);

    }

}