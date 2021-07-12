package com.example.covid19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.StringRequest;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    Button call_btn;
    Button news_update_btn, precautions_btn, symptoms_btn, countryWise_data_btn, test_labs, search_vaccine_btn;
    TextView total, active, recovered, deaths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        call_btn = findViewById(R.id.call_button);
        news_update_btn = findViewById(R.id.news_update_btn);
        precautions_btn = findViewById(R.id.precautions_btn);
        symptoms_btn = findViewById(R.id.symptoms_btn);
        countryWise_data_btn = findViewById(R.id.countryWise_data_btn);
        test_labs = findViewById(R.id.testLabs_btn);
        search_vaccine_btn = findViewById(R.id.search_vaccine_btn);

        total = findViewById(R.id.tv_total);
        active = findViewById(R.id.tv_active);
        recovered = findViewById(R.id.tv_recovered);
        deaths = findViewById(R.id.tv_death);

        setSupportActionBar(toolbar);

        if (isNetworkAvailable()) {

            fetchTotalsData(); //Fetching total cases data to display on the dashboard

            DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar
                    , R.string.nav_open_drawer, R.string.nav_close_drawer);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();
            toggle.setDrawerIndicatorEnabled(false);
            toggle.setHomeAsUpIndicator(R.drawable.ic_menu);

            toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    if (drawer.isDrawerOpen(GravityCompat.START)) {
                        drawer.closeDrawer(GravityCompat.START);
                    } else {
                        drawer.openDrawer(GravityCompat.START);
                    }
                }
            });

            call_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent call_intent = new Intent(Intent.ACTION_DIAL);
                    call_intent.setData(Uri.parse("tel:" + 1123978046));
                    startActivity(call_intent);
                }
            });


            search_vaccine_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, VaccinationSearchActivity.class));
                }
            });

            news_update_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                    startActivity(intent);
                }
            });

            precautions_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, FragmentActivity.class);
                    intent.putExtra("frag", "precautions");
                    startActivity(intent);
                }
            });

            symptoms_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, FragmentActivity.class);
                    intent.putExtra("frag", "symptoms");
                    startActivity(intent);
                }
            });


            countryWise_data_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, CountryWiseData.class);
                    startActivity(intent);
                }
            });


            test_labs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri gmmIntentUri = Uri.parse("geo:0,0?q=covid testing lab");
                    Intent intent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    intent.setPackage("com.google.android.apps.maps");
                    startActivity(intent);
                }
            });

            NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
        }
        //No Internet Activity is started if the network is not available
        else {

            Intent intent = new Intent(this, NoInternet.class);
            startActivity(intent);
            finish();
        }

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    /**
     * This to to open the particular navigation item when
     * item is clicked
     */
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        Intent intent = null;

        switch (id) {

            case R.id.nav_helpline:
                intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + 1123978046));
                break;

            case R.id.nav_news_updates:
                //   Log.d("TAg","nav item clicked");
                intent = new Intent(this, NewsActivity.class);
                break;
            case R.id.nav_precautions:
                intent = new Intent(this, FragmentActivity.class);
                intent.putExtra("frag", "precautions");
                break;
            case R.id.nav_symptoms:
                intent = new Intent(this, FragmentActivity.class);
                intent.putExtra("frag", "symptoms");
                break;
            case R.id.nav_help:
                intent = new Intent(this, HelpActivity.class);
                break;
            case R.id.nav_feedback:
                intent = new Intent(this, FeedbackActivity.class);
                break;
            case R.id.nav_country:
                intent = new Intent(this, CountryWiseData.class);
                break;
            case R.id.nav_testing_labs:
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=covid testing lab");
                intent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                intent.setPackage("com.google.android.apps.maps");
                break;
            case R.id.nav_vaccination_centre:
                intent = new Intent(this, VaccinationSearchActivity.class);
                break;
        }

        startActivity(intent);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }


    /**
     * This is to fetch the cases statistics data which is displayed on the dashboard.
     */
    private void fetchTotalsData() {

        String url = "https://corona.lmao.ninja/v2/all/";
        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {

            try {

                JSONObject jsonObject = new JSONObject(response);

                String total_json = jsonObject.getString("cases");
                long totals = Long.parseLong(total_json);
                total.setText(NumberFormat.getNumberInstance(Locale.US).format(totals));

                String death_json = jsonObject.getString("deaths");
                long death = Long.parseLong(death_json);
                deaths.setText(NumberFormat.getNumberInstance(Locale.US).format(death));

                String active_json = jsonObject.getString("active");
                long actives = Long.parseLong(active_json);
                active.setText(NumberFormat.getNumberInstance(Locale.US).format(actives));

                String recovered_json = jsonObject.getString("recovered");
                long recoveries = Long.parseLong(recovered_json);
                recovered.setText(NumberFormat.getNumberInstance(Locale.US).format(recoveries));

//                    total.setText(jsonObject.getString("cases"));
//                    deaths.setText(jsonObject.getString("deaths"));
//                    active.setText(jsonObject.getString("active"));
//                    recovered.setText(jsonObject.getString("recovered"));


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show());

        MySingleton.getInstance(this).addToRequestQueue(request);
    }


    /**
     * To check the Network Availability on device
     *
     * @return true or false based on network connectivity
     */
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}


