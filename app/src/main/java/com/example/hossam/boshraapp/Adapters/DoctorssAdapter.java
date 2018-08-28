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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hossam.boshraapp.DoctorsInDeptFragment;
import com.example.hossam.boshraapp.Fragments.ReservationFragment;
import com.example.hossam.boshraapp.Helpers.PreferenceHelper;
import com.example.hossam.boshraapp.Modules.AllDoctors;
import com.example.hossam.boshraapp.R;

import java.util.ArrayList;


/**
 * Created by hossam on 21/07/2018.
 */

public class DoctorssAdapter extends RecyclerView.Adapter<DoctorssAdapter.ViewHolder>  {

    private Context context;
    ArrayList<AllDoctors.DataBean> mValues;
    Fragment fragment;
    FragmentManager fr;
    FragmentTransaction ft;
PreferenceHelper helper;
    public DoctorssAdapter(FragmentActivity activity, ArrayList<AllDoctors.DataBean> data) {
        context=activity;
        mValues=data;
        helper=new PreferenceHelper(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.docror_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        if (helper.getLanguage().matches("0"))
            holder.doctorname.setText(mValues.get(position).getName());
        else
            holder.doctorname.setText(mValues.get(position).getName_en());



      Glide.with(context).load("http://sfc-oman.com/library/doctors/" +
                mValues.get(position).getPhoto()).into(holder.doctorImage);

      holder.doctorLayout.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              Bundle bundle =new Bundle();
              bundle.putString("image","http://sfc-oman.com/library/doctors/" + mValues.get(position).getPhoto());
              bundle.putInt("doctor_id",mValues.get(position).getId());
              if (helper.getLanguage().matches("0"))
              {
                  bundle.putString("name",mValues.get(position).getName());
                  bundle.putString("spicialist", mValues.get(position).getTitle());
              }
              else
              {
                  bundle.putString("name",mValues.get(position).getName_en());
                  bundle.putString("spicialist", mValues.get(position).getTitle_en());
              }
              bundle.putString("face",mValues.get(position).getFb());
              bundle.putString("phone",mValues.get(position).getPhone());

              if (mValues.get(position).getRatings()!=null ) {

                  if (mValues.get(position).getRatings()!=null) {
                      if (mValues.get(position).getRatings().size() > 0) {
                          if (mValues.get(position).getRatings().get(0).getCount() > 0 &&
                                  mValues.get(position).getRatings().get(0).getStars() > 0)

                              bundle.putFloat("rate", mValues.get(position).getRatings().get(0).getStars()
                                      / mValues.get(position).getRatings().get(0).getCount());
                          else
                              bundle.putFloat("rate", 0);
                      }
                      else
                          bundle.putFloat("rate", 0);
                  }
                  else
                      bundle.putFloat("rate",0);
              }
              else
                  bundle.putFloat("rate",0);
              fragment = new ReservationFragment();
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

      //  public BloodModel.GetData mItem;
        public TextView doctorname;
        public ImageView doctorImage;
        LinearLayout doctorLayout;
        public ViewHolder(View view) {

            super(view);
            mView = view;
            doctorname=mView.findViewById(R.id.doctorname);
            doctorImage=mView.findViewById(R.id.doctorimage);
            doctorLayout=mView.findViewById(R.id.doctorlayout);


        }
    }

}

