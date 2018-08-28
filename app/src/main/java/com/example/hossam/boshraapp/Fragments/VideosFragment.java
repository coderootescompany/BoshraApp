package com.example.hossam.boshraapp.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hossam.boshraapp.Activities.videoActivity;
import com.example.hossam.boshraapp.Adapters.DoctorssAdapter;
import com.example.hossam.boshraapp.Helpers.ApiClient;
import com.example.hossam.boshraapp.Helpers.ApiInterface;
import com.example.hossam.boshraapp.Helpers.ProgressDialogHelper;
import com.example.hossam.boshraapp.Helpers.RecyclerItemClickListener;
import com.example.hossam.boshraapp.Modules.AllDoctors;
import com.example.hossam.boshraapp.Modules.VideosModel;
import com.example.hossam.boshraapp.R;
import com.example.hossam.boshraapp.Adapters.VideosRecyclerViewAdapter;



import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VideosFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    VideoView videoView;
    RecyclerView mRecyclerView;
    VideosRecyclerViewAdapter videosRecyclerViewAdapter;
    public VideosFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static VideosFragment newInstance(int columnCount) {
        VideosFragment fragment = new VideosFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.videos_fragment_item_list, container, false);
        mRecyclerView =  view.findViewById(R.id.list);
         getVideossData();
        return view;
    }


    private VideosModel videosModel;
    Call<VideosModel> calls;
    private void getVideossData() {

        ProgressDialogHelper.showSimpleProgressDialog(getActivity(),true);
        //  progressBar.setVisibility(View.VISIBLE);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        calls = apiService.getAllVideosData();
        calls.enqueue(new Callback<VideosModel>(){

            @Override
            public void onResponse(Call<VideosModel> call, final Response<VideosModel> response) {
                if (response.body() != null) {
                   ProgressDialogHelper.removeSimpleProgressDialog();
                    videosModel = response.body();
                    videosRecyclerViewAdapter = new VideosRecyclerViewAdapter(getActivity(),videosModel.getData());
                    mRecyclerView.setAdapter(videosRecyclerViewAdapter);
                }
            }
            @Override
            public void onFailure(Call<VideosModel> call, Throwable t) {
                ProgressDialogHelper.removeSimpleProgressDialog();
                if ( getActivity()!=null)
                    Toast.makeText(  getActivity(), getString(R.string.connection_error),Toast.LENGTH_SHORT).show();
            }
        });

    }



}
