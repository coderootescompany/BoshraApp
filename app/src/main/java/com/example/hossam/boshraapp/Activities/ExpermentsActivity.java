package com.example.hossam.boshraapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.hossam.boshraapp.Adapters.ExpRecyclerViewAdapter;
import com.example.hossam.boshraapp.AddExpActivity;
import com.example.hossam.boshraapp.Helpers.ApiClient;
import com.example.hossam.boshraapp.Helpers.ApiClient2;
import com.example.hossam.boshraapp.Helpers.ApiInterface;
import com.example.hossam.boshraapp.Helpers.PreferenceHelper;
import com.example.hossam.boshraapp.Helpers.ProgressDialogHelper;
import com.example.hossam.boshraapp.Modules.AllExpsModel;
import com.example.hossam.boshraapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ExpermentsActivity extends AppCompatActivity {

    ExpRecyclerViewAdapter expRecyclerViewAdapter;
    RecyclerView recyclerView;
    PreferenceHelper helper;
    int index=0;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_experments);

        index = getIntent().getIntExtra("pid",0);
        Log.d("index",index+"");

        String data= getIntent().getDataString();

        if (data!=null) {

            Uri uri = Uri.parse(data);
            String qq =  uri.getQuery();
            String fg= uri.getQueryParameter("pid");

            //index = Integer.parseInt(uri.getQueryParameter("pid"));
        }

        helper=new PreferenceHelper(ExpermentsActivity.this);
        findViewById(R.id.addexper).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(ExpermentsActivity.this, AddExpActivity.class));
                finish();
            }
        });

        recyclerView =  findViewById(R.id.list);
        if (helper.getUserId()!=null)
            getExpData(Integer.parseInt(helper.getUserId()));

        else
            getExpData(0);
    }

    private AllExpsModel allExpsModel;
    Call<AllExpsModel> calls;
    List<AllExpsModel.DataBean> data=new ArrayList<>();
    private void getExpData(int userid) {

        ProgressDialogHelper.showSimpleProgressDialog(ExpermentsActivity.this,false);
        ApiInterface apiService = ApiClient2.getClient().create(ApiInterface.class);
        calls = apiService.getExpsData(userid);
        calls.enqueue(new Callback<AllExpsModel>(){

            @Override
            public void onResponse(Call<AllExpsModel> call, final Response<AllExpsModel> response) {
                if (response.body() != null) {

                    ProgressDialogHelper.removeSimpleProgressDialog();
                    allExpsModel = response.body();

                    if (helper.getUserId()!=null) //////////// if user register
                    {
                        if (helper.getUserId().matches("1"))  ////////if admin
                        { for (int i = 0; i < allExpsModel.getData().size(); i++) {
                            if (allExpsModel.getData().get(i).getAccept().matches("0"))
                                data.add(allExpsModel.getData().get(i));
                        }
                        }
                        else
                            for (int i = 0; i < allExpsModel.getData().size(); i++) {
                                if (allExpsModel.getData().get(i).getAccept().matches("1"))
                                    data.add(allExpsModel.getData().get(i));
                            }
                    }
                    else  ////if not print all posts that accepted
                    {
                        for (int i = 0; i < allExpsModel.getData().size(); i++) {
                            if (allExpsModel.getData().get(i).getAccept().matches("1"))
                                data.add(allExpsModel.getData().get(i));
                        }
                    }

                    expRecyclerViewAdapter=new ExpRecyclerViewAdapter(ExpermentsActivity.this,data);
                    recyclerView.setAdapter(expRecyclerViewAdapter);



                    int position = 0;
                    for (int j=0;j<allExpsModel.getData().size();j++)
                    {
                        if (allExpsModel.getData().get(j).getId()==index)
                        {
                            position = j;
                            break;
                        }
                    }
                   recyclerView.scrollToPosition(position);
                }
            }


            @Override
            public void onFailure(Call<AllExpsModel> call, Throwable t) {
                ProgressDialogHelper.removeSimpleProgressDialog();
         Toast.makeText(ExpermentsActivity.this, getString(R.string.connection_error),Toast.LENGTH_SHORT).show();
            }
        });

    }
}
