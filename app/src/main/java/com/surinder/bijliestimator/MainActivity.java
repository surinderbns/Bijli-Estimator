package com.surinder.bijliestimator;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import android.util.DisplayMetrics;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
     Button act1;Button act2;Button act3;Button act4; TextView tv14;
    Spinner spinner;
    Locale localevar;
    //language variable
    String currentLanguage1=" " ;// initially set to punjabi
    String currentLang2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// start new activity


        act1 = findViewById(R.id.button);
        act2 = findViewById(R.id.button2);
        act3 = findViewById(R.id.button3);
        act4 = findViewById(R.id.button4);
        tv14 = findViewById(R.id.textView14);
        tv14.setSelected(true);  // Set focus to the textview
        currentLanguage1 = getIntent().getStringExtra(currentLang2);
        //current language taken from second string
        spinner = findViewById(R.id.spn_lan);
        List<String> list = new ArrayList<>();
        list.add("ਭਾਸ਼ਾ ਚੁਣੋ (Select Language)");
        list.add("ਪੰਜਾਬੀ");
        list.add("English");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:default:

                        break;
                    case 1:
                        setLocale("pa");
                        break;
                    case 2:
                        setLocale("en");
                        break;

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                setLocale("pa");
            }
        });



        act1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoadCalc();
            }

        });
        act2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBillActivity();
            }

        });
        act3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openConExp();
            }

        });
        act4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoadExt();
            }

        });


    }

    public void setLocale(String localeName) {


        if (!localeName.equals(currentLanguage1)) {
            localevar = new Locale(localeName);
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = localevar;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this, MainActivity.class);
            refresh.putExtra(currentLang2, localeName);
            startActivity(refresh);
        } else{
            Toast.makeText(MainActivity.this, "Language already selected", Toast.LENGTH_SHORT).show();
        }
    }
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        System.exit(0);
    }



    public void openLoadExt() {
        Intent intent = new Intent(this, LoadExtension.class);
        startActivity(intent);
    }

    public void openLoadCalc() {
        Intent intent = new Intent(this, loadcalculator.class);
        startActivity(intent);
    }
    public void openBillActivity() {
        Intent intent = new Intent(this, bill_mini.class);
        startActivity(intent);
    }
    public void openConExp() {
        Intent intent = new Intent(this, ConExp.class);
        startActivity(intent);
    }


}




