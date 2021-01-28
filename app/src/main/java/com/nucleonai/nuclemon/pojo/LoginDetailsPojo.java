package com.nucleonai.nuclemon.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginDetailsPojo {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("loc")
    @Expose
    private Object loc;
    @SerializedName("user_profile")
    @Expose
    private UserProfile userProfile;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Object getLoc() {
        return loc;
    }

    public void setLoc(Object loc) {
        this.loc = loc;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
    public class UserProfile {

        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("user_name")
        @Expose
        private String userName;
        @SerializedName("login_id")
        @Expose
        private String loginId;
        @SerializedName("login_pass")
        @Expose
        private String loginPass;
        @SerializedName("token")
        @Expose
        private String token;
        @SerializedName("Role_id")
        @Expose
        private String roleId;
        @SerializedName("User_email")
        @Expose
        private String userEmail;
        @SerializedName("Notify_me")
        @Expose
        private Integer notifyMe;
        @SerializedName("Is_active")
        @Expose
        private String isActive;
        @SerializedName("Is_approver")
        @Expose
        private String isApprover;
        @SerializedName("last_login")
        @Expose
        private String lastLogin;
        @SerializedName("last_ip")
        @Expose
        private String lastIp;
        @SerializedName("lat_long")
        @Expose
        private String latLong;
        @SerializedName("added_date")
        @Expose
        private String addedDate;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getLoginId() {
            return loginId;
        }

        public void setLoginId(String loginId) {
            this.loginId = loginId;
        }

        public String getLoginPass() {
            return loginPass;
        }

        public void setLoginPass(String loginPass) {
            this.loginPass = loginPass;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getRoleId() {
            return roleId;
        }

        public void setRoleId(String roleId) {
            this.roleId = roleId;
        }

        public String getUserEmail() {
            return userEmail;
        }

        public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
        }

        public Integer getNotifyMe() {
            return notifyMe;
        }

        public void setNotifyMe(Integer notifyMe) {
            this.notifyMe = notifyMe;
        }

        public String getIsActive() {
            return isActive;
        }

        public void setIsActive(String isActive) {
            this.isActive = isActive;
        }

        public String getIsApprover() {
            return isApprover;
        }

        public void setIsApprover(String isApprover) {
            this.isApprover = isApprover;
        }

        public String getLastLogin() {
            return lastLogin;
        }

        public void setLastLogin(String lastLogin) {
            this.lastLogin = lastLogin;
        }

        public String getLastIp() {
            return lastIp;
        }

        public void setLastIp(String lastIp) {
            this.lastIp = lastIp;
        }

        public String getLatLong() {
            return latLong;
        }

        public void setLatLong(String latLong) {
            this.latLong = latLong;
        }

        public String getAddedDate() {
            return addedDate;
        }

        public void setAddedDate(String addedDate) {
            this.addedDate = addedDate;
        }

    }
}
