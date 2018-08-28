package com.example.hossam.boshraapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hossam.boshraapp.DoctorsInDeptAdapter;
import com.example.hossam.boshraapp.DoctorsInDeptFragment;
import com.example.hossam.boshraapp.Fragments.MainFragment;
import com.example.hossam.boshraapp.Helpers.PreferenceHelper;
import com.example.hossam.boshraapp.Modules.AllDepartment;
import com.example.hossam.boshraapp.R;

import java.util.ArrayList;


/**
 * Created by hossam on 21/07/2018.
 */

public class DepartmentsAdapter extends RecyclerView.Adapter<DepartmentsAdapter.ViewHolder>  {

    private Context context;

    private ArrayList<AllDepartment.DataBean> mValues;
    Fragment fragment;
    FragmentManager fr;
    FragmentTransaction ft;

    PreferenceHelper helper;
    public DepartmentsAdapter(FragmentActivity activity, ArrayList<AllDepartment.DataBean> data) {
        context = activity;
        mValues=data;
        helper = new PreferenceHelper(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.department_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (helper.getLanguage().matches("0"))
        holder.departmentname.setText(mValues.get(position).getName());
        else
            holder.departmentname.setText(mValues.get(position).getName_en());

        Glide.with(context).load("http://sfc-oman.com/images/" +
                mValues.get(position).getPhoto()).into(holder.departmentImage);

        holder.departmentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*Fragment fragment = new DoctorsInDeptFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("d_id", mValues.get(position).getId());
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_frame, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/


                fragment = new DoctorsInDeptFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("d_id", mValues.get(position).getId());
                fragment.setArguments(bundle);
                fr = ((FragmentActivity)context).getSupportFragmentManager();
                ft = fr.beginTransaction();
                ft.replace(R.id.main_frame , fragment);
                ft.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public TextView departmentname;
        public ImageView departmentImage;
        public ViewHolder(View view) {

            super(view);
            mView = view;
            departmentname=mView.findViewById(R.id.departmerntname);
            departmentImage=mView.findViewById(R.id.departmentimage);


        }

    }


}

