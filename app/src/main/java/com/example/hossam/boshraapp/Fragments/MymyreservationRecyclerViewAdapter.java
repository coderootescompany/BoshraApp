package com.example.hossam.boshraapp.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hossam.boshraapp.Helpers.PreferenceHelper;
import com.example.hossam.boshraapp.Modules.MyReservationModel;
import com.example.hossam.boshraapp.R;

import java.util.List;

import static java.security.AccessController.getContext;


public class MymyreservationRecyclerViewAdapter extends RecyclerView.Adapter<MymyreservationRecyclerViewAdapter.ViewHolder> {

    FragmentActivity activity;
    List<MyReservationModel.DataBean> mydata;
     PreferenceHelper helper;

    public MymyreservationRecyclerViewAdapter(FragmentActivity context, List<MyReservationModel.DataBean> data) {

        activity = context;
        mydata = data;
        helper = new PreferenceHelper(activity);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.myreservationfragment_item, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        if (helper.getLanguage().matches("0"))
        {
            if ((mydata.get(position).getDoctor().getName()!=null))
                 holder.name.setText(mydata.get(position).getDoctor().getName());
            holder.spiecial.setText(mydata.get(position).getDoctor().getTitle());
            holder.date.setText(mydata.get(position).getDate_res()+" : ");
        }
        else
        {
            holder.name.setText(mydata.get(position).getDoctor().getName_en());
            holder.spiecial.setText(mydata.get(position).getDoctor().getTitle_en());
            holder.date.setText(" : "+mydata.get(position).getDate_res());
        }


        holder.face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mydata.get(position).getDoctor().getFb().matches(""))
                {  Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mydata.get(position).getDoctor().getFb()));
                 activity.startActivity(intent);}
                 else
                    Toast.makeText(activity,activity.getResources().getString(R.string.nofb),Toast.LENGTH_SHORT).show();
            }
        });
        holder.whats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!contactExists(activity,"+"+mydata.get(position).getMobile()))
                {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(activity);
                    builder1.setMessage(activity.getResources().getString(R.string.notexists));
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "تسجبل ",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    Intent contactIntent = new Intent(ContactsContract.Intents.Insert.ACTION);
                                    contactIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                                    contactIntent
                                            .putExtra(ContactsContract.Intents.Insert.NAME,mydata.get(position).getName())
                                            .putExtra(ContactsContract.Intents.Insert.PHONE, mydata.get(position).getMobile())
                                            .putExtra(ContactsContract.Intents.Insert.EMAIL, "INFO@SFC-OMAN.COM");
                                    activity.startActivityForResult(contactIntent, 1);
                                    dialog.cancel();
                                }
                            });

                    builder1.setNegativeButton(
                            "لا ",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
                else
                    openWhatsApp(mydata.get(position).getMobile());

            }
        });


        Glide.with(activity).load("http://sfc-oman.com/library/doctors/" +
                mydata.get(position).getDoctor().getPhoto()).into(holder.doctorImage);


      if (mydata.get(position).getDoctor().getRatings()!=null ) {

            if (mydata.get(position).getDoctor().getRatings()!=null) {
                if (mydata.get(position).getDoctor().getRatings().size() > 0) {
                    if (mydata.get(position).getDoctor().getRatings().get(0).getCount() > 0 &&
                            mydata.get(position).getDoctor().getRatings().get(0).getStars() > 0)

                        holder.ratingBar.setRating( mydata.get(position).getDoctor().getRatings().get(0).getStars()
                                / mydata.get(position).getDoctor().getRatings().get(0).getCount());
                    else
                        holder.ratingBar.setRating(0);
                }
                else
                    holder.ratingBar.setRating(0);
            }
            else
                holder.ratingBar.setRating(0);
        }

    }
    private void openWhatsApp(String smsNumber) {


        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        sendIntent.putExtra(Intent.EXTRA_TEXT, activity.getResources().getString(R.string.send));
        sendIntent.putExtra("jid", smsNumber + "@s.whatsapp.net"); //phone number without "+" prefix
        sendIntent.setPackage("com.whatsapp");
        if (sendIntent.resolveActivity(activity.getPackageManager()) == null) {
            Toast.makeText(activity, "Error/n" , Toast.LENGTH_SHORT).show();
            return;
        }

        activity.startActivity(sendIntent);
    }

    public boolean contactExists(Context context, String number) {

        Uri lookupUri = Uri.withAppendedPath(
                ContactsContract.PhoneLookup.CONTENT_FILTER_URI,
                Uri.encode(number));
        String[] mPhoneNumberProjection = { ContactsContract.PhoneLookup._ID, ContactsContract.PhoneLookup.NUMBER, ContactsContract.PhoneLookup.DISPLAY_NAME };
        Cursor cur = context.getContentResolver().query(lookupUri,mPhoneNumberProjection, null, null, null);
        try {
            if (cur.moveToFirst()) {
                return true;
            }
        } finally {
            if (cur != null)
                cur.close();
        }
        return false;
    }

    @Override
    public int getItemCount() {
        return mydata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        TextView name,spiecial,date;
        RatingBar ratingBar;
        ImageView doctorImage,face,whats;
        public ViewHolder(View view) {
            super(view);
            mView = view;

            name=mView.findViewById(R.id.name);
            spiecial=mView.findViewById(R.id.special);
            date=mView.findViewById(R.id.date);
            ratingBar=mView.findViewById(R.id.rates);
            doctorImage=mView.findViewById(R.id.doctor_image);
            face=mView.findViewById(R.id.faceicon);
            whats=mView.findViewById(R.id.whatsicon);
        }


    }
}
