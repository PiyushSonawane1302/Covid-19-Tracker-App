package com.example.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class VaccinationSearchActivity extends AppCompatActivity {

    DatePickerDialog datePickerDialog;
    EditText pin_et;
    Button search_details_btn,pick_date_btn;
    TextView date_tv;
    String pinCode,date;
    Calendar calendar = Calendar.getInstance();
    final String PIN_CODE = "pincode";
    final String DATE = "date";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination_search);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.pickdate_btn));



        pin_et = findViewById(R.id.pin_et);
        search_details_btn = findViewById(R.id.search_details_btn);
        pick_date_btn = findViewById(R.id.pick_date_btn);
        date_tv = findViewById(R.id.date_tv);


        search_details_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pinCode = pin_et.getText().toString();
                if (pinCode.isEmpty()){
                    Toast.makeText(VaccinationSearchActivity.this,"Please Provide PinCode",Toast.LENGTH_SHORT).show();
                }else{
//                Log.d("VSA","Pin = "+pinCode);
//                Log.d("VSA","Date = "+date);
                    //TODO Send Intent to the details activity with date and pin
                    Intent intent = new Intent(VaccinationSearchActivity.this,VaccinationDetailsActivity.class);
                    intent.putExtra(PIN_CODE,pinCode);
                    intent.putExtra(DATE,date);
                    startActivity(intent);
                }
            }
        });

        pick_date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });


    }

    private void showDatePickerDialog() {

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        // date picker dialog
        datePickerDialog = new DatePickerDialog(VaccinationSearchActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(Calendar.YEAR,year);
                        calendar.set(Calendar.MONTH,month);
                        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        dateFormat();
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    private void dateFormat(){
        String format = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
        date = sdf.format(calendar.getTime());
        date_tv.setText(date);

    }


}