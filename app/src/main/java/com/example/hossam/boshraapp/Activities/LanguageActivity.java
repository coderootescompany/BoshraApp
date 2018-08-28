package com.example.hossam.boshraapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.LocaleList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hossam.boshraapp.Helpers.PreferenceHelper;
import com.example.hossam.boshraapp.R;

import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LanguageActivity extends AppCompatActivity {

    LinearLayout layout_arabic,layout_eng;

    String lang_already;
    String arabic = "0";
    String eng = "1";
    TextView arb,engs;
    PreferenceHelper helper;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        helper=new PreferenceHelper(LanguageActivity.this);
        layout_arabic =  findViewById(R.id.layout_arabic);
        layout_eng = findViewById(R.id.layout_eng);

        lang_already = helper.getLanguage();

        layout_arabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (lang_already != null && lang_already.equals("0")){
                    Toast.makeText(LanguageActivity.this, R.string.already_arabic, Toast.LENGTH_SHORT).show();
                }

                else {

                    String languageToLoad  = "ar";
                    Locale locale = new Locale(languageToLoad);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    LanguageActivity.this.getResources().updateConfiguration(config,LanguageActivity.this.getResources().getDisplayMetrics());
                    helper.setLanguage(arabic);
                    Toast.makeText(LanguageActivity.this, R.string.arabic_change, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LanguageActivity.this, MainActivity.class);
                    startActivity(intent);
                    LanguageActivity.this.finish();
                }
            }
        });


        layout_eng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (lang_already != null && lang_already.equals("1")){
                    Toast.makeText(LanguageActivity.this, R.string.already_eng, Toast.LENGTH_SHORT).show();
                }

                else
                    {
                    String languageToLoad  = "en";
                    Locale locale = new Locale(languageToLoad);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                  LanguageActivity.this.getResources().updateConfiguration(config, LanguageActivity.this.getResources().getDisplayMetrics());
                    helper.setLanguage(eng);
                    Toast.makeText(LanguageActivity.this, R.string.eng_change, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LanguageActivity.this, MainActivity.class);
                    startActivity(intent);
                    LanguageActivity.this.finish();
                }
            }
        });



    }

}
