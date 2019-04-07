package com.example.sayed.myapplication.data.rest;

import android.renderscript.ScriptIntrinsicYuvToRGB;

import com.example.sayed.myapplication.data.model.cities.Cities;
import com.example.sayed.myapplication.data.model.contact.Contact;
import com.example.sayed.myapplication.data.model.donationdetails.DonationDetails;
import com.example.sayed.myapplication.data.model.donationrequestcreate.DonationRequestCreate;
import com.example.sayed.myapplication.data.model.donationrequests.DonationRequests;
import com.example.sayed.myapplication.data.model.governorates.Governorates;
import com.example.sayed.myapplication.data.model.login.Login;
import com.example.sayed.myapplication.data.model.logs.Logs;
import com.example.sayed.myapplication.data.model.myfavourites.MyFavourites;
import com.example.sayed.myapplication.data.model.newpassword.NewPassword;
import com.example.sayed.myapplication.data.model.notificationscount.NotificationsCount;
import com.example.sayed.myapplication.data.model.notificationslist.NotificationsList;
import com.example.sayed.myapplication.data.model.notificationssettings.NotificationSettings;
import com.example.sayed.myapplication.data.model.postdetails.PostDetails;
import com.example.sayed.myapplication.data.model.posts.Posts;
import com.example.sayed.myapplication.data.model.posttogglefavourite.PostToggleFavourite;
import com.example.sayed.myapplication.data.model.profile.Profile;
import com.example.sayed.myapplication.data.model.register.Register;
import com.example.sayed.myapplication.data.model.registertoken.RegisterToken;
import com.example.sayed.myapplication.data.model.report.Report;
import com.example.sayed.myapplication.data.model.resetpassword.ResetPassword;
import com.example.sayed.myapplication.data.model.settings.Settings;
import com.example.sayed.myapplication.data.model.removetoken.RemoveToken;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by sayed on 19/01/2019.
 */

public interface ApiServices {

    @POST("register")
    @FormUrlEncoded
    Call<Register> getRegister(@Field("name") String name
                      , @Field("email") String email
                     , @Field("birth_date") String birth_date
                     , @Field("city_id") int city_id
                     , @Field("phone") String phone
                      , @Field("donation_last_date") String donation_last_date
                      , @Field("password") String password
                     , @Field("password_confirmation") String password_confirmation
                    , @Field("blood_type") String blood_type);



    @POST("login")
    @FormUrlEncoded
    Call<Login> getLogin(@Field("phone") String phone
                          , @Field("password") String password);


    @POST("reset-password")
    @FormUrlEncoded
    Call<ResetPassword> getResetPassword(@Field("phone") String phone);

    @POST("new-password")
    @FormUrlEncoded
    Call<NewPassword> getNewPassword(@Field("password") String password
                                      ,@Field("password_confirmation")String password_confirmation
                                      ,@Field("pin_code") String pin_code);



    @POST("profile")
    @FormUrlEncoded
    Call<Profile> getProfile(@Field("name") String name
                             , @Field("email") String email
                             , @Field("birth_date") String birth_date
                              , @Field("city_id")Integer city_id
                              , @Field("phone")String phone
                              , @Field("donation_last_date")String donation_last_date
                               , @Field("password")String password
                               , @Field("password_confirmation")String password_confirmation
                               , @Field("blood_type")String blood_type
                                , @Field("api_token")String api_token);


    @POST("register-token")
    @FormUrlEncoded
    Call<RegisterToken> getRegisterToken(@Field("token")String token
                                          , @Field("api_token")ScriptIntrinsicYuvToRGB api_token
                                         , @Field("platform")String platform);


    @POST("remove-token")
    @FormUrlEncoded
    Call<RemoveToken> getRemoveToken(@Field("token")String token
                                     , @Field("api_token")String api_token);

    @POST("notifications-settings")
    @FormUrlEncoded
    Call<NotificationSettings> getNotificationSettings(@Field("api_token")String api_token
                                                       , @Field("cities[0]")String[] cities
                                                        , @Field("blood_types[0]")String[] blood_types);


    @GET("notifications")
    Call<NotificationsList> getNotificationsList(@Query("api_token")String api_token);

    @GET("notifications-count")
    Call<NotificationsCount> getNotificationsCount(@Query("api_token")String api_token);


    @POST("donation-request/create")
    @FormUrlEncoded
    Call<DonationRequestCreate> getDonationRequestCreate(@Field("api_token")String api_token
                                                         , @Field("patient_name") String patient_name
                                                         , @Field("patient_age") String patient_age
                                                          , @Field("blood_type")String blood_type
                                                           , @Field("bags_num") String bags_num
                                                            , @Field("hospital_name") String hospital_name
                                                           , @Field("hospital_address")String hospital_address
                                                           , @Field("city_id") int city_id
                                                           , @Field("phone")String phone
                                                           , @Field("notes") String notes
                                                            , @Field("latitude")double latitude
                                                            , @Field("longitude") double longitude);


    @GET("donation-requests")
    Call<DonationRequests> getDonationRequests(@Query("api_token")String api_token);

    @GET("donation-request")
    Call<DonationDetails> getDonationDetails(@Query("api_token")String api_token
                                             , @Query("donation_id")int donation_id);



    @GET("posts")
    Call<Posts> getPosts(@Query("api_token")String api_token
                         , @Query("page")int page);


    @GET("post")
    Call<PostDetails> getPostDetails(@Query("api_token") String api_token
                                     , @Query("post_id") int post_id);

    @GET("my-favourites")
    Call<MyFavourites> getMyFavourites(@Query("api_token") String api_token);

    @POST("post-toggle-favourite")
    @FormUrlEncoded
    Call<PostToggleFavourite> getPostToggleFavourite(@Field("post_id")int post_id
                                                     , @Field("api_token") String api_token);



    @GET("governorates")
    Call<Governorates> getGovernorates();


    @GET("cities")
    Call<Cities> getCities(@Query("governorate_id") Long governorate_id);

    @GET("settings")
    Call<Settings> getSettings(@Query("api_token") String api_token);


    @POST("contact")
    @FormUrlEncoded
    Call<Contact> getContact(@Field("api_token")String api_token
                             ,@Field("title")String title
                             ,@Field("message")String message);



    @POST("report")
    @FormUrlEncoded
    Call<Report> getReport(@Field("api_token")String api_token
                           , @Field("message")String message);



    @GET("logs")
    Call<Logs> getLogs();



}
