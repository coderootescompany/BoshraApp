package com.example.hossam.boshraapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.hossam.boshraapp.Activities.MoreSubjectsActivity;
import com.example.hossam.boshraapp.Modules.AllPosts;
import com.example.hossam.boshraapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class SubjectsAdapter extends RecyclerView.Adapter<SubjectsAdapter.ViewHolder> {

    private final ArrayList<AllPosts.DataBean> mValues;
    Context mcontext;

    public SubjectsAdapter(FragmentActivity activity, ArrayList<AllPosts.DataBean> data) {
        mcontext=activity;
        mValues = data;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.posts_item, parent, false);
        return new ViewHolder(view);
    }

    private String  getdate(String date,int posi)
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
        String format = sdf.format(date3);
        System.out.print("Result: "+posi + format);
        Log.wtf("result"+posi ,format);

        return format;

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

       holder.title.setText(mValues.get(position).getSubject());

      String postedate=  getdate(mValues.get(position).getCreated(),position);
        //Date date=(Date) mValues.get(position).getCreated();

        holder.postdate.setText(postedate);
       String newpost=android.text.Html.fromHtml(mValues.get(position).getPost()).toString();

        newpost = newpost.replaceAll("\n", "");
        newpost = newpost.replaceAll("\t", "");
       if (newpost.toCharArray().length>45)
        holder.description.setText(newpost.substring(0,45)+"...");
       else
           holder.description.setText(newpost);

        Glide.with(mcontext).load("http://sfc-oman.com/library/photos/" +
                mValues.get(position).getPhoto()).into(holder.postimage);

        final String finalNewpost1 = newpost;

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mcontext, MoreSubjectsActivity.class);
                mcontext.startActivity(intent);
            }
        });

        holder.shareimg.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {

                String imageToShare = "http://sfc-oman.com/stories/storydeatils/10";

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, imageToShare);
               // intent.putExtra(Intent.EXTRA_HTML_TEXT, "http://sfc-oman.com/");
               // intent.putExtra(Intent.EXTRA_SUBJECT, finalNewpost1);
                mcontext.startActivity(Intent.createChooser(intent, "Share"));


            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public  View mView;
        public  TextView title;
        public  TextView description,postdate;
        public  ImageView postimage,shareimg;

        public ViewHolder(View view) {

            super(view);
            mView = view;
            title =  view.findViewById(R.id.titledescription);
            description = view.findViewById(R.id.description);
            postimage =  view.findViewById(R.id.postimage);
            postdate =  view.findViewById(R.id.postdate);
            shareimg =  view.findViewById(R.id.share);
        }

    }
}
