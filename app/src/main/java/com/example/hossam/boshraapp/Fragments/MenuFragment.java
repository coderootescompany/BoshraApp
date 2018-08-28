package com.example.hossam.boshraapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.hossam.boshraapp.Activities.ExpermentsActivity;
import com.example.hossam.boshraapp.Activities.LanguageActivity;
import com.example.hossam.boshraapp.Helpers.PreferenceHelper;
import com.example.hossam.boshraapp.ProfileActivity;
import com.example.hossam.boshraapp.R;
import com.google.android.gms.plus.PlusOneButton;


public class MenuFragment extends Fragment {


    public MenuFragment() {
        // Required empty public constructor
    }

    FragmentManager fragmentManager;
    Fragment fragment;
    FragmentManager fr;
    FragmentTransaction ft;
    PreferenceHelper helper;
    LinearLayout profile,medicalsub,experment,login,logout,language;
    View logview,logtview;

    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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

        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        profile=view.findViewById(R.id.profilelayout);
        medicalsub=view.findViewById(R.id.medicalsub);
        experment=view.findViewById(R.id.experment);
        login=view.findViewById(R.id.login);
        language=view.findViewById(R.id.lang);
        logout=view.findViewById(R.id.logout);
        logview=view.findViewById(R.id.lognview);
        logtview=view.findViewById(R.id.logoutview);
        logout=view.findViewById(R.id.logout);

        helper=new PreferenceHelper(getActivity());

        if (helper.getUserId()!=null ) {
            login.setVisibility(View.GONE);
            logview.setVisibility(View.GONE);
        }

        else {

            logout.setVisibility(View.GONE);
            logtview.setVisibility(View.GONE);
        }

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (helper.getUserId()==null)
                    Toast.makeText(getActivity(),getResources().getString(R.string.loginfirst),Toast.LENGTH_SHORT).show();
                else
                startActivity(new Intent(getActivity(), ProfileActivity.class));
            }
        });

        medicalsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new SubjectsFragment();
                fr = getActivity().getSupportFragmentManager();
                ft = fr.beginTransaction();
                ft.replace(R.id.main_frame, fragment);
                ft.commit();
            }
        });

        experment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

      startActivity(new Intent(getActivity(), ExpermentsActivity.class));

       /*  fragment = new ExpFragment();
                fr = getActivity().getSupportFragmentManager();
                ft = fr.beginTransaction();
                ft.replace(R.id.main_frame, fragment);
                ft.commit();*/


            }
        });

        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(getActivity(), LanguageActivity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new RegisterFragment();
                fr = getActivity().getSupportFragmentManager();
                ft = fr.beginTransaction();
                ft.replace(R.id.main_frame, fragment);
                ft.commit();
                login.setVisibility(View.GONE);
                logout.setVisibility(View.VISIBLE);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_frame, new MainFragment()).disallowAddToBackStack().commitAllowingStateLoss();
                Toast.makeText(getActivity(), R.string.loged_out, Toast.LENGTH_SHORT).show();
                helper.setUserId("");
                helper.setToken("");
                helper.Logout();
             /*   SharedPreferences.Editor clears = getActivity().getSharedPreferences("userdetails", Context.MODE_PRIVATE).edit();
                clears.clear();
                clears.apply();*/
                login.setVisibility(View.VISIBLE);
                logout.setVisibility(View.GONE);
            }
        });

        return view;
    }

}
