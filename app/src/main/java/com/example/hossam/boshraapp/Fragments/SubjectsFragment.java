package com.example.hossam.boshraapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.hossam.boshraapp.Adapters.SubjectsAdapter;
import com.example.hossam.boshraapp.Helpers.ApiClient;
import com.example.hossam.boshraapp.Helpers.ApiInterface;
import com.example.hossam.boshraapp.Helpers.ProgressDialogHelper;
import com.example.hossam.boshraapp.Modules.AllPosts;
import com.example.hossam.boshraapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubjectsFragment extends Fragment {

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 444;
    private int MY_PERMISSIONS_REQUEST_READ_CONTACTS=555;
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private SubjectsAdapter subjectsAdapter;
    RecyclerView postsRecycle;
    ProgressBar progressBar;
    public SubjectsFragment() {
    }

    public static SubjectsFragment newInstance(int columnCount) {
        SubjectsFragment fragment = new SubjectsFragment();
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
        View view = inflater.inflate(R.layout.posts_fragment, container, false);
        progressBar=view.findViewById(R.id.progressBar);

            postsRecycle =  view.findViewById(R.id.postslist);
            postsRecycle.setLayoutManager(new GridLayoutManager(getContext(), mColumnCount));
        getDepartmentsData();

        return view;
    }


    private AllPosts allPosts;
    Call<AllPosts> calls;

    private void getDepartmentsData() {
      //  progressBar.setVisibility(View.VISIBLE);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        calls = apiService.getPostsData();
        calls.enqueue(new Callback<AllPosts>(){

            @Override
            public void onResponse(Call<AllPosts> call, final Response<AllPosts> response) {
                if (response.body() != null) {
                   // progressBar.setVisibility(View.GONE);
                    allPosts = response.body();
                    subjectsAdapter = new SubjectsAdapter(getActivity(),allPosts.getData());
                    postsRecycle.setAdapter(subjectsAdapter);
                }
            }
            @Override
            public void onFailure(Call<AllPosts> call, Throwable t) {
                //progressBar.setVisibility(View.GONE);
                ProgressDialogHelper.removeSimpleProgressDialog();
                if (getActivity()!=null)
                { getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(  getActivity(), getString(R.string.connection_error),Toast.LENGTH_SHORT).show();
                    }
                });}
            }
        });

    }

}
