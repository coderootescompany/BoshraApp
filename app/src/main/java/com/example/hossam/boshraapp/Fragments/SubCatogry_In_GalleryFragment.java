package com.example.hossam.boshraapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hossam.boshraapp.Adapters.Subcatogry_in_galleryAdapter;
import com.example.hossam.boshraapp.Helpers.ApiClient;
import com.example.hossam.boshraapp.Helpers.ApiInterface;
import com.example.hossam.boshraapp.Helpers.ProgressDialogHelper;
import com.example.hossam.boshraapp.Modules.SubCatogryModel;
import com.example.hossam.boshraapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubCatogry_In_GalleryFragment extends Fragment {


    Subcatogry_in_galleryAdapter subcatogry_in_galleryAdapter;
    RecyclerView recyclerView;
    public SubCatogry_In_GalleryFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static SubCatogry_In_GalleryFragment newInstance(int columnCount) {
        SubCatogry_In_GalleryFragment fragment = new SubCatogry_In_GalleryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.subcatogry_in_galleryfragment, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
             recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
            getData();
        }
        return view;
    }


    private SubCatogryModel subCatogryModel;
    Call<SubCatogryModel> calls;

    private void getData() {
        ProgressDialogHelper.showSimpleProgressDialog(getActivity(),true);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        calls = apiService.getBoshraImages();
        calls.enqueue(new Callback<SubCatogryModel>(){

            @Override
            public void onResponse(Call<SubCatogryModel> call, final Response<SubCatogryModel> response) {
                if (response.body() != null) {
                    ProgressDialogHelper.removeSimpleProgressDialog();
                    subCatogryModel = response.body();
                    subcatogry_in_galleryAdapter = new Subcatogry_in_galleryAdapter(getContext(),subCatogryModel.getGetALlCats());
                    recyclerView.setAdapter(subcatogry_in_galleryAdapter);
                }
            }
            @Override
            public void onFailure(Call<SubCatogryModel> call, Throwable t) {
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
