package com.example.hossam.boshraapp.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hossam.boshraapp.Helpers.ApiClient;
import com.example.hossam.boshraapp.Helpers.ApiInterface;
import com.example.hossam.boshraapp.Helpers.PreferenceHelper;
import com.example.hossam.boshraapp.Helpers.ProgressDialogHelper;
import com.example.hossam.boshraapp.Modules.LoginModel;
import com.example.hossam.boshraapp.R;
import com.google.android.gms.plus.PlusOneButton;

import java.util.Calendar;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ReservationFragment extends Fragment{

    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;
    private String date_time = "", day_name, month_name, days_number;
    Fragment fragment;
    FragmentManager fr;
    FragmentTransaction ft;
    TextView name,specialist;
    ImageView doctorimg;
    EditText reservedate,uname,mobile;
    RatingBar ratingBar;
    PreferenceHelper helper;
    ImageView face,whats;
    public ReservationFragment() {
        // Required empty public constructor
       }

    public static ReservationFragment newInstance(String param1, String param2) {
        ReservationFragment fragment = new ReservationFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reservation, container, false);

        helper=new PreferenceHelper(getActivity());

        uname=view.findViewById(R.id.uname);
        mobile=view.findViewById(R.id.mobile);
        ratingBar=view.findViewById(R.id.rates);
        face=view.findViewById(R.id.face);
        whats=view.findViewById(R.id.whats);


        //Find the +1 button
        final Bundle b = getArguments();

        name=view.findViewById(R.id.name);
        specialist=view.findViewById(R.id.specialist);
        doctorimg=view.findViewById(R.id.doctor_image);

            view.findViewById(R.id.reserve).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (helper.getUserId() !=null)
                      resevedoctor(b.getInt("doctor_id"), Integer.parseInt(helper.getUserId()));

                    else
                        Toast.makeText(getActivity(),getResources().getString(R.string.loginfirst),Toast.LENGTH_SHORT).show();
                }
            });

            view.findViewById(R.id.face).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!b.get("face").toString().matches(""))
                    {  Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(b.get("face").toString()));
                        getActivity().startActivity(intent);}
                    else
                        Toast.makeText(getActivity(),getActivity().getResources().getString(R.string.nofb),Toast.LENGTH_SHORT).show();

                }
            });

        view.findViewById(R.id.whats).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!b.get("phone").toString().matches("")) {
                    if (!contactExists(getActivity(), b.get("phone").toString())) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                        builder1.setMessage(getActivity().getResources().getString(R.string.notexists));
                        builder1.setCancelable(true);

                        builder1.setPositiveButton(
                                "تسجبل ",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        Intent contactIntent = new Intent(ContactsContract.Intents.Insert.ACTION);
                                        contactIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                                        contactIntent
                                                .putExtra(ContactsContract.Intents.Insert.NAME, b.get("name").toString())
                                                .putExtra(ContactsContract.Intents.Insert.PHONE, b.get("phone").toString())
                                                .putExtra(ContactsContract.Intents.Insert.EMAIL, "INFO@SFC-OMAN.COM");
                                        getActivity().startActivityForResult(contactIntent, 1);
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
                    } else
                        openWhatsApp(b.get("phone").toString());

                }

                else
                    Toast.makeText(getActivity(),getActivity().getResources().getString(R.string.nowhts),Toast.LENGTH_SHORT).show();

            }


        });



        ratingBar.setRating(b.getFloat("rate"));
        reservedate= view.findViewById(R.id.reservedate);

        String dd=b.getString("image");

        int ddid=b.getInt("doctor_id");

        Glide.with(getActivity()).load(b.getString("image")).into(doctorimg);
        name.setText(b.getString("name"));
        specialist.setText(b.getString("spicialist"));

       view.findViewById(R.id.reservedate).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               datePicker();

           }
       });

        return view;
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

    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MultipartBody.FORM, descriptionString);
    }

    private void resevedoctor(int doctor_id, int userid) {

        if (!uname.getText().toString().trim().equals("") || !mobile.getText().toString().trim().equals("")
                || !reservedate.getText().toString().trim().equals(""))
        {

            ProgressDialogHelper.showSimpleProgressDialog(getActivity(), false);
            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);

            Call<LoginModel> call = apiService.AddResevation(
                    createPartFromString(String.valueOf(doctor_id)),
                    createPartFromString(String.valueOf(userid)),
                    createPartFromString(name.getText().toString().trim()),
                    createPartFromString(mobile.getText().toString().trim()),
                    createPartFromString(reservedate.getText().toString().trim())

                    );

            call.enqueue(new Callback<LoginModel>() {
                @Override
                public void onResponse(Call<LoginModel> call, final Response<LoginModel> response) {
                    ProgressDialogHelper.removeSimpleProgressDialog();
                    if (response.body() != null) {

                        Log.d("response" , response.body().toString()) ;

                        if (response.body().isSuccess())
                        {
                            Log.d("success","ss");

                            Toast.makeText(getActivity(),getResources().getString(R.string.reservesuccess),Toast.LENGTH_LONG).show();
                            fragment = new MainFragment();
                            fr = getActivity().getSupportFragmentManager();
                            ft = fr.beginTransaction();
                            ft.replace(R.id.main_frame , fragment);
                            ft.commit();

                        }

                        else
                            Toast.makeText(getActivity(),getResources().getString(R.string.userorpasserror),Toast.LENGTH_LONG).show();
                    }

                    else
                    {
                        Toast.makeText(getActivity(), getString(R.string.something_wrong), Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<LoginModel> call, Throwable t) {
                    ProgressDialogHelper.removeSimpleProgressDialog();
                    Log.d("fail",call.toString());
                    Toast.makeText(getActivity(), getString(R.string.connection_error), Toast.LENGTH_SHORT).show();

                }
            });

        }
        else
        {

            Toast.makeText(getActivity(), R.string.complete, Toast.LENGTH_SHORT).show();
        }

    }

    private void openWhatsApp(String smsNumber) {

        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        sendIntent.putExtra(Intent.EXTRA_TEXT, getActivity().getResources().getString(R.string.send));
        sendIntent.putExtra("jid", smsNumber + "@s.whatsapp.net"); //phone number without "+" prefix
        sendIntent.setPackage("com.whatsapp");
        if (sendIntent.resolveActivity(getActivity().getPackageManager()) == null) {
            Toast.makeText(getActivity(), "Error/n" , Toast.LENGTH_SHORT).show();
            return;
        }

        getActivity().startActivity(sendIntent);
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

    @Override
    public void onResume() {
        super.onResume();

        // Refresh the state of the +1 button each time the activity receives focus.
    }

    private void datePicker() {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {

                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        Log.d("year",year+"");
                        Log.d("monthOfYear",monthOfYear+"");
                        Log.d("dayOfMonth",dayOfMonth+"");
                        if (monthOfYear + 1 == 1) {
                            month_name = getString(R.string.January);
                        } else if (monthOfYear + 1 == 2) {
                            month_name = getString(R.string.February);
                        } else if (monthOfYear + 1 == 3) {
                            month_name = getString(R.string.March);
                        } else if (monthOfYear + 1 == 4) {
                            month_name = getString(R.string.April);
                        } else if (monthOfYear + 1 == 5) {
                            month_name = getString(R.string.May);
                        } else if (monthOfYear + 1 == 6) {
                            month_name = getString(R.string.June);
                        } else if (monthOfYear + 1 == 7) {
                            month_name = getString(R.string.July);
                        } else if (monthOfYear + 1 == 8) {
                            month_name = getString(R.string.August);
                        } else if (monthOfYear + 1 == 9) {
                            month_name = getString(R.string.September);
                        } else if (monthOfYear + 1 == 10) {
                            month_name = getString(R.string.October);
                        } else if (monthOfYear + 1 == 11) {
                            month_name = getString(R.string.November);
                        } else if (monthOfYear + 1 == 12) {
                            month_name = getString(R.string.December);
                        }

                        reservedate.setText(dayOfMonth+"/"+month_name+"/"+year);
                    /*    int d = dayOfMonth;
                        int m = monthOfYear + 1;
                        int y = year;
                        if (m < 3) {
                            m += 12;
                            y -= 12;
                        }
                        int k = y % 100;
                        int j = y / 100;
                        int day = ((d + (((m + 1) * 26) / 10) + k + (k / 4) + (j / 4) + (5 * j)) % 7);

                        final String DAYS[] = {getString(R.string.saturday), getString(R.string.sunday),
                                getString(R.string.monday), getString(R.string.tuesday),
                                getString(R.string.wednesday), getString(R.string.thursday),
                                getString(R.string.friday)};

                        day_name = DAYS[day];
                        date_time = month_name + "/" + day_name;
                        days_number = String.valueOf(dayOfMonth);*/

                        //*************Call Time Picker Here ********************
                        //tiemPicker();
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }


}
