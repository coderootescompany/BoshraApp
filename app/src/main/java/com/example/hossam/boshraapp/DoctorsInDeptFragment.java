package com.example.hossam.boshraapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hossam.boshraapp.Adapters.SubjectsAdapter;
import com.example.hossam.boshraapp.Helpers.ApiClient;
import com.example.hossam.boshraapp.Helpers.ApiInterface;
import com.example.hossam.boshraapp.Helpers.ProgressDialogHelper;
import com.example.hossam.boshraapp.Modules.AllPosts;
import com.example.hossam.boshraapp.Modules.DoctorsInDeptModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DoctorsInDeptFragment extends Fragment {

    RecyclerView recyclerView;
    DoctorsInDeptAdapter doctorsInDeptAdapter;

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;

    public DoctorsInDeptFragment() {
    }


    public static DoctorsInDeptFragment newInstance(int columnCount) {
        DoctorsInDeptFragment fragment = new DoctorsInDeptFragment();
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
        View view = inflater.inflate(R.layout.doctors_in_dept_list, container, false);

        recyclerView=view.findViewById(R.id.list);
        getDepartmentsData();



        // Set the adapter
        if (view instanceof RecyclerView) {

            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

        }
        return view;
    }


    private DoctorsInDeptModel doctorsInDeptModel;
    Call<DoctorsInDeptModel> calls;
    int myInt;
    private void getDepartmentsData() {
        ProgressDialogHelper.showSimpleProgressDialog(getActivity(),false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
             myInt = bundle.getInt("d_id", 0);
        }

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        calls = apiService.getDoctorsinDept(myInt);
        calls.enqueue(new Callback<DoctorsInDeptModel>(){

            @Override
            public void onResponse(Call<DoctorsInDeptModel> call, final Response<DoctorsInDeptModel> response) {
                ProgressDialogHelper.removeSimpleProgressDialog();
                if (response.body() != null) {
                    if (response.body().getData().size()>0)
                    {ProgressDialogHelper.removeSimpleProgressDialog();
                    doctorsInDeptModel = response.body();
                    doctorsInDeptAdapter=new DoctorsInDeptAdapter(getActivity(),doctorsInDeptModel.getData());
                    recyclerView.setAdapter(doctorsInDeptAdapter);}
                    else
                        Toast.makeText(  getActivity(), getString(R.string.nodoctorsfound),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DoctorsInDeptModel> call, Throwable t) {
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
