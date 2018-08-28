package com.example.hossam.boshraapp.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hossam.boshraapp.Activities.DetailImageActivity;
import com.example.hossam.boshraapp.Adapters.GalleryAdapter;
import com.example.hossam.boshraapp.Helpers.ApiClient;
import com.example.hossam.boshraapp.Helpers.ApiInterface;
import com.example.hossam.boshraapp.Helpers.ProgressDialogHelper;
import com.example.hossam.boshraapp.Helpers.RecyclerItemClickListener;
import com.example.hossam.boshraapp.Modules.ImageModel;
import com.example.hossam.boshraapp.Modules.NewFarhaModel;
import com.example.hossam.boshraapp.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.hossam.boshraapp.Helpers.ProgressDialogHelper.showSimpleProgressDialog;


public class ImagesGalleryFragment extends Fragment {

    GalleryAdapter mAdapter;
    RecyclerView mRecyclerView;
    ProgressDialogHelper progressDialogHelper=new ProgressDialogHelper();
    ImageView imageView;
    ArrayList<NewFarhaModel.PhotosBean> data = new ArrayList<>();
     public  static int pos=0;
    ArrayList<ImageModel> images = new ArrayList<>();
    public ImagesGalleryFragment() {
    }


    // TODO: Rename and change types and number of parameters
    public static ImagesGalleryFragment newInstance(String param1, String param2) {
        ImagesGalleryFragment fragment = new ImagesGalleryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_images, container, false);
        imageView=view.findViewById(R.id.mainimage);
        mRecyclerView =  view.findViewById(R.id.list);

        getDepartmentsData();

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        pos=position;
                        Glide.with(getContext()).load("http://sfc-oman.com/library/photos/"+data.get(position).getPhoto())
                                .thumbnail(0.5f)
                                .override(200,200)
                                .crossFade(200)
                                .animate(R.anim.animyforimg)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(imageView);
                    }
                }));


        if (data.size()>0)
            Glide.with(getContext()).load(newFarhaModel.getPhotos().get(0).getPhoto())
                    .thumbnail(0.5f)
                    .override(200,200)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DetailImageActivity.class);
                intent.putParcelableArrayListExtra("data", images);
                intent.putExtra("pos",pos);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }

    private NewFarhaModel newFarhaModel;
    Call<NewFarhaModel> calls;
    private void
    getDepartmentsData() {

        ProgressDialogHelper.showSimpleProgressDialog(getActivity(),true);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        calls = apiService.getNewhappyData();
        calls.enqueue(new Callback<NewFarhaModel>(){
            @Override
            public void onResponse(Call<NewFarhaModel> call, final Response<NewFarhaModel> response) {
                if (response.body() != null) {
                    ProgressDialogHelper.removeSimpleProgressDialog();
                    newFarhaModel = response.body();
                    data=newFarhaModel.getPhotos();
                    mAdapter = new GalleryAdapter(getActivity(),newFarhaModel.getPhotos());
                    mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
                    mRecyclerView.setHasFixedSize(true);
                    mRecyclerView.setAdapter(mAdapter);

                        Glide.with(getContext()).load("http://sfc-oman.com/library/photos/"+newFarhaModel.getPhotos().get(0).getPhoto())
                                .thumbnail(0.5f)
                                .crossFade()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(imageView);

                    for (int i = 0; i < data.size(); i++) {
                        ImageModel imageModel = new ImageModel();
                        imageModel.setName("Image " + i);
                        imageModel.setUrl("http://sfc-oman.com/library/photos/"+data.get(i).getPhoto());
                        images.add(imageModel);
                    }

                }
            }
            @Override
            public void onFailure(Call<NewFarhaModel> call, Throwable t) {
                ProgressDialogHelper.removeSimpleProgressDialog();
                ProgressDialogHelper.removeSimpleProgressDialog();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(  getActivity(), getString(R.string.connection_error),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
