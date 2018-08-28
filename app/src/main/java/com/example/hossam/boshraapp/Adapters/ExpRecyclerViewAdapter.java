package com.example.hossam.boshraapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hossam.boshraapp.Activities.CommentsActivity;
import com.example.hossam.boshraapp.Helpers.ApiClient;
import com.example.hossam.boshraapp.Helpers.ApiClient2;
import com.example.hossam.boshraapp.Helpers.ApiInterface;
import com.example.hossam.boshraapp.Helpers.PreferenceHelper;
import com.example.hossam.boshraapp.Helpers.ProgressDialogHelper;
import com.example.hossam.boshraapp.Modules.AllExpsModel;
import com.example.hossam.boshraapp.R;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ExpRecyclerViewAdapter extends RecyclerView.Adapter<ExpRecyclerViewAdapter.ViewHolder> {

    FragmentActivity context;
     private MediaPlayer mp;
     private boolean isliked =false;
    List<AllExpsModel.DataBean> mydata;
    PreferenceHelper helper;


    boolean [] likes;
    public ExpRecyclerViewAdapter(FragmentActivity activity, List<AllExpsModel.DataBean> data) {

        context = activity;
        mydata=data;
        mp = MediaPlayer.create(context, R.raw.facebook_pop);
        helper =new PreferenceHelper(context);
        likes =new boolean[data.size()];
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.experiment_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        RecycleExpAdapter recycleExpAdapter = null;

        if (helper.getUserId()!=null) //////////// if user register
        {
            if (helper.getUserId().matches("1"))  ////////if admin
            {
                holder.useractions.setVisibility(View.GONE);
                holder.postinfo.setVisibility(View.GONE);
                holder.adminaccept.setVisibility(View.VISIBLE);
            }

        }




        if (mydata.get(position).getExperienceimage().size()>0) {
            recycleExpAdapter = new RecycleExpAdapter(context,mydata.get(position).getExperienceimage());
            holder.imagesrecycle.setAdapter(recycleExpAdapter);
        }

        if ( mydata.get(position).getUser().getPhoto()!=null) {

            if (!mydata.get(position).getUser().getPhoto().matches(""))
                Glide.with(context).load("http://sfc-oman.com/library/sfc/" +
                        mydata.get(position).getUser().getPhoto()).into(holder.userimage);

        }

        if (helper.getUserId()!=null) {
            if (mydata.get(position).getLikes().size() > 0) {
                holder.like.setImageResource(R.drawable.liked);
                likes[position]=true;

            }

            else {
                likes[position] = false;

            }

        }

        if (mydata.get(position).getPost().toCharArray().length>25)
          addReadMore(mydata.get(position).getPost(),holder.description);
        else
            holder.description.setText(mydata.get(position).getPost());
        holder.name.setText(mydata.get(position).getUser().getUsername());

        if(mydata.get(position).getExperiencecomments()!=null)
            holder.commentcounter.setText(" "+mydata.get(position).getExperiencecomments().size()+" ");

        if(mydata.get(position).getTotal_like() !=null) {
            if (mydata.get(position).getTotal_like().size()>0)
            holder.likecounter.setText(" "+mydata.get(position).getTotal_like().size()+" ");
            else
                holder.likecounter.setText(" 0 ");
        }
        else
            holder.likecounter.setText(" 0 ");

     //   if (mydata.get(position).getUser().getPhoto()!=null)
        Locale locale = new Locale("ar");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-d");
        Date date3 = null;
        try {
            date3 = sdf.parse(mydata.get(position).getCreated());
        }
        catch (Exception e) {
        }

        sdf = new SimpleDateFormat("dd MMMM yyyy",locale);
        String format = sdf.format(date3);
            holder.time.setText(format);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        holder.adminaccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            acceptPost(mydata.get(position).getId());
                mydata.remove(mydata.get(position));

                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, mydata.size());
            }
        });


        holder.comment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context,CommentsActivity.class);
                //intent.putExtra("comments",  mydata.get(position).getExperiencecomments());

                intent.putExtra("postid", mydata.get(position).getId());
                context.startActivity(intent);
            }
        });

        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (helper.getUserId()!=null)
                {

                    if (!likes[position]) {
                        mp.start();
                        holder.like.setImageResource(R.drawable.liked);
                        isliked = true;
                        likes[position]=true;
                        int likec= Integer.parseInt(holder.likecounter.getText().toString())+1;
                        holder.likecounter.setText(likec+ "");
                        makeLike(mydata.get(position).getId(), helper.getUserId(), 1);
                    }
                    else
                    {
                        holder.like.setImageResource(R.drawable.like);
                        deleteLike(mydata.get(position).getId(), helper.getUserId());
                        int likec= Integer.parseInt(holder.likecounter.getText().toString())-1;
                        holder.likecounter.setText(likec+ "");
                        likes[position]=false;

                    }

                }
                else
                    Toast.makeText(context,context.getResources().getString(R.string.loginfirst), Toast.LENGTH_SHORT).show();

                if (!isliked)
                    {

                    }
            }
        });

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com?pid="+mydata.get(position).getId());
                intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "any extra text with the link");
                context.startActivity(Intent.createChooser(intent, "Share"));

      /*      Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Boshra App");
                String sAux = "Your friend suggests that post for you\n\n";
                sAux = sAux + "https://play.google.com/store/apps/developer?id=codesroots&pid="+mydata.get(position).getId()+"\n";
                intent.putExtra(Intent.EXTRA_TEXT, sAux);
                context.startActivity(Intent.createChooser(intent, "Share"));*/
            }
        });
    }


    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MultipartBody.FORM, descriptionString);
    }


     private boolean returnvalue =false;

    private boolean makeLike(int postid, String userid, int statues) {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiService.MakeLike(
                createPartFromString(String.valueOf(postid)),
                createPartFromString(String.valueOf(userid)),
                createPartFromString(String.valueOf(statues)));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                if (response.body() != null) {
                    if (response.isSuccessful())
                    {
                        returnvalue=true;
                    }
                        }
                else {
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("fail",call.toString());
           //   Toast.makeText(getActivity(), getString(R.string.something_wrong), Toast.LENGTH_SHORT).show();
            }
        });
            return  returnvalue;
    }

    private boolean acceptPost(int postid) {

        ApiInterface apiService =
                ApiClient2.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiService.changeStatus(
                String.valueOf(postid),
                createPartFromString("1"));

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                if (response.body() != null) {
                    if (response.isSuccessful())
                    {
                        returnvalue=true;
                    }
                }

                else
                {
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("fail",call.toString());
                //   Toast.makeText(getActivity(), getString(R.string.something_wrong), Toast.LENGTH_SHORT).show();
            }
        });
        return  returnvalue;
    }


    private boolean deleteLike(int postid, String userid) {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseBody> call = apiService.deleteLike(
               String.valueOf(postid),
             userid);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {

                if (response.body() != null) {
                    if (response.isSuccessful())
                    {
                        returnvalue=true;
                    }
                }
                else {
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("fail",call.toString());
                //   Toast.makeText(getActivity(), getString(R.string.something_wrong), Toast.LENGTH_SHORT).show();
            }
        });
        return  returnvalue;
    }
    @Override
    public int getItemCount() {
        return mydata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        TextView name,time,description,likecounter,commentcounter,sharecounter;
        ImageView like,comment,share,userimage;
        RecyclerView imagesrecycle;
        LinearLayout useractions,postinfo,adminaccept;
        public ViewHolder(View view) {
            super(view);
            mView = view;
            name =  view.findViewById(R.id.name);
            time =  view.findViewById(R.id.time);
            description =  view.findViewById(R.id.description);
            likecounter =  view.findViewById(R.id.likes);
            commentcounter =  view.findViewById(R.id.comments);

            imagesrecycle =  view.findViewById(R.id.images_recycler);
            comment=view.findViewById(R.id.comment);
            like=view.findViewById(R.id.like);
            share =view.findViewById(R.id.share);
            userimage =view.findViewById(R.id.user_image);
            useractions =view.findViewById(R.id.useractions);
            postinfo =view.findViewById(R.id.counters);
            adminaccept =view.findViewById(R.id.adminaccept);
        }
    }

    private void addReadMore(final String text, final TextView textView) {

        SpannableString ss = new SpannableString(text.substring(0,20) +context.getResources().getString(R.string.readmore));
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                addReadLess(text, textView);
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                 //   ds.setColor(getResources().getColor(R.color.color_primary, getTheme()));
                } else {
                   // ds.setColor(getResources().getColor(R.color.color_primary));
                }
            }
        };
        ss.setSpan(clickableSpan, ss.length() - 10, ss.length() , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void addReadLess(final String text, final TextView textView) {
        SpannableString ss = new SpannableString(text +context.getResources().getString(R.string.readless));
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                addReadMore(text, textView);
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                 //   ds.setColor(getResources().getColor(R.color.color_primary, getTheme()));
                } else {
                  //  ds.setColor(getResources().getColor(R.color.color_primary));
                }
            }
        };

        ss.setSpan(clickableSpan, ss.length() - 10, ss.length() , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
