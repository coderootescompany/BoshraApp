package com.example.hossam.boshraapp.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hossam.boshraapp.Activities.CommentsActivity;
import com.example.hossam.boshraapp.Fragments.ReservationFragment;
import com.example.hossam.boshraapp.Modules.AllDoctors;
import com.example.hossam.boshraapp.Modules.CommentsModel;
import com.example.hossam.boshraapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * Created by hossam on 21/07/2018.
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder>  {

    private Context context;
   List<CommentsModel.DataBean.ExperiencecommentsBean> mValues;


    public CommentsAdapter(CommentsActivity commentsActivity, List<CommentsModel.DataBean.ExperiencecommentsBean> data) {

        context=commentsActivity;
        mValues=data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


            holder.comment.setText(mValues.get(position).getComment());
            holder.date.setText(getdate(mValues.get(position).getCreated()));
            holder.name.setText(mValues.get(position).getUser().getUsername());

        if ( mValues.get(position).getUser().getPhoto()!=null) {
            if (!mValues.get(position).getUser().getPhoto().toString().matches(""))
                Glide.with(context).load("http://sfc-oman.com/library/sfc/" +
                        mValues.get(position).getUser().getPhoto()).into(holder.userimage);
        }

        else
            holder.userimage.setImageResource(R.drawable.userone);

    }
    private String  getdate(String date)
    {
        Locale locale = new Locale("ar");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-d");
        Date date3 = null;
        try {
            date3 = sdf.parse(date);
        }
        catch (Exception e) {
        }

        sdf = new SimpleDateFormat("dd MMMM yyyy",locale);

        return sdf.format(date3);

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        public TextView name,date,comment;
        public ImageView userimage;
        LinearLayout doctorLayout;
        public ViewHolder(View view) {

            super(view);
            mView = view;

          name=mView.findViewById(R.id.name);
            date=mView.findViewById(R.id.time);
            comment=mView.findViewById(R.id.comment);
            userimage=mView.findViewById(R.id.user_image);


        }
    }

}

