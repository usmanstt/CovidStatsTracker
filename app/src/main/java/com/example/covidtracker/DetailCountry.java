package com.example.covidtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailCountry extends AppCompatActivity {

    private int positionCountry;
    TextView cases,recovered,deathstoday,totaldeaths,casestoday,active,critical,country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_country);

        ActionBar actionBar = getSupportActionBar();

        Intent intent = getIntent();
        positionCountry = intent.getIntExtra("position", 0);

        actionBar.setTitle("Covid Stats In " + AffectedCountries.countryHelperList.get(positionCountry).getCountry());
        actionBar.setDisplayHomeAsUpEnabled(true);



        cases = findViewById(R.id.tvcases);
        recovered = findViewById(R.id.tvrecovered);
        deathstoday = findViewById(R.id.tvtodaydeaths);
        totaldeaths = findViewById(R.id.tvtotaldeaths);
        casestoday = findViewById(R.id.tvtodaycases);
        active = findViewById(R.id.tvactive);
        critical = findViewById(R.id.tvcritical);
        country = findViewById(R.id.tvCountry);

        country.setText(AffectedCountries.countryHelperList.get(positionCountry).getCountry());
        recovered.setText(AffectedCountries.countryHelperList.get(positionCountry).getRecovered());
        deathstoday.setText(AffectedCountries.countryHelperList.get(positionCountry).getTodayDeaths());
        totaldeaths.setText(AffectedCountries.countryHelperList.get(positionCountry).getDeaths());
        casestoday.setText(AffectedCountries.countryHelperList.get(positionCountry).getTodayCases());
        cases.setText(AffectedCountries.countryHelperList.get(positionCountry).getCases());
        critical.setText(AffectedCountries.countryHelperList.get(positionCountry).getCritical());
        active.setText(AffectedCountries.countryHelperList.get(positionCountry).getActive());



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}