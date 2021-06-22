package com.example.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class VaccinationDetailsActivity extends AppCompatActivity {

    final String PIN_CODE = "pincode";
    final String DATE = "date";
    ArrayList<Centre> centreArrayList;
    CentreAdapter adapter;
    RecyclerView centreRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination_details);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.LTGRAY);

        String pin = getIntent().getStringExtra(PIN_CODE);
        String date = getIntent().getStringExtra(DATE);

        centreRecyclerView = findViewById(R.id.vaccination_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        centreRecyclerView.setLayoutManager(layoutManager);

        fetchVaccinationDetails(pin,date);

        adapter = new CentreAdapter();
        centreRecyclerView.setAdapter(adapter);


        Log.d("VDA",pin);
        Log.d("VDA",date);

    }


    private void fetchVaccinationDetails(String pin,String date){
        String url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?pincode="+pin+"&date="+date;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray jsonArray = response.getJSONArray("centers");
                    if (jsonArray.length() == 0) {
                        Toast.makeText(VaccinationDetailsActivity.this, "No centers available", Toast.LENGTH_SHORT).show();
                    }


                    centreArrayList = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject centersObject = jsonArray.getJSONObject(i);


                        String centerName = centersObject.getString("name");
                        String centerAddress = centersObject.getString("address");
                        String centerFromTiming = centersObject.getString("from");
                        String centerToTiming = centersObject.getString("to");
                        String fees = centersObject.getString("fee_type");

                        JSONObject sessionsObject = centersObject.getJSONArray("sessions").getJSONObject(0);
                        String vaccineName = sessionsObject.getString("vaccine");
                        String availability = sessionsObject.getString("available_capacity");
                        int ageLimit = sessionsObject.getInt("min_age_limit");

                        Centre centre = new Centre(centerName,centerAddress,centerFromTiming,centerToTiming,
                                fees,vaccineName,availability,ageLimit);

                        centreArrayList.add(centre);

                        Log.d("VDA",centerName);
                        Log.d("VDA",centerAddress);
                        Log.d("VDA",centerFromTiming);
                        Log.d("VDA",centerToTiming);
                        Log.d("VDA",fees);
                        Log.d("VDA",vaccineName);
                        Log.d("VDA",availability);
                        Log.d("VDA", String.valueOf(ageLimit));


                    }

                    adapter.updateCentre(centreArrayList);

                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VaccinationDetailsActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

    }

}