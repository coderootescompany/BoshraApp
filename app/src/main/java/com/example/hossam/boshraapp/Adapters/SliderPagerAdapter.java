package com.example.hossam.boshraapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daimajia.slider.library.SliderLayout;
import com.example.hossam.boshraapp.Fragments.ReservationFragment;
import com.example.hossam.boshraapp.Helpers.PreferenceHelper;
import com.example.hossam.boshraapp.Modules.SliderModel;
import com.example.hossam.boshraapp.R;

import java.util.ArrayList;

/**
 * Created by hossam on 21/07/2018.
 */

public class SliderPagerAdapter extends PagerAdapter {
    private Context activity;
    Fragment fragment;
    FragmentManager fr;
    FragmentTransaction ft;
PreferenceHelper helper;
    private ArrayList<SliderModel.DataBean> mValues=new ArrayList<>();

    public SliderPagerAdapter(FragmentActivity context, ArrayList<SliderModel.DataBean> data) {
        activity=context;
        mValues=data;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        View view = layoutInflater.inflate(R.layout.viewpagerslide, container, false);
        ImageView im_slider =  view.findViewById(R.id.im_slider);
        TextView name= view.findViewById(R.id.doctornametxt);
        TextView speacialist =view.findViewById(R.id.doctorspeaialist);
        TextView reserve =view.findViewById(R.id.reservedoc);
        helper=new PreferenceHelper(activity);

       helper.getLanguage();
        String   check_lang = helper.getLanguage();
        if (mValues.get(position).getDoctor()!=null)
        {
            if (check_lang.matches("0"))
            {
                name.setText(mValues.get(position).getDoctor().getName());
                speacialist.setText(mValues.get(position).getDoctor().getTitle());
            }
            else
            {
                name.setText(mValues.get(position).getDoctor().getName_en());
                speacialist.setText(mValues.get(position).getDoctor().getTitle_en());

            }


        }

     Glide.with(activity.getApplicationContext())
                .load("http://sfc-oman.com/library/sliderdoctor/"+mValues.get(position).getPhoto())
                .into(im_slider);
        container.addView(view);


        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle =new Bundle();
                bundle.putString("image","http://sfc-oman.com/library/sliderdoctor/" + mValues.get(position).getPhoto());
                if (helper.getLanguage().matches("0"))
                {
                    bundle.putString("name",mValues.get(position).getDoctor().getName());
                    bundle.putString("spicialist",mValues.get(position).getDoctor().getTitle());
                }
                else
                {
                    bundle.putString("name",mValues.get(position).getDoctor().getName_en());
                    bundle.putString("spicialist",mValues.get(position).getDoctor().getTitle_en());

                }

                if (mValues.get(position).getDoctor().getRatings()!=null ) {

                    if (mValues.get(position).getDoctor().getRatings()!=null) {
                        if (mValues.get(position).getDoctor().getRatings().size() > 0) {
                            if (mValues.get(position).getDoctor().getRatings().get(0).getCount() > 0 &&
                                    mValues.get(position).getDoctor().getRatings().get(0).getStars() > 0)

                                bundle.putFloat("rate", mValues.get(position).getDoctor().getRatings().get(0).getStars()
                                        / mValues.get(position).getDoctor().getRatings().get(0).getCount());
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



                bundle.putInt("doctor_id",mValues.get(position).getDoctor_id());

                fragment = new ReservationFragment();
                fragment.setArguments(bundle);
                fr = ((FragmentActivity)activity).getSupportFragmentManager();
                ft = fr.beginTransaction();
                ft.replace(R.id.main_frame , fragment);
                ft.commit();
            }
        });


        return view;
    }

    @Override
    public int getCount() {
        return mValues.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}

