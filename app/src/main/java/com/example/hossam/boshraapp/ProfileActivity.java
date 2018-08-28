package com.example.hossam.boshraapp;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hossam.boshraapp.Activities.CommentsActivity;
import com.example.hossam.boshraapp.Fragments.MymyreservationRecyclerViewAdapter;
import com.example.hossam.boshraapp.Helpers.ApiClient;
import com.example.hossam.boshraapp.Helpers.ApiInterface;
import com.example.hossam.boshraapp.Helpers.FileUtils;
import com.example.hossam.boshraapp.Helpers.PreferenceHelper;
import com.example.hossam.boshraapp.Helpers.ProgressDialogHelper;
import com.example.hossam.boshraapp.Helpers.UploadHelper;
import com.example.hossam.boshraapp.Modules.MyReservationModel;
import com.example.hossam.boshraapp.Modules.ProfileModel;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ProfileActivity extends AppCompatActivity {

    ImageView upload;
    EditText name,mobile;
     Uri uri;
    MultipartBody.Part part;

    TextView save;
    PreferenceHelper helper;
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        upload=findViewById(R.id.imgupload);
        name=findViewById(R.id.luname);
        mobile=findViewById(R.id.lpass);
        save =findViewById(R.id.save);

        helper=new PreferenceHelper(ProfileActivity.this);

        getProfileData(Integer.parseInt(helper.getUserId()));

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int permissionCheck = ContextCompat.checkSelfPermission(ProfileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                    permission();
                } else {

                    CropImage.activity()
                            .setGuidelines(CropImageView.Guidelines.ON)
                            .start(ProfileActivity.this);
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update(Integer.parseInt(helper.getUserId()));
            }
        });

    }


    private void update( int userid) {

        ProgressDialogHelper.showSimpleProgressDialog(ProfileActivity.this, false);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseBody> call = apiService.EditProfile(
                String.valueOf(userid),
                createPartFromString(name.getText().toString()),
                createPartFromString(mobile.getText().toString()),
                createPartFromString(mobile.getText().toString()),
                part);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                ProgressDialogHelper.removeSimpleProgressDialog();
                if (response.body() != null) {

                    if (response.isSuccessful()) {

                        Toast.makeText(ProfileActivity.this, getString(R.string.editsucess), Toast.LENGTH_SHORT).show();
                        getProfileData(Integer.parseInt(helper.getUserId()));
                    }

                }

                else {
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ProgressDialogHelper.removeSimpleProgressDialog();
                Log.d("fail",call.toString());
                // Toast.makeText(getActivity(), getString(R.string.something_wrong), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MultipartBody.FORM, descriptionString);
    }


    private ProfileModel profileModel;
    Call<ProfileModel> calls;

    private void getProfileData(int userid ) {

        ProgressDialogHelper.showSimpleProgressDialog(ProfileActivity.this,false);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        calls = apiService.getUserProfile(userid);
        calls.enqueue(new Callback<ProfileModel>(){

            @Override
            public void onResponse(Call<ProfileModel> call, final Response<ProfileModel> response) {
                ProgressDialogHelper.removeSimpleProgressDialog();
                if (response.body() != null) {

                    profileModel =response.body();
                    name.setText(profileModel.getUser().getUsername());
                    mobile.setText(profileModel.getUser().getMobile());

                    if (profileModel.getUser().getPhoto()!=null) {
                        if (!profileModel.getUser().getPhoto().toString().matches("")) {
                            Glide.with(ProfileActivity.this).load("http://sfc-oman.com/library/sfc/" +
                                    profileModel.getUser().getPhoto()).into(upload);

                            helper.setphoto(String.valueOf(response.body().getUser().getPhoto()));
                        }
                    }
                }
               // else
                    //Toast.makeText(ProfileActivity.this, getString(R.string.nodoctorsfound),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {
                ProgressDialogHelper.removeSimpleProgressDialog();
                Toast.makeText(  ProfileActivity.this, getString(R.string.connection_error),Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                uri = result.getUri();
                upload.setImageURI(uri);////set the image after taking it
                part=prepareFilePart("photo",uri);

           //  Log.d("uri",UploadHelper.getFileDataFromDrawable(ProfileActivity.this, uri)) ;

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    public void permission() {
        final Dialog dialog = new Dialog(ProfileActivity.this, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_permission_cam);
        dialog.show();
        Button cancel_log, open;
        ImageView close_dialog;
        open =  dialog.findViewById(R.id.open);

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                final Intent i = new Intent();
                i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                i.addCategory(Intent.CATEGORY_DEFAULT);
                i.setData(Uri.parse("package:" + ProfileActivity.this.getPackageName()));
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                ProfileActivity.this.startActivity(i);
            }
        });

        cancel_log = (Button) dialog.findViewById(R.id.cancel_log);
        cancel_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        close_dialog = (ImageView) dialog.findViewById(R.id.close_dialog);
        close_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }

    @NonNull
    private MultipartBody.Part prepareFilePart(String name, Uri fileUri) {

        File file = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {

            file = FileUtils.getFile(ProfileActivity.this, fileUri);
        }

        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse("image"),
                        file
                );

        return MultipartBody.Part.createFormData(name, file.getName(), requestFile);
    }


}
