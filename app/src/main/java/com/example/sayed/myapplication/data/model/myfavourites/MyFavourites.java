
package com.example.sayed.myapplication.data.model.myfavourites;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyFavourites {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private MyFavouritesData data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public MyFavouritesData getData() {
        return data;
    }

    public void setData(MyFavouritesData data) {
        this.data = data;
    }

}
