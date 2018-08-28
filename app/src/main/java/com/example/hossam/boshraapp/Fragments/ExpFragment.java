package com.example.hossam.boshraapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hossam.boshraapp.Activities.MainActivity;
import com.example.hossam.boshraapp.Adapters.ExpRecyclerViewAdapter;
import com.example.hossam.boshraapp.Adapters.SubjectsAdapter;
import com.example.hossam.boshraapp.AddExpActivity;
import com.example.hossam.boshraapp.Helpers.ApiClient;
import com.example.hossam.boshraapp.Helpers.ApiInterface;
import com.example.hossam.boshraapp.Helpers.PreferenceHelper;
import com.example.hossam.boshraapp.Helpers.ProgressDialogHelper;
import com.example.hossam.boshraapp.Modules.AllExpsModel;
import com.example.hossam.boshraapp.Modules.AllPosts;
import com.example.hossam.boshraapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ExpFragment extends Fragment {


    public ExpFragment() {
    }


    @SuppressWarnings("unused")
    public static ExpFragment newInstance(int columnCount) {
        ExpFragment fragment = new ExpFragment();
        return fragment;
    }

    ExpRecyclerViewAdapter  expRecyclerViewAdapter;
    RecyclerView recyclerView;
    PreferenceHelper helper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.exp_fragment, container, false);

        helper=new PreferenceHelper(getActivity());
        view.findViewById(R.id.addexper).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (helper.getUserId() !=null )

                {
                    startActivity(new Intent(getActivity(), AddExpActivity.class));
                }

                else
                    Toast.makeText(getActivity(),getResources().getString(R.string.loginfirst),Toast.LENGTH_SHORT).show();
            }
        });

       recyclerView =  view.findViewById(R.id.list);
       if (helper.getUserId()!=null)
        getExpData(Integer.parseInt(helper.getUserId()));

       else
           getExpData(0);

        return view;
    }


    private AllExpsModel allExpsModel;
    Call<AllExpsModel> calls;

    private void getExpData(int userid) {

        ProgressDialogHelper.showSimpleProgressDialog(getActivity(),false);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        calls = apiService.getExpsData(userid);
        calls.enqueue(new Callback<AllExpsModel>(){

            @Override
            public void onResponse(Call<AllExpsModel> call, final Response<AllExpsModel> response) {
                if (response.body() != null) {
                   ProgressDialogHelper.removeSimpleProgressDialog();
                    allExpsModel = response.body();
                    expRecyclerViewAdapter=new ExpRecyclerViewAdapter(getActivity(),allExpsModel.getData());
                    recyclerView.setAdapter(expRecyclerViewAdapter);
                }
            }

            @Override
            public void onFailure(Call<AllExpsModel> call, Throwable t) {
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
