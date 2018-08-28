package com.example.hossam.boshraapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.hossam.boshraapp.Helpers.ApiClient;
import com.example.hossam.boshraapp.Helpers.ApiInterface;
import com.example.hossam.boshraapp.Helpers.PreferenceHelper;
import com.example.hossam.boshraapp.Helpers.ProgressDialogHelper;
import com.example.hossam.boshraapp.Modules.MyReservationModel;
import com.example.hossam.boshraapp.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyreservationFragment extends Fragment {

    RecyclerView recyclerView;
    MymyreservationRecyclerViewAdapter mymyreservationRecyclerViewAdapter;
    PreferenceHelper helper;
LinearLayout linearLayoutnotfound;
    public MyreservationFragment() {

    }

    public static MyreservationFragment newInstance(int columnCount) {
        MyreservationFragment fragment = new MyreservationFragment();
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
        View view = inflater.inflate(R.layout.fragment_myreservation_list, container, false);
        recyclerView=view.findViewById(R.id.list);
        linearLayoutnotfound =view.findViewById(R.id.not_found);
        helper=new PreferenceHelper(getActivity());
        if (helper.getUserId() !=null )

        {
            getMyReservationData(Integer.parseInt(helper.getUserId()));
        }

        else
            Toast.makeText(getActivity(),getResources().getString(R.string.loginfirst),Toast.LENGTH_SHORT).show();


        return view;
    }


    private MyReservationModel myReservationModel;
    Call<MyReservationModel> calls;

    private void getMyReservationData(int userid ) {

        ProgressDialogHelper.showSimpleProgressDialog(getActivity(),false);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        calls = apiService.getMyReservation(userid);
        calls.enqueue(new Callback<MyReservationModel>(){

            @Override
            public void onResponse(Call<MyReservationModel> call, final Response<MyReservationModel> response) {
                ProgressDialogHelper.removeSimpleProgressDialog();
                if (response.body() != null) {

                    myReservationModel =response.body();
                    if (myReservationModel.getData().size()>0) {
                        mymyreservationRecyclerViewAdapter = new MymyreservationRecyclerViewAdapter(getActivity(), myReservationModel.getData());
                        recyclerView.setAdapter(mymyreservationRecyclerViewAdapter);
                    }
                    else
                    {
                        recyclerView.setVisibility(View.GONE);
                        linearLayoutnotfound.setVisibility(View.VISIBLE);
                    }

                }
                    else
                        Toast.makeText(getActivity(), getString(R.string.error),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<MyReservationModel> call, Throwable t) {
                ProgressDialogHelper.removeSimpleProgressDialog();
                Toast.makeText(  getActivity(), getString(R.string.connection_error),Toast.LENGTH_SHORT).show();
            }
        });

    }


}
