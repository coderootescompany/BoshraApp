package com.example.hossam.boshraapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.example.hossam.boshraapp.Adapters.GalleryAdapterForSubCat;
import com.example.hossam.boshraapp.Adapters.Subcatogry_in_galleryAdapter;
import com.example.hossam.boshraapp.Helpers.ApiClient;
import com.example.hossam.boshraapp.Helpers.ApiInterface;
import com.example.hossam.boshraapp.Helpers.ProgressDialogHelper;
import com.example.hossam.boshraapp.Helpers.RecyclerItemClickListener;
import com.example.hossam.boshraapp.Modules.ImageModel;
import com.example.hossam.boshraapp.Modules.NewFarhaModel;
import com.example.hossam.boshraapp.Modules.SubCatogryModel;
import com.example.hossam.boshraapp.R;
import com.google.android.gms.plus.PlusOneButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ImagesGalleryFragmentForSubCatogry extends Fragment {


    GalleryAdapterForSubCat subcatogry_in_galleryAdapter;
    RecyclerView mRecyclerView;
    ProgressDialogHelper progressDialogHelper=new ProgressDialogHelper();
    ImageView imageView;
    public  static int pos=0;
    ArrayList<ImageModel> images = new ArrayList<>();
    List<SubCatogryModel.GetALlCatsBean.GalariesBean> data = new ArrayList<>();
    public ImagesGalleryFragmentForSubCatogry() {
        // Required empty public constructor
    }


    public static ImagesGalleryFragmentForSubCatogry newInstance(String param1, String param2) {
        ImagesGalleryFragmentForSubCatogry fragment = new ImagesGalleryFragmentForSubCatogry();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        int myInt = 0;
        View view = inflater.inflate(R.layout.fragment_images_gallery_fragment_for_sub_catogry, container, false);
        imageView=view.findViewById(R.id.mainimage);
        mRecyclerView =  view.findViewById(R.id.recyclelist);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
             myInt = bundle.getInt("index", 0);
        }
        getData(myInt);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        pos=position;
                        Glide.with(getContext()).load("http://sfc-oman.com/library/photos/"+data.get(position).getPhoto())
                                .thumbnail(0.5f)
                                .override(200,200)
                                .crossFade()
                                .animate(R.anim.animyforimg)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(imageView);
                    }
                }));

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

    private SubCatogryModel subCatogryModel;
    Call<SubCatogryModel> calls;

    private void getData(final int index) {
        ProgressDialogHelper.showSimpleProgressDialog(getActivity(),true);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        calls = apiService.getBoshraImages();
        calls.enqueue(new Callback<SubCatogryModel>(){

            @Override
            public void onResponse(Call<SubCatogryModel> call, final Response<SubCatogryModel> response) {
                if (response.body() != null) {

                    ProgressDialogHelper.removeSimpleProgressDialog();
                    subCatogryModel = response.body();
                    data=subCatogryModel.getGetALlCats().get(index).getGalaries();
                    subcatogry_in_galleryAdapter = new GalleryAdapterForSubCat(getActivity(),subCatogryModel.getGetALlCats().get(index).getGalaries());
                    mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
                    mRecyclerView.setHasFixedSize(true);
                    mRecyclerView.setAdapter(subcatogry_in_galleryAdapter);


                    Glide.with(getContext()).load("http://sfc-oman.com/library/photos/"+data.get(0).getPhoto())
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
            public void onFailure(Call<SubCatogryModel> call, Throwable t) {
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

}
