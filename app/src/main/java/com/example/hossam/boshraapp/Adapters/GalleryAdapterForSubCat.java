package com.example.hossam.boshraapp.Adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hossam.boshraapp.Modules.NewFarhaModel;
import com.example.hossam.boshraapp.Modules.SubCatogryModel;
import com.example.hossam.boshraapp.R;

import java.util.ArrayList;
import java.util.List;


public class GalleryAdapterForSubCat extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<SubCatogryModel.GetALlCatsBean.GalariesBean> data = new ArrayList<>();

    public GalleryAdapterForSubCat(FragmentActivity activity, List<SubCatogryModel.GetALlCatsBean.GalariesBean> galaries) {
        this.context = activity;
        data=galaries;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View v;
            v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.list_item, parent, false);
            viewHolder = new MyItemHolder(v);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            Glide.with(context).load("http://sfc-oman.com/library/photos/"+data.get(position).getPhoto())
                    .thumbnail(0.5f)
                    .override(200,200)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(((MyItemHolder) holder).mImg);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyItemHolder extends RecyclerView.ViewHolder {
        ImageView mImg;
        public MyItemHolder(View itemView) {
            super(itemView);
            mImg =  itemView.findViewById(R.id.item_img);
        }
    }
}
