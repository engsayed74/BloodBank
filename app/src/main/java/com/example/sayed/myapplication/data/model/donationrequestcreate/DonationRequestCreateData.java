
package com.example.sayed.myapplication.data.model.donationrequestcreate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DonationRequestCreateData {

    @SerializedName("donationRequest")
    @Expose
    private DonationRequestCreateDonationRequest donationRequest;

    public DonationRequestCreateDonationRequest getDonationRequest() {
        return donationRequest;
    }

    public void setDonationRequest(DonationRequestCreateDonationRequest donationRequest) {
        this.donationRequest = donationRequest;
    }

}
