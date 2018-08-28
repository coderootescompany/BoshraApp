package com.example.hossam.boshraapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.hossam.boshraapp.Helpers.ApiClient;
import com.example.hossam.boshraapp.Helpers.ApiClient2;
import com.example.hossam.boshraapp.Helpers.ApiInterface;
import com.example.hossam.boshraapp.Helpers.PreferenceHelper;
import com.example.hossam.boshraapp.Helpers.ProgressDialogHelper;
import com.example.hossam.boshraapp.Modules.LoginModel;
import com.example.hossam.boshraapp.Modules.RegisterModel;
import com.example.hossam.boshraapp.R;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterFragment extends Fragment {

    Fragment fragment;
    FragmentManager fr;
    FragmentTransaction ft;

    public RegisterFragment() {
        // Required empty public constructor
    }

    PreferenceHelper helper;
    EditText reg_uname,reg_mobile,log_uname,log_mobile;
    TextView register,login;
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_register, container, false);

        helper=new PreferenceHelper(getActivity());
        reg_uname=view.findViewById(R.id.runame);
        reg_mobile=view.findViewById(R.id.rpass);
        log_uname=view.findViewById(R.id.luname);
        log_mobile=view.findViewById(R.id.lpass);
        register=view.findViewById(R.id.register);
        login=view.findViewById(R.id.loginn);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        return view;
    }

    private void login() {
        if (!log_uname.getText().toString().trim().equals("") || !log_mobile.getText().toString().trim().equals(""))
        {

            ProgressDialogHelper.showSimpleProgressDialog(getActivity(), false);
            ApiInterface apiService =
                    ApiClient2.getClient().create(ApiInterface.class);
            Call<LoginModel> call = apiService.UserLogin(
                    createPartFromString(log_uname.getText().toString().trim()),
                    createPartFromString(log_mobile.getText().toString().trim()));

            call.enqueue(new Callback<LoginModel>() {
                @Override
                public void onResponse(Call<LoginModel> call, final Response<LoginModel> response) {
                    ProgressDialogHelper.removeSimpleProgressDialog();
                    if (response.body() != null) {

                        Log.d("response" , response.body().toString()) ;

                        if (response.body().isSuccess())
                        {
                            Log.d("success","ss");
                            Toast.makeText(getActivity(),getResources().getString(R.string.loginsucess),Toast.LENGTH_LONG).show();
                            helper.setUserId(String.valueOf(response.body().getData().getUserid()));
                            helper.setToken(String.valueOf(response.body().getData().getToken()));
                            helper.setphoto(String.valueOf(response.body().getData().getPhoto()));
                            fragment = new MainFragment();
                            fr = getActivity().getSupportFragmentManager();
                            ft = fr.beginTransaction();
                            ft.replace(R.id.main_frame , fragment);
                            ft.commit();
                        }

                        else
                            Toast.makeText(getActivity(),getResources().getString(R.string.userorpasserror),Toast.LENGTH_LONG).show();
                    }

                    else
                    {
                        Toast.makeText(getActivity(), getString(R.string.something_wrong), Toast.LENGTH_SHORT).show();

                    }
                }





                @Override
                public void onFailure(Call<LoginModel> call, Throwable t) {
                    ProgressDialogHelper.removeSimpleProgressDialog();
                    Log.d("fail",call.toString());
                    Toast.makeText(getActivity(), getString(R.string.connection_error), Toast.LENGTH_SHORT).show();

                }
            });




        }
        else
        {

            Toast.makeText(getActivity(), R.string.complete, Toast.LENGTH_SHORT).show();
        }


    }
    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MultipartBody.FORM, descriptionString);
    }

    private void register() {
        if (!reg_uname.getText().toString().trim().equals("") || !reg_mobile.getText().toString().trim().equals(""))
        {

            ProgressDialogHelper.showSimpleProgressDialog(getActivity(), false);
            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);
            Call<RegisterModel> call = apiService.UserRegister(
                    createPartFromString(reg_uname.getText().toString().trim()),
                    createPartFromString(reg_mobile.getText().toString().trim()),
                    createPartFromString(reg_mobile.getText().toString().trim()),
                    createPartFromString("1"),
                    createPartFromString("1"),
                    createPartFromString("2")
                    );

            call.enqueue(new Callback<RegisterModel>() {
                @Override
                public void onResponse(Call<RegisterModel> call, final Response<RegisterModel> response) {
                    ProgressDialogHelper.removeSimpleProgressDialog();
                    if (response.body() != null) {

                        Log.d("response" , response.body().toString()) ;

                        if (response.body().isSuccess())
                        {
                            Log.d("success","ss");

                            Toast.makeText(getActivity(),getResources().getString(R.string.registersuccess),Toast.LENGTH_LONG).show();
                            helper.setUserId(String.valueOf(response.body().getData().getId()));
                            helper.setToken(String.valueOf(response.body().getData().getToken()));

                            if (response.body().getData().getClient_photo()!=null)
                                helper.setphoto(String.valueOf(response.body().getData().getClient_photo()));

                            fragment = new MainFragment();
                            fr = getActivity().getSupportFragmentManager();
                            ft = fr.beginTransaction();
                            ft.replace(R.id.main_frame , fragment);
                            ft.commit();

                        }
                        else
                            Toast.makeText(getActivity(),getResources().getString(R.string.aleadytoken),Toast.LENGTH_LONG).show();
                    }

                    else
                        {
                            Toast.makeText(getActivity(), getString(R.string.something_wrong), Toast.LENGTH_SHORT).show();

                        }
                }

                @Override
                public void onFailure(Call<RegisterModel> call, Throwable t) {
                    ProgressDialogHelper.removeSimpleProgressDialog();
                    Log.d("fail",call.toString());
                    Toast.makeText(getActivity(), getString(R.string.connection_error), Toast.LENGTH_SHORT).show();

                }
            });




        }
        else
        {

            Toast.makeText(getActivity(), R.string.complete, Toast.LENGTH_SHORT).show();
        }


        }


    @Override
    public void onResume() {
        super.onResume();

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
