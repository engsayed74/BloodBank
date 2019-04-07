
package com.example.sayed.myapplication.data.model.notificationscount;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationsCountData {

    @SerializedName("notifications_count")
    @Expose
    private Integer notificationsCount;

    public Integer getNotificationsCount() {
        return notificationsCount;
    }

    public void setNotificationsCount(Integer notificationsCount) {
        this.notificationsCount = notificationsCount;
    }

}
