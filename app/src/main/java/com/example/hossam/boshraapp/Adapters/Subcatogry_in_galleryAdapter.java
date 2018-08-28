package com.example.hossam.boshraapp.Adapters;

import android.content.Context;
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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hossam.boshraapp.Fragments.ImagesGalleryFragmentForSubCatogry;
import com.example.hossam.boshraapp.Modules.ImageModel;
import com.example.hossam.boshraapp.Modules.SubCatogryModel;
import com.example.hossam.boshraapp.R;
import java.util.ArrayList;


public class Subcatogry_in_galleryAdapter extends RecyclerView.Adapter<Subcatogry_in_galleryAdapter.ViewHolder> {

    //private final List<DummyItem> mValues;

    ArrayList<SubCatogryModel.GetALlCatsBean> alldata;
    Context context;
    ArrayList<ImageModel> images = new ArrayList<>();

    Fragment fragment;
    FragmentManager fr;
    FragmentTransaction ft;


    public Subcatogry_in_galleryAdapter(Context activity, ArrayList<SubCatogryModel.GetALlCatsBean> getALlCats) {
        context=activity;
        alldata=getALlCats;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subcatogry_in_galleryfragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.name.setText(alldata.get(position).getName());
        Glide.with(context).load("http://sfc-oman.com/library/photos/"+alldata.get(position).getGalaries().get(0).getPhoto())
                .thumbnail(0.5f)
                .override(200,200)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                fragment = new ImagesGalleryFragmentForSubCatogry();
                Bundle  bundle=new Bundle();
                bundle.putInt("index",position);
                fragment.setArguments(bundle);
                fr =  ((FragmentActivity)context).getSupportFragmentManager();
                ft = fr.beginTransaction();
                ft.replace(R.id.main_frame, fragment);
                ft.commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return alldata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
      public final View mView;
      public  TextView name;
       public  ImageView image;


        public ViewHolder(View view) {

            super(view);
            mView = view;
            name =  view.findViewById(R.id.sub_name);
            image =  view.findViewById(R.id.sub_image);
        }

    }
}
