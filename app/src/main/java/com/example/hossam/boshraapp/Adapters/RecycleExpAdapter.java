package com.example.hossam.boshraapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hossam.boshraapp.Activities.DetailImageActivity;
import com.example.hossam.boshraapp.Modules.AllDoctors;
import com.example.hossam.boshraapp.Modules.AllExpsModel;
import com.example.hossam.boshraapp.Modules.ImageModel;
import com.example.hossam.boshraapp.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by hossam on 21/07/2018.
 */

public class RecycleExpAdapter extends RecyclerView.Adapter<RecycleExpAdapter.ViewHolder>  {

    private Context context;
    private List<AllExpsModel.DataBean.ExperienceimageBean> eexperienceimage ;
    ArrayList<ImageModel> images = new ArrayList<>();
    RecycleExpAdapter(FragmentActivity activity, List<AllExpsModel.DataBean.ExperienceimageBean> experienceimage) {
        context=activity;
        eexperienceimage = experienceimage;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_item_inexp, parent, false);
        return new ViewHolder(view);
    }
    //http://sfc-oman.com/library/exp/

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (eexperienceimage.get(position).getImage()!=null)

        Glide.with(context).load("http://192.168.1.8:8080/sfc/library/sfc/" +
                eexperienceimage.get(position).getImage()).into(holder.Image);

        holder.Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i = 0; i < eexperienceimage.size(); i++) {
                    ImageModel imageModel = new ImageModel();
                    imageModel.setName("Image " + i);
                    imageModel.setUrl("http://192.168.1.8:8080/sfc/library/sfc/"+eexperienceimage.get(i).getImage());
                    images.add(imageModel);
                }

                Intent intent = new Intent(context, DetailImageActivity.class);
                intent.putParcelableArrayListExtra("data", images);
                intent.putExtra("pos",position);
                context.startActivity(intent);
            }
        });


      /*holder.doctorname.setText(mValues.get(position).getName());
      Glide.with(context).load("http://sfc-oman.com/library/doctors/" +
                mValues.get(position).getPhoto()).into(holder.doctorImage);*/
    }

    @Override
    public int getItemCount() {
        return eexperienceimage.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

      //  public BloodModel.GetData mItem;
        public TextView doctorname;
        public ImageView Image;
        public ViewHolder(View view) {

            super(view);
            mView = view;
         //doctorname=mView.findViewById(R.id.doctorname);
           Image=mView.findViewById(R.id.expimage);

        }
    }

}

