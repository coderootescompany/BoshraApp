package com.example.hossam.boshraapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.hossam.boshraapp.Activities.videoActivity;
import com.example.hossam.boshraapp.Modules.VideosModel;
import com.example.hossam.boshraapp.R;
import java.util.List;


public class VideosRecyclerViewAdapter extends RecyclerView.Adapter<VideosRecyclerViewAdapter.ViewHolder> {

    Context context;
    private List<VideosModel.DataBean> mydata;

    public VideosRecyclerViewAdapter(FragmentActivity activity, List<VideosModel.DataBean> data) {

        context=activity;
        mydata=data;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_video_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        Glide.with(context).load("http://sfc-oman.com/library/sfc/"+
                mydata.get(position).getPhoto()).into(holder.imageView);

        holder.name.setText(mydata.get(position).getName());
        holder.wachmum.setText(mydata.get(position).getViews()+" " + context.getResources().getString(R.string.views )+" ");

        Log.d("video",mydata.get(position).getLink());

        holder.wachnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(context, videoActivity.class);
                i.putExtra("urlvideo",mydata.get(position).getLink());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {

        return mydata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        ImageView imageView,wachnow;
        TextView   name,wachmum;
        public ViewHolder(View view) {
            super(view);
            mView = view;
            imageView=mView.findViewById(R.id.videoimg);
            name=mView.findViewById(R.id.vname);
            wachmum=mView.findViewById(R.id.nunviews);
            wachnow=mView.findViewById(R.id.play);
        }

    }
}
