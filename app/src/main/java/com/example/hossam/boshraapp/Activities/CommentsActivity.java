package com.example.hossam.boshraapp.Activities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hossam.boshraapp.Adapters.CommentsAdapter;
import com.example.hossam.boshraapp.AddExpActivity;
import com.example.hossam.boshraapp.DoctorsInDeptAdapter;
import com.example.hossam.boshraapp.Helpers.ApiClient;
import com.example.hossam.boshraapp.Helpers.ApiInterface;
import com.example.hossam.boshraapp.Helpers.PreferenceHelper;
import com.example.hossam.boshraapp.Helpers.ProgressDialogHelper;
import com.example.hossam.boshraapp.Modules.AllExpsModel;
import com.example.hossam.boshraapp.Modules.CommentsModel;
import com.example.hossam.boshraapp.Modules.DoctorsInDeptModel;
import com.example.hossam.boshraapp.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class CommentsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    List<AllExpsModel.DataBean.ExperiencecommentsBean> comments = new ArrayList<AllExpsModel.DataBean.ExperiencecommentsBean>();

    ImageView imageadd;
    PreferenceHelper helper;
    EditText commenttxt;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_comments);
        helper=new PreferenceHelper(CommentsActivity.this);
      //  comments = (ArrayList<AllExpsModel.DataBean.ExperiencecommentsBean>)getIntent().getSerializableExtra("comments");

        recyclerView =  findViewById(R.id.recycler_view_comments);
        imageadd=findViewById(R.id.commentsend);
        commenttxt=findViewById(R.id.commenttxt);
        final int pid=getIntent().getIntExtra("postid",0);

        getcommentsData(pid);
        imageadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (helper.getUserId() !=null )

                {
                    addPost(commenttxt.getText().toString().trim(), Integer.parseInt(helper.getUserId()), pid);
                    commenttxt.setText("");
                }
                else
                    Toast.makeText(CommentsActivity.this,getResources().getString(R.string.loginfirst),Toast.LENGTH_SHORT).show();
            }
        });
    }


    private CommentsModel commentsModel;
    Call<CommentsModel> calls;
    int myInt;
    private void getcommentsData(int postid ) {
        ProgressDialogHelper.showSimpleProgressDialog(CommentsActivity.this,false);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        calls = apiService.getCommentsDataForPost(postid);
        calls.enqueue(new Callback<CommentsModel>(){

            @Override
            public void onResponse(Call<CommentsModel> call, final Response<CommentsModel> response) {
                ProgressDialogHelper.removeSimpleProgressDialog();
                if (response.body() != null) {
                    commentsModel =response.body();
                    if (commentsModel.getData().size()>0)
                    {
                        CommentsAdapter commentsAdapter=new CommentsAdapter(CommentsActivity.this,commentsModel.getData().get(0).getExperiencecomments());
                        recyclerView.setAdapter(commentsAdapter);
                    }
                    else
                        Toast.makeText(CommentsActivity.this, getString(R.string.nodoctorsfound),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CommentsModel> call, Throwable t) {
                ProgressDialogHelper.removeSimpleProgressDialog();
                Toast.makeText(  CommentsActivity.this, getString(R.string.connection_error),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MultipartBody.FORM, descriptionString);
    }

    private void addPost(String comment, int userid, final int postid) {

      //  ProgressDialogHelper.showSimpleProgressDialog(CommentsActivity.this, false);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseBody> call = apiService.ADDComment(
                createPartFromString(comment),
                createPartFromString(String.valueOf(userid)),
                createPartFromString(String.valueOf(postid)));

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
               // ProgressDialogHelper.removeSimpleProgressDialog();
                if (response.body() != null) {

                    if (response.isSuccessful())
                        getcommentsData(postid);
                }

                else {
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
              //  ProgressDialogHelper.removeSimpleProgressDialog();
                Log.d("fail",call.toString());
                // Toast.makeText(getActivity(), getString(R.string.something_wrong), Toast.LENGTH_SHORT).show();
            }
        });

    }

}
