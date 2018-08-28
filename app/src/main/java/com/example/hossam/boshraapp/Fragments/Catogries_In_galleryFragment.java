package com.example.hossam.boshraapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.hossam.boshraapp.DoctorsInDeptFragment;
import com.example.hossam.boshraapp.R;

public class Catogries_In_galleryFragment extends Fragment {

    LinearLayout layout1,layout2;

    Fragment fragment;
    FragmentManager fr;
    FragmentTransaction ft;
    public Catogries_In_galleryFragment() {
        // Required empty public constructor
    }


    public static Catogries_In_galleryFragment newInstance(String param1, String param2) {
        Catogries_In_galleryFragment fragment = new Catogries_In_galleryFragment();

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
        View view = inflater.inflate(R.layout.fragment_firstpage__in_gallery, container, false);
        layout1=view.findViewById(R.id.depart1);
        layout2=view.findViewById(R.id.depart2);

        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new ImagesGalleryFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_frame, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                /*
                fragment = new ImagesGalleryFragment();
                fr = getActivity().getSupportFragmentManager();
                ft = fr.beginTransaction();
                ft.replace(R.id.main_frame, fragment);
                ft.commit();*/
            }
        });

        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new SubCatogry_In_GalleryFragment();

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_frame, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                /*
                fragment = new SubCatogry_In_GalleryFragment();
                fr = getActivity().getSupportFragmentManager();
                ft = fr.beginTransaction();
                ft.replace(R.id.main_frame, fragment);
                ft.commit();*/
            }
        });
                return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

}
