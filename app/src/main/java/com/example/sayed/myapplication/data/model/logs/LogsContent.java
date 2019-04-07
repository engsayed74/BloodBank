
package com.example.sayed.myapplication.data.model.logs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogsContent {

    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("post_id")
    @Expose
    private String postId;
    @SerializedName("api_token")
    @Expose
    private String apiToken;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("page")
    @Expose
    private String page;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("birth_date")
    @Expose
    private String birthDate;
    @SerializedName("city_id")
    @Expose
    private String cityId;
    @SerializedName("donation_last_date")
    @Expose
    private String donationLastDate;
    @SerializedName("password_confirmation")
    @Expose
    private String passwordConfirmation;
    @SerializedName("blood_type")
    @Expose
    private String bloodType;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("governorate_id")
    @Expose
    private String governorateId;
    @SerializedName("donation_id")
    @Expose
    private String donationId;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getDonationLastDate() {
        return donationLastDate;
    }

    public void setDonationLastDate(String donationLastDate) {
        this.donationLastDate = donationLastDate;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGovernorateId() {
        return governorateId;
    }

    public void setGovernorateId(String governorateId) {
        this.governorateId = governorateId;
    }

    public String getDonationId() {
        return donationId;
    }

    public void setDonationId(String donationId) {
        this.donationId = donationId;
    }

}
