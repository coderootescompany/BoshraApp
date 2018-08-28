package com.example.hossam.boshraapp.Helpers;


import com.example.hossam.boshraapp.Modules.AllDepartment;
import com.example.hossam.boshraapp.Modules.AllDoctors;
import com.example.hossam.boshraapp.Modules.AllExpsModel;
import com.example.hossam.boshraapp.Modules.AllPosts;
import com.example.hossam.boshraapp.Modules.CommentsModel;
import com.example.hossam.boshraapp.Modules.DoctorsInDeptModel;
import com.example.hossam.boshraapp.Modules.LoginModel;
import com.example.hossam.boshraapp.Modules.MyReservationModel;
import com.example.hossam.boshraapp.Modules.NewFarhaModel;
import com.example.hossam.boshraapp.Modules.ProfileModel;
import com.example.hossam.boshraapp.Modules.RegisterModel;
import com.example.hossam.boshraapp.Modules.SliderModel;
import com.example.hossam.boshraapp.Modules.StatuesModel;
import com.example.hossam.boshraapp.Modules.SubCatogryModel;
import com.example.hossam.boshraapp.Modules.VideosModel;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterface {



    @GET("Doctors/GetDoctor.json")
    @Headers("Accept: Application/json")
    Call<AllDoctors> getAllDocrorsData(
        );

    @GET("departments.json")
    @Headers("Accept: Application/json")
    Call<AllDepartment> getAllDepartmentsData(

    );

    @GET("SliderDoctors/GetSliderDoctor.json")
    @Headers("Accept: Application/json")
    Call<SliderModel> getAllSlidersData(

    );


    @GET("SliderSettings.json")
    @Headers("Accept: Application/json")
    Call<StatuesModel> getStatuesData(

    );

    @GET("MedicalPosts/GetMedicalPost.json")
    @Headers("Accept: Application/json")
    Call<AllPosts> getPostsData(
    );

    @GET("Galaries/newhappy.json")
    @Headers("Accept: Application/json")
    Call<NewFarhaModel> getNewhappyData(
    );


    @GET("Galaries/getAllCats.json")
    @Headers("Accept: Application/json")
    Call<SubCatogryModel> getBoshraImages(
    );

    @GET("Doctors/GetDoctorDepartment/{dept_id}.json")
    @Headers("Accept: Application/json")
    Call<DoctorsInDeptModel> getDoctorsinDept(
            @Path(value = "dept_id", encoded = true) int dept_id
    );


    @Multipart
    @POST("myexperience/addexprience.json")
    Call<ResponseBody> ADDExperience(
            @Part List<MultipartBody.Part> files,
            @Part("post") RequestBody post,
            @Part("user_id") RequestBody machine_count

         );

    @GET("Videos/GetVideo.json")
    @Headers("Accept: Application/json")
    Call<VideosModel> getAllVideosData(
    );

    @Multipart
    @POST("Users/add.json")
    Call<RegisterModel> UserRegister(
            @Part("username") RequestBody uname,
            @Part("password") RequestBody password,
            @Part("mobile") RequestBody mobile,
            @Part("active") RequestBody active,
            @Part("email_verified") RequestBody email_verified,
            @Part("user_group_id") RequestBody user_group_id
    );

    @Multipart
    @POST("Users/token.json")
    Call<LoginModel> UserLogin(
            @Part("username") RequestBody uname,
            @Part("password") RequestBody password

    );

    @Multipart
    @POST("Reservations/add.json")
    Call<LoginModel> AddResevation(
            @Part("res_doctor") RequestBody res_doctor,
            @Part("user_id") RequestBody user_id,
            @Part("name") RequestBody name,
            @Part("mobile") RequestBody mobile,
            @Part("date_res") RequestBody date_res
    );


    @GET("Myexperience/Getmyexperience/{userid}.json")
    @Headers("Accept: Application/json")
    Call<AllExpsModel> getExpsData(
            @Path(value = "userid", encoded = true) int userid
    );

    @GET("Myexperience/GetComment/{postid}.json")
    @Headers("Accept: Application/json")
    Call<CommentsModel> getCommentsDataForPost(

            @Path(value = "postid", encoded = true) int postid
    );


    @GET("Reservations/GetMyReservation/{userId}.json")
    @Headers("Accept: Application/json")
    Call<MyReservationModel> getMyReservation(

            @Path(value = "userId", encoded = true) int userId
    );


    @GET("Users/view/{userId}.json")
    @Headers("Accept: Application/json")
    Call<ProfileModel> getUserProfile(
            @Path(value = "userId", encoded = true) int userId
    );



    @Multipart
    @POST("Users/editandroid/{userid}.json")
    Call<ResponseBody> EditProfile(
            @Path(value = "userid", encoded = true) String user_id,
            @Part("username") RequestBody username,
            @Part("mobile") RequestBody mobile,
            @Part("password") RequestBody password,
            @Part MultipartBody.Part file);



    @Multipart
    @POST("Experiencecomments/add.json")
    Call<ResponseBody> ADDComment(
            @Part("comment") RequestBody comment,
            @Part("user_id") RequestBody user_id,
            @Part("myexperience_id") RequestBody myexperience_id

    );

    @Multipart
    @POST("Likes/add.json")
    Call<ResponseBody> MakeLike(
            @Part("myexperience_id") RequestBody myexperience_id,
            @Part("user_id") RequestBody user_id,
            @Part(" status") RequestBody  status
    );

    @POST("likes/deletelike/{expId}/{userId}.json")
    Call<ResponseBody> deleteLike(
            @Path(value = "userId", encoded = true) String userId,
            @Path(value = "expId", encoded = true) String expId
    );

    @Multipart
    @POST("Myexperience/edit/{postId}.json")
    Call<ResponseBody> changeStatus(
            @Path(value = "postId", encoded = true) String postId,
            @Part("accept") RequestBody accept
    );


}

