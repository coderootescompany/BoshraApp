package com.example.hossam.boshraapp.Fragments;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.hossam.boshraapp.Adapters.DepartmentsAdapter;
import com.example.hossam.boshraapp.Adapters.DoctorssAdapter;
import com.example.hossam.boshraapp.Adapters.SliderPagerAdapter;
import com.example.hossam.boshraapp.Helpers.ApiClient;
import com.example.hossam.boshraapp.Helpers.ApiInterface;
import com.example.hossam.boshraapp.Helpers.ProgressDialogHelper;
import com.example.hossam.boshraapp.Modules.AllDepartment;
import com.example.hossam.boshraapp.Modules.AllDoctors;
import com.example.hossam.boshraapp.Modules.SliderModel;
import com.example.hossam.boshraapp.Modules.StatuesModel;
import com.example.hossam.boshraapp.R;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainFragment extends Fragment{

    RecyclerView doctors,departments;
    ProgressBar progressBar;
    private LinearLayout ll_dots,socialcontainer;
    private TextView[] dots;
    int page_position = 0,arraysize=0;
    DepartmentsAdapter departmentsAdapter;
    DoctorssAdapter doctorssAdapter;
    private ArrayList imageModelArrayList;
    ViewPager viewPager;
    private SliderPagerAdapter sliderPagerAdapter;
    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;
    ImageView whatsapp,facebook,insta;
    String statues="";
    ArrayList<SliderModel.DataBean> mValues=new ArrayList<>();
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 444;
    private int MY_PERMISSIONS_REQUEST_READ_CONTACTS=555;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view=inflater.inflate(R.layout.fragment_main, container, false);
        //progressBar =view.findViewById(R.id.progressBar);
        doctors=view.findViewById(R.id.doctors_recycler);
        departments=view.findViewById(R.id.department_recycler);
        socialcontainer=view.findViewById(R.id.socialcontainer);
        viewPager=view.findViewById(R.id.vp_slider);
        ll_dots = view.findViewById(R.id.ll_dots);
        whatsapp=view.findViewById(R.id.whats);
        facebook = view.findViewById(R.id.face);
        insta = view.findViewById(R.id.inests);
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_CONTACTS)) {}
            else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            }
            }

            facebook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = "https://www.facebook.com/Albushra.Medical.Specialty";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            });

        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.instagram.com/AlbushraComplex/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });


        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (!contactExists(getContext(),"+95597021"))
                {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                    builder1.setMessage(getResources().getString(R.string.notexists));
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "تسجبل ",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    Intent contactIntent = new Intent(ContactsContract.Intents.Insert.ACTION);
                                    contactIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                                    contactIntent
                                            .putExtra(ContactsContract.Intents.Insert.NAME, getResources().getString(R.string.boshraname))
                                            .putExtra(ContactsContract.Intents.Insert.PHONE, "+95597021")
                                            .putExtra(ContactsContract.Intents.Insert.EMAIL, "INFO@SFC-OMAN.COM");
                                    startActivityForResult(contactIntent, 1);
                                    dialog.cancel();
                                }
                            });

                    builder1.setNegativeButton(
                            "لا ",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
                else
                openWhatsApp();

            }
        });

        /////////get methods to fill data
        getDoctorsData();    ///to fill doctor data
        getDepartmentsData();  ////// to fill department data
         //////// to fill slider data
        statues=  getstatues();
        init();

      //  viewPager.setPageTransformer(false,new ZoomOutPageTransformer());
        hideView(socialcontainer);
        addBottomDots(0);
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            public void run() {
                if (page_position == arraysize) {
                    page_position = 0;
                } else {
                    page_position = page_position + 1;
                }
                viewPager.setCurrentItem(page_position, true);

            }
        };

        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(update);
            }
        }, 6000, 4000);

        viewPager.setPageTransformer(true, new ViewPager.PageTransformer() {

            private static final float MIN_SCALE = 0.85f;
            private static final float MIN_ALPHA = 0.5f;
            @Override
            public void transformPage(View view, float position) {

                view.setTranslationX(-position * view.getWidth());
                if (Math.abs(position) < 0.5) {
                    view.setVisibility(View.VISIBLE);
                    view.setScaleX(1 - Math.abs(position));
                    view.setScaleY(1 - Math.abs(position));
                } else if (Math.abs(position) > 0.5) {
                    view.setVisibility(View.GONE);
                }

            }
        });
        return  view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == 1)
        {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(getActivity(), getResources().getString(R.string.contactadd), Toast.LENGTH_SHORT).show();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getActivity(), getResources().getString(R.string.Cancelled), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
            } else {
                Toast.makeText(getActivity(), "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void openWhatsApp() {

            String smsNumber = "95597021"; // E164 format without '+' sign
            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.setType("text/plain");
            sendIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.send));
            sendIntent.putExtra("jid", smsNumber + "@s.whatsapp.net"); //phone number without "+" prefix
            sendIntent.setPackage("com.whatsapp");
            if (sendIntent.resolveActivity(getActivity().getPackageManager()) == null) {
                Toast.makeText(getActivity(), "Error/n" , Toast.LENGTH_SHORT).show();
                return;
            }

            startActivity(sendIntent);
    }

    private AllDoctors allDoctors;
    Call<AllDoctors> calls;
    private void getDoctorsData() {

      //  progressBar.setVisibility(View.VISIBLE);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        calls = apiService.getAllDocrorsData();
        calls.enqueue(new Callback<AllDoctors>(){

            @Override
            public void onResponse(Call<AllDoctors> call, final Response<AllDoctors> response) {
                if (response.body() != null) {
                  //  progressBar.setVisibility(View.GONE);
                    allDoctors = response.body();
                    doctorssAdapter = new DoctorssAdapter(getActivity(), (ArrayList<AllDoctors.DataBean>) allDoctors.getData());
                    doctors.setAdapter(doctorssAdapter);
                }
            }
            @Override
            public void onFailure(Call<AllDoctors> call, Throwable t) {
                if ( getActivity()!=null)
                Toast.makeText(  getActivity(), getString(R.string.connection_error),Toast.LENGTH_SHORT).show();
            }
        });

    }


    private AllDepartment allDepartment;
    Call<AllDepartment> calls2;
    private void getDepartmentsData() {
     //   progressBar.setVisibility(View.VISIBLE);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        calls2 = apiService.getAllDepartmentsData();
        calls2.enqueue(new Callback<AllDepartment>(){

            @Override
            public void onResponse(Call<AllDepartment> call, final Response<AllDepartment> response) {
                if (response.body() != null) {
                   // progressBar.setVisibility(View.GONE);
                    allDepartment = response.body();
                    departmentsAdapter = new DepartmentsAdapter(getActivity(),allDepartment.getData());
                    departments.setAdapter(departmentsAdapter);
                }
            }
            @Override
            public void onFailure(Call<AllDepartment> call, Throwable t) {
               // progressBar.setVisibility(View.GONE);
              /*  getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {*/
                if ( getActivity()!=null)
                        Toast.makeText(  getActivity(), getString(R.string.connection_error),Toast.LENGTH_SHORT).show();
                   /* }
                });*/
            }
        });
    }



    private SliderModel sliderModel;
    Call<SliderModel> calls3;
    private void getSlidersData() {
     //   progressBar.setVisibility(View.VISIBLE);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        calls3 = apiService.getAllSlidersData();
        calls3.enqueue(new Callback<SliderModel>(){
            @Override
            public void onResponse(Call<SliderModel> call, final Response<SliderModel> response) {
                if (response.body() != null) {
                    //////get data debend on doctor statues
                  //  progressBar.setVisibility(View.GONE);
                    sliderModel = response.body();
                    arraysize=sliderModel.getData().size();
                    if (!statues.matches("events")) {
                        for (int i = 0; i < sliderModel.getData().size(); i++) {
                            if (sliderModel.getData().get(i).getDoctor_id() > 0)
                                mValues.add(sliderModel.getData().get(i));
                        }
                    }
                    else
                    {
                        for (int i = 0; i < sliderModel.getData().size(); i++) {
                        if (sliderModel.getData().get(i).getDoctor_id() == 0)
                            mValues.add(sliderModel.getData().get(i));
                    }
                    }
                    sliderPagerAdapter = new SliderPagerAdapter(getActivity(),mValues);
                    viewPager.setAdapter(sliderPagerAdapter);
                }
            }
            @Override
            public void onFailure(Call<SliderModel> call, Throwable t) {
              //  progressBar.setVisibility(View.GONE);
                if ( getActivity()!=null)
                Toast.makeText(  getActivity(), getString(R.string.connection_error),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private StatuesModel statuesModel;
    Call<StatuesModel> calls4;

    private  String getstatues()
    {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        calls4 = apiService.getStatuesData();
        calls4.enqueue(new Callback<StatuesModel>(){
            @Override
            public void onResponse(Call<StatuesModel> call, final Response<StatuesModel> response) {
                if (response.body() != null) {
                    statuesModel = response.body();
                    statues= statuesModel.getData().get(0).getStatus();
                }
            }
            @Override
            public void onFailure(Call<StatuesModel> call, Throwable t) {
                ProgressDialogHelper.removeSimpleProgressDialog();
                if ( getActivity()!=null)
                Toast.makeText(  getActivity(), getString(R.string.connection_error),Toast.LENGTH_SHORT).show();
            }
        });
    return statues;
    }


    private void init() {
    imageModelArrayList = new ArrayList<>();


        getSlidersData();
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    addBottomDots(position);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }

        private void addBottomDots(int currentPage) {

            dots = new TextView[arraysize];
            ll_dots.removeAllViews();

            for (int i = 0; i < dots.length; i++) {
                if (getContext()!=null) {
                    dots[i] = new TextView(getContext());
                    dots[i].setText(Html.fromHtml("&#8226;"));
                    dots[i].setTextSize(35);
                    if (i==currentPage)
                        dots[i].setTextColor(Color.parseColor("#FFFFFF"));
                    else
                           dots[i].setTextColor(Color.parseColor("#000000"));
                    ll_dots.addView(dots[i]);
                }
            }

            //if (dots!=null&&dots.length>0 && currentPage>=0&&currentPage<dots.length)
              //  dots[currentPage].setTextColor(Color.parseColor("#FFFFFF"));
        }

    private void hideView(final View view){
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.animy);
        //use this to make it longer:  animation.setDuration(1000);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationRepeat(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {

            }
        });

        view.startAnimation(animation);
    }


    public boolean contactExists(Context context, String number) {

        Uri lookupUri = Uri.withAppendedPath(
                ContactsContract.PhoneLookup.CONTENT_FILTER_URI,
                Uri.encode(number));
        String[] mPhoneNumberProjection = { ContactsContract.PhoneLookup._ID, ContactsContract.PhoneLookup.NUMBER, ContactsContract.PhoneLookup.DISPLAY_NAME };
        Cursor cur = context.getContentResolver().query(lookupUri,mPhoneNumberProjection, null, null, null);
        try {
            if (cur.moveToFirst()) {
                return true;
            }
        } finally {
            if (cur != null)
                cur.close();
        }
        return false;
    }

}
