package com.example.hossam.boshraapp;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hossam.boshraapp.Fragments.ReservationFragment;
import com.example.hossam.boshraapp.Helpers.PreferenceHelper;
import com.example.hossam.boshraapp.Modules.DoctorsInDeptModel;
import java.util.List;


public class DoctorsInDeptAdapter extends RecyclerView.Adapter<DoctorsInDeptAdapter.ViewHolder> {

    Fragment fragment;
    FragmentManager fr;
    FragmentTransaction ft;
    FragmentManager fragmentManager;
    private  List<DoctorsInDeptModel.DataBean> mValues;
      Context  context;
      PreferenceHelper helper;
    public DoctorsInDeptAdapter(FragmentActivity activity, List<DoctorsInDeptModel.DataBean> data) {

        mValues=data;
        context=activity;
        helper=new PreferenceHelper(context);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.doctors_in_dept_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (helper.getLanguage().matches("0")) {
            holder.dname.setText(mValues.get(position).getName());
            holder.speial.setText(mValues.get(position).getTitle());
        }
        else {

            holder.dname.setText(mValues.get(position).getName_en());
            holder.speial.setText(mValues.get(position).getTitle_en());
        }

        if (mValues.get(position).getRatings()!=null ) {
            if (mValues.get(position).getRatings()!=null) {
                if (mValues.get(position).getRatings().size() > 0) {
                    if (mValues.get(position).getRatings().get(0).getCount() > 0 &&
                            mValues.get(position).getRatings().get(0).getStars() > 0)
                        holder.ratingBar.setRating( mValues.get(position).getRatings().get(0).getStars()
                                / mValues.get(position).getRatings().get(0).getCount());

                    else
                        holder.ratingBar.setRating(0);
                }
                else
                    holder.ratingBar.setRating(0);
            }
            else
                holder.ratingBar.setRating(0);
        }
        else
            holder.ratingBar.setRating(0);

        Glide.with(context).load("http://sfc-oman.com/library/doctors/" +
                mValues.get(position).getPhoto()).into(  holder.mContentView);

        holder.face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (!mValues.get(position).getFb().matches(""))
               {  Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mValues.get(position).getFb()));
                   context.startActivity(intent);
               }

               else
                   Toast.makeText(context,context.getResources().getString(R.string.nofb),Toast.LENGTH_SHORT).show();


            }
        });
        holder.whtas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });

        holder.reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle =new Bundle();
                bundle.putString("image","http://sfc-oman.com/library/doctors/" + mValues.get(position).getPhoto());
                bundle.putInt("doctor_id",mValues.get(position).getId());

                if (helper.getLanguage().matches("0")) {
                    bundle.putString("name", mValues.get(position).getName());
                    bundle.putString("spicialist", mValues.get(position).getTitle());
                }

                else
                {
                    bundle.putString("name", mValues.get(position).getName_en());
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

               /* Fragment fragment = new ReservationFragment();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_frame, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/

                ReservationFragment fragobj = new ReservationFragment();
                fragobj.setArguments(bundle);
                fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_frame, fragobj).commit();

            }
        });


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        final ImageView mContentView;
        TextView dname,speial,reserve;
        RatingBar ratingBar;
        ImageView face,whtas;
        public ViewHolder(View view) {
            super(view);

            mContentView = view.findViewById(R.id.doctor_image);
            dname = view.findViewById(R.id.name);
            reserve = view.findViewById(R.id.reserve);
            speial = view.findViewById(R.id.specialist);
            ratingBar = view.findViewById(R.id.rates);
            face = view.findViewById(R.id.face);
            whtas = view.findViewById(R.id.whts);

        }

    }
}
