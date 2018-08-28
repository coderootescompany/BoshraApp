package com.example.hossam.boshraapp.Activities;



import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hossam.boshraapp.Fragments.Catogries_In_galleryFragment;
import com.example.hossam.boshraapp.Fragments.ExpFragment;
import com.example.hossam.boshraapp.Fragments.MainFragment;
import com.example.hossam.boshraapp.Fragments.MenuFragment;
import com.example.hossam.boshraapp.Fragments.MyreservationFragment;
import com.example.hossam.boshraapp.Fragments.RegisterFragment;
import com.example.hossam.boshraapp.Fragments.ReservationFragment;
import com.example.hossam.boshraapp.Fragments.SubjectsFragment;
import com.example.hossam.boshraapp.Fragments.VideosFragment;
import com.example.hossam.boshraapp.Helpers.PreferenceHelper;
import com.example.hossam.boshraapp.R;

import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private int PERMISSIONS_REQUEST_READ_CONTACTS=222;
    LinearLayout main,videos,images,reservation,morelayout;
    private String TAG_FRAGMENT="fragm";
    Fragment fragment;
    FragmentManager fr;
    FragmentTransaction ft;
    String arabic = "0";
    String eng = "1";
    String check_lang;
    PreferenceHelper helper;
    TextView maintxt,resertxt,videotxt,imagestxt,moretxt;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findFromXml();
        helper=new PreferenceHelper(MainActivity.this);
        String lan =  Locale.getDefault().getDisplayLanguage();
        Log.d("local",lan);
        fragment = new MainFragment();
        fr = getSupportFragmentManager();
        ft = fr.beginTransaction();
        ft.replace(R.id.main_frame, fragment);
        ft.commit();

        check_lang = helper.getLanguage();




      if (check_lang == null) {
            Locale.getDefault().getDisplayLanguage();
            if (Locale.getDefault().getDisplayLanguage() != null && Locale.getDefault().getDisplayLanguage().equals("setDefault")) {
               helper.setLanguage(arabic);
                String languageToLoad = "setDefault";
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                MainActivity.this.getResources().updateConfiguration(config, MainActivity.this.getResources().getDisplayMetrics());

            }
            else if (Locale.getDefault().getDisplayLanguage() != null && Locale.getDefault().getDisplayLanguage().equals("English")) {
                helper.setLanguage(eng);
                String languageToLoad = "en";
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                MainActivity.this.getResources().updateConfiguration(config, MainActivity.this.getResources().getDisplayMetrics());
            }
         }

         else if (check_lang != null) {

            if (check_lang != null && check_lang.equals("0")) {
                String languageToLoad = "ar";
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;

                moretxt.setText("المزيد");
                maintxt.setText("الرئيسية");
                videotxt.setText("فيديوهات");
                imagestxt.setText("الصور");
                resertxt.setText("حجوزاتي");
                MainActivity.this.getResources().updateConfiguration(config, MainActivity.this.getResources().getDisplayMetrics());

            } else if (check_lang != null && check_lang.equals("1")) {
                String languageToLoad = "en";
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;

                moretxt.setText("more");
                maintxt.setText("main");
                videotxt.setText("videos");
                imagestxt.setText("images");
                resertxt.setText("reserve");


                MainActivity.this.getResources().updateConfiguration(config, MainActivity.this.getResources().getDisplayMetrics());
            }
        }


        morelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new MenuFragment();
                fr = getSupportFragmentManager();
                ft = fr.beginTransaction();
                ft.replace(R.id.main_frame, fragment);
                ft.commit();
            }
        });

        images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new Catogries_In_galleryFragment();
                fr = getSupportFragmentManager();
                ft = fr.beginTransaction();
                ft.replace(R.id.main_frame, fragment);
                ft.commit();
            }
        });

        videos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment = new VideosFragment() ;
                fr = getSupportFragmentManager();
                ft = fr.beginTransaction();
                ft.replace(R.id.main_frame, fragment);
                ft.commit();
            }
        });
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment = new MainFragment();
                fr = getSupportFragmentManager();
                ft = fr.beginTransaction();
                ft.replace(R.id.main_frame , fragment);
                ft.commit();
            }
        });

        reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (helper.getUserId() !=null )

                {

                    fragment = new MyreservationFragment();
                    fr = getSupportFragmentManager();
                    ft = fr.beginTransaction();
                    ft.replace(R.id.main_frame , fragment);
                    ft.commit();
                }

                else
                    Toast.makeText(MainActivity.this,getResources().getString(R.string.loginfirst),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void findFromXml() {

        main=findViewById(R.id.mainpage);
        reservation=findViewById(R.id.reservations);
        videos=findViewById(R.id.videos);
        images=findViewById(R.id.images);
        morelayout=findViewById(R.id.more);

        moretxt=findViewById(R.id.moretxt);
        maintxt=findViewById(R.id.maintxt);
        videotxt=findViewById(R.id.videostxt);
        imagestxt=findViewById(R.id.imagestxt);
        resertxt=findViewById(R.id.reservtxt);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                //showContacts();
                // Log.d("phone found ",contactExists(whatsapp.this,"5555555555")+"") ;
            } else {
                Toast.makeText(MainActivity.this, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.more:

                SubjectsFragment subjectsFragment =  new SubjectsFragment();
                android.support.v4.app.FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                t.replace(R.id.main_frame, subjectsFragment);
                t.commit();
                break;
        }
    }


}
