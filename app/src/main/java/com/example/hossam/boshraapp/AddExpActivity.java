package com.example.hossam.boshraapp;

import android.Manifest;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hossam.boshraapp.Activities.ExpermentsActivity;
import com.example.hossam.boshraapp.Adapters.GalleryAdapterForexper;
import com.example.hossam.boshraapp.Adapters.ImagesAdapterForExp;
import com.example.hossam.boshraapp.Helpers.ApiClient;
import com.example.hossam.boshraapp.Helpers.ApiInterface;
import com.example.hossam.boshraapp.Helpers.FileUtils;
import com.example.hossam.boshraapp.Helpers.PreferenceHelper;
import com.example.hossam.boshraapp.Helpers.ProgressDialogHelper;
import com.example.hossam.boshraapp.Modules.AddExpResponseModel;
import com.example.hossam.boshraapp.Modules.ImagesModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import id.zelory.compressor.Compressor;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddExpActivity extends AppCompatActivity {

    private Button btn,sendpost;
    int PICK_IMAGE_MULTIPLE = 1;
    String imageEncoded;
    List<String> imagesEncodedList;
    private GridView gvGallery;
    RecyclerView recyclerViewGallery;
    EditText posttext;
    ImageView userimage;
    private GalleryAdapterForexper galleryAdapter;
    private ImagesAdapterForExp galleryAdapter2;
    ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
    List<MultipartBody.Part> parts = new ArrayList<>();
     PreferenceHelper helper;
    private ArrayList<ImagesModel> imagesModels;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exp);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      helper=new PreferenceHelper(AddExpActivity.this);
        userimage = findViewById(R.id.userimage);


      if (helper.getphoto()!=null)
      {
          if (!helper.getphoto().matches(""))
              Glide.with(AddExpActivity.this).load("http://sfc-oman.com/library/sfc/" +
                      helper.getphoto()).into(userimage);
      }

        btn = findViewById(R.id.btn);
        posttext = findViewById(R.id.post);
        sendpost = findViewById(R.id.addpost);
        gvGallery = findViewById(R.id.gv);

        recyclerViewGallery = findViewById(R.id.images_recycler);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int permissionCheck = ContextCompat.checkSelfPermission(AddExpActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                    permission();
                } else {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_IMAGE_MULTIPLE);
                }

            }
        });

        sendpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            addPost(posttext.getText().toString(),helper.getUserId());

            }
        });

    }
    public void permission() {
        final Dialog dialog = new Dialog(AddExpActivity.this, android.R.style.Theme_Translucent_NoTitleBar);
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
                i.setData(Uri.parse("package:" + AddExpActivity.this.getPackageName()));
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                AddExpActivity.this.startActivity(i);
            }
        });

        cancel_log = dialog.findViewById(R.id.cancel_log);
        cancel_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        close_dialog = dialog.findViewById(R.id.close_dialog);
        close_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }

    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MultipartBody.FORM, descriptionString);
    }

    private void addPost(String post,String userid) {


        ProgressDialogHelper.showSimpleProgressDialog(AddExpActivity.this, false);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
           Call<ResponseBody> call = apiService.ADDExperience(parts,
                createPartFromString(post),
                createPartFromString(userid));

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                ProgressDialogHelper.removeSimpleProgressDialog();
                if (response.body() != null) {

                    try {
                        Log.i("response" , response.body().string()) ;


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (response.isSuccessful())
                      {
                          Log.d("success","ss");
                          Toast.makeText(AddExpActivity.this,getResources().getString(R.string.addsuccess),Toast.LENGTH_LONG).show();
                          AddExpActivity.this.finish();
                          startActivity(new Intent(AddExpActivity.this, ExpermentsActivity.class));

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
    private MultipartBody.Part prepareFilePart(String name, Uri fileUri) {
        File file = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            file = FileUtils.getFile(AddExpActivity.this, fileUri);
        }

        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse("image/*"),
                        file
                );
        return MultipartBody.Part.createFormData(name, file.getName(), requestFile);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            // When an Image is picked
            if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK
                    && null != data) {

                mArrayUri.clear();
                // Get the Image from data
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                imagesEncodedList = new ArrayList<String>();
                if(data.getData()!=null){
                    Uri mImageUri=data.getData();
                    // Get the cursor
                    Cursor cursor = getContentResolver().query(mImageUri,
                            filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imageEncoded  = cursor.getString(columnIndex);
                    cursor.close();
                    ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                    mArrayUri.add(mImageUri);
                    galleryAdapter2 = new ImagesAdapterForExp(getApplicationContext(),mArrayUri);
                    // gvGallery.setAdapter(galleryAdapter);
                    recyclerViewGallery.setAdapter(galleryAdapter2);
                    gvGallery.setVerticalSpacing(gvGallery.getHorizontalSpacing());
                    ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) gvGallery
                            .getLayoutParams();
                    mlp.setMargins(0, gvGallery.getHorizontalSpacing(), 0, 0);
                    if (mArrayUri.size() > 0) {
                        for (int i = 0; i < mArrayUri.size(); i++) {
                            parts.add(prepareFilePart("image" + "[" + i + "]", mArrayUri.get(i)));
                            Log.i("[5]", "[" + i + "]");
                        }

                    }
                } else {
                    if (data.getClipData() != null) {

                        ClipData mClipData = data.getClipData();

                        for (int i = 0; i < mClipData.getItemCount(); i++) {

                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();
                            mArrayUri.add(uri);
                            // Get the cursor
                            Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
                            // Move to first row
                            cursor.moveToFirst();

                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            imageEncoded  = cursor.getString(columnIndex);
                            imagesEncodedList.add(imageEncoded);
                            cursor.close();

                            galleryAdapter2 = new ImagesAdapterForExp(getApplicationContext(),mArrayUri);
                            // gvGallery.setAdapter(galleryAdapter);

                            recyclerViewGallery.setAdapter(galleryAdapter2);
                            // galleryAdapter = new GalleryAdapterForexper(getApplicationContext(),mArrayUri);
                            // gvGallery.setAdapter(galleryAdapter);
                            //  gvGallery.setVerticalSpacing(gvGallery.getHorizontalSpacing());

                            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) gvGallery
                                    .getLayoutParams();
                            mlp.setMargins(0, gvGallery.getHorizontalSpacing(), 0, 0);

                        }
                        Log.v("LOG_TAG", "Selected Images" + mArrayUri.size());
                    }
                    if (mArrayUri.size() > 0) {
                        for (int i = 0; i < mArrayUri.size(); i++) {
                            parts.add(prepareFilePart("image" + "[" + i + "]", mArrayUri.get(i)));
                        }
                    }
                }
            } else {
                Toast.makeText(this, getResources().getString(R.string.notselect),
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, getResources().getString(R.string.error), Toast.LENGTH_LONG)
                    .show();
            Log.v("exc",e.getMessage());

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

}
